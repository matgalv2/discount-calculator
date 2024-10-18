package io.github.g4lowy.strategies;

import io.github.g4lowy.domain.Item;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

final public class ProportionalRestForLastDiscount implements DiscountAlgorithm {
    @Override
    public Map<String, Integer> calculate(List<Item> items, int discount) {
        double sum = items.stream().map(Item::value).reduce(0, Integer::sum);
        int restDiscount = discount;
        Map<String, Integer> discounts = new HashMap<>();

        for(Iterator<Item> iterator = items.iterator(); iterator.hasNext(); ) {
            Item item = iterator.next();
            int discountForCurrentProduct = (int) (item.value() / sum * discount);
            if(iterator.hasNext())
                restDiscount -= discountForCurrentProduct;
            else
                discountForCurrentProduct = restDiscount;

            discounts.put(item.key(), discountForCurrentProduct);
        }

        return discounts;
    }

}

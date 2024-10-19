package io.github.g4lowy.strategies;

import io.github.g4lowy.domain.Discount;
import io.github.g4lowy.domain.Item;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

final public class ProportionalRestForLastDiscount implements DiscountAlgorithm {
    @Override
    public Map<String, Integer> calculate(List<Item> items, Discount discount) {
        double sum = items.stream().map(Item::getValue).reduce(0, Integer::sum);
        int restDiscount = discount.getValue();
        Map<String, Integer> discounts = new HashMap<>();

        for(Iterator<Item> iterator = items.iterator(); iterator.hasNext(); ) {
            Item item = iterator.next();
            int discountForCurrentProduct = (int) (item.getValue() / sum * discount.getValue());
            if(iterator.hasNext())
                restDiscount -= discountForCurrentProduct;
            else
                discountForCurrentProduct = restDiscount;

            discounts.put(item.getKey(), discountForCurrentProduct);
        }

        return discounts;
    }

}

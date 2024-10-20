package io.github.g4lowy.strategy;

import io.github.g4lowy.domain.Discount;
import io.github.g4lowy.domain.Item;

import java.util.*;

final public class ProportionalRestForLastDiscount implements DiscountAlgorithm {
    @Override
    public Map<String, Integer> calculate(List<Item> items, Discount discount) {

        double totalCost = items.stream().map(Item::getValue).reduce(0, Integer::sum);
        int restDiscount = discount.getValue();

        Map<String, Integer> discounts = new HashMap<>();

        // discount higher than total cost
        if (discount.getValue() >= totalCost ) {
            if(discount.getValue() > totalCost )
                System.out.println("Unused discount: " + (discount.getValue() - totalCost));
            items.forEach(item -> discounts.put(item.getKey(), item.getValue()));
            return discounts;
        }

        for(Iterator<Item> iterator = items.iterator(); iterator.hasNext(); ) {
            Item item = iterator.next();
            int discountForCurrentProduct = (int) (item.getValue() / totalCost * discount.getValue());

            if(iterator.hasNext()){
                restDiscount -= discountForCurrentProduct;
            }
            else{
                // if restDiscount is higher than price of last item it should be allocated to the previous ones
                if(restDiscount > item.getValue()){
                    discounts.put(item.getKey(), 0);
                    splitRest(items, discounts, restDiscount);
                    return discounts;
                }
                else
                    discountForCurrentProduct = restDiscount;
            }
            discounts.put(item.getKey(), discountForCurrentProduct);
        }
        return discounts;
    }

    private void splitRest(List<Item> items, Map<String, Integer> discounts, int restDiscount) {
        boolean finished = false;
        for(Iterator<Item> it = items.reversed().iterator(); it.hasNext() && !finished; ) {
            Item item = it.next();
            int currentItemDiscount = discounts.get(item.getKey());

            if(currentItemDiscount < item.getValue()) {
                int additionalDiscount = calculateAdditionalDiscountForProduct(currentItemDiscount, item.getValue(), restDiscount);

                discounts.put(item.getKey(), currentItemDiscount + additionalDiscount);
                restDiscount -= additionalDiscount;
            }
            if (restDiscount == 0)
                finished = true;
        }

    }

    private int calculateAdditionalDiscountForProduct(int currentDiscount, int itemPrice, int restDiscount){
        int currentDiff =  itemPrice - currentDiscount;
        return Math.min(currentDiff, restDiscount);
    }

}

package io.github.g4lowy.strategy;

import io.github.g4lowy.domain.Discount;
import io.github.g4lowy.domain.Product;

import java.util.*;

final public class ProportionalRestForLastDiscount implements DiscountAlgorithm {
    @Override
    public Map<String, Integer> calculate(List<Product> products, Discount discount) {

        double totalCost = products.stream().map(Product::getValue).reduce(0, Integer::sum);
        int restDiscount = discount.getValue();

        Map<String, Integer> discounts = new HashMap<>();

        // discount higher than total cost
        if (discount.getValue() >= totalCost ) {
            if(discount.getValue() > totalCost )
                System.out.println("Unused discount: " + (discount.getValue() - totalCost));
            products.forEach(item -> discounts.put(item.getKey(), item.getValue()));
            return discounts;
        }

        for(Iterator<Product> iterator = products.iterator(); iterator.hasNext(); ) {
            Product product = iterator.next();
            int discountForCurrentProduct = (int) (product.getValue() / totalCost * discount.getValue());

            if(iterator.hasNext()){
                restDiscount -= discountForCurrentProduct;
            }
            else{
                // if restDiscount is higher than price of last item it should be allocated to the previous ones
                if(restDiscount > product.getValue()){
                    discounts.put(product.getKey(), 0);
                    splitRest(products, discounts, restDiscount);
                    return discounts;
                }
                else
                    discountForCurrentProduct = restDiscount;
            }
            discounts.put(product.getKey(), discountForCurrentProduct);
        }
        return discounts;
    }

    private void splitRest(List<Product> products, Map<String, Integer> discounts, int restDiscount) {
        boolean finished = false;
        for(Iterator<Product> it = products.reversed().iterator(); it.hasNext() && !finished; ) {
            Product product = it.next();
            int currentItemDiscount = discounts.get(product.getKey());

            if(currentItemDiscount < product.getValue()) {
                int additionalDiscount = calculateAdditionalDiscountForProduct(currentItemDiscount, product.getValue(), restDiscount);

                discounts.put(product.getKey(), currentItemDiscount + additionalDiscount);
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

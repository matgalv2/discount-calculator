package io.github.g4lowy.view;

import java.util.Map;
import java.util.Random;

public class DiscountCalculationViewConsole implements DiscountCalculationView {

    private static int [] DISCOUNTS = new int []{100, 125, 150, 175};
    private static Map<String, Integer> ITEMS_PRICE = Map.of(
            "Product1", 130,
            "Product2", 150,
            "Product3", 175,
            "Product4", 250);

    @Override
    public int getDiscount() {
        int discount = DISCOUNTS[new Random().nextInt(DISCOUNTS.length)];
        System.out.println(discount);
        return discount;
    }

    @Override
    public Map<String, Integer> getProducts() {
        System.out.println(ITEMS_PRICE);
        return ITEMS_PRICE;
    }

    @Override
    public void reset() {
        System.out.println("");
    }

    @Override
    public void showCalculatedDiscounts(Map<String, Integer> discounts) {
        System.out.println("Discounts:");
        discounts.forEach((k, v) -> System.out.println(k + ": " + v));
    }
}

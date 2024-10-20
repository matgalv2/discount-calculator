package io.github.g4lowy.view;

import io.github.g4lowy.dto.Data;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

public class DiscountCalculationViewConsole implements DiscountCalculationView {

    private static final int [] DISCOUNTS = new int []{100, 125, 150, 175};
    private static final LinkedHashMap<String, Integer> ITEMS_PRICE;

    static {
        ITEMS_PRICE = new LinkedHashMap<>();
        ITEMS_PRICE.put("Product1", 130);
        ITEMS_PRICE.put("Product2", 150);
        ITEMS_PRICE.put("Product3", 175);
        ITEMS_PRICE.put("Product4", 250);
    }

    @Override
    public Data getData() {
        int discount = DISCOUNTS[new Random().nextInt(DISCOUNTS.length)];
        return new Data(discount, ITEMS_PRICE);
    }

    @Override
    public void showResult(Map<String, Integer> discounts) {
        System.out.println("Discounts:");
        discounts.forEach((k, v) -> System.out.println(k + ": " + v));
    }
}

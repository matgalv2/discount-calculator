package io.github.g4lowy.view;

import java.util.Map;

public interface DiscountCalculationView {
    int getDiscount();
    Map<String, Integer> getProducts();

    void reset();
    void showCalculatedDiscounts(Map<String, Integer> discounts);
}

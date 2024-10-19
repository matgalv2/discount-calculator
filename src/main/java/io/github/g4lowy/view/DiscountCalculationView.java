package io.github.g4lowy.view;

import io.github.g4lowy.dto.Data;

import java.util.Map;

public interface DiscountCalculationView {

    Data getData();

    void showResult(Map<String, Integer> discounts);
}

package io.github.g4lowy.view;

import io.github.g4lowy.dto.DiscountRequestData;

import java.util.Map;

public interface DiscountCalculationView {

    DiscountRequestData getData();

    void showResult(Map<String, Integer> discounts);
}

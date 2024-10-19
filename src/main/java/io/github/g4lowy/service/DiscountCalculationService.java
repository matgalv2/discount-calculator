package io.github.g4lowy.service;

import io.github.g4lowy.domain.Discount;
import io.github.g4lowy.domain.Item;

import java.util.List;
import java.util.Map;

public interface DiscountCalculationService {
    Map<String, Integer> getDiscounts(List<Item> items, Discount discount);
}

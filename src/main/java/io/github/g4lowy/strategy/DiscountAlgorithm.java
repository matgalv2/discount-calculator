package io.github.g4lowy.strategy;

import io.github.g4lowy.domain.Discount;
import io.github.g4lowy.domain.Item;

import java.util.List;
import java.util.Map;

public interface DiscountAlgorithm {
    Map<String, Integer> calculate(List<Item> items, Discount discount);
}

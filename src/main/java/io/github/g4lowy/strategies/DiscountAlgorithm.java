package io.github.g4lowy.strategies;

import io.github.g4lowy.domain.Item;

import java.util.List;
import java.util.Map;

public interface DiscountAlgorithm {
    Map<String, Integer> calculate(List<Item> items, int discount);
}

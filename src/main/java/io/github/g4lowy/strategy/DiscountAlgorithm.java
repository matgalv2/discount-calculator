package io.github.g4lowy.strategy;

import io.github.g4lowy.domain.Discount;
import io.github.g4lowy.domain.Product;

import java.util.List;
import java.util.Map;

public interface DiscountAlgorithm {
    Map<String, Integer> calculate(List<Product> products, Discount discount);
}

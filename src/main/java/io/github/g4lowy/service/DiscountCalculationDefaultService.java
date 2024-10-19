package io.github.g4lowy.service;

import io.github.g4lowy.domain.Discount;
import io.github.g4lowy.strategies.DiscountAlgorithm;
import io.github.g4lowy.strategies.DiscountStrategy;
import io.github.g4lowy.domain.Item;
import io.github.g4lowy.strategies.ProportionalRestForLastDiscount;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DiscountCalculationDefaultService implements DiscountCalculationService {

    private final Map<DiscountStrategy, DiscountAlgorithm> algorithms;
    private static final DiscountStrategy DEFAULT_STRATEGY = DiscountStrategy.PROPORTIONAL;


    public DiscountCalculationDefaultService() {
        algorithms = new HashMap<>();
        algorithms.put(DiscountStrategy.PROPORTIONAL, new ProportionalRestForLastDiscount());
    }

    @Override
    public Map<String, Integer> getDiscounts(List<Item> items, Discount discount){
        return algorithms.get(DEFAULT_STRATEGY).calculate(items, discount);
    }
    @Override
    public Map<String, Integer> getDiscounts(List<Item> items, Discount discount, DiscountStrategy discountStrategy){
        return algorithms.get(discountStrategy).calculate(items, discount);
    }
}

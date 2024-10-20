package io.github.g4lowy.service;

import io.github.g4lowy.dto.Data;
import io.github.g4lowy.error.DomainValidationException;
import io.github.g4lowy.strategy.DiscountStrategy;

import java.util.Map;

public interface DiscountCalculationService {
    Map<String, Integer> getDiscounts(Data data) throws DomainValidationException;
    Map<String, Integer> getDiscounts(Data data, DiscountStrategy discountStrategy) throws DomainValidationException;
}

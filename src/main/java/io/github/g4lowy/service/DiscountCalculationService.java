package io.github.g4lowy.service;

import io.github.g4lowy.dto.DiscountRequestData;
import io.github.g4lowy.error.DomainValidationException;
import io.github.g4lowy.strategy.DiscountStrategy;

import java.util.Map;

public interface DiscountCalculationService {
    Map<String, Integer> getDiscounts(DiscountRequestData discountRequestData) throws DomainValidationException;
    Map<String, Integer> getDiscounts(DiscountRequestData discountRequestData, DiscountStrategy discountStrategy) throws DomainValidationException;
}

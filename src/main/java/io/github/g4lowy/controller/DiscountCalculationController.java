package io.github.g4lowy.controller;

import io.github.g4lowy.error.DomainValidationException;
import io.github.g4lowy.dto.DiscountRequestData;
import io.github.g4lowy.service.DiscountCalculationService;
import lombok.RequiredArgsConstructor;

import java.util.Map;

@RequiredArgsConstructor
public class DiscountCalculationController {

    private final DiscountCalculationService discountCalculationService;

    public Map<String, Integer> calculateDiscounts(DiscountRequestData discountRequestData) throws DomainValidationException {
        return discountCalculationService.getDiscounts(discountRequestData);
    }

}

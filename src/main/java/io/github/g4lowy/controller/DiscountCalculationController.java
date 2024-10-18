package io.github.g4lowy.controller;

import io.github.g4lowy.domain.DomainValidationException;
import io.github.g4lowy.service.DiscountCalculationService;
import io.github.g4lowy.view.DiscountCalculationView;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import io.github.g4lowy.domain.Item;

@RequiredArgsConstructor
public class DiscountCalculationController {

    private final DiscountCalculationService discountCalculationService;
    private final DiscountCalculationView view;


    private static Optional<Item> makeItem(String key, int value){
        if(value > 0 && !key.isBlank() && !key.isEmpty())
            return Optional.of(new Item(key, value));
        else
            return Optional.empty();
    }

    public void run() throws DomainValidationException {

        int discount = view.getDiscount();
        // check if discount is positive
        if(discount <= 0)
            throw new DomainValidationException("Negative discount");

        // check items
        Map<String, Integer> viewItems = view.getProducts();


        List<Item> items =
                viewItems.entrySet()
                        .stream()
                        .map(entry -> makeItem(entry.getKey(), entry.getValue()))
                        .filter(Optional::isPresent)
                        .map(Optional::get)
                        .toList();

        if(viewItems.size() != items.size())
            throw new DomainValidationException("Provided items violates domain constraints");

        Map<String, Integer> discounts = discountCalculationService.getDiscounts(items, discount);

        view.showCalculatedDiscounts(discounts);

    }

}

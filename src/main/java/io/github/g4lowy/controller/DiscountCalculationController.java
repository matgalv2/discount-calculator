package io.github.g4lowy.controller;

import io.github.g4lowy.domain.Discount;
import io.github.g4lowy.domain.DomainValidationException;
import io.github.g4lowy.dto.Data;
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



    public void run() throws DomainValidationException {

        Data data = view.getData();

        if(data == null || data.items() == null)
            throw new DomainValidationException("Data.items is null");


        Optional<Discount> discountOps = Discount.of(data.discount());

        List<Optional<Item>> itemsOps =
                data.items()
                        .entrySet()
                        .stream()
                        .map(entry -> Item.of(entry.getKey(), entry.getValue()))
                        .toList();

        if(discountOps.isEmpty() || itemsOps.stream().filter(Optional::isPresent).toList().size() != data.items().size())
            throw new DomainValidationException("Provided data violates domain constraints");
        else{
            Discount discount = discountOps.get();
            List<Item> items = itemsOps.stream().filter(Optional::isPresent).map(Optional::get).toList();

            Map<String, Integer> discounts = discountCalculationService.getDiscounts(items, discount);

            view.showResult(discounts);
        }
    }
}

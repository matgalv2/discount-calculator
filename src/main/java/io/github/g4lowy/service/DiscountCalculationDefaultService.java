package io.github.g4lowy.service;

import io.github.g4lowy.domain.Discount;
import io.github.g4lowy.domain.Product;
import io.github.g4lowy.dto.DiscountRequestData;
import io.github.g4lowy.error.DomainValidationException;
import io.github.g4lowy.strategy.DiscountAlgorithm;
import io.github.g4lowy.strategy.DiscountStrategy;
import io.github.g4lowy.strategy.ProportionalRestForLastDiscount;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class DiscountCalculationDefaultService implements DiscountCalculationService {

    private static final Map<DiscountStrategy, DiscountAlgorithm> algorithms;
    private static final DiscountStrategy DEFAULT_STRATEGY = DiscountStrategy.PROPORTIONAL;

    static {
        algorithms = new HashMap<>();
        algorithms.put(DiscountStrategy.PROPORTIONAL, new ProportionalRestForLastDiscount());
    }


    @Override
    public Map<String, Integer> getDiscounts(DiscountRequestData discountRequestData, DiscountStrategy discountStrategy) throws DomainValidationException {

        if(discountRequestData == null || discountRequestData.items() == null)
            throw new DomainValidationException("Data.items is null");

        Optional<Discount> discountOpt = Discount.of(discountRequestData.discount());

        List<Optional<Product>> itemsOpt =
                discountRequestData.items()
                        .entrySet()
                        .stream()
                        .map(entry -> Product.of(entry.getKey(), entry.getValue()))
                        .toList();

        if(discountOpt.isEmpty() || itemsOpt.stream().filter(Optional::isPresent).toList().size() != discountRequestData.items().size())
            throw new DomainValidationException("Provided data violates domain constraints");
        else{
            Discount discount = discountOpt.get();
            List<Product> products = itemsOpt.stream().filter(Optional::isPresent).map(Optional::get).toList();

            return algorithms.get(discountStrategy).calculate(products, discount);
        }
    }

    @Override
    public Map<String, Integer> getDiscounts(DiscountRequestData discountRequestData) throws DomainValidationException {
        return getDiscounts(discountRequestData, DEFAULT_STRATEGY);
    }
}

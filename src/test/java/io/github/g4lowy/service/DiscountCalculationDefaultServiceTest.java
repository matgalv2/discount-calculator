package io.github.g4lowy.service;

import io.github.g4lowy.dto.DiscountRequestData;
import io.github.g4lowy.error.DomainValidationException;
import org.junit.Test;

import static org.junit.Assert.*;

import java.util.LinkedHashMap;
import java.util.Map;

public class DiscountCalculationDefaultServiceTest {

    private final DiscountCalculationDefaultService service = new DiscountCalculationDefaultService();

    @Test(expected = DomainValidationException.class)
    public void dataIsNull() throws DomainValidationException {
        service.getDiscounts(null);
    }

    @Test(expected = DomainValidationException.class)
    public void productsFieldIsNull() throws DomainValidationException {
        DiscountRequestData data = new DiscountRequestData(0, null);
        service.getDiscounts(data);
    }

    @Test(expected = DomainValidationException.class)
    public void discountIsNegative() throws DomainValidationException {
        DiscountRequestData data = new DiscountRequestData(-1, new LinkedHashMap<>());
        service.getDiscounts(data);
    }

    @Test(expected = DomainValidationException.class)
    public void invalidProductValue() throws DomainValidationException {
        LinkedHashMap<String, Integer> products = new LinkedHashMap<>();
        products.put("prod1", 0);

        DiscountRequestData data = new DiscountRequestData(1, products);
        service.getDiscounts(data);
    }

    @Test(expected = DomainValidationException.class)
    public void invalidProductKeyEmpty() throws DomainValidationException {
        LinkedHashMap<String, Integer> products = new LinkedHashMap<>();
        products.put("", 1);

        DiscountRequestData data = new DiscountRequestData(1, products);
        service.getDiscounts(data);
    }

    @Test(expected = DomainValidationException.class)
    public void invalidProductKeyBlank() throws DomainValidationException {
        LinkedHashMap<String, Integer> products = new LinkedHashMap<>();
        products.put("  ", 1);

        DiscountRequestData data = new DiscountRequestData(1, products);
        service.getDiscounts(data);
    }

    @Test(expected = DomainValidationException.class)
    public void invalidOnlyOneProduct() throws DomainValidationException {
        LinkedHashMap<String, Integer> products = new LinkedHashMap<>();
        products.put("prod1", 0);
        products.put("prod2", 1);

        DiscountRequestData data = new DiscountRequestData(1, products);
        service.getDiscounts(data);
    }

    @Test
    public void validData() throws DomainValidationException {
        LinkedHashMap<String, Integer> products = new LinkedHashMap<>();
        products.put("prod1", 500);
        products.put("prod2", 1500);

        DiscountRequestData data = new DiscountRequestData(100, products);
        Map<String, Integer> actual = service.getDiscounts(data);

        Map<String, Integer> expected = Map.of("prod1", 25, "prod2", 75);

        assertEquals(expected, actual);
    }


}

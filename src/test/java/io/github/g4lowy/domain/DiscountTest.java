package io.github.g4lowy.domain;

import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.assertTrue;

public class DiscountTest {

    @Test
    public void negativeDiscount() {
        Optional<Discount> discount = Discount.of(-1);
        assertTrue(discount.isEmpty());
    }

    @Test
    public void zeroDiscount() {
        Optional<Discount> discount = Discount.of(0);
        assertTrue(discount.isPresent());
    }

    @Test
    public void positiveDiscount() {
        Optional<Discount> discount = Discount.of(1);
        assertTrue(discount.isPresent());
    }
}

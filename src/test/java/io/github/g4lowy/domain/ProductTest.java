package io.github.g4lowy.domain;

import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.assertTrue;

public class ProductTest {

    @Test
    public void negativeDiscount() {
        Optional<Product> item = Product.of("p1", -1);
        assertTrue(item.isEmpty());
    }

    @Test
    public void zeroDiscount() {
        Optional<Product> item = Product.of("p1", 0);
        assertTrue(item.isEmpty());
    }

    @Test
    public void positiveDiscount() {
        Optional<Product> item = Product.of("p1", 1);
        assertTrue(item.isPresent());
    }

    @Test
    public void nullName() {
        Optional<Product> item = Product.of(null, 0);
        assertTrue(item.isEmpty());
    }

    @Test
    public void blankName() {
        Optional<Product> item = Product.of(" ", 0);
        assertTrue(item.isEmpty());
    }

    @Test
    public void emptyName() {
        Optional<Product> item = Product.of("", 0);
        assertTrue(item.isEmpty());
    }
}

package io.github.g4lowy.domain;

import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.assertTrue;

public class ItemTest {

    @Test
    public void negativeDiscount() {
        Optional<Item> item = Item.of("", -1);
        assertTrue(item.isEmpty());
    }

    @Test
    public void zeroDiscount() {
        Optional<Item> item = Item.of("", 0);
        assertTrue(item.isEmpty());
    }

    @Test
    public void positiveDiscount() {
        Optional<Item> item = Item.of("", 1);
        assertTrue(item.isPresent());
    }
}

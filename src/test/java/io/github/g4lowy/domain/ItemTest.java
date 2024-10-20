package io.github.g4lowy.domain;

import org.junit.Test;

import java.util.Optional;

import static org.junit.Assert.assertTrue;

public class ItemTest {

    @Test
    public void negativeDiscount() {
        Optional<Item> item = Item.of("p1", -1);
        assertTrue(item.isEmpty());
    }

    @Test
    public void zeroDiscount() {
        Optional<Item> item = Item.of("p1", 0);
        assertTrue(item.isEmpty());
    }

    @Test
    public void positiveDiscount() {
        Optional<Item> item = Item.of("p1", 1);
        assertTrue(item.isPresent());
    }

    @Test
    public void nullName() {
        Optional<Item> item = Item.of(null, 0);
        assertTrue(item.isEmpty());
    }

    @Test
    public void blankName() {
        Optional<Item> item = Item.of(" ", 0);
        assertTrue(item.isEmpty());
    }

    @Test
    public void emptyName() {
        Optional<Item> item = Item.of("", 0);
        assertTrue(item.isEmpty());
    }
}

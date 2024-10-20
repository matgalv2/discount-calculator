package io.github.g4lowy.strategy;

import io.github.g4lowy.domain.Discount;
import io.github.g4lowy.domain.Item;
import org.junit.Test;

import static org.junit.Assert.*;

import java.util.*;

public class ProportionalRestForLastDiscountTest {

    private final DiscountAlgorithm algorithm = new ProportionalRestForLastDiscount();


    @Test
    public void exerciseTest() {
        List<Item> list =
                Map.of("Product1", 500, "Product2", 1500)
                    .entrySet()
                    .stream()
                    .map(entry -> Item.of(entry.getKey(), entry.getValue()).get())
                    .toList();

        Discount discount = Discount.of(100).get();

        Map<String, Integer> actual = algorithm.calculate(list, discount);
        Map<String, Integer> expected = Map.of(
                "Product1", 25,
                "Product2", 75);

        assertEquals(expected, actual);
    }


    @Test
    public void proportionalSplit() {

        List<Item> list =
                Map.of(
                        "Product1", 2,
                        "Product2", 4,
                        "Product3", 6,
                        "Product4", 8)
                        .entrySet()
                        .stream()
                        .map(entry -> Item.of(entry.getKey(), entry.getValue()).get())
                        .toList();

        Discount discount = Discount.of(10).get();

        Map<String, Integer> actual = algorithm.calculate(list, discount);
        Map<String, Integer> expected = Map.of(
                "Product1", 1,
                "Product2", 2,
                "Product3", 3,
                "Product4", 4);

        assertEquals(expected, actual);
    }

    @Test
    public void proportionalSplitRestForLast() {

        Map<String, Integer> itemsPrices = new LinkedHashMap<>();
        itemsPrices.put("Product1", 1);
        itemsPrices.put("Product2", 1);
        itemsPrices.put("Product3", 1);
        itemsPrices.put("Product4", 5);

        List<Item> list =
                itemsPrices
                        .entrySet()
                        .stream()
                        .map(entry -> Item.of(entry.getKey(), entry.getValue()).get())
                        .toList();


        Discount discount = Discount.of(5).get();

        Map<String, Integer> actual = algorithm.calculate(list, discount);
        Map<String, Integer> expected = Map.of(
                "Product1", 0,
                "Product2", 0,
                "Product3", 0,
                "Product4", 5);


        assertEquals(expected, actual);
    }


    @Test
    public void proportionalSplitRestForLast_ifLastIsFullThanFulfillFromTheEnd() {

        Map<String, Integer> itemsPrices = new LinkedHashMap<>();
        itemsPrices.put("Product1", 1);
        itemsPrices.put("Product2", 5);
        itemsPrices.put("Product3", 1);
        itemsPrices.put("Product4", 1);

        List<Item> list =
                itemsPrices
                        .entrySet()
                        .stream()
                        .map(entry -> Item.of(entry.getKey(), entry.getValue()).get())
                        .toList();


        Discount discount = Discount.of(5).get();

        Map<String, Integer> actual = algorithm.calculate(list, discount);
        Map<String, Integer> expected = Map.of(
                "Product1", 0,
                "Product2", 3,
                "Product3", 1,
                "Product4", 1);


        assertEquals(expected, actual);
    }

    @Test
    public void discountEqualToTotalPrice() {

        List<Item> list =
                Map.of(
                                "Product1", 2,
                                "Product2", 4,
                                "Product3", 6,
                                "Product4", 8)
                        .entrySet()
                        .stream()
                        .map(entry -> Item.of(entry.getKey(), entry.getValue()).get())
                        .toList();

        Discount discount = Discount.of(20).get();

        Map<String, Integer> actual = algorithm.calculate(list, discount);
        Map<String, Integer> expected = Map.of(
                "Product1", 2,
                "Product2", 4,
                "Product3", 6,
                "Product4", 8);

        assertEquals(expected, actual);
    }

    @Test
    public void discountHigherThanTotalPrice() {

        List<Item> list =
                Map.of(
                                "Product1", 2,
                                "Product2", 4,
                                "Product3", 6,
                                "Product4", 8)
                        .entrySet()
                        .stream()
                        .map(entry -> Item.of(entry.getKey(), entry.getValue()).get())
                        .toList();

        Discount discount = Discount.of(21).get();

        Map<String, Integer> actual = algorithm.calculate(list, discount);
        Map<String, Integer> expected = Map.of(
                "Product1", 2,
                "Product2", 4,
                "Product3", 6,
                "Product4", 8);

        assertEquals(expected, actual);
    }
}

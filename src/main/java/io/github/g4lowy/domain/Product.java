package io.github.g4lowy.domain;


import lombok.Getter;

import java.util.Optional;

// lepsza nazwa
@Getter
public class Product {
    private final String key;
    private final int value;

    private Product(String key, int value) {
        this.key = key;
        this.value = value;
    }

    public static Optional<Product> of(String key, int value) {
        if(value > 0 && key != null && !key.isBlank() && !key.isEmpty())
            return Optional.of(new Product(key, value));
        else
            return Optional.empty();
    }
}

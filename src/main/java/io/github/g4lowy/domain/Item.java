package io.github.g4lowy.domain;


import lombok.Getter;

import java.util.Optional;

@Getter
public class Item {
    private final String key;
    private final int value;

    private Item(String key, int value) {
        this.key = key;
        this.value = value;
    }

    public static Optional<Item> of(String key, int value) {
        if(value > 0 && key != null && !key.isBlank() && !key.isEmpty())
            return Optional.of(new Item(key, value));
        else
            return Optional.empty();
    }
}

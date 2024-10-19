package io.github.g4lowy.domain;

import lombok.Getter;

import java.util.Optional;

@Getter
public class Discount {

    private final int value;

    private Discount(int value) {
        this.value = value;
    }

    public static Optional<Discount> of(int value) {
        if(value >= 0)
            return Optional.of(new Discount(value));
        else
            return Optional.empty();
    }
}

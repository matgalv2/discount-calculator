package io.github.g4lowy.dto;

import java.util.LinkedHashMap;

public record DiscountRequestData(int discount, LinkedHashMap<String, Integer> items){}

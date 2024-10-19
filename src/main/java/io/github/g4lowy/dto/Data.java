package io.github.g4lowy.dto;

import java.util.Map;

public record Data(int discount, Map<String, Integer> items){}

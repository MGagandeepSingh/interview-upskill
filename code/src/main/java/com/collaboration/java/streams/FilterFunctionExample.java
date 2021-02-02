package com.collaboration.java.streams;

import java.util.Arrays;
import java.util.List;

public class FilterFunctionExample {

    public static void main(String[] args) {
        multipleOfThree();
    }

    // 1. filter(Predicate<T>): Stream<T>
    private static void multipleOfThree() {

        List<Integer> numbers = Arrays.asList(3, 5, 7, 9, 1, 23, 45);
        numbers.stream()
                .filter(n -> n % 3 == 0)
                .forEach(System.out::println);
    }
}

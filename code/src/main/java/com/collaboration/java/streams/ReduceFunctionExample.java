package com.collaboration.java.streams;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class ReduceFunctionExample {
    public static void main(String[] args) {
        simpleReduce();
        simpleReduceWithInitialValue();
        parallelReduce();
    }

    private static void simpleReduce() {

        List<String> names = Arrays.asList("John", "Sean", "Ron", "Don");
        Optional<String> result = names.stream()
                .reduce((i, c) -> i + c);
        result.ifPresent(System.out::println);
    }

    private static void simpleReduceWithInitialValue() {

        List<String> names = Arrays.asList("John", "Sean", "Ron", "Don");
        String result = names.stream()
                .reduce("Names: ", (i, c) -> i + c);

        System.out.println(result);
    }

    private static void parallelReduce() {
        List<Integer> ages = Arrays.asList(25, 30, 45, 28, 32);
        int computedAges = ages.parallelStream().reduce(0, (a, b) -> a + b, Integer::sum);
        System.out.println(computedAges);
    }
}

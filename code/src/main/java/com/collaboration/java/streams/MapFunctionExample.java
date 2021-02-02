package com.collaboration.java.streams;

import java.util.Arrays;
import java.util.List;

public class MapFunctionExample {

    public static void main(String[] args) {
//        simple();
        simpleWorking();
    }

    // It has four overloaded function
    // 1. map(Function<T,R>): Stream<R>
    // 2. mapToDouble(ToDoubleFunction<T,R>): DoubleStream<R>
    // 3. mapToInt(ToIntFunction<T,R>): IntStream<R>
    // 4. mapToLong(ToLongFunction<T,R>): LongStream<R>

    // 1. map(Function<T,R>): Stream<R>
    private static void simple() {
        List<String> names = Arrays.asList("Gagan", "Raman", "Hargun", "Taran");
        names.stream().map(String::toUpperCase).forEach(System.out::println);
    }

    // 2. How it works
    private static void simpleWorking() {
        List<String> names = Arrays.asList("Gagan", "Raman", "Hargun", "Taran");
        names.stream()
                .map(name -> {
                    System.out.println("Transforming: " + name + " to " + name.toUpperCase());
                    return "";
                })
                .forEach(System.out::println);
    }

}

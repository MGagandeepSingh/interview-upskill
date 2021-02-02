package com.collaboration.java.streams;

import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class BuiltInCollectorsExample {

    public static void main(String[] args) {
//        simpleToCollection();
        simpleJoining();
    }

    private static void simpleToCollection() {

        // Simply put I can convert the List type to a Set too.
        // In the example below I am filtering out the names that are greater than 3 and then resturning the
        // result as a TreeSet meaning they will be sorted.
        // This method does not have any overloaded functions.

        List<String> names = Arrays.asList("John", "Sean", "Ron", "Don", "Tom");
        TreeSet<String> result = names.stream()
                .filter(name -> name.length() < 4)
                .collect(Collectors.toCollection(TreeSet::new));
        System.out.println(result);

        // Similar to this are toList and toSet
        // toList(): Collector<T, ?, List<T>>
        // toUnmodifiableList(): Collector<T, ?, List<T>>
        // toSet(): Collector<T, ?, Set<T>>
        // toUnmodifiableSet(): Collector<T, ?, Set<T>>

    }

    private static void simpleJoining() {

        // It has 3 overloaded methods. The example is for the third one. Other one's are simple.
        // 1. joining()
        // 2. joining(CharSequence): For delimiter
        // 3. joining(CharSequence, CharSequence, CharSequence): For delimiter, prefix and suffix.

        String[] arr = {"John", "Sean", "Ron", "Don"};
        String result = Arrays.stream(arr)
                .collect(Collectors.joining(", ", "My favourite names are: ", "."));
        System.out.println(result);
    }
}

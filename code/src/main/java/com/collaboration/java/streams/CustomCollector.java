package com.collaboration.java.streams;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.IntStream;

public class CustomCollector {

    // We can use Collector.of method in Collector interface to create custom collector.
    // Collector requires only 5 parts
    // 1. Supplier: Supplier<A>: Must return a function that returns an empty accumulator. Usually a temporary place to
    //      hold the intermediate result. Mostly as simple as creating a List.
    // 2. Accumulator: BiConsumer<A, T>: Return a function which performs the reduction operation. This is about adding
    //      the values to temporary place (above accumulator).
    // 3. Finisher: Function<A, R>: Function to perform the transformation. This is like changing the return type. Say
    //      we have temporary place as List but we want our return type to be set, this is where you do it.
    // 4. Combiner: BinaryOperator<A>: If and when the stream is collected in parallel then the combiner is used,
    //      which knows how to merge the results
    // 5. Characteristic: Set<Characteristics> Define the behavior of the collector. Like CONCURRENT is the
    //      characteristic then the process can run in parallel

    // Generic type used and their meaning
    // T: Type of data stream
    // A: Accumulator type
    // R: Return type
    //
    // There are 3 types of Characteristics available:
    // CONCURRENT: Indicates that this collector is concurrent, meaning that the result container can support the
    //      accumulator function being called concurrently with the same result container from multiple threads.
    // IDENTITY_FINISH: Indicates that the finisher function is the identity function and can be elided
    // UNORDERED: Indicates that the collection operation does not commit to preserving the encounter order of input
    //      elements.


    public List<Integer> unique(int[] arr) {

        Collector<Integer, Set<Integer>, List<Integer>> collector =
                Collector.of(
                        HashSet::new,   // supplier: where to place temporarily.
                        Set::add,       // accumulator: how to add to the temporary place
                        (result1, result2) -> {
                            result1.addAll(result2);
                            return result1;
                        },              // combiner: how to collect in case of parallel processing
                        ArrayList::new, // finisher: converting to final return type
                        Collector.Characteristics.CONCURRENT,
                        Collector.Characteristics.UNORDERED
                        // characteristics: this is concurrent and unordered.
                );

        return IntStream.of(arr)
                .boxed()
                .collect(collector);
    }

    public static void main(String[] args) {
        CustomCollector customCollector = new CustomCollector();
        customCollector.unique(new int[]{1, 4, 2, 4, 1, 6, 2, 5, 2, 7, 8, 4}).forEach(System.out::println);
    }
}

package streams;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**

Streams are immutable like dataset in Spark, any transformation would not change the original stream rather it will come up with new one, here execution starts only when we encounter
any terminal operation upon Stream[similar concept like action in Spark]

The Java 8 Stream API provides a powerful, functional-style approach to processing sequences of elements from various sources like collections,
arrays, and I/O channels. Streams enable declarative data processing, allowing you to define what operations to perform rather
than how (which is the imperative style of traditional loops).

Key Concepts -->
a.) Not a Data Structure: A stream does not store data itself; it processes data from a source on demand.
b.) Immutability: Streams operations do not modify the original source data structure.
c.) Pipelining: Multiple stream operations can be chained together (pipelined) to form a query.
d.) Laziness: Intermediate operations are lazy and only execute when a terminal operation is invoked, which enables performance optimizations like short-circuiting.
e.) Internal Iteration: The Stream API handles the iteration process internally, abstracting away the need for explicit loops (like for or while).

The Stream Pipeline -->
A typical stream pipeline consists of three parts:--
a.) Source: The origin of the data, such as a Collection, array, or Files.lines().
b.) Intermediate Operations: These operations transform the stream into another stream. They are lazy and don't produce a result until a
 terminal operation is called. Examples include filter(), map(), sorted(), distinct(), limit(), and skip().

c.) Terminal Operation: This operation ends the pipeline and initiates the processing of the source data. It produces a final result (e.g., a List, an Integer, or void).
Examples include forEach(), collect(), reduce(), count(), anyMatch(), allMatch(), and findFirst()


 Streams operations in Java do not modify the original source data structure because the Java Streams API is designed around the principle of immutability of the source and a functional programming approach to data processing [1].
Here is a detailed explanation of why this design choice was made:-
 1. Functional Programming Principles
 The Streams API was heavily influenced by functional programming paradigms, which emphasize the use of pure functions [1].
 Pure Functions: A pure function is one that, given the same input, always produces the same output and has no side effects (it does not modify any external state or data) [1, 2].
 Benefits of No Side Effects: By not modifying the source data structure, streams operations become more predictable, easier to reason about, and less prone to introducing bugs related to state changes.

 2. Ensuring Immutability and Predictability
 Maintaining the immutability of the source data provides several significant advantages:
 Thread Safety: Since the source data is not modified, it can be safely shared across multiple threads without the need for complex synchronization mechanisms.
 This is essential for the parallel() stream operations [1].
 Predictability: The original data structure remains a reliable source of truth, regardless of how many different stream operations are performed on it.
 Reusability: The same source data can be used to perform multiple, different processing pipelines sequentially without the results of one pipeline affecting the input of the next [1].

 3. Separation of Concerns
 The API design separates the concerns of data storage (the source collection/array) and data processing (the stream pipeline) [1].
 Source: The original collection is responsible for holding the data.
 Stream: The stream is responsible for the computation logic. Intermediate operations (like filter(), map(), sorted()) produce new streams, and terminal operations (like collect(), reduce(), forEach()) consume the stream and produce a final result or side effect [1].

Basic implementation of Stream being serial && parallel

Reference -->
a.) https://www.baeldung.com/java-8-streams-introduction
b.) https://www.baeldung.com/java-8-streams
 * */

public class BasicStreamImplementation {

    public static void main(String ar[]) {
        List<String> names = Arrays.asList("Adam", "Alexander", "John", "Tom");
        names.stream().forEach(name -> System.out.println(name));

        System.out.println("Printing names using parallel streams !!");
        names.parallelStream().forEach(name -> printWithDelay(name, new Random().nextInt(5000)));
    }

    private static void printWithDelay(String name, long millis) {
        try {
            Thread.sleep(millis);
            System.out.println(name);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

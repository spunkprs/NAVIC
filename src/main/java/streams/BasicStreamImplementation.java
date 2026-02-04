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

Basic implementation of Stream being serial && parallel

Reference -->
a.) https://www.baeldung.com/java-8-streams-introduction
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

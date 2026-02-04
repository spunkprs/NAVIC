package streams;


import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
Reference --> https://www.baeldung.com/java-8-streams

Awesome learning can be tracked at reduce() method usage in the above article
 * */

public class StreamOperationsThree {

    public static void main(String ar[]) {
        /*Stream<String> stream =
                Stream.of("a", "b", "c").filter(element -> element.contains("b"));
        Optional<String> anyElement = stream.findAny();

        Optional<String> firstElement = stream.findFirst();


        If we try to call terminal operation again on the closed Stream we come across IllegalStateException because internal Stream has already been closed !!
        -->We can only use one terminal operation per stream<--
        Exception in thread "main" java.lang.IllegalStateException: stream has already been operated upon or closed
        */


        List<String> elements =
                Stream.of("a", "b", "c")
                        //.filter(element -> element.contains("b"))
                        .collect(Collectors.toList());
        Optional<String> anyElement = elements.stream().findAny();
        Optional<String> firstElement = elements.stream().findFirst();

        System.out.println("First usage " + anyElement.get());
        System.out.println("Second usage " + firstElement.get());


        List<String> list = Arrays.asList("abc3", "abc2", "abc1");
        List<String> sortedList = list
                .stream()
                .map(element -> element.substring(0, 4))
                .sorted()
                .collect(Collectors.toList());

        printList(sortedList);

        //Accumulator vs Combiner

        int reducedTwoParams =
                IntStream.range(1, 4).reduce(10, (a, b) -> a + b);

        System.out.println("Result of aggregation in phase one is " + reducedTwoParams);

        //Combiner does not work with serial streams and below example not producing output proves that !!

        int reducedParamsOne = Stream.of(1, 2, 3)
                .reduce(10, (a, b) -> a + b, (a, b) -> {
                    System.out.println("combiner was called in phase 2 !!");
                    return a + b;
                });

        System.out.println("Result of aggregation in phase two is " + reducedParamsOne);

        //Combiner works only with parallel streams and below example output proves that !!

        int reducedParamsTwo = Arrays.asList(1, 2, 3)
                .parallelStream()
                .reduce(10, (a, b) -> a + b, (a, b) -> {
                    System.out.println("combiner was called in phase 3 !!");
                    return a + b;
                });

        System.out.println("Result of aggregation in phase three is " + reducedParamsTwo);
    }

    private static void printList(List<String> list) {
        for (String word : list) {
            System.out.println(word);
        }
    }
}

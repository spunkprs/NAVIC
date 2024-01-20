package lamdaexpressionandfunctionalinterface.streams;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class PrintingElementsTestUsingStreamsAPI {

    /*
    Aim of application is to print elements in the provided collection using Streams API
    * */
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, -2, 30, 40, 50, 16, 70, 18, 99);
        System.out.println("Printing input numbers without using forEach Stream API:: " + numbers);

        //Using Lambda expression
        numbers.stream().forEach(s -> System.out.println(s));

        System.out.println();
        //Using Method Reference
        numbers.stream().forEach(System.out :: println);

        //Printing elements using Stram.Of() method
        Stream<Integer> streamOfIntegers = Stream.of(8, 88, 888, 8888, 88888);
        streamOfIntegers.forEach(System.out :: println);

        Integer [] arrayOfIntegers = {7, 77, 777, 7777, 77777};
        streamOfIntegers = Stream.of(arrayOfIntegers);
        streamOfIntegers.forEach(System.out :: println);
    }
}

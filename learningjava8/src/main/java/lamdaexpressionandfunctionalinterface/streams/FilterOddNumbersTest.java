package lamdaexpressionandfunctionalinterface.streams;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FilterOddNumbersTest {

    /*
    Aim of application is to filter out off numbers from collection of numbers using Streams API
    * */
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);

        System.out.println("Printing input numbers :: " + numbers);

        //Logic to pull odd numbers
        List<Integer> oddNumberList = numbers.stream().filter(num -> num % 2 != 0).collect(Collectors.toList());
        System.out.println("Printing odd numbers :: " + oddNumberList);
    }
}

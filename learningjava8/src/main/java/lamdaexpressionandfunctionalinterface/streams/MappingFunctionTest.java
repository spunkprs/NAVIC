package lamdaexpressionandfunctionalinterface.streams;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MappingFunctionTest {

    /*
    Aim of application is to find square of the numbers present in the list using Streams API
    * */
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);

        System.out.println("Printing input numbers :: " + numbers);

        //Logic to find square of numbers present in the list
        List<Integer> result = numbers.stream().map(num -> num * num).collect(Collectors.toList());
        System.out.println("Printing squared numbers :: " + result);
    }
}

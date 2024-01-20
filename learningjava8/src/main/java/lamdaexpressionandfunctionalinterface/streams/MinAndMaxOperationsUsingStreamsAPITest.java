package lamdaexpressionandfunctionalinterface.streams;

import java.util.Arrays;
import java.util.List;

public class MinAndMaxOperationsUsingStreamsAPITest {

    /*
    Aim of application is find min && max of the provided collection using Streams API
    * */
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, -2, 30, 40, 50, 16, 70, 18, 99);
        System.out.println("Printing input numbers :: " + numbers);

        //Logic to find minimum number from the collection provided
        System.out.println("Finding minimum number from the provided collection :: " +
                numbers.stream().min((numOne, numTwo) -> numOne.compareTo(numTwo)).get());

        //Logic to find maximum number from the collection provided
        System.out.println("Finding maximum number from the provided collection :: " +
                numbers.stream().max((numOne, numTwo) -> numOne.compareTo(numTwo)).get());
    }
}

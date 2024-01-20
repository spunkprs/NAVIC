package lamdaexpressionandfunctionalinterface.streams;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SortingUsingStreamsAPITest {

    /*
    Aim of application is to sort numbers present in the list by default sort order using Stream API
    * */
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, -2, 30, 40, 50, 16, 70, 18, 99);
        System.out.println("Printing input numbers before sorting:: " + numbers);

        //Logic to sort numbers using natural sorting order
        List<Integer> result = numbers.stream().sorted().collect(Collectors.toList());
        System.out.println("Printing numbers post sorting using natural sorting order:: " + result);

        //Logic to sort numbers in descending order
        List<Integer> resultDescendingOrder = numbers.stream().sorted((numOne, numTwo) -> numOne.compareTo(numTwo) * -1).collect(Collectors.toList());
        System.out.println("Printing numbers post sorting in descending order:: " + resultDescendingOrder);
    }
}

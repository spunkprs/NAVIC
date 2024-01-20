package lamdaexpressionandfunctionalinterface.functionalinterface;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SortingNumbersInArrayListUsingLambdaFunctionTest {

    public static void main(String ar[]) {
        List<Integer> unorderedList = new ArrayList<>();
        unorderedList.add(10);
        unorderedList.add(20);
        unorderedList.add(30);
        unorderedList.add(40);
        unorderedList.add(-100);
        unorderedList.add(0);
        System.out.println("Printing unordered list " + unorderedList);

        //Sorting elements of list in ascending order
        Collections.sort(unorderedList);
        System.out.println("Printing ascending ordered list " + unorderedList);

        Comparator<Integer> comparator = (numOne, numTwo) ->
                numOne.intValue() > numTwo.intValue() ? -1 : numOne.intValue() < numTwo.intValue() ? 1 : 0;
        //Sorting elements of list in descending order
        Collections.sort(unorderedList, comparator);
        System.out.println("Printing descending ordered list " + unorderedList);
    }
}

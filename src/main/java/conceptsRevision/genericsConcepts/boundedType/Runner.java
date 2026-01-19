package conceptsRevision.genericsConcepts.boundedType;

import java.util.ArrayList;
import java.util.List;

public class Runner {

    public static void main(String ar[]) {
        List<Integer> inputListOne = new ArrayList<>();
        inputListOne.add(1);
        inputListOne.add(2);
        inputListOne.add(3);

        List<Double> inputListTwo = new ArrayList<>();
        inputListTwo.add(1.1);
        inputListTwo.add(2.2);
        inputListTwo.add(3.3);

        System.out.println("Sum of the list elements having integer values is " + BoundedTypeParameterExample.calculateSumOne(inputListOne));

        System.out.println("Sum of the list elements having double values is " + BoundedTypeParameterExample.calculateSumOne(inputListTwo));

        System.out.println("Sum of the list elements having double values is " + BoundedTypeParameterExample.calculateSumTwo(inputListTwo));

        BoundedTypeParameterExample.printElements(inputListOne);

        System.out.println("Sum of the list elements having integer values is " + BoundedTypeParameterExample.calculateSumThree(inputListOne));
    }
}

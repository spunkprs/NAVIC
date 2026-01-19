package conceptsRevision.genericsConcepts.boundedType;

import java.util.List;

/**
Awesome article used for reference : https://medium.com/@pratik.941/understanding-generics-in-java-a-comprehensive-guide-afd437f413cf
 * */

public class BoundedTypeParameterExample {

    //Example of upper bound
    public static <T extends Number> double calculateSumOne(List<T> list) {
        System.out.println("Inside method calculateSumOne !!");
        double sum = 0.0;
        for (T number : list) {
            sum += number.doubleValue();
        }
        return sum;
    }

    //Example of upper bound
    public static double calculateSumTwo(List<? extends Number> list) {
        System.out.println("Inside method calculateSumTwo !!");
        double sum = 0.0;
        for (Number number : list) {
            sum += number.doubleValue();
        }
        return sum;
    }

    //Example of lower bound
    public static int calculateSumThree(List<? super Integer> list) {
        System.out.println("Inside method calculateSumThree !!");
        int sum = 0;
        for (Object number : list) {
            sum += (Integer) number;
        }
        return sum;
    }

    //Example of upper bound
    public static void printElements(List<? extends Number> list) {
        System.out.println("Printing elements inside the list !!");
        for (Number number : list) {
            System.out.println(number);
        }
    }
}

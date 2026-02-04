package inbuiltfunctionalinterfaces.function;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 Most important thing to notice here is that we can define Function as on line 17 && pass it to map method
 but to promote functional programming we can directly pass it's implementation as done already in line 20

Implemented basic function chaining too as part of this exercise !!

Reference -->
a.) https://www.youtube.com/watch?v=N9oO1O0mlkM
b.) https://www.baeldung.com/java-method-references
 * */

public class SimpleFunctionImplementation {

    public static void main(String ar[]) {
        List<String> names = Arrays.asList("Adam", "Alexander", "John", "Tom");
        //Function<String, Integer> filterPredicate = name -> name.length();
        List<Integer> result = names.stream()
                //.map(filterPredicate)
                .map(word -> word.length())
                //.filter(filterPredicate)
                .collect(Collectors.toList());
        for (Integer lengthOfWord : result) {
            System.out.println(lengthOfWord);
        }


        //Implementing basic chaining
        Function<Integer, Integer> functionSquare = (a) -> a * a;
        Function<Integer, Integer> functionAdd = (a) -> a + a;

        System.out.println("Forward chaining result " + functionSquare.andThen(functionAdd).apply(3));
        System.out.println("Reverse chaining result " + functionSquare.compose(functionAdd).apply(3));

        //Making use of method reference
        List<Double> numList = new ArrayList<>();
        numList.add(1.0);
        numList.add(2.0);
        numList.add(3.0);

        List<String> resultUsingMethodReference = numList.stream().map(String :: valueOf).collect(Collectors.toList());
        for (String num : resultUsingMethodReference) {
            System.out.println(num);
        }
    }
}

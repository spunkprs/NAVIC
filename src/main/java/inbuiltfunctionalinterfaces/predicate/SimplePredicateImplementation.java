package inbuiltfunctionalinterfaces.predicate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
Most important thing to notice here is that we can define predicate as on line 15 && pass it to filter method
but to promote functional programming we can directly pass it's implementation as done already in line 18

Reference Link ---> https://www.baeldung.com/java-predicate-chain
 * */

public class SimplePredicateImplementation {

    public static void main(String ar[]) {
        List<String> names = Arrays.asList("Adam", "Alexander", "John", "Tom");
        //Predicate<String> filterPredicate = name -> name.startsWith("A");
        List<String> result = names.stream()
                .filter(name -> name.startsWith("A"))
                //.filter(filterPredicate)
                .collect(Collectors.toList());
        for (String name : result) {
            System.out.println(name);
        }
    }
}

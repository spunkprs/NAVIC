package inbuiltfunctionalinterfaces.predicate;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
Multiple predicates can be chained together the way I have done it here :-
a.) Line 20
b.) Line 22

Reference Link ---> https://www.baeldung.com/java-predicate-chain
 * */

public class PredicateChainingImplementation {

    public static void main(String ar[]) {
        List<String> names = Arrays.asList("Adam", "Alexander", "John", "Tom");
        //Predicate<String> filterPredicate = name -> name.startsWith("A");
        List<String> result = names.stream()
                .filter(name -> name.startsWith("A"))
                //.filter(filterPredicate)
                .filter(name -> name.length() > 4)
                .collect(Collectors.toList());
        for (String name : result) {
            System.out.println(name);
        }
    }
}

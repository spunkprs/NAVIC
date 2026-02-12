package inbuiltfunctionalinterfaces.predicate;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
Multiple predicates can be combined together the way I have done it here :-
a.) Line 21 --> predicateOne
b.) Line 22 --> predicateTwo
c.) Line 23 --> predicateThree

Reference Link ---> https://www.baeldung.com/java-predicate-chain
 * */

public class PredicateCombinationImplementation {

    public static void main(String ar[]) {
        List<String> names = Arrays.asList("Adam", "Alexander", "John", "Jom");
        Predicate<String> predicateOne = name -> name.startsWith("A");
        Predicate<String> predicateTwo = name -> name.startsWith("J");
        Predicate<String> predicateThree = name -> name.length() >= 4;

        List<String> result = names.stream()
                .filter(predicateOne.or(predicateTwo).and(predicateThree))
                .collect(Collectors.toList());
        for (String name : result) {
            System.out.println(name);
        }
    }
}

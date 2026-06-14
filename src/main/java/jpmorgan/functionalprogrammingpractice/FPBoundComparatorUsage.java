package jpmorgan.functionalprogrammingpractice;

import java.util.*;
import java.util.stream.Collectors;

/**

Exercise 1 -->

You are given:

 List<String> names = Arrays.asList(
 "John",
 "Alice",
 "Bob",
 "Amanda",
 "Alex",
 "David",
 "Andrew"
 );

Return a list containing:-

a.) names starting with 'A',
b.) converted to uppercase,
c.) sorted by length,
d.) and then alphabetically if lengths are equal.

 Expected output:-

[ALEX, ALICE, AMANDA, ANDREW]
 * */

public class FPBoundComparatorUsage {

    public static void main(String ar[]) {
        FPBoundComparatorUsage unit = new FPBoundComparatorUsage();

        List<String> inputList = Arrays.asList(
                "John",
                "Alice",
                "Bob",
                "Amanda",
                "Alex",
                "David",
                "Andrew"
        );

        System.out.print("Expected Output is " + unit.generateResultAsPerExpectation(inputList));
    }

    public List<String> generateResultAsPerExpectation(List<String> inputList) {
        /*return inputList.stream().
                filter(Objects::nonNull).
                filter(word -> word.startsWith("A")).
                map(String::toUpperCase).sorted((a, b) -> {
            return a.length() < b.length() ? -1 : a.length() > b.length() ? 1 : a.compareTo(b);
        }).collect(Collectors.toList());*/

        return Optional.
                ofNullable(inputList).
                orElseGet(Collections::emptyList)
                .stream().
                filter(Objects::nonNull).
                filter(word -> word.startsWith("A")).
                map(String::toUpperCase).
                sorted(Comparator.comparingInt(String::length).thenComparing(Comparator.naturalOrder())).
                collect(Collectors.toList());

        /*return inputList.stream().
                filter(Objects::nonNull).
                filter(word -> word.startsWith("A")).
                map(String::toUpperCase).
                sorted(Comparator.comparingInt(String::length).reversed().thenComparing(Comparator.naturalOrder()).reversed()).
                collect(Collectors.toList());*/
    }
}

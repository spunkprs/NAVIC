package lamdaexpressionandfunctionalinterface.predicate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class RemovingNullAndEmptyStringFromList {

    public static void main(String ar[]) {
        List<String> names = Arrays.asList("Naina", " ", "Naas Kumari", "Natalie", "", null);
        Predicate<String> predicate = name ->  name != null && !name.isEmpty() && !name.trim().isEmpty();

        List<String> updatedList = new ArrayList<>();
        for (String name : names) {
            if (predicate.test(name)) {
                updatedList.add(name);
            }
        }
        System.out.println("Printing Updated List :: " + updatedList);
    }
}

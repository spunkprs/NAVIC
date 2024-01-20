package lamdaexpressionandfunctionalinterface.predicate;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

public class FindingNamesStartWithGivenAlphabet {

    public static void main(String ar[]) {
        char alphabetToLookFor = 'N';
        List<String> names = Arrays.asList("Naina", "Neha", "Naas Kumari", "Natalie", "Nitin", "Prateek");
        Predicate<String> predicate = name ->  name.charAt(0) == 'N';

        //Iterate over names && print those names only whose first alphabet is 'N' using Predicate

        for (String name : names) {
            if (predicate.test(name)) {
                System.out.println(name);
            }
        }
    }
}

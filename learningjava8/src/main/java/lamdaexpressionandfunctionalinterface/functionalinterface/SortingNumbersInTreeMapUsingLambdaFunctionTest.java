package lamdaexpressionandfunctionalinterface.functionalinterface;

import java.util.Map;
import java.util.TreeMap;

public class SortingNumbersInTreeMapUsingLambdaFunctionTest {

    public static void main(String ar[]) {
        Map<Integer, String> map = new TreeMap<>();
        map.put(100, "Prateek");
        map.put(400, "Piyush");
        map.put(200, "Naina");
        map.put(500, "Bhaskar");
        map.put(300, "Naash Kumari");
        map.put(600, "Chhola");

        System.out.println("Printing elements of map by natural ascending order of keys !!" + map);

        //Providing descending order comparator
        map = new TreeMap<>((indexOne,  indexTwo) ->
             indexOne > indexTwo ? -1 : indexOne < indexTwo ? 1 : 0);

        map.put(100, "Prateek");
        map.put(400, "Piyush");
        map.put(200, "Naina");
        map.put(500, "Bhaskar");
        map.put(300, "Naash Kumari");
        map.put(600, "Chhola");

        System.out.println("Printing elements of map by natural descending order of keys !!" + map);
    }
}

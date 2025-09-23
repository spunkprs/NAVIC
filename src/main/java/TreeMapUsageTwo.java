import java.util.TreeMap;

/**
 Intention is to explore following methods present in TreeMap :-
 1.) higherKey(K key)
 2.) lowerKey(K key)
 3.) headMap(K key, boolean inclusive)
 4.) tailMap(K key, boolean inclusive)
 5.) descendingKeySet()
 6.) firstKey()
 7.) lastKey()

 Official document:  https://docs.oracle.com/javase/8/docs/api/java/util/TreeMap.html

 Important thing to note here is when reverse comparator is provided meaning of following methods will also be reversed :-
 1.) headMap() --> ordering of original map is preserved
 2.) tailMap() --> ordering of original map is preserved
 3.) higherKey()
 4.) lowerKey()

 ------> In this example specifically keys will be sorted in the descending order <------------
 * */



public class TreeMapUsageTwo {

    public static void main(String ar[]) {

        TreeMap<Integer, String> treeMap = new TreeMap<>((obj1, obj2) -> {
            return obj1 < obj2 ? 1 : obj1 > obj2 ? -1 : 0;
        });


        treeMap.put(20, "PQRS");
        treeMap.put(30, "MNOP");
        treeMap.put(60, "JKLM");
        treeMap.put(40, "BCDE");
        treeMap.put(10, "ABCD");
        treeMap.put(50, "DEFG");
        treeMap.put(42, "CDEF");

        System.out.println("First key && associated value in the map is " + treeMap.firstKey() + " " +
                treeMap.get(treeMap.firstKey()));

        System.out.println("Last key && associated value in the map is " + treeMap.lastKey() + " " +
                treeMap.get(treeMap.lastKey()));

        System.out.println("Key lesser than " + 41 + " and its associated value in the map is " + treeMap.higherKey(41) + " " +
                treeMap.get(treeMap.higherKey(41)));

        System.out.println("Key higher than " + 41 + " and its associated value in the map is " + treeMap.lowerKey(41) + " " +
                treeMap.get(treeMap.lowerKey(41)));

        System.out.println("All keys greater than 41 and it's associated values are ::");

        for (Integer key : treeMap.headMap(41, false).keySet()) {
            System.out.println("key " + key + " and associated value is " + treeMap.get(key));
        }

        System.out.println("All keys lesser than 41 and it's associated values are ::");

        for (Integer key : treeMap.tailMap(41, false).keySet()) {
            System.out.println("key " + key + " and associated value is " + treeMap.get(key));
        }

        System.out.println("Printing keys in the reverse order alongside it's values !!");
        for (Integer key : treeMap.descendingKeySet()) {
            System.out.println("key " + key + " and associated value is " + treeMap.get(key));
        }

    }
}

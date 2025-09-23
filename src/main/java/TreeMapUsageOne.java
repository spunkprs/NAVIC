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

------> In this example specifically keys will be sorted in the natural or ascending order <------------
 * */


public class TreeMapUsageOne {

    public static void main(String ar[]) {

        TreeMap<Integer, String> treeMap = new TreeMap<>();


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

        System.out.println("Key greater than " + 41 + " and its associated value in the map is " + treeMap.higherKey(41) + " " +
                treeMap.get(treeMap.higherKey(41)));

        System.out.println("Key lesser than " + 41 + " and its associated value in the map is " + treeMap.lowerKey(41) + " " +
                treeMap.get(treeMap.lowerKey(41)));

        System.out.println("All keys smaller than 41 and it's associated values are ::");

        for (Integer key : treeMap.headMap(41, false).keySet()) {
            System.out.println("key " + key + " and associated value is " + treeMap.get(key));
        }

        System.out.println("All keys greater than 41 and it's associated values are ::");

        for (Integer key : treeMap.tailMap(41, false).keySet()) {
            System.out.println("key " + key + " and associated value is " + treeMap.get(key));
        }

    }
}

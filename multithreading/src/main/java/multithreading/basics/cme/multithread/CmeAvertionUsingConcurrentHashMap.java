package multithreading.basics.cme.multithread;


import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/*
This class is the demonstration against running into ConcurrentModificationException while using following category of maps :-
a.) HashMap
b.) Hashtable
c.) Synchronized Map

ConcurrentHashMap is the only map which can resume it's flow i.e not running into ConcurrentModificationException
when iteration on it's key is happening along with the alteration to it's structure

Below example is for single threaded environment only but the concept remains same for multithreaded environment too
* */

public class CmeAvertionUsingConcurrentHashMap {

        public static void main( String args[] ) {
            test(new Hashtable<String, Integer>());
            test(new HashMap<String, Integer>());
            test(Collections.synchronizedMap(new HashMap<String, Integer>()));
            test(new ConcurrentHashMap<String, Integer>());
        }

        static void test(Map<String, Integer> map) {

            // Put some data in the map
            int i;
            for (i = 0; i < 10; i++) {
                map.put("key-" + i, i);
            }

            Iterator it = map.entrySet().iterator();

            while (it.hasNext()) {
                map.put("key-" + i, i);
                try {
                    it.next();
                } catch (ConcurrentModificationException ex) {
                    System.out.println("ConcurrentModificationException thrown for map " + map.getClass().getName());
                    return;
                }
                i++;
            }

            System.out.println("No exception thrown for map " + map.getClass().getName());
        }
}

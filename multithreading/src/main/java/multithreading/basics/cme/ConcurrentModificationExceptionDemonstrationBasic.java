package multithreading.basics.cme;

import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ConcurrentModificationException;

/*
*
* This class is the demonstration against running into ConcurrentModificationException while working in the single
* threaded environment
* Important thing to notice here is CME doesn't even come under java.util.concurrent package
* */

public class ConcurrentModificationExceptionDemonstrationBasic {

        public static void main( String args[] ) {
            Map<String, Integer> map = new HashMap<>();

            // Fill the HashMap with some data
            int i = 0;
            for (i = 0; i < 100; i++) {
                map.put("key-" + i, i);
            }

            // Get an iterator for the entries in the map
            Iterator it = map.entrySet().iterator();
            System.out.println("Value of i :: " + i);

            try {
                while (it.hasNext()) {
                    // Add a new key/value pair while the map is
                    // being traversed.
                    map.put("key-" + i, i);
                    it.next();
                    i++;
                }
            } catch (ConcurrentModificationException e) {
                e.printStackTrace();
            }
        }

}

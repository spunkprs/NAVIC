package multithreading.basics.cme;

import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ConcurrentModificationException;

/*
*
* This class is the demonstration against running into ConcurrentModificationException while working in the single
* threaded environment when when map is getting altered while traversing it simultaneously
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
                    /*
                    Again pushing key/value pairs into map but important thing to notice
                    is new key are getting pushed into map hence state of it's keyset would be altered
                    leading to ConcurrentModificationException
                    * */
                    map.put("key-" + i, i);
                    it.next();
                    i++;
                }
            } catch (ConcurrentModificationException e) {
                e.printStackTrace();
            }
        }

}

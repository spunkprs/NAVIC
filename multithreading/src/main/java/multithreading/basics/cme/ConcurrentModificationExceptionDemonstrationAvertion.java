package multithreading.basics.cme;

import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/*
This class is the demonstration against not running into ConcurrentModificationException while working in the single
threaded environment when map is getting altered while traversing it simultaneously
Have simulated that though we are altering the map while traversing it simultaneously then in which scenario we don't
run into ConcurrentModificationException
*
*
* */

public class ConcurrentModificationExceptionDemonstrationAvertion {

    public static void main( String args[] ) {
        Map<String, Integer> map = new HashMap<>();

        // Fill the HashMap with some data
        int i = 0;
        for (i = 0; i < 100; i++) {
            map.put("key-" + i, i);
        }

        // Get an iterator for the entries in the map
        Iterator it = map.entrySet().iterator();
        System.out.println("Value of i before traversal:: " + i);

        //Resetting the value of i to 0
        i = 0;

        try {
            while (it.hasNext()) {
                /*
                *
                Again pushing key/value pairs into map but important thing to notice
                is no new key is pushed rather values are altered against the same set
                of keys hence we don't run into ConcurrentModificationException
                * */
                map.put("key-" + i, i + 10);
                it.next();
                i++;
            }
        } catch (ConcurrentModificationException e) {
            e.printStackTrace();
        }

        System.out.println("Value of i post traversal:: " + i);
    }
}

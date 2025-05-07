package multithreading.basics.synchronizedcollection;

import java.util.Collections;
import java.util.List;

/*
 * Example of using synchronized list where client locking is enabled hence chances of IndexOutOfBoundsException is not there
 * */

public class SynchronizedListConsistent {

    private List<String> synchronizedList;

    public SynchronizedListConsistent(List<String> list) {
        this.synchronizedList = Collections.synchronizedList(list);
    }

    public String getLast() {
        synchronized (synchronizedList) {
            int size = synchronizedList.size();
            return synchronizedList.get(size - 1);
        }
    }

    public void removeLast() {
        synchronized (synchronizedList) {
            int size = synchronizedList.size();
            synchronizedList.remove(size - 1);
        }
    }
}

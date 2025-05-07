package multithreading.basics.synchronizedcollection;

import java.util.Collections;
import java.util.List;

/*
* Example of using synchronized list where client locking is not enabled hence chances of IndexOutOfBoundsException is there
* */

public class SynchronizedListInconsistent {

    private List<String> synchronizedList;

    public SynchronizedListInconsistent(List<String> list) {
        this.synchronizedList = Collections.synchronizedList(list);
    }

    public String getLast() {
        int size = synchronizedList.size();
        return synchronizedList.get(size - 1);
    }

    public void removeLast() {
        int size = synchronizedList.size();
         synchronizedList.remove(size - 1);
    }

}

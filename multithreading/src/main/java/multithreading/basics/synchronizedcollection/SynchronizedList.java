package multithreading.basics.synchronizedcollection;

import java.util.List;

public class SynchronizedList {

    private List<String> synchronizedList;

    public SynchronizedList(List<String> synchronizedList) {
        this.synchronizedList = synchronizedList;
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

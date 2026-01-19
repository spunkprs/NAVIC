package conceptsRevision.genericsConcepts;

import java.util.LinkedList;

public class Box<T> implements Container<T> {

    private LinkedList<T> items = new LinkedList();

    @Override
    public void addItem(T item) {
        items.add(item);
    }

    @Override
    public T removeItem() {
        if (items.size() == 0) {
            throw new RuntimeException("Can't pull element from empty Box !!");
        }
        return items.poll();
    }

    @Override
    public void displayItems() {
        for (T item : items) {
            System.out.println(item);
        }
    }
}

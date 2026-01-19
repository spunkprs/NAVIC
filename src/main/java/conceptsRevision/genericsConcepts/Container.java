package conceptsRevision.genericsConcepts;

public interface Container<T> {
    void addItem(T item);
    T removeItem();
    void displayItems();
}

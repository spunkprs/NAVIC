package multithreading.problems.nonblockingdatastructure.stack.variant1;

public class StackNode <T> {

    private T item;
    private StackNode<T> next;

    public StackNode(T item) {
        this.item = item;
    }

    public T getItem() {
        return item;
    }

    public StackNode<T> getNext() {
        return next;
    }

    public void setNext(StackNode<T> next) {
        this.next = next;
    }
}

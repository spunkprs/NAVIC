package multithreading.problems.nonblockingdatastructure.queue;

public class QueueNode<T> {
    private T item;
    private QueueNode<T> next;

    public QueueNode(T item) {
        this.item = item;
    }

    public T getItem() {
        return item;
    }

    public void setNext(QueueNode<T> next) {
        this.next = next;
    }

    public QueueNode<T> getNext() {
        return next;
    }
}

package multithreading.problems.ratelimiter.slidingWindowLog;

public class Node {

    private long timeStamp;
    private Node next;

    public Node(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }
}

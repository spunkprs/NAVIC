package multithreading.jobscheduler;

import java.util.Comparator;

public class TaskComparator implements Comparator<Node> {

    @Override
    public int compare(Node o1, Node o2) {
        return o1.getTimestamp() < o2.getTimestamp() ? -1 : o1.getTimestamp() > o2.getTimestamp() ? 1 : 0;
    }
}

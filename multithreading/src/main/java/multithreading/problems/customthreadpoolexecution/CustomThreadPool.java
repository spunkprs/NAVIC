package multithreading.problems.customthreadpoolexecution;

import java.util.HashSet;
import java.util.concurrent.locks.ReentrantLock;

public class CustomThreadPool {

    private int maxWorkerThreads;
    private HashSet<Worker> workers;
    private int maxTasksCapacity;
    private Object lock;
    private Node head;
    private Node tail;
    private int currentEngagedWorkers;
    private int workerThreadNumber;
    private NodeUtility nodeUtility;

    public CustomThreadPool(int maxWorkerThreads, int maxTasksCapacity) {
        this.maxWorkerThreads = maxWorkerThreads;
        this.maxTasksCapacity = maxTasksCapacity;
        this.lock = new Object();
        this.workerThreadNumber = 1;
        this.nodeUtility = new NodeUtility();
    }

    public void removeWorker(Worker worker) {
            synchronized (lock) {
                if (workers != null && workers.size() >= 1 && workers.contains(worker)) {
                    workers.remove(worker);
                }
            }
    }

    public void submitTask(Runnable task) {
        synchronized (lock) {
            if (workers.isEmpty()) {
                Worker worker = new Worker(task, "worker- " + this.workerThreadNumber, nodeUtility, this);
                workers.add(worker);
                Thread runningThread = worker.getRunningThread();
                runningThread.start();
                this.workerThreadNumber++;
            } else {
                 if (currentEngagedWorkers < maxWorkerThreads) {
                    //Add another worker
                    Worker worker = new Worker(task, "worker- " + this.workerThreadNumber, nodeUtility, this);
                    workers.add(worker);
                    Thread runningThread = worker.getRunningThread();
                    runningThread.start();
                }
            }
        }
    }

    static class Node {
        private Runnable task;
        private Node next;

        public Node(Runnable task) {
            this.task = task;
        }

        public Runnable getTask() {
            return task;
        }
    }

    static class NodeUtility {
        private Node head;
        private Node tail;
        private ReentrantLock lockForAccess = new ReentrantLock();
        private int queueLength;

        public void addNode(Node node) {
            try {
                lockForAccess.lock();
                    if (head == null) {
                        head = node;
                        tail = head;
                    } else {
                        tail.next = node;
                        tail = node;
                    }
                    queueLength++;
            } finally {
                lockForAccess.unlock();
            }
        }

        public Node removeNode() {
            try {
                lockForAccess.lock();
                Node node = null;
                if (head != null) {
                    node = head;
                    head = head.next;
                    queueLength--;
                }
                return node;
            } finally {
                lockForAccess.unlock();
            }
        }

        public int getQueueLength() {
            try {
                lockForAccess.lock();
                return queueLength;
            } finally {
                lockForAccess.unlock();
            }
        }
    }

}

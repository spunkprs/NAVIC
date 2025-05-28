package multithreading.problems.customthreadpoolexecution;

import java.util.HashSet;
import java.util.concurrent.locks.ReentrantLock;

public class CustomThreadPool {

    private int maxWorkerThreads;
    private HashSet<Worker> workers;
    private ReentrantLock lock;
    private int currentEngagedWorkers;
    private int workerThreadNumber;
    private NodeUtility nodeUtility;

    public CustomThreadPool(int maxWorkerThreads, int maxTasksCapacity) {
        this.maxWorkerThreads = maxWorkerThreads;
        this.lock = new ReentrantLock();
        this.workerThreadNumber = 1;
        this.nodeUtility = new NodeUtility(maxTasksCapacity);
        this.workers = new HashSet<>();
    }


    public void removeWorker(Worker worker) {
        try {
                lock.lock();
                if (workers != null && workers.size() >= 1 && workers.contains(worker)) {
                    workers.remove(worker);
                }
            } finally {
                lock.unlock();
            }
    }

    public void submitTask(Runnable task, String taskName) throws RuntimeException {
        try {
            lock.lock();
                if (currentEngagedWorkers < maxWorkerThreads) {
                    //Add worker
                    Worker worker = new Worker(task, "worker - " + this.workerThreadNumber, nodeUtility, this);
                    workers.add(worker);
                    Thread runningThread = worker.getRunningThread();
                    runningThread.start();
                    this.currentEngagedWorkers++;
                    this.workerThreadNumber++;
                    lock.unlock();
                } else if (currentEngagedWorkers == maxWorkerThreads) {
                    lock.unlock();
                    System.out.println("Task with name " + taskName + " getting inserted into queue !!");
                    nodeUtility.addNode(new Node(task));
                }
        } finally {
            if (lock.getHoldCount() > 0) {
                lock.unlock();
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
        private int maxTasksCapacity;

        public NodeUtility(int maxTasksCapacity) {
            this.maxTasksCapacity = maxTasksCapacity;
        }

        public void addNode(Node node) throws RuntimeException {
            try {
                lockForAccess.lock();
                    if (head == null) {
                        head = node;
                        tail = head;
                    } else {
                        if (queueLength == maxTasksCapacity) {
                            throw new RuntimeException("Tasks can't be added beyond max length of " + maxTasksCapacity);
                        }
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

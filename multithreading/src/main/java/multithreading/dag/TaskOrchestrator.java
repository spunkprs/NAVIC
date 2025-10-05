package multithreading.dag;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;

public class TaskOrchestrator {

    private Job job;
    private ExecutorService executorService;

    private Set<Node> exploredTasks;
    private Set<Node> unExploredTasks;
    private Set<Node> submittedTasks;

    private ListNode head;
    private ListNode tail;

    public void orchestrateTasks() {
        List<Node> parentList = fetchInitialDependentTasks();

        head = transform(parentList);
        boolean shallContinue = true;

        //Loop shall be run on unexploredTask && check for it's size > 0
        while (unExploredTasks.size() > 0 && shallContinue) {
            /**
             To avoid any ConcurrentModificationException have tweaked the logic to
             make use of ListNode instead && checking size of unExploredTasks > 0
             * */
            ListNode traversalNode = head;
            while (traversalNode != null) {
                if (!exploredTasks.contains(traversalNode.getNode()) && checkIfAllParentExplored(traversalNode.getNode())
                        && !submittedTasks.contains(traversalNode.getNode())) {
                    submittedTasks.add(traversalNode.getNode());
                    executorService.submit(traversalNode.getNode().getTask());
                } else if (traversalNode.getNode().getTask().getState().equals(TaskState.SUCCESS)) {
                    removeNodeFromList(traversalNode);
                    unExploredTasks.remove(traversalNode.getNode());
                    exploredTasks.add(traversalNode.getNode());
                    //add children generated from parent
                    addChildren(traversalNode.getNode());
                } else if (traversalNode.getNode().getTask().getState().equals(TaskState.FAILURE)) {
                    System.out.println("Task with id " + traversalNode.getNode().getTask().getTaskId()
                            + " has failed for job id" + job.getJobId() + " hence marking overall job as failed !!");
                    shallContinue = false;
                }
            }
        }
    }

    private boolean checkIfAllParentExplored(Node node) {
        boolean result = true;
        List<Task> parents = node.getTask().getParent();
        if (parents == null || parents.isEmpty()) {
            return true;
        } else {
            for (Task parent : parents) {
                if (!parent.getState().equals(TaskState.SUCCESS)) {
                    result = false;
                    break;
                }
            }
        }
        return result;
    }

    private void addChildren(Node node) {
        Task parentTask = node.getTask();
        List<Task> childTasks = parentTask.getChildren();
        for (Task child : childTasks) {
            Node childNode = new Node(job.getJobId(), child);
            if (!unExploredTasks.contains(childNode)) {
                unExploredTasks.add(childNode);
                ListNode listNode = new ListNode(childNode);
                if (tail != null) {
                    tail.setNext(listNode);
                    listNode.setPrevious(tail);
                    tail = listNode;
                } else {
                    head = tail = listNode;
                }
            }
        }
    }

    private void removeNodeFromList(ListNode traversalNode) {
        if (traversalNode.getNext() != null && traversalNode.getPrevious() != null) {
            ListNode previous = traversalNode.getPrevious();
            ListNode next = traversalNode.getNext();
            previous.setNext(next);
            next.setPrevious(previous);
        } else if (traversalNode.getNext() == null && traversalNode.getPrevious() != null) {
            tail = traversalNode.getPrevious();
        } else if (traversalNode.getNext() != null && traversalNode.getPrevious() == null) {
            head = traversalNode.getNext();
        }
    }

    private ListNode transform(List<Node> parentList) {
        for (Node node : parentList) {
            if (head == null) {
                head = new ListNode(node);
                tail = head;
            } else {
                ListNode nextNode = new ListNode(node);
                tail.setNext(nextNode);
                nextNode.setPrevious(tail);
                tail = nextNode;
            }
            unExploredTasks.add(node);
        }
        return head;
    }

    private List<Node> fetchInitialDependentTasks() {
        List<Node> rootedTasks = new ArrayList<>();
        for (Task task : job.getDependentTasks()) {
            if (task.getParent() == null || task.getParent().isEmpty()) {
                rootedTasks.add(new Node(job.getJobId(), task));
            }
        }
        return rootedTasks;
    }
}

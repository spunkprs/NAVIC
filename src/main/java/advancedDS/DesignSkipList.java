package advancedDS;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class DesignSkipList {

    private Node head;
    private Node tail;
    private boolean continueWithInsertionProcess = true;
    private boolean continueWithSearchProcess = true;


    /*
    * Utility to test following methods/functionality that have been added as part of SkipList i.e add, search, erase
    * */
    public static void main(String ar[]) {
        DesignSkipList designSkipList = new DesignSkipList();

        System.out.println("Is number 100 present inside the skiplist :: " + designSkipList.search(100));

        //Add following nodes in this order to skiplist 30, 50, 40, 45, 65, 70
        designSkipList.add(30);
        designSkipList.add(50);
        designSkipList.add(40);
        designSkipList.add(45);
        designSkipList.add(65);
        designSkipList.add(32);
        designSkipList.add(45);
        designSkipList.add(75);
        System.out.println("All above nodes were inserted into skiplist ");

        //Multiple test cases to validate search functionality inside the skiplist
        System.out.println("Is number 32 present inside the skiplist :: " + designSkipList.search(32));
        System.out.println("Is number 38 present inside the skiplist :: " + designSkipList.search(38));
        System.out.println("Is number 75 present inside the skiplist :: " + designSkipList.search(75));
        System.out.println("Is number 30 present inside the skiplist :: " + designSkipList.search(30));
    }

    public boolean search(int target) {
        if (head == null || head.forwardNodes.isEmpty()) {
            return false;
        } else {
            continueWithSearchProcess = true;
            processToSearchForAnElementInTheSkipList(head, target);
        }
        return !continueWithSearchProcess;
    }

    private void processToSearchForAnElementInTheSkipList(Node parent, int target) {
        int heightOfParent = parent.height;
        while (heightOfParent >= 1 && continueWithSearchProcess) {
            Node child = parent.forwardNodes.get(heightOfParent);
            if (child != tail) {
                if (child.num < target) {
                    processToSearchForAnElementInTheSkipList(child, target);
                } else if (child.num == target) {
                    continueWithSearchProcess = false;
                }
            }
            heightOfParent--;
        }
    }

    public void add(int num) {
        continueWithInsertionProcess = true;
        if (head != null) {
            int height = fetchMaxLevelForTheNumberToBeInserted();
            insertElement(num, height);
        } else {
            head = new Node();
            head.setHeight(1);
            head.setCount(1);

            tail = new Node();
            tail.setHeight(1);
            tail.setCount(1);

            head.setForwardNodes(new HashMap<>());
            head.setBackwardNodes(new HashMap<>());

            tail.setForwardNodes(new HashMap<>());
            tail.setBackwardNodes(new HashMap<>());

            int height = fetchMaxLevelForTheNumberToBeInserted();
            insertElement(num, height);
        }
    }

    private void insertElement(int num, int height) {
        populatePointers(num, height);
    }

    private void populatePointers(int num, int height) {
        Node node = new Node();
        node.setNum(num);
        node.setHeight(height);
        node.setCount(1);
        node.setForwardNodes(new HashMap<>());
        node.setBackwardNodes(new HashMap<>());

        if (head.forwardNodes.isEmpty()) {
            int level = 1;
                head.forwardNodes.put(level, node);
                tail.backwardNodes.put(level, node);
                node.backwardNodes.put(level, head);
                node.forwardNodes.put(level, tail);
                node.setHeight(1);
            head.setHeight(head.forwardNodes.size());
            tail.setHeight(tail.backwardNodes.size());
        } else {
            insertElementIntoSkipList(node);
        }
    }

    private void insertElementIntoSkipList(Node node) {
        insertionProcess(head, node);
    }

    private void insertionProcess(Node parent, Node nodeToBeInserted) {
        int heightOfParent = parent.height;
        while (heightOfParent >= 1 && continueWithInsertionProcess) {
            Node child = parent.forwardNodes.get(heightOfParent);
            if (child != tail) {
              if (child.num == nodeToBeInserted.num) {
                  child.setCount(child.count + 1);
                  continueWithInsertionProcess = false;
              } else {
                  if (child.num < nodeToBeInserted.num) {
                      insertionProcess(child, nodeToBeInserted);
                  } else {
                      if (nodeToBeInserted.height >= heightOfParent) {
                          child.backwardNodes.put(heightOfParent, nodeToBeInserted);
                          nodeToBeInserted.forwardNodes.put(heightOfParent, child);
                          nodeToBeInserted.backwardNodes.put(heightOfParent, parent);
                          parent.forwardNodes.put(heightOfParent, nodeToBeInserted);
                          if (heightOfParent == 1) {
                              continueWithInsertionProcess = false;
                          }
                      }
                  }
              }
            } else {
                if (continueWithInsertionProcess) {
                    if (nodeToBeInserted.height >= heightOfParent) {
                        maintainPointers(parent, nodeToBeInserted, heightOfParent);
                    }
                }
            }
            heightOfParent--;
        }

        if (nodeToBeInserted.height > head.height) {
            nodeToBeInserted.setHeight(head.height + 1);
            head.setHeight(head.height + 1);
            tail.setHeight(tail.height + 1);

            head.forwardNodes.put(head.height, nodeToBeInserted);
            nodeToBeInserted.backwardNodes.put(nodeToBeInserted.height, head);

            tail.backwardNodes.put(tail.height, nodeToBeInserted);
            nodeToBeInserted.forwardNodes.put(nodeToBeInserted.height, tail);
        }
    }

    private void maintainPointers(Node parent, Node nodeToBeInserted, int level) {
            parent.forwardNodes.put(level, nodeToBeInserted);
            nodeToBeInserted.backwardNodes.put(level, parent);
            nodeToBeInserted.forwardNodes.put(level, tail);
            tail.backwardNodes.put(level, nodeToBeInserted);
            if (level == 1) {
                continueWithInsertionProcess = false;
            }
    }

    private int fetchMaxLevelForTheNumberToBeInserted() {
        boolean tossChance = useRandomization();
        int height = 1;
        while (tossChance) {
            tossChance = useRandomization();
            height++;
        }
        return height;
    }

    private boolean useRandomization() {
        Random random = new Random();
        int num = random.nextInt(2);
        return num == 0 ? true : false;
    }

    public boolean erase(int num) {
        return false;
    }

    class Node {
        private int num;
        private int height;
        private int count;
        private Map<Integer, Node> forwardNodes;
        private Map<Integer, Node> backwardNodes;

        public void setForwardNodes(Map<Integer, Node> forwardNodes) {
            this.forwardNodes = forwardNodes;
        }

        public void setBackwardNodes(Map<Integer, Node> backwardNodes) {
            this.backwardNodes = backwardNodes;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public void setNum(int num) {
            this.num = num;
        }
    }

}

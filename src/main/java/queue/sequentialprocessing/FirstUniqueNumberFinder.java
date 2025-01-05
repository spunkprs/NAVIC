package queue.sequentialprocessing;

/*
* Problem: Design a First Unique Number finder that allows you to process a data stream and find the first unique number at any point. First Unique Number finder class supports following functions:

void add(int value) processes a new number from the data stream.

int get_first_unique() returns the value of the first unique integer of the data stream, and returns -1 if there is no such integer.

Input:
["FirstUnique","add","add","get_first_unique","add","get_first_unique"]
[[],[2],[3],[],[2],[3]]

Output:
[null,null,null,2,null,3]

Explanation:
FirstUnique firstUnique = new FirstUnique(); // Initialize
firstUnique.add(2);            // data: [2]
firstUnique.add(3);            // data: [2,3]
firstUnique.get_first_unique(); // returns 2 since 2 appeared first and only once (unique)
firstUnique.add(2);            // data: [2,3,2]
firstUnique.get_first_unique(); // returns 3 since 3 is the only number that appears once (unique)
* */

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FirstUniqueNumberFinder {

    private Node head = null;
    private Node tail = null;

    private Map<Integer, Integer> frequencyMap = new HashMap<>();
    private Map<Integer, Node> nodeAddressMap = new HashMap<>();

    class Node {
        private int number;
        private Node left;
        private Node right;

        public Node(int number) {
            this.number = number;
        }
    }

    public static void main(String ar[]) {

        FirstUniqueNumberFinder firstUniqueNumberFinder = new FirstUniqueNumberFinder();

        List<String> input = Arrays.asList("getFirstUnique", "add:2", "add:3","getFirstUnique", "add:2", "getFirstUnique",
                "add:4", "getFirstUnique", "add:5", "add:3", "getFirstUnique", "add:4", "getFirstUnique", "add:5", "getFirstUnique");

        for (String ip : input) {
            if (ip.contains("add")) {
                String ipArray[] = ip.split(":");
                firstUniqueNumberFinder.add(Integer.parseInt(ipArray[1]));
            } else {
                System.out.println(firstUniqueNumberFinder.getFirstUnique());
            }
        }
    }

    public void add(int number) {
        if (head == null && tail == null) {
            Node node = new Node(number);
            head = node;
            tail = node;
            frequencyMap.put(number, 1);
            nodeAddressMap.put(number, node);
        } else {
            if (!frequencyMap.containsKey(number)) {
                frequencyMap.put(number, 1);
                Node node = new Node(number);
                tail.right = node;
                node.left = tail;
                tail = tail.right;
                nodeAddressMap.put(number, node);
            } else {
                frequencyMap.put(number, frequencyMap.get(number) + 1);
                removalOfRedundantNodeFromQueue(number);
            }
        }
    }

    private void removalOfRedundantNodeFromQueue(int number) {
        if (nodeAddressMap.containsKey(number)) {
            Node node = nodeAddressMap.get(number);
            Node leftNode = node.left;
            Node rightNode = node.right;

            if (leftNode != null && rightNode != null) {
                leftNode.right = rightNode;
                rightNode.left = leftNode;
            } else if (leftNode == null && rightNode == null) {
                head = null;
                tail = null;
            } else if (leftNode == null && rightNode != null) {
                rightNode.left = null;
                head = head.right;
            } else {
                leftNode.right = null;
                tail = leftNode;
            }
            nodeAddressMap.remove(number);
        }
    }

    public int getFirstUnique() {
        if (head == null && tail == null) {
            return -1;
        } else {
            return head.number;
        }
    }


}

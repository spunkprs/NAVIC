package graph;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;

/*
This problem aims at generating deep copy of nodes present in the graph
Link : https://leetcode.com/explore/interview/card/facebook/52/trees-and-graphs/277/
Solution was accepted with memory && time constraints in limit
* */
public class CloneGraphLeetCode {
    static class Node {
        public int val;
        public List<Node> neighbors;
        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }
        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }
        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }

    class ListNode {
        private Node node;
        private ListNode next;
        public ListNode(Node node) {
            this.node = node;
        }
    }

    public static void main(String ar[]) {
        CloneGraphLeetCode unit = new CloneGraphLeetCode();

        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);
        Node n4 = new Node(4);

        n1.neighbors.add(n2);
        n1.neighbors.add(n4);

        n2.neighbors.add(n1);
        n2.neighbors.add(n3);

        n3.neighbors.add(n2);
        n3.neighbors.add(n4);

        n4.neighbors.add(n1);
        n4.neighbors.add(n3);

        System.out.println("Returning cloned graph :: " + unit.cloneGraph(n1));
    }

    public Node cloneGraph(Node node) {
        Map<Integer, List<Integer>> mapOne = new HashMap();
        Map<Integer, Node> mapTwo = new HashMap();
        if (node == null) {
            return null;
        } else {
            populateMaps(node, mapOne, mapTwo);
            buildAdjacencyListForCopiedNodes(mapOne, mapTwo);
            return mapTwo.get(node.val);
        }
    }

    private void buildAdjacencyListForCopiedNodes(Map<Integer, List<Integer>> mapOne, Map<Integer, Node> mapTwo) {
        for (Integer key : mapOne.keySet()) {
            List<Integer> dependencies = mapOne.get(key);
            for (Integer dependency : dependencies) {
                Node parentNode = mapTwo.get(key);
                parentNode.neighbors.add(mapTwo.get(dependency));
            }
        }
    }

    private void populateMaps(Node node, Map<Integer, List<Integer>> mapOne, Map<Integer, Node> mapTwo) {
        ListNode head = new ListNode(node);
        ListNode tail = head;
        Set<Integer> exploredNodes = new HashSet();
        exploredNodes.add(head.node.val);
        while (head != null) {
            List<Node> neighbors = head.node.neighbors;
            List<Integer> neighborValues = new ArrayList();
            mapOne.put(head.node.val, neighborValues);
            Node deepCopiedNode = new Node(head.node.val);
            mapTwo.put(head.node.val, deepCopiedNode);
            for (Node neighbor : neighbors) {
                neighborValues.add(neighbor.val);
                if (!exploredNodes.contains(neighbor.val)) {
                    exploredNodes.add(neighbor.val);
                    ListNode ln = new ListNode(neighbor);
                    tail.next = ln;
                    tail = tail.next;
                    }
                }
                head = head.next;
        }
    }

}

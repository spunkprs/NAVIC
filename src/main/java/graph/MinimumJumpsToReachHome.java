package graph;

import java.util.*;

/**
 A certain bug's home is on the x-axis at position x. Help them get there from position 0.

 The bug jumps according to the following rules:

 1.) It can jump exactly a positions forward (to the right).
 2.) It can jump exactly b positions backward (to the left).
 3.) It cannot jump backward twice in a row.
 4.) It cannot jump to any forbidden positions.
 5.) The bug may jump forward beyond its home, but it cannot jump to positions numbered with negative integers.

 Given an array of integers forbidden, where forbidden[i] means that the bug cannot jump to the position forbidden[i], and integers a, b, and x,
 return the minimum number of jumps needed for the bug to reach its home.
 If there is no possible sequence of jumps that lands the bug on position x, return -1.


 Constraints:

 1.) 1 <= forbidden.length <= 1000
 2.) 1 <= a, b, forbidden[i] <= 2000
 3.) 0 <= x <= 2000
 4.) All the elements in forbidden are distinct.
 5.) Position x is not forbidden.

 Source --> Leetcode

 * */

public class MinimumJumpsToReachHome {

    private Node head;
    private Node tail;
    private boolean haltProcess;

    public static void main(String ar[]) {
        MinimumJumpsToReachHome unit = new MinimumJumpsToReachHome();

        int forbidden[] = {1,6,2,14,5,17,4};
        int fwdDistance = 16;
        int bwdDistance = 9;
        int destination = 7;

        System.out.println("Minimum number of jumps to reach destination is " + unit.minimumJumps(forbidden, fwdDistance, bwdDistance, destination));
    }

    public int minimumJumps(int[] forbidden, int a, int b, int x) {
        Set<Node> forbiddenNodes = prepareForbiddenNodes(forbidden);
        int minimumJumps = -1;
        if (x == 0) {
            return 0;
        } else {
            Set<Node> visitedNodes = new HashSet<>();
            Node node = new Node(0, 0, 0);
            if (head == null && tail == null) {
                head = node;
                tail = head;
            }

            visitedNodes.add(head);

            Node traversalNode = head;

            while (traversalNode != null) {
                for (Node childNode : processToFindChildNodes(traversalNode, forbiddenNodes, a, b, x, visitedNodes)) {
                    if (haltProcess) {
                        return minimumJumps;
                    }
                    tail.next = childNode;
                    childNode.previous = tail;
                    tail = childNode;
                    if (tail.destinationIndex == x) {
                        minimumJumps = tail.depth;
                        haltProcess = true;
                        break;
                    }
                }
                traversalNode = traversalNode.next;
            }
            return minimumJumps;
        }
    }

    private Set<Node> prepareForbiddenNodes(int[] forbidden) {
        Set<Node> forbiddenNodes = new HashSet<>();

        for (int i = 0; i < forbidden.length; i++) {
            Node forbiddenNode = new Node(forbidden[i], 0, 0);
            forbiddenNodes.add(forbiddenNode);
        }
        return forbiddenNodes;
    }

    private List<Node> processToFindChildNodes(Node traversalNode, Set<Node> forbiddenNodes, int fwdDistance, int bwdDistance,
                                         int finalDestination, Set<Node> visitedNodes) {

        List<Node> resultantNodes = new ArrayList<>();

        //Fwd Node
        Node fwdNode = new Node(traversalNode.destinationIndex + fwdDistance, 0, traversalNode.depth + 1);

        if (!visitedNodes.contains(fwdNode) && !forbiddenNodes.contains(fwdNode)) {
            visitedNodes.add(fwdNode);
            resultantNodes.add(fwdNode);
        }

        //Bwd Node
        Node bwdNode = null;

        if (traversalNode.state == 0) {
          if (traversalNode.destinationIndex - bwdDistance > 0) {
              bwdNode = new Node(traversalNode.destinationIndex - bwdDistance, -1, traversalNode.depth + 1);
          }
            if (bwdNode != null && !visitedNodes.contains(bwdNode) && !forbiddenNodes.contains(bwdNode)) {
                visitedNodes.add(bwdNode);
                resultantNodes.add(bwdNode);
            } else {
                bwdNode = null;
            }
        }

        if (fwdNode != null && bwdNode != null) {
            if (fwdNode.destinationIndex > finalDestination && bwdNode.destinationIndex > finalDestination) {
                haltProcess = true;
            }
        }

        /*if (fwdNode != null && bwdNode == null) {
            if (fwdNode.destinationIndex > finalDestination) {
                haltProcess = true;
            }
        }*/

        return resultantNodes;
    }

    static class Node {
        private int destinationIndex;
        private int state;
        private int depth;

        private Node next;
        private Node previous;

        public Node(int destinationIndex, int state, int depth) {
            this.destinationIndex = destinationIndex;
            this.state = state;
            this.depth = depth;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return destinationIndex == node.destinationIndex;
        }

        @Override
        public int hashCode() {
            return Objects.hash(destinationIndex);
        }
    }
}

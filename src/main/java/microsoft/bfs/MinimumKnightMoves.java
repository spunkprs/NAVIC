package microsoft.bfs;

import java.util.*;

/**
Problem : 1197
In an infinite chess board with coordinates from -infinity to +infinity, you have a knight at square [0, 0].

A knight has 8 possible moves it can make, as illustrated below. Each move is two squares in a cardinal direction, then one square in an orthogonal direction.

Constraints:-

a.) -300 <= x, y <= 300
b.) 0 <= |x| + |y| <= 300

----> 36 / 45 tests getting passed, for remaining witnessing TLE, have a look into it && update T.C && S.C accordingly <----------

 * */

public class MinimumKnightMoves {

    public static void main(String ar[]) {
        MinimumKnightMoves unit = new MinimumKnightMoves();
        int finalX = 5;
        int finalY = 5;

        System.out.println("Minimum moves required by Knight to reach destination is " + unit.minKnightMoves(finalX, finalY));
    }

    public int minKnightMoves(int x, int y) {

        Node startingNode = new Node(0, 0);
        startingNode.distance = 0;

        Node finalNode = new Node(x, y);

        if (startingNode.equals(finalNode)) {
            return 0;
        } else {
            Queue<Node> queue = new LinkedList<>();
            Set<Node> exploredNodes = new HashSet<>();
            return processToComputeMinDistance(startingNode, queue, exploredNodes, finalNode);
        }
    }

    private int processToComputeMinDistance(Node startingNode, Queue<Node> queue, Set<Node> exploredNodes, Node finalNode) {
        int minDistance = 0;
        exploredNodes.add(startingNode);
        queue.add(startingNode);
        boolean flag = false;

        while (!queue.isEmpty()) {
            for (Node childNode : fetchChildren(queue.peek(), exploredNodes)) {
                if (!childNode.equals(finalNode)) {
                    queue.add(childNode);
                    exploredNodes.add(childNode);
                } else {
                    minDistance = childNode.distance;
                    flag = true;
                    break;
                }
            }
            if (flag) {
                break;
            }
            queue.poll();
        }
        return minDistance;
    }

    private List<Node> fetchChildren(Node parentNode, Set<Node> exploredNodes) {
        List<Node> childNodes = new ArrayList<>();

        Node childOne = new Node(parentNode.x + 1, parentNode.y + 2);
        Node childTwo = new Node(parentNode.x - 1, parentNode.y + 2);

        Node childThree = new Node(parentNode.x + 1, parentNode.y - 2);
        Node childFour = new Node(parentNode.x - 1, parentNode.y - 2);

        Node childFive = new Node(parentNode.x + 2, parentNode.y + 1);
        Node childSix = new Node(parentNode.x + 2, parentNode.y - 1);

        Node childSeven = new Node(parentNode.x - 2, parentNode.y + 1);
        Node childEight = new Node(parentNode.x - 2, parentNode.y - 1);

        if (!exploredNodes.contains(childOne)) {
            childNodes.add(childOne);
        }

        if (!exploredNodes.contains(childTwo)) {
            childNodes.add(childTwo);
        }

        if (!exploredNodes.contains(childThree)) {
            childNodes.add(childThree);
        }

        if (!exploredNodes.contains(childFour)) {
            childNodes.add(childFour);
        }

        if (!exploredNodes.contains(childFive)) {
            childNodes.add(childFive);
        }

        if (!exploredNodes.contains(childSix)) {
            childNodes.add(childSix);
        }

        if (!exploredNodes.contains(childSeven)) {
            childNodes.add(childSeven);
        }

        if (!exploredNodes.contains(childEight)) {
            childNodes.add(childEight);
        }

        childNodes.forEach(x -> x.distance = parentNode.distance + 1);
        return childNodes;
    }

    static class Node {
        private int x;
        private int y;
        private int distance;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return x == node.x && y == node.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

}

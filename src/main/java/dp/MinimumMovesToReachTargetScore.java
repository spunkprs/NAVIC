package dp;

import java.util.*;

/**
 You are playing a game with integers. You start with the integer 1 and you want to reach the integer target.

 In one move, you can either:

 Increment the current integer by one (i.e., x = x + 1).
 Double the current integer (i.e., x = 2 * x).
 You can use the increment operation any number of times, however, you can only use the double operation at most maxDoubles times.

 Given the two integers target and maxDoubles, return the minimum number of moves needed to reach target starting with 1

 Constraints:-
 1.) 1 <= target <= pow(10,9)
 2.) 0 <= maxDoubles <= 100

 * */

public class MinimumMovesToReachTargetScore {

    public static void main(String ar[]) {
        MinimumMovesToReachTargetScore unit = new MinimumMovesToReachTargetScore();
        int target = 5;
        int maxDoubles = 0;

        System.out.print("Minimum moves required to reach target is : " + unit.minMoves(target, maxDoubles));
    }

    public int minMoves(int target, int maxDoubles) {

        if (maxDoubles == 0) {
            return target - 1;
        } else {
            return processToFindMinimumMoves(target, maxDoubles);
        }
    }

    private int processToFindMinimumMoves(int target, int maxDoubles) {
        int minMoves = Integer.MAX_VALUE;
        Queue<Node> queue = new LinkedList<>();
        Set<Node> visitedNodes = new HashSet<>();
        Node rootNode = new Node(1, maxDoubles, 0);

        visitedNodes.add(rootNode);
        queue.add(rootNode);

        //Start BFS to find minimum moves to reach target
        while(!queue.isEmpty()) {
            Node parentNode = queue.peek();
            if (parentNode.num != target) {
                for (Node child : fetchChildren(parentNode, target, visitedNodes)) {
                    queue.add(child);
                }
                queue.poll();
            } else {
                minMoves = parentNode.depth < minMoves ? parentNode.depth : minMoves;
                break;
            }
        }
        return minMoves;
    }

    private List<Node> fetchChildren(Node parentNode, int target, Set<Node> visitedNodes) {
        List<Node> children = new ArrayList<>();
        if (parentNode.num + 1 <= target) {
            Node childNode = new Node(parentNode.num + 1, parentNode.remainingDoubles, parentNode.depth + 1);
            if (!visitedNodes.contains(childNode)) {
                children.add(childNode);
            }
        }

        if (parentNode.num * 2 <= target && parentNode.remainingDoubles >= 1) {
            Node childNode = new Node(parentNode.num * 2, parentNode.remainingDoubles - 1, parentNode.depth + 1);
            if (!visitedNodes.contains(childNode)) {
                children.add(childNode);
            }
        }
        return children;
    }


    static class Node {
        private int num;
        private int remainingDoubles;
        private int depth;


        public Node(int num, int remainingDoubles, int depth) {
            this.num = num;
            this.remainingDoubles = remainingDoubles;
            this.depth = depth;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return num == node.num && remainingDoubles == node.remainingDoubles;
        }

        @Override
        public int hashCode() {
            return Objects.hash(num, remainingDoubles);
        }
    }
}

package microsoft.bfs;

import java.util.*;

public class OpenTheLock {


    public static void main(String ar[]) {
        OpenTheLock unit = new OpenTheLock();
        String deadEnds[] = {"0201","0101","0102","1212","2002"};
        String target = "0202";

        //String deadEnds[] = {"8888"};
        //String target = "0009";

        System.out.println("Source is always 0000");

        System.out.print("Minimum steps to reach target from source is " + unit.openLock(deadEnds, target));
    }

    public int openLock(String[] deadends, String target) {
        Node startNode = new Node("0000", 0);

        if (startNode.pattern.equals(target)) {
            return 0;
        }

        Set<String> deadEndsSet = populateDeadEndsSet(deadends);

        if (deadEndsSet.contains("0000")) {
            return -1;
        }
        return processToComputeMinimumStepsToOpenLock(startNode, deadEndsSet, target);
    }

    private int processToComputeMinimumStepsToOpenLock(Node startNode, Set<String> deadEndsSet, String target) {
        int minimumSteps = Integer.MAX_VALUE;
        Queue<Node> queue = new LinkedList<>();
        queue.add(startNode);
        Set<Node> visitedNodes = new HashSet<>();
        visitedNodes.add(startNode);
        boolean flag = true;

        while (!queue.isEmpty() && flag) {
            for (Node childNode : fetchPossibleChild(queue.peek(), deadEndsSet, visitedNodes)) {
                if (childNode.pattern.equals(target)) {
                    minimumSteps = childNode.depth;
                    flag = false;
                    break;
                } else {
                    queue.add(childNode);
                }
            }
            queue.poll();
        }
        return minimumSteps == Integer.MAX_VALUE ? -1 : minimumSteps;
    }

    private List<Node> fetchPossibleChild(Node parentNode, Set<String> deadEndsSet, Set<Node> visitedNodes) {
        String pattern = parentNode.pattern;
        char arr[] = pattern.toCharArray();
        List<Node> childNodes = new ArrayList<>();

        for (int i = 0; i < arr.length; i++) {
            StringBuilder sb = new StringBuilder(pattern);
            int num = Integer.parseInt(String.valueOf(arr[i]));
            if (num == 0) {
                sb.setCharAt(i, '1');
                Node nodeOne = new Node(sb.toString(),
                        parentNode.depth + 1);

                if (pushNode(deadEndsSet, visitedNodes, nodeOne)) {
                    childNodes.add(nodeOne);
                    visitedNodes.add(nodeOne);
                }

                sb.setCharAt(i, '9');
                Node nodeTwo = new Node(sb.toString(),
                        parentNode.depth + 1);

                if (pushNode(deadEndsSet, visitedNodes, nodeTwo)) {
                    childNodes.add(nodeTwo);
                    visitedNodes.add(nodeTwo);
                }

            } else if (num == 9) {
                sb.setCharAt(i, '0');
                Node nodeOne = new Node(sb.toString(),
                        parentNode.depth + 1);

                if (pushNode(deadEndsSet, visitedNodes, nodeOne)) {
                    childNodes.add(nodeOne);
                    visitedNodes.add(nodeOne);
                }

                sb.setCharAt(i, '8');
                Node nodeTwo = new Node(sb.toString(),
                        parentNode.depth + 1);

                if (pushNode(deadEndsSet, visitedNodes, nodeTwo)) {
                    childNodes.add(nodeTwo);
                    visitedNodes.add(nodeTwo);
                }
            } else {
                sb.setCharAt(i, String.valueOf(num + 1).toCharArray()[0]);
                Node nodeOne = new Node(sb.toString(),
                        parentNode.depth + 1);

                if (pushNode(deadEndsSet, visitedNodes, nodeOne)) {
                    childNodes.add(nodeOne);
                    visitedNodes.add(nodeOne);
                }

                sb.setCharAt(i, String.valueOf(num - 1).toCharArray()[0]);
                Node nodeTwo = new Node(sb.toString(),
                        parentNode.depth + 1);

                if (pushNode(deadEndsSet, visitedNodes, nodeTwo)) {
                    childNodes.add(nodeTwo);
                    visitedNodes.add(nodeTwo);
                }
            }
        }
        return childNodes;
    }

    private boolean pushNode(Set<String> deadEndsSet, Set<Node> visitedNodes, Node node) {
        if (!deadEndsSet.contains(node.pattern) && !visitedNodes.contains(node)) {
            return true;
        }
        return false;
    }

    private Set<String> populateDeadEndsSet(String[] deadends) {
        Set<String> deadEndsSet = new HashSet<>();
        for (String deadend : deadends) {
            deadEndsSet.add(deadend);
        }
        return deadEndsSet;
    }

    static class Node {
        private String pattern;
        private int depth;

        public Node(String pattern, int depth) {
            this.pattern = pattern;
            this.depth = depth;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return Objects.equals(pattern, node.pattern);
        }

        @Override
        public int hashCode() {
            return Objects.hash(pattern);
        }
    }
}

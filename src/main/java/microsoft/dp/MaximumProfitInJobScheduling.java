package microsoft.dp;

import java.util.*;

/**
Problem : 1235
We have n jobs, where every job is scheduled to be done from startTime[i] to endTime[i], obtaining a profit of profit[i].

You're given the startTime, endTime and profit arrays, return the maximum profit you can take such that there are no two jobs in the subset with overlapping time range.

If you choose a job that ends at time X you will be able to start another job that starts at time X


Constraints:-

a.) 1 <= startTime.length == endTime.length == profit.length <= 5 * 10^4
b.) 1 <= startTime[i] < endTime[i] <= 10^9
c.) 1 <= profit[i] <= 10^4

23 / 35 testcases passed
Approach Used : DP

Time Complexity = O(N^2)
Space Complexity = O(N)
 * */

public class MaximumProfitInJobScheduling {

    private Set<Node> visitedNodes = new HashSet<>();
    private Map<Node, Integer> map = new HashMap<>();
    private PriorityQueue<Node> priorityQueue;
    private int profitObtained = 0;


    public static void main(String ar[]) {
        MaximumProfitInJobScheduling unit = new MaximumProfitInJobScheduling();
        int startTime[] = {1,2,3,3};
        int endTime[] = {3,4,5,6};
        int profit[] = {50,10,40,70};
        System.out.print("Maximum profit obtained is " + unit.jobScheduling(startTime, endTime, profit));
    }


    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        List<Node> nodes = new ArrayList<>();
        nodes = prepareNodes(startTime, endTime);
        processToComputeMaximumProfit(nodes, profit);
        return profitObtained;
    }

    private void processToComputeMaximumProfit(List<Node> nodes, int[] profit) {
        while (!priorityQueue.isEmpty()) {
            Node peekedNode = priorityQueue.peek();
            if (!visitedNodes.contains(peekedNode)) {
                process(peekedNode, nodes, profit);
            }
            priorityQueue.poll();
        }
    }

    private int process(Node parentNode, List<Node> nodes, int[] profit) {
        List<Node> children = fetchPossibleChildren(parentNode, nodes);
        if (!children.isEmpty()) {
            int maxProfitAcrossChildren = 0;
            for (Node child : children) {
                if (map.containsKey(child)) {
                    maxProfitAcrossChildren = map.get(child) < maxProfitAcrossChildren ? maxProfitAcrossChildren : map.get(child);
                } else {
                int profitForChild = process(child, nodes, profit);
                    maxProfitAcrossChildren = profitForChild < maxProfitAcrossChildren ? maxProfitAcrossChildren : profitForChild;
                }
            }
            map.put(parentNode, profit[parentNode.index] + maxProfitAcrossChildren);
        } else {
            map.put(parentNode, profit[parentNode.index]);
        }
        visitedNodes.add(parentNode);
        profitObtained = map.get(parentNode) > profitObtained ? map.get(parentNode) : profitObtained;
        return map.get(parentNode);
    }

    private List<Node> fetchPossibleChildren(Node parentNode, List<Node> nodes) {
        List<Node> children = new ArrayList<>();
        for (Node child : nodes) {
            if (child.startTime >= parentNode.endTime && child.index != parentNode.index) {
                children.add(child);
            }
        }
        return children;
    }

    private List<Node> prepareNodes(int[] startTime, int[] endTime) {
        List<Node> nodes = new ArrayList<>();
        priorityQueue = new PriorityQueue<>(new NodeComparator());

        for (int i = 0; i < startTime.length; i++) {
            Node node = new Node(startTime[i], endTime[i], i);
            nodes.add(node);
            priorityQueue.add(node);
        }
        return nodes;
    }


    static class Node {
        private int startTime;
        private int endTime;
        private int index;


        public Node(int startTime, int endTime, int index) {
            this.startTime = startTime;
            this.endTime = endTime;
            this.index = index;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return index == node.index;
        }

        @Override
        public int hashCode() {
            return Objects.hash(index);
        }
    }

    static class NodeComparator implements Comparator<Node> {

        @Override
        public int compare(Node o1, Node o2) {
            return o1.startTime < o2.startTime ? -1 : o1.startTime > o2.startTime ? 1 : 0;
        }
    }
}

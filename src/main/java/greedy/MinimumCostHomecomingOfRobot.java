package greedy;

import java.util.*;

public class MinimumCostHomecomingOfRobot {

    public static void main(String ar[]) {
        MinimumCostHomecomingOfRobot unit = new MinimumCostHomecomingOfRobot();
        int startPos[] = {1, 0};
        int homePos[] = {2, 3};
        int[] rowCosts = {5, 4, 3};
        int[] colCosts = {8, 2, 6, 7};

        /*System.out.print("Minimum cost to reach destination from home when robot can move in all four directions is : " +
                unit.minCost(startPos, homePos, rowCosts, colCosts));*/

        System.out.print("Minimum cost to reach destination from home when robot can move in all four directions is : " +
                unit.minCost(startPos, homePos, rowCosts, colCosts));
    }

    public int minCostOne(int[] startPos, int[] homePos, int[] rowCosts, int[] colCosts) {
        if (startPos[0] == homePos[0] && startPos[1] == homePos[1]) {
            return 0;
        }
        int minCost = 0;
        int startRowIndex = startPos[0];
        int startColumnIndex = startPos[1];

        int homeRowIndex = homePos[0];
        int homeColumnIndex = homePos[1];

        if (startRowIndex <= homeRowIndex) {
            minCost = updateMinCostOne(minCost, startRowIndex, homeRowIndex, rowCosts);
        } else {
            minCost = updateMinCostTwo(minCost, startRowIndex, homeRowIndex, rowCosts);
        }

        if (startColumnIndex <= homeColumnIndex) {
            minCost = updateMinCostOne(minCost, startColumnIndex, homeColumnIndex, colCosts);
        } else {
            minCost = updateMinCostTwo(minCost, startColumnIndex, homeColumnIndex, colCosts);
        }

        return minCost;
    }

    private int updateMinCostOne(int minCost, int startIndex, int endIndex, int[] costs) {
        for (int i = startIndex + 1; i <= endIndex; i++) {
            minCost += costs[i];
        }
        return minCost;
    }

    private int updateMinCostTwo(int minCost, int startIndex, int endIndex, int[] costs) {
        for (int i = startIndex - 1; i >= endIndex; i--) {
            minCost += costs[i];
        }
        return minCost;
    }

    /**
     *
     *
     * */

    public int minCost(int[] startPos, int[] homePos, int[] rowCosts, int[] colCosts) {
        if (startPos[0] == homePos[0] && startPos[1] == homePos[1]) {
          return 0;
        }
        int minCost = Integer.MAX_VALUE;
        Set<Node> exploredNodes = new HashSet<>();
        Queue<Node> queue = new LinkedList<>();
        Node startPosition = new Node(startPos[0], startPos[1], 0);

        exploredNodes.add(startPosition);
        queue.add(startPosition);

        while (!queue.isEmpty()) {
            Node peekedNode = queue.peek();
            if (peekedNode.rowNum == homePos[0] && peekedNode.columnNum == homePos[1]) {
                minCost = peekedNode.costIncurred < minCost ? peekedNode.costIncurred : minCost;
            }
            List<Node> childNodes = fetchChildNodes(peekedNode, exploredNodes, rowCosts, colCosts);
            for (Node childNode : childNodes) {
                queue.add(childNode);
            }
            exploredNodes.add(peekedNode);
            queue.poll();
        }
        return minCost;
    }

    private List<Node> fetchChildNodes(Node peekedNode, Set<Node> exploredNodes, int[] rowCosts, int[] colCosts) {
        List<Node> children = new ArrayList<>();

        if (peekedNode.rowNum + 1 < rowCosts.length) {
            Node node = new Node(peekedNode.rowNum + 1, peekedNode.columnNum, peekedNode.costIncurred + rowCosts[peekedNode.rowNum + 1]);
            addNodeToChildList(node, exploredNodes, children);
        }

        if (peekedNode.rowNum - 1 >= 0) {
            Node node = new Node(peekedNode.rowNum - 1, peekedNode.columnNum,
                    peekedNode.costIncurred + rowCosts[peekedNode.rowNum - 1]);
            addNodeToChildList(node, exploredNodes, children);
        }

        if (peekedNode.columnNum + 1 < colCosts.length) {
            Node node = new Node(peekedNode.rowNum, peekedNode.columnNum + 1, peekedNode.costIncurred + colCosts[peekedNode.columnNum + 1]);
            addNodeToChildList(node, exploredNodes, children);
        }

        if (peekedNode.columnNum - 1 >= 0) {
            Node node = new Node(peekedNode.rowNum, peekedNode.columnNum - 1, peekedNode.costIncurred + colCosts[peekedNode.columnNum - 1]);
            addNodeToChildList(node, exploredNodes, children);
        }
        return children;
    }

    private void addNodeToChildList(Node node, Set<Node> exploredNodes, List<Node> children) {
        if (!exploredNodes.contains(node)) {
            children.add(node);
        }
    }

    static class Node {
        private int rowNum;
        private int columnNum;
        private int costIncurred;

        public Node(int rowNum, int columnNum, int costIncurred) {
            this.rowNum = rowNum;
            this.columnNum = columnNum;
            this.costIncurred = costIncurred;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return rowNum == node.rowNum && columnNum == node.columnNum;
        }

        @Override
        public int hashCode() {
            return Objects.hash(rowNum, columnNum);
        }
    }

}

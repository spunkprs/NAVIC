package greedy;

import java.util.*;


/**
 There is an m x n grid, where (0, 0) is the top-left cell and (m - 1, n - 1) is the bottom-right cell. You are given an integer array startPos where startPos = [startrow, startcol] indicates that initially, a robot is at the cell (startrow, startcol). You are also given an integer array homePos where homePos = [homerow, homecol] indicates that its home is at the cell (homerow, homecol).

 The robot needs to go to its home. It can move one cell in four directions: left, right, up, or down, and it can not move outside the boundary. Every move incurs some cost. You are further given two 0-indexed integer arrays: rowCosts of length m and colCosts of length n.

 If the robot moves up or down into a cell whose row is r, then this move costs rowCosts[r].
 If the robot moves left or right into a cell whose column is c, then this move costs colCosts[c].
 Return the minimum total cost for this robot to return home.

 Constraints:

 1.) m == rowCosts.length
 2.) n == colCosts.length
 3.) 1 <= m, n <= pow(10,5)
 4.) 0 <= rowCosts[r], colCosts[c] <= pow(10,4)
 5.) startPos.length == 2
 6.) homePos.length == 2
 7.) 0 <= startrow, homerow < m
 8.) 0 <= startcol, homecol < n

 Source : LeetCode

 Time Complexity = O(m + n)
 Space Complexity = O(1)
 * */

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

    /**
     *
     * Time Complexity for this approach is O(m + n)
     * Space Complexity = O(1)
     * */

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
     * Time Complexity for this approach is O(m * n)
     * Space Complexity = Maximum nodes at a level in the tree {Using BFS}
     *
     * First thought process was to make use of BFS because robot can move in all four directions but way more optimal solution that's mentioned above
     * always takes the shortest path of just matching source row to destination row && source column to destination column that's why doesn't
     * unnecessarily explore other nodes/indexes
     *
     * Issue would arise when cost against each index would have been given in that case longest path may give minimum cost and because of the same reason
     * we would not be able to make use of BFS
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

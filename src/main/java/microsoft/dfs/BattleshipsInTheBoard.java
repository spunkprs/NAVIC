package microsoft.dfs;

import java.util.*;

/**
Problem : 419
Link : https://leetcode.com/problems/battleships-in-a-board/description/?envType=problem-list-v2&envId=depth-first-search

Given an m x n matrix board where each cell is a battleship 'X' or empty '.', return the number of the battleships on board.

Battleships can only be placed horizontally or vertically on board. In other words, they can only be made of the shape
1 x k (1 row, k columns) or k x 1 (k rows, 1 column), where k can be of any size. At least one horizontal or vertical cell
separates between two battleships (i.e., there are no adjacent battleships).

Constraints:-

a.) m == board.length
b.) n == board[i].length
c.) 1 <= m, n <= 200
d.) board[i][j] is either '.' or 'X'.

Level : Medium

Time Complexity : O(m * n)
Space Complexity : O(m * n), will be lesser than exact O(m*n) as per the conditions provided in the problem but still it will be on the similar lines
 * */

public class BattleshipsInTheBoard {

    public int countBattleships(char[][] board) {
        int battleshipsCount = 0;
        Set<Node> visitedNodes = new HashSet<>();

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                Node node = new Node(i, j);
                node.value = board[i][j];
                if (node.value == 'X' && !visitedNodes.contains(node)) {
                    battleshipsCount++;
                    processToCountBattleShips(node, board, visitedNodes);
                }
            }
        }
        return battleshipsCount;
    }

    private void processToCountBattleShips(Node parentNode, char[][] board, Set<Node> visitedNodes) {
        visitedNodes.add(parentNode);
        for (Node childNode : fetchChildNodes(parentNode, visitedNodes, board)) {
            processToCountBattleShips(childNode, board, visitedNodes);
        }
    }

    private List<Node> fetchChildNodes(Node parentNode, Set<Node> visitedNodes, char[][] board) {
        List<Node> childNodes = new ArrayList<>();
        if (parentNode.xIndex + 1 < board.length && board[parentNode.xIndex + 1][parentNode.yIndex] == 'X') {
            Node childNode = new Node(parentNode.xIndex + 1, parentNode.yIndex);
            if (!visitedNodes.contains(childNode)) {
                childNodes.add(childNode);
            }
        }

        if (parentNode.xIndex - 1 >= 0 && board[parentNode.xIndex - 1][parentNode.yIndex] == 'X') {
            Node childNode = new Node(parentNode.xIndex - 1, parentNode.yIndex);
            if (!visitedNodes.contains(childNode)) {
                childNodes.add(childNode);
            }
        }

        if (parentNode.yIndex + 1 < board[0].length && board[parentNode.xIndex][parentNode.yIndex + 1] == 'X') {
            Node childNode = new Node(parentNode.xIndex, parentNode.yIndex + 1);
            if (!visitedNodes.contains(childNode)) {
                childNodes.add(childNode);
            }
        }

        if (parentNode.yIndex - 1 >= 0 && board[parentNode.xIndex][parentNode.yIndex - 1] == 'X') {
            Node childNode = new Node(parentNode.xIndex, parentNode.yIndex - 1);
            if (!visitedNodes.contains(childNode)) {
                childNodes.add(childNode);
            }
        }

        return childNodes;
    }

    class Node {
        private int xIndex;
        private int yIndex;
        private char value;

        public Node(int xIndex, int yIndex) {
            this.xIndex = xIndex;
            this.yIndex = yIndex;
        }

        public int hashCode() {
            return Objects.hash(xIndex, yIndex);
        }

        public boolean equals(Object obj) {
            if (obj != null) {
                Node object = (Node) obj;
                return this.xIndex == object.xIndex && this.yIndex == object.yIndex;
            }
            return false;
        }
    }
}

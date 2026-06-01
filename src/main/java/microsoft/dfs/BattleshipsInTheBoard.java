package microsoft.dfs;

import java.util.*;

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

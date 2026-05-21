package microsoft.bfs;

import java.util.*;
import java.util.stream.Collectors;

public class WordSearchTwo {

    public static void main(String ar[]) {
        WordSearchTwo unit = new WordSearchTwo();

        char [][]board = {{'o','a','a','n'},{'e','t','a','e'},{'i','h','k','r'},{'i','f','l','v'}};
        String words[] = {"oath","pea","eat","rain"};

        //char [][] board = {{'a'}};
        //String words[] = {"a"};

        List<String> result = unit.findWords(board, words);

        for (String word : result) {
            System.out.println(word);
        }

    }

    private Set<String> resultantWords = new HashSet<>();

    public List<String> findWords(char[][] board, String[] words) {
        for (String word: words) {
            processToFindWords(board, word);
        }
        return resultantWords.stream().collect(Collectors.toList());
    }

    private void processToFindWords(char[][] board, String word) {
        char arr[] = word.toCharArray();
        int currentIndex = 0;
        Set<Node> visitedNodes = new HashSet<>();

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == arr[currentIndex]) {
                    if (word.length() > 1) {
                        Node startNode = new Node(currentIndex, i, j);
                        visitedNodes.add(startNode);
                        process(startNode, visitedNodes, board, arr, word);
                    } else {
                        resultantWords.add(word);
                    }
                }
            }
        }
    }

    private void process(Node node, Set<Node> visitedNodes, char[][] board, char[] arr, String word) {
        Queue<Node> queue = new LinkedList<>();

        queue.add(node);

        boolean flag = false;
        Node parentNode = queue.peek();

        while (!queue.isEmpty() && parentNode.index < arr.length - 1 && !flag) {
            for (Node child : fetchChildren(parentNode, board, visitedNodes, parentNode.index + 1, arr)) {
                if (child.index == arr.length - 1) {
                    resultantWords.add(word);
                    flag = true;
                    break;
                }
                queue.add(child);
            }
            if (!flag) {
                queue.poll();
                parentNode = queue.peek();
            }
        }
    }

    private List<Node> fetchChildren(Node node, char[][] board, Set<Node> visitedNodes, int nextIndex, char[] arr) {
        List<Node> children = new ArrayList<>();

        if (node.xIndex + 1 < board.length && arr[nextIndex] == board[node.xIndex + 1][node.yIndex]) {
            Node childNode = new Node(nextIndex, node.xIndex + 1, node.yIndex);
            if (!visitedNodes.contains(childNode)) {
                children.add(childNode);
                visitedNodes.add(childNode);
            }
        }

        if (node.yIndex + 1 < board[0].length && arr[nextIndex] == board[node.xIndex][node.yIndex + 1]) {
            Node childNode = new Node(nextIndex, node.xIndex, node.yIndex + 1);
            if (!visitedNodes.contains(childNode)) {
                children.add(childNode);
                visitedNodes.add(childNode);
            }
        }

        if (node.xIndex - 1 >= 0 && arr[nextIndex] == board[node.xIndex - 1][node.yIndex]) {
            Node childNode = new Node(nextIndex, node.xIndex - 1, node.yIndex);
            if (!visitedNodes.contains(childNode)) {
                children.add(childNode);
                visitedNodes.add(childNode);
            }
        }

        if (node.yIndex - 1 >= 0 && arr[nextIndex] == board[node.xIndex][node.yIndex - 1]) {
            Node childNode = new Node(nextIndex, node.xIndex, node.yIndex - 1);
            if (!visitedNodes.contains(childNode)) {
                children.add(childNode);
                visitedNodes.add(childNode);
            }
        }
        return children;
    }

    static class Node {
        int index;
        int xIndex;
        int yIndex;

        public Node(int index, int xIndex, int yIndex) {
            this.index = index;
            this.xIndex = xIndex;
            this.yIndex = yIndex;
        }

        public int hashCode() {
            return Objects.hash(xIndex, yIndex);
        }

        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            } else {
                Node n = (Node)obj;
                return this.xIndex == n.xIndex && this.yIndex == n.yIndex;
            }
        }
    }
}

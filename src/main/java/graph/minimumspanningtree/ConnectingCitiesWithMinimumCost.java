package graph.minimumspanningtree;


import java.util.*;

/**
 There are n cities labeled from 1 to n. You are given the integer n and an array connections
 where connections[i] = [xi, yi, costi] indicates that the cost of connecting city xi and city yi (bidirectional connection) is costi.

 Return the minimum cost to connect all the n cities such that there is at least one path between each pair of cities.
 If it is impossible to connect all the n cities, return -1,

 The cost is the sum of the connections' costs used.

 Constraints:

 1.) 1 <= n <= pow(10,4)
 2.) 1 <= connections.length <= pow(10,4)
 3.) connections[i].length == 3
 4.) 1 <= xi, yi <= n
 5.) xi != yi
 6.) 0 <= costi <= pow(10,5)

 Source : Leetcode


 This is the classic problem of finding minimum spanning tree , will be making use of Prim's algorithm to find the same

 * */

public class ConnectingCitiesWithMinimumCost {

    public static void main(String ar[]) {
        ConnectingCitiesWithMinimumCost unit = new ConnectingCitiesWithMinimumCost();

        int numberOfCities = 5;
        int connections[][] = {{1, 2, 3}, {1, 3, 2}, {3, 4, 1}, {3, 5, 5}, {4, 5, 1}, {5, 2, 1}};

        System.out.println("Minimum amount to connect all the cities is " + unit.minimumCost(numberOfCities, connections));
    }


    public int minimumCost(int n, int[][] connections) {
        int matrix[][] = prepareAdjacencyMatrix(connections, n);
        PriorityQueue<Node> minHeap = new PriorityQueue<>(new NodeComparator());
        Set<Integer> visitedNodes = new HashSet<>();
        List<Node> resultList = new ArrayList<>();

        Node parentNode = new Node(0, 0, -1);
        minHeap.add(parentNode);

        while (!minHeap.isEmpty()) {
            Node topNode = minHeap.poll();
            if (!visitedNodes.contains(topNode.node)) {
                addChildren(topNode, matrix, visitedNodes, minHeap);
            }
            if (!visitedNodes.contains(topNode.node)) {
                visitedNodes.add(topNode.node);
                if (topNode.parentNode != -1) {
                    resultList.add(topNode);
                }
            }
        }
        return computeMinDistance(resultList, n);
    }

    private int computeMinDistance(List<Node> resultList, int n) {
        int result = 0;
        if (resultList.size() == n - 1) {
            for (Node node : resultList) {
                result += node.distance;
            }
            return result;
        }
        return -1;
    }

    private void addChildren(Node topNode, int[][] matrix, Set<Integer> visitedNodes, PriorityQueue<Node> minHeap) {
        for (int j = 0; j < matrix[topNode.node].length; j++) {
            if (matrix[topNode.node][j] != -1 && !visitedNodes.contains(j)) {
                minHeap.add(new Node(matrix[topNode.node][j], j, topNode.node));
            }
        }
    }

    private int[][] prepareAdjacencyMatrix(int[][] connections, int n) {
        int matrix[][] = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n ; j++) {
                matrix[i][j] = -1;
            }
        }

        for (int i = 0; i < connections.length; i++) {
            int x = connections[i][0];
            int y = connections[i][1];
            int cost = connections[i][2];

            matrix[x - 1][y - 1] = cost;
            matrix[y - 1][x - 1] = cost;
        }
        return matrix;
    }


    static class Node {
        int distance;
        int node;
        int parentNode;


        public Node(int distance, int node, int parentNode) {
            this.distance = distance;
            this.node = node;
            this.parentNode = parentNode;
        }
    }

    class NodeComparator implements Comparator<Node> {

        @Override
        public int compare(Node o1, Node o2) {
            return o1.distance < o2.distance ? -1 : o1.distance > o2.distance ? 1 : 0;
        }
    }
}

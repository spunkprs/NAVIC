package graph.minimumspanningtree;


import java.util.Comparator;
import java.util.PriorityQueue;

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
        return -1;


    }

    private int[][] prepareAdjacencyMatrix(int[][] connections, int n) {
        int matrix[][] = new int[n][n];
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

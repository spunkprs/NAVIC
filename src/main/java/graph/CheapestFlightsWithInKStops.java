package graph;


import java.util.*;

/**
 There are n cities connected by some number of flights. You are given an array flights
 where flights[i] = [from(i), to(i), price(i)] indicates that there is a flight from city from(i) to city to(i) with cost price(i).

 You are also given three integers src, dst, and k, return the cheapest price
 from src to dst with at most k stops. If there is no such route, return -1.

 Constraints:

 1.) 1 <= n <= 100
 2.) 0 <= flights.length <= (n * (n - 1) / 2)
 3.) flights[i].length == 3
 4.) 0 <= from(i), to(i) < n
 5.) from(i) != to(i)
 6.) 1 <= price(i) <= pow(10,4)
 7.) There will not be any multiple flights between two cities.
 8.) 0 <= src, dst, k < n
 9.) src != dst

 Source : LeetCode

 * */

public class CheapestFlightsWithInKStops {

    public static void main(String ar[]) {
        CheapestFlightsWithInKStops unit = new CheapestFlightsWithInKStops();

        int flights[][] = {{0,1,100},{1,2,100},{2,0,100},{1,3,600},{2,3,200}};
        int numberOfStopsAllowed = 1;
        int source = 0;
        int destination = 3;
        int numOfNodes = 4;

        System.out.println("Minimum cost to move from source " + source + " to destination "
                + destination + " with stops count " + numberOfStopsAllowed + " is " + unit.findCheapestPrice(numOfNodes, flights, source, destination, numberOfStopsAllowed));
    }

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        if (k < 0) {
            return -1;
        }
        int matrix[][] = prepareAdjacencyMatrix(n, flights);
        return processMatrix(matrix, src, dst, k);
    }

    private int processMatrix(int[][] matrix, int src, int dst, int k) {
        int result = Integer.MAX_VALUE;
        Node parentNode = new Node(-1, src, 0, 0);
        Set<Node> exploredIndexes =  new HashSet<>();

        Queue<Node> queue = new LinkedList<>();
        queue.add(parentNode);
        exploredIndexes.add(parentNode);

        while (!queue.isEmpty()) {
            if (queue.peek().level - 1 <= k && queue.peek().index == dst) {
                result = updateResult(result, queue.peek().value);
            }
            pushChildrenToQueue(queue.peek(), matrix, queue, exploredIndexes, k);
            queue.poll();
        }
        return result == Integer.MAX_VALUE ? -1 : result;
    }

    private int updateResult(int result, int value) {
        return result < value ? result : value;
    }

    private void pushChildrenToQueue(Node node, int[][] matrix, Queue<Node> queue, Set<Node> exploredIndexes, int k) {
        for (int j = 0; j < matrix[node.index].length; j++) {
            if (matrix[node.index][j] != 0 && node.level <= k) {
                Node childNode = new Node(node.index, j, node.level + 1, node.value + matrix[node.index][j]);
                if (!exploredIndexes.contains(childNode)) {
                    queue.add(childNode);
                    exploredIndexes.add(childNode);
                }
            }
        }
    }

    private int[][] prepareAdjacencyMatrix(int numOfNodes, int[][] flights) {
        int matrix[][] = new int[numOfNodes][numOfNodes];

        for (int i = 0; i < flights.length; i++) {
            int source = flights[i][0];
            int destination = flights[i][1];
            int price = flights[i][2];
            matrix[source][destination] = price;
        }
        return matrix;
    }


    static class Node {
        private int parentIndex;
        private int index;
        private int level;
        private int value;

        public Node(int parentIndex, int index, int level, int value) {
            this.parentIndex = parentIndex;
            this.index = index;
            this.level = level;
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return index == node.index && level == node.level && parentIndex == node.parentIndex;
        }

        @Override
        public int hashCode() {
            return Objects.hash(index, level, parentIndex);
        }
    }
}

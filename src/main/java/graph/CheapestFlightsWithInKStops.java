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

 Will bank on Djisktra's algorithm to solve this problem !!

 * */

public class CheapestFlightsWithInKStops {

    public static void main(String ar[]) {
        CheapestFlightsWithInKStops unit = new CheapestFlightsWithInKStops();

        //int flights[][] = {{0,1,100},{1,2,100},{2,0,100},{1,3,600},{2,3,200}};

        int flights[][] = {{0,1,5},{1,2,5},{0,3,2},{3,1,2},{1,4,1},{4,2,1}};
        int numberOfStopsAllowed = 2;
        int source = 0;
        int destination = 2;
        int numOfNodes = 5;

        /*System.out.println("Minimum cost to move from source " + source + " to destination "
                + destination + " with stops count " + numberOfStopsAllowed + " is " + unit.findCheapestPrice(numOfNodes, flights, source, destination, numberOfStopsAllowed));*/

        System.out.println("Minimum cost to move from source " + source + " to destination "
                + destination + " with stops count " + numberOfStopsAllowed + " is " + unit.findCheapestPriceOne(numOfNodes, flights, source, destination, numberOfStopsAllowed));
    }

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        if (k < 0) {
            return -1;
        }
        int matrix[][] = prepareAdjacencyMatrix(n, flights);
        return processMatrix(matrix, src, dst, k);
    }

    public int findCheapestPriceOne(int n, int[][] flights, int src, int dst, int k) {
        if (k < 0) {
            return -1;
        }
        Map<Integer, List<Information>> adjacencyList = prepareAdjacencyList(n, flights);
        return processToFindCheapestPrice(adjacencyList, src, dst, k, n);
    }

    private int processToFindCheapestPrice(Map<Integer, List<Information>> adjacencyList,
                                            int src, int dst, int k, int numOfCities) {

        int distances[] = new int[numOfCities];
        for (int i = 0; i < numOfCities; i++) {
            distances[i] = -1;
        }

        PriorityQueue<NodeOne> minHeap = new PriorityQueue<>(new NodeOneComparator());
        NodeOne sourceNode = new NodeOne(0, 0, src);

        minHeap.add(sourceNode);
        distances[sourceNode.nodeValue] = 0;

        while (!minHeap.isEmpty()) {
            NodeOne topNode = minHeap.poll();
            for (NodeOne childNode : fetchChildren(topNode, distances, adjacencyList, k)) {
                minHeap.add(childNode);
            }
        }
        return distances[dst];
    }

    private List<NodeOne> fetchChildren(NodeOne topNode, int distances[],
                                        Map<Integer, List<Information>> adjacencyList, int maxStopsAllowed) {
        List<NodeOne> childNodes = new ArrayList<>();

        if (adjacencyList.containsKey(topNode.nodeValue)) {
            for (Information child : adjacencyList.get(topNode.nodeValue)) {
                int distanceFromSource = distances[child.nodeValue];
                NodeOne childNode = new NodeOne(topNode.distanceFromSource + child.distanceFromSource,
                        topNode.level + 1, child.nodeValue);
                if (topNode.level + 1 <= maxStopsAllowed + 1) {
                    if (distanceFromSource == -1) {
                        distances[child.nodeValue] = childNode.distanceFromSource;
                        childNodes.add(childNode);
                    } else {
                        if (childNode.distanceFromSource < distanceFromSource) {
                            childNodes.add(childNode);
                            distances[childNode.nodeValue] = childNode.distanceFromSource;
                        }
                    }
                }
            }
        }
        return childNodes;
    }

    static class NodeOneComparator implements Comparator<NodeOne> {

        @Override
        public int compare(NodeOne o1, NodeOne o2) {
            return o1.distanceFromSource < o2.distanceFromSource ? -1 : o1.distanceFromSource > o2.distanceFromSource ? 1 : 0;
        }
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

    private Map<Integer, List<Information>> prepareAdjacencyList(int numOfNodes, int[][] flights) {
        Map<Integer, List<Information>> mapping = new HashMap<>();

        for (int i = 0; i < flights.length; i++) {
            int source = flights[i][0];
            int destination = flights[i][1];
            int price = flights[i][2];

            if (!mapping.containsKey(source)) {
                List<Information> list = new ArrayList<>();
                list.add(new Information(destination, price));
                mapping.put(source, list);
            } else {
                mapping.get(source).add(new Information(destination, price));
            }
        }
        return mapping;
    }

    static class Information {
        private int nodeValue;
        private int distanceFromSource;

        public Information(int nodeValue, int distanceFromSource) {
            this.nodeValue = nodeValue;
            this.distanceFromSource = distanceFromSource;
        }
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

    static class NodeOne {
        private int distanceFromSource;
        private int nodeValue;
        private int level;

        public NodeOne(int distanceFromSource, int level, int nodeValue) {
            this.distanceFromSource = distanceFromSource;
            this.nodeValue = nodeValue;
            this.level = level;
        }
    }
}

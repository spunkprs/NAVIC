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

 ------> Used Djisktra's algorithm to solve this problem with a tweak of considering numberOfStopsAllowed as another state variable <---------

 Time Complexity = O(E * V) {For building adjacency list} + O(E * K log(E * K)) {For building min heap} where E are number of edges,
 V is number of vertices or nodes && K is allowed number of stops

 Space Complexity = O(E * V) + O(E * K)

 Enjoyed solving this problem !!

 * */

public class CheapestFlightsWithInKStops {

    public static void main(String ar[]) {
        CheapestFlightsWithInKStops unit = new CheapestFlightsWithInKStops();

        int flights[][] = {{1,2,10},{2,0,7},{1,3,8},{4,0,10},{3,4,2},{4,2,10},{0,3,3},{3,1,6},{2,4,5}};

        //int flights[][] = {{0,1,100},{1,2,100},{0,2,500}};
        int numberOfStopsAllowed = 1;
        int source = 0;
        int destination = 4;
        int numOfNodes = 5;

        System.out.println("Minimum cost to move from source " + source + " to destination "
                + destination + " with stops count " + numberOfStopsAllowed + " is " + unit.findCheapestPriceOne(numOfNodes, flights, source, destination, numberOfStopsAllowed));
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

        Map<Integer, List<Integer>> map = new HashMap<>();
        prePopulateMap(map, k, src, numOfCities);

        PriorityQueue<NodeOne> minHeap = new PriorityQueue<>(new NodeOneComparator());
        NodeOne sourceNode = new NodeOne(0, -1, src);

        minHeap.add(sourceNode);

        while (!minHeap.isEmpty()) {
            NodeOne topNode = minHeap.poll();
            for (NodeOne childNode : fetchChildren(topNode, adjacencyList, k, map)) {
                minHeap.add(childNode);
            }
        }
        return fetchResult(map, dst);
    }

    private int fetchResult(Map<Integer, List<Integer>> map, int dst) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (Integer k : map.keySet()) {
            minHeap.add(map.get(k).get(dst));
        }
        return minHeap.peek() == Integer.MAX_VALUE ? -1 : minHeap.peek();
    }

    private void prePopulateMap(Map<Integer, List<Integer>> map, int numberOfStops, int src, int numOfCities) {
        for (int i = 0; i <= numberOfStops; i++) {
            List<Integer> distances = new ArrayList<>();
            for (int j = 0; j < numOfCities; j++) {
                if (j == src) {
                    distances.add(0);
                } else {
                    distances.add(Integer.MAX_VALUE);
                }
            }
            map.put(i, distances);
        }
    }

    private List<NodeOne> fetchChildren(NodeOne topNode, Map<Integer, List<Information>> adjacencyList, int maxStopsAllowed,
                                        Map<Integer, List<Integer>> map) {
        List<NodeOne> childNodes = new ArrayList<>();

        if (adjacencyList.containsKey(topNode.nodeValue)) {
            for (Information child : adjacencyList.get(topNode.nodeValue)) {
                if (topNode.level + 1 <= maxStopsAllowed) {
                    List<Integer> distanceList = map.get(topNode.level + 1);
                    int distanceFromSourceUpdated = distanceList.get(child.nodeValue);
                    if (topNode.distanceFromSource + child.distanceFromSource < distanceFromSourceUpdated) {
                        NodeOne childNode = new NodeOne(topNode.distanceFromSource + child.distanceFromSource,
                                topNode.level + 1, child.nodeValue);
                        distanceList.set(child.nodeValue, topNode.distanceFromSource + child.distanceFromSource);
                        childNodes.add(childNode);
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

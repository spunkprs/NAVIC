package graph;

import java.util.*;

/**
 You are given an array routes representing bus routes where routes[i] is a bus route that the ith bus repeats forever.

 For example, if routes[0] = [1, 5, 7], this means that the 0th bus travels in the
 sequence 1 -> 5 -> 7 -> 1 -> 5 -> 7 -> 1 -> ... forever.
 You will start at the bus stop source (You are not on any bus initially),
 and you want to go to the bus stop target. You can travel between bus stops by buses only.

 Return the least number of buses you must take to travel from source to target. Return -1 if it is not possible.


 Constraints:

 1.) 1 <= routes.length <= 500.
 2.) 1 <= routes[i].length <= pow(10,5)
 3.) All the values of routes[i] are unique.
 4.) sum(routes[i].length) <= pow(10,5)
 5.) 0 <= routes[i][j] < pow(10,6)
 6.) 0 <= source, target < pow(10,6)

 Source : LeetCode


 Time Complexity : Will update it tomorrow(2025-09-10)
 Space Complexity : Will update it tomorrow(2025-09-10)

 * */

public class BusRoutes {

    public static void main(String ar[]) {
        BusRoutes unit = new BusRoutes();

        int routes[][] = {{1, 2, 7}, {3, 6, 7}};
        //int routes[][] = {{2}, {2, 8}};
        int source = 1;
        int destination = 6;

        System.out.println("Minimum buses required to travel from source " + source + " to destination " + destination + " is " +
                unit.numBusesToDestination(routes, source, destination));
    }

    public int numBusesToDestination(int[][] routes, int source, int target) {
        Map<Integer, List<Integer>> map = prepareMap(routes);
        return processToFetchMinimumBusesRequired(map, source, target, routes);
    }

    private int processToFetchMinimumBusesRequired(Map<Integer, List<Integer>> map, int source, int target, int[][] routes) {
        List<Integer> routeIndexesForSource = map.get(source);
        Set<Node> exploredNodes = new HashSet<>();
        int result = Integer.MAX_VALUE;

        Node sourceNode = new Node(source, routeIndexesForSource.get(0));
        sourceNode.busCount = 1;

        Queue<Node> queue = new LinkedList<>();
        queue.add(sourceNode);
        List<Integer> indexList = new ArrayList<>();

        while (!queue.isEmpty()) {
            indexList = map.get(queue.peek().value);

            for (int i = 0; i < indexList.size(); i++) {
                Node node = new Node(queue.peek().value, indexList.get(i));
                node.busCount = queue.peek().routeIndex == indexList.get(i) ? queue.peek().busCount : queue.peek().busCount + 1;
                if (node.value == target) {
                    result = updateResult(result, node.busCount);
                }
                if (!exploredNodes.contains(node)) {
                exploredNodes.add(node);
                if (!queue.peek().equals(node)) {
                    queue.add(node);
                }
                addRemainingNodes(node.routeIndex, routes,  exploredNodes, queue, node);
            }
            }
            queue.poll();
        }
        return result == Integer.MAX_VALUE ? -1 : result;
    }

    private int updateResult(int result, int busCount) {
        return result < busCount ? result : busCount;
    }

    private void addRemainingNodes(int routeIndex, int[][] routes, Set<Node> exploredNodes, Queue<Node> queue, Node sourceNode) {
        for (int j = 0; j < routes[routeIndex].length; j++) {
            Node node = new Node(routes[routeIndex][j], routeIndex);
            node.busCount = sourceNode.busCount;
            if (!exploredNodes.contains(node)) {
                queue.add(node);
                exploredNodes.add(node);
            }
        }
    }

    private Map<Integer, List<Integer>> prepareMap(int[][] routes) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < routes.length; i++) {
            for (int j = 0; j < routes[i].length; j++) {
                if (!map.containsKey(routes[i][j])) {
                    List<Integer> routeIndexes = new ArrayList<>();
                    routeIndexes.add(i);
                    map.put(routes[i][j], routeIndexes);
                } else {
                    map.get(routes[i][j]).add(i);
                }
            }
        }
        return map;
    }

    static class Node {
        private int value;
        private int routeIndex;
        private int busCount;

        public Node(int value, int routeIndex) {
            this.value = value;
            this.routeIndex = routeIndex;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return value == node.value && routeIndex == node.routeIndex;
        }

        @Override
        public int hashCode() {
            return Objects.hash(value, routeIndex);
        }
    }
}

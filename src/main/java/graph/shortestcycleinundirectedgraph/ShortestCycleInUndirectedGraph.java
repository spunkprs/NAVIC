package graph.shortestcycleinundirectedgraph;

import java.util.*;

/**
This question aims at finding shortest cycle in the graph using BFS approach
Time Complexity = O(V*E)
Space Complexity = 2 * O(E) + 2 * O(V)
 */
public class ShortestCycleInUndirectedGraph {

    private int lengthOfShortestCycle = Integer.MAX_VALUE;

    public int findShortestCycle(int n, int[][] edges) {
        Map<Integer, List<Integer>> adjacencyListMap = new HashMap<>();
        prepareAdjacencyList(adjacencyListMap, edges);
        processToFindShortestCycle(adjacencyListMap, n);
        return lengthOfShortestCycle == Integer.MAX_VALUE ? -1 : lengthOfShortestCycle;
    }

    public static void main(String ar []) {
        ShortestCycleInUndirectedGraph unit = new ShortestCycleInUndirectedGraph();
        /*int edges[][] = {{0, 1}, {1, 2}, {2, 0}, {3, 4}, {4, 5}, {5, 6}, {6, 3}};
        System.out.println("Length of smallest cycle is " + unit.findShortestCycle(7, edges));*/

        int edges[][] = {{0, 1}, {0, 2}};
        System.out.println("Length of smallest cycle is " + unit.findShortestCycle(4, edges));
    }

    private void processToFindShortestCycle(Map<Integer, List<Integer>> adjacencyListMap, int numberOfVertices) {
        for (int i = 0; i < numberOfVertices; i++) {
            int distanceArray[] = new int[numberOfVertices];
            Arrays.fill(distanceArray, Integer.MAX_VALUE);
            distanceArray[i] = 0;
            process(adjacencyListMap, i, distanceArray);
        }
    }

    private void process(Map<Integer, List<Integer>> adjacencyListMap, int startingNode, int[] distanceArray) {
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> visitedNodes = new HashSet<>();
        Set<Integer> queueNodes = new HashSet<>();
        queue.add(startingNode);
        queueNodes.add(startingNode);
        while (!queue.isEmpty()) {
            if (adjacencyListMap.containsKey(queue.peek())) {
                for (Integer childNode : adjacencyListMap.get(queue.peek())) {
                    if (!visitedNodes.contains(childNode)) {
                        if (!queueNodes.contains(childNode)) {
                            queueNodes.add(childNode);
                            queue.add(childNode);
                            distanceArray[childNode] = distanceArray[queue.peek()] + 1;
                        } else {
                            int lengthOfCycle = distanceArray[childNode] + distanceArray[queue.peek()] + 1;
                            updateLengthOfShortestCycle(lengthOfCycle);
                        }
                    }
                }
            }
            int head = queue.peek();
            visitedNodes.add(head);
            queueNodes.remove(head);
            queue.poll();
        }
    }

    private void updateLengthOfShortestCycle(int lengthOfCycle) {
        lengthOfShortestCycle = lengthOfCycle < lengthOfShortestCycle ? lengthOfCycle : lengthOfShortestCycle;
    }

    private void prepareAdjacencyList(Map<Integer, List<Integer>> adjacencyListMap, int[][] edges) {
        for (int i = 0; i < edges.length; i++) {
            int parentNode = edges[i][0];
            int childNode = edges[i][1];
            insertDataIntoMap(adjacencyListMap, parentNode, childNode);
            insertDataIntoMap(adjacencyListMap, childNode, parentNode);
        }
    }

    private void insertDataIntoMap(Map<Integer, List<Integer>> adjacencyListMap, int parentNode, int childNode) {
        if (!adjacencyListMap.containsKey(parentNode)) {
        List<Integer> list = new ArrayList<>();
        list.add(childNode);
        adjacencyListMap.put(parentNode, list);
        } else {
        adjacencyListMap.get(parentNode).add(childNode);
        }
    }
}

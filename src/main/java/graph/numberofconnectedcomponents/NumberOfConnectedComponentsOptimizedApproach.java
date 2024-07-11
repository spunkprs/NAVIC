package graph.numberofconnectedcomponents;

import java.util.*;

public class NumberOfConnectedComponentsOptimizedApproach {

    Set<Integer> exploredNodes = new HashSet();

    public int countComponents(int n, int[][] edges) {
        int connectedComponents = 0;
        Map<Integer, List<Integer>> adjacencyMap = prepareAdjacencyMap(edges);
        for (Integer node : adjacencyMap.keySet()) {
            if (!exploredNodes.contains(node)) {
                connectedComponents++;
                processToCountConnectedComponents(null, node, adjacencyMap);
            }
        }

        /*
        * To handle those cases where some of the vertices aren't connected to other node in the forest
        * */
        for (int i = 0; i < n; i++) {
            if (!exploredNodes.contains(i)) {
                connectedComponents++;
            }
        }
        return connectedComponents;
    }

    private void processToCountConnectedComponents(Integer parentNode, Integer node, Map<Integer, List<Integer>> adjacencyMap) {
        if (!exploredNodes.contains(node)) {
            exploredNodes.add(node);
            List<Integer> childNodes = adjacencyMap.get(node);
            for (Integer child : childNodes) {
                if ((parentNode == null) || (parentNode != null && parentNode.intValue() != child.intValue())) {
                    processToCountConnectedComponents(node, child, adjacencyMap);
                }
            }
        }
    }

    public static void main(String ar[]) {
        NumberOfConnectedComponentsOptimizedApproach unit = new NumberOfConnectedComponentsOptimizedApproach();
        int edges[][] = {{0, 1}, {1, 2}, {3, 4}};
        System.out.println("Number of connected components " + unit.countComponents(5, edges));
    }

    private Map<Integer, List<Integer>> prepareAdjacencyMap(int[][] edges) {
        Map<Integer, List<Integer>> adjacencyMap = new HashMap<>();
        for (int i = 0; i < edges.length; i++) {
            if (!adjacencyMap.containsKey(edges[i][0])) {
                List<Integer> list = new ArrayList<>();
                list.add(edges[i][1]);
                adjacencyMap.put(edges[i][0], list);
            } else {
                List<Integer> list = adjacencyMap.get(edges[i][0]);
                list.add(edges[i][1]);
            }

            if (!adjacencyMap.containsKey(edges[i][1])) {
                List<Integer> list = new ArrayList<>();
                list.add(edges[i][0]);
                adjacencyMap.put(edges[i][1], list);
            } else {
                List<Integer> list = adjacencyMap.get(edges[i][1]);
                list.add(edges[i][0]);
            }
        }
        return adjacencyMap;
    }

}

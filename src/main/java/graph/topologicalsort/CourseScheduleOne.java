package graph.topologicalsort;

import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Map;

/*
This problem aims at detecting whether courses can be completed or not depending upon
completion hierarchy of courses is provided to you
* */
public class CourseScheduleOne {
    private Set<Integer> visitedNodes = new HashSet();
    private boolean isCycleDetected = false;

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if (prerequisites.length == 0) {
            return true;
        } else {
            Map<Integer, List<Integer>> adjacencyListMap = new HashMap();
            prepareAdjacencyList(adjacencyListMap, prerequisites);
            for (int i = 0; i < numCourses; i++) {
                if (isCycleDetected) {
                    break;
                }
                Set<Integer> distinctVertexInThePath = new HashSet();
                if (!visitedNodes.contains(i)) {
                    traverseUsingDFS(i, adjacencyListMap, distinctVertexInThePath);
                }
            }
            return isCycleDetected ? false : true;
        }
    }

    private void traverseUsingDFS(int vertex, Map<Integer, List<Integer>> adjacencyListMap, Set<Integer> distinctVertexInThePath) {
        if (!visitedNodes.contains(vertex)) {
            distinctVertexInThePath.add(vertex);
            if (adjacencyListMap.containsKey(vertex)) {
                for (Integer childVertex : adjacencyListMap.get(vertex)) {
                    if (distinctVertexInThePath.contains(childVertex)) {
                        isCycleDetected = true;
                    } else {
                        traverseUsingDFS(childVertex, adjacencyListMap, distinctVertexInThePath);
                    }
                }
            }
            distinctVertexInThePath.remove(vertex);
            visitedNodes.add(vertex);
        }

    }

    private void prepareAdjacencyList(Map<Integer, List<Integer>> adjacencyListMap, int[][] edges) {
        for (int i = 0; i < edges.length; i++) {
            int parentNode = edges[i][1];
            int childNode = edges[i][0];
            insertDataIntoMap(adjacencyListMap, parentNode, childNode);
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

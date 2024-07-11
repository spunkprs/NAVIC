package graph.numberofconnectedcomponents;

import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

/*
This problem aims at finding number of connected components in a graph, if there are multiple vertices in the graph then it would be considered as one component
Have solved the problem got accepted too on leetcode but it has scope of optimization
Time Taken = O(n2), where n = number of vertices in the graph
Space = O(n2)
* */
public class NumberOfConnectedComponents {

    public int countComponents(int n, int[][] edges) {
        Set<Integer> exploredNodes = new HashSet();
        int [][] dependencyMatrix = new int[n][n];
        int connectedComponents = 0;
        dependencyMatrix = prepareDependencyMatrix(dependencyMatrix, edges);
        for (int i = 0; i < dependencyMatrix.length; i++) {
            if (!exploredNodes.contains(i)) {
                connectedComponents++;
                processToFindConnectedComponents(i, exploredNodes, dependencyMatrix);
            }
        }
        return connectedComponents;
    }

    private void processToFindConnectedComponents(int vertex, Set<Integer> exploredNodes, int [][] dependencyMatrix) {
        List<Integer> children = new ArrayList();
        exploredNodes.add(vertex);
        for (int j = 0; j < dependencyMatrix[0].length; j++) {
            if (dependencyMatrix[vertex][j] == 1 && !exploredNodes.contains(j)) {
                children.add(j);
            }
        }

        for (Integer child : children) {
            processToFindConnectedComponents(child, exploredNodes, dependencyMatrix);
        }
    }

    private int[][] prepareDependencyMatrix(int [][] dependencyMatrix, int[][] edges) {
        for (int i = 0; i < edges.length; i++) {
            dependencyMatrix[edges[i][0]][edges[i][1]] = 1;
            dependencyMatrix[edges[i][1]][edges[i][0]] = 1;
        }
        return dependencyMatrix;
    }
}

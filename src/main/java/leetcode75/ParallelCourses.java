package leetcode75;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ParallelCourses {

    public static void main(String ar[]) {
        ParallelCourses unit = new ParallelCourses();
        //int n = 3;
        //int relations[][] = {{1, 3}, {2, 3}};

        int n = 3;
        int relations[][] = {{1, 2}, {2, 3}, {3, 1}};
        System.out.print("Minimum number of semesters required to complete courses is " + unit.minimumSemesters(n , relations));
    }

    private boolean isComputationPossible = true;

    public int minimumSemesters(int n, int[][] relations) {
        int adjacencyMatrix[][] = prepareAdjacencyMatrix(n, relations);
        Set<Integer> visitedIndexes = new HashSet<>();
        Map<Integer, Integer> map = new HashMap<>();

        iterateAdjacencyMatrix(adjacencyMatrix, visitedIndexes, map);
        return isComputationPossible ? fetchMinimumSemesterNeeded(map) : -1;
    }

    private int fetchMinimumSemesterNeeded(Map<Integer, Integer> map) {
        Set<Integer> set = new HashSet<>();
        for (Integer key : map.keySet()) {
            set.add(map.get(key));
        }
        return set.size();
    }

    private void iterateAdjacencyMatrix(int[][] adjacencyMatrix, Set<Integer> visitedIndexes, Map<Integer, Integer> map) {
        for (int i = 0; i < adjacencyMatrix.length; i++) {
            if (!visitedIndexes.contains(i)) {
                map.put(i, 1);
                visitedIndexes.add(i);
                for (int j = 0; j < adjacencyMatrix[0].length; j++) {
                    if (adjacencyMatrix[i][j] == 1) {
                        traverseElementsInDFSFashion(adjacencyMatrix, i, j, map, visitedIndexes);
                    }
                }
            }
        }
    }

    private void traverseElementsInDFSFashion(int[][] adjacencyMatrix, int parentIndex, int childIndex, Map<Integer, Integer> map, Set<Integer> visitedIndexes) {
        if (isComputationPossible) {
            int parentDepth = map.get(parentIndex);
            int j = 0;

            if (!visitedIndexes.contains(childIndex)) {
                visitedIndexes.add(childIndex);
                map.put(childIndex, parentDepth + 1);
            } else {
                int existingDepth = map.get(childIndex);
                if (existingDepth < map.get(parentIndex) + 1) {
                    if (map.get(parentIndex) + 1 <= adjacencyMatrix.length) {
                        map.put(childIndex, map.get(parentIndex) + 1);
                    } else {
                        isComputationPossible = false;
                    }
                }
            }

            while (j < adjacencyMatrix[0].length) {
                if (adjacencyMatrix[childIndex][j] == 1) {
                    traverseElementsInDFSFashion(adjacencyMatrix, childIndex, j, map, visitedIndexes);
                }
                j++;
            }
        }
    }

    private int[][] prepareAdjacencyMatrix(int n, int[][] relations) {
        int matrix[][] = new int[n][n];

        for (int i = 0; i < relations.length; i++) {
            int parent = relations[i][0];
            int child = relations[i][1];
            matrix[parent - 1][child - 1] = 1;
        }
        return matrix;
    }
}

package leetcode75;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class NumberOfProvinces {

    public static void main(String ar[]) {
        NumberOfProvinces unit = new NumberOfProvinces();
        int [][] isConnected = {{1,0,0,1}, {0,1,1,0}, {0,1,1,1}, {1,0,1,1}};
        System.out.print("Number of provinces is " + unit.findCircleNum(isConnected));
    }

    public int findCircleNum(int[][] isConnected) {
        int provinceCount = 0;
        Set<Integer> visitedNodes = new HashSet();

        for (int index = 0; index < isConnected.length; index++) {
            if (!visitedNodes.contains(index)) {
                provinceCount++;
                fetchChildren(index, isConnected, visitedNodes);
            }
        }
        return provinceCount;
    }

    private void fetchChildren(int parentIndex, int[][] isConnected, Set<Integer> visitedNodes) {
        visitedNodes.add(parentIndex);
        List<Integer> childIndexes = getChildren(parentIndex, isConnected, visitedNodes);
        for (int childIndex : childIndexes) {
            if (!visitedNodes.contains(childIndex)) {
                fetchChildren(childIndex, isConnected, visitedNodes);
            }
        }
    }

    private List<Integer> getChildren(int parentIndex, int[][] isConnected, Set<Integer> visitedNodes) {
        List<Integer> childIndexes = new ArrayList();
        for (int k = 0; k < isConnected[0].length; k++) {
            if (k != parentIndex && isConnected[parentIndex][k] == 1 && !visitedNodes.contains(k)) {
                childIndexes.add(k);
            }
        }
        return childIndexes;
    }
}

package leetcode75;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
Problem : 547

There are n cities. Some of them are connected, while some are not. If city a is connected directly with city b, and city b is connected directly with city c, then city a is connected indirectly with city c.

A province is a group of directly or indirectly connected cities and no other cities outside of the group.

You are given an n x n matrix isConnected where isConnected[i][j] = 1 if the ith city and the jth city are directly connected, and isConnected[i][j] = 0 otherwise.

Return the total number of provinces


Constraints:-

a.) 1 <= n <= 200
b.) n == isConnected.length
c.) n == isConnected[i].length
d.) isConnected[i][j] is 1 or 0.
e.) isConnected[i][i] == 1
f.) isConnected[i][j] == isConnected[j][i]

Time Complexity = O(N ^ 2)
Space Complexity = O(N ^ 2)
 * */

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

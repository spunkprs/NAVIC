package binarytree;

import java.util.*;

/**
 You are given an integer n. There is a complete binary tree with 2n - 1 nodes. The root of that tree is the node with the value 1, and every node with a value val in the range [1, 2n - 1 - 1] has two children where:

 The left node has the value 2 * val, and
 The right node has the value 2 * val + 1.
 You are also given a 2D integer array queries of length m, where queries[i] = [ai, bi]. For each query, solve the following problem:

 Add an edge between the nodes with values ai and bi.
 Find the length of the cycle in the graph.
 Remove the added edge between nodes with values ai and bi.
 Note that:

 A cycle is a path that starts and ends at the same node, and each edge in the path is visited only once.
 The length of a cycle is the number of edges visited in the cycle.
 There could be multiple edges between two nodes in the tree after adding the edge of the query.
 Return an array answer of length m where answer[i] is the answer to the ith query.


Constraints:

1.) 2 <= n <= 30
2.) m == queries.length
3.) 1 <= m <= 105
4.) queries[i].length == 2
5.) 1 <= ai, bi <= pow(2,n) - 1
6.) ai != bi


Source : LeetCode
 * */

public class CycleLengthQueriesInTree {


    public int[] cycleLengthQueries(int n, int[][] queries) {
        int result[] = new int[queries.length];
        updateResult(n, result, queries);
        return result;
    }

    private int[] updateResult(int n, int[] result, int[][] queries) {
        for (int i = 0; i < queries.length; i++) {
            int fromNode = queries[i][0];
            int toNode = queries[i][1];

            List<Integer> fromNodePathSet = prepareSet(fromNode);
            List<Integer> toNodePathSet = prepareSet(toNode);

            int indexOne = 0;
            int indexTwo = 0;
            int totalEdges = 0;

            while (indexOne < fromNodePathSet.size() && indexTwo < toNodePathSet.size()) {
                if (fromNodePathSet.get(indexOne) == toNodePathSet.get(indexTwo)) {
                    indexOne++;
                    indexTwo++;
                } else {
                    break;
                }
            }

            if (indexTwo == toNodePathSet.size() && indexOne < fromNodePathSet.size()) {
                totalEdges += fromNodePathSet.size() - indexOne + 1;
            }

            if (indexTwo < toNodePathSet.size() && indexOne == fromNodePathSet.size()) {
                totalEdges += toNodePathSet.size() - indexOne + 1;
            }

            if (indexOne < fromNodePathSet.size() && indexTwo < toNodePathSet.size()) {
                totalEdges += fromNodePathSet.size() - indexOne + toNodePathSet.size() - indexTwo + 1;
            }
            result[i] = totalEdges;
        }
        return result;
    }

    private List<Integer> prepareSet(int node) {
        List<Integer> path = new ArrayList<>();
        while (node != 1) {
            path.add(node);
            node /=2;
        }
        path.add(1);
        Collections.reverse(path);
        return path;
    }

}

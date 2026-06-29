package microsoft.dfs;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
Problem : 261
Link : https://leetcode.com/problems/graph-valid-tree/description/

You have a graph of n nodes labeled from 0 to n - 1. You are given an integer n and a list of edges where edges[i] = [ai, bi]
indicates that there is an undirected edge between nodes ai and bi in the graph.

Return true if the edges of the given graph make up a valid tree, and false otherwise.


Constraints:-

a.) 1 <= n <= 2000
b.) 0 <= edges.length <= 5000
c.) edges[i].length == 2
d.) 0 <= ai, bi < n
e.) ai != bi
f.) There are no self-loops or repeated edges.

Time Complexity : O(N + E)
Space Complexity : O(N + E)

Verdict : 46 / 47 testcases passed !!
 * */

public class GraphValidTree {

    boolean result = false;
    int numberOfVertices = 0;

    public static void main(String ar[]) {
        GraphValidTree unit = new GraphValidTree();

        //int[][] edges = {{0,1},{1,2},{2,3},{1,3},{1,4}};

        //int edges[][] = {{0,1},{0,2},{0,3},{1,4}};

        int edges[][] = {{0,1},{2,3}};

        System.out.print("Is provided graph valid tree " + unit.validTree(4, edges));
    }

    public boolean validTree(int n, int[][] edges) {
        Map<Integer, Set<Integer>> map = prepareMap(edges);
        Set<Integer> visitedIndexes = new HashSet<>();
        if (!map.isEmpty()) {
            processToCheckWhetherGraphIsTree(map, visitedIndexes, n);
        } else if (map.isEmpty() && n == 1) {
            result = false;
        } else if (map.isEmpty() && n > 1) {
            result = true;
        }
        return !result;
    }

    private void processToCheckWhetherGraphIsTree(Map<Integer, Set<Integer>> map, Set<Integer> visitedIndexes, int numberOfNodes) {
        for (Integer fromIndex : map.keySet()) {
            visitedIndexes.add(fromIndex);
            numberOfVertices++;
            if (!result) {
                process(-1, fromIndex, visitedIndexes, map);
                if (!result) {
                    visitedIndexes.remove(fromIndex);
                }
            }
            if (result) {
                break;
            }

            if (numberOfVertices != numberOfNodes) {
                result = true;
            }
            break;
        }
    }

    private void process(Integer immediateParent, Integer fromIndex, Set<Integer> visitedIndexes, Map<Integer, Set<Integer>> map) {
        if (immediateParent != -1) {
            for (Integer neighbour : map.get(fromIndex)) {
            if (neighbour != immediateParent && !visitedIndexes.contains(neighbour)) {
                visitedIndexes.add(neighbour);
                numberOfVertices++;
                if (!result) {
                    process(fromIndex, neighbour, visitedIndexes, map);
                    if (!result) {
                        visitedIndexes.remove(neighbour);
                    }
                }
                if (result) {
                    return;
                }
            } else if (neighbour != immediateParent && visitedIndexes.contains(neighbour)) {
                result = true;
            }
            }
        } else {
            for (Integer neighbour : map.get(fromIndex)) {
                if (!visitedIndexes.contains(neighbour)) {
                    visitedIndexes.add(neighbour);
                    numberOfVertices++;
                    process(fromIndex, neighbour, visitedIndexes, map);
                    visitedIndexes.remove(neighbour);
                }
            }
        }

    }

    private Map<Integer, Set<Integer>> prepareMap(int[][] edges) {
        Map<Integer, Set<Integer>> map = new HashMap<>();

        for (int i = 0; i < edges.length; i++) {
            if (!map.containsKey(edges[i][0])) {
                Set<Integer> neighbours = new HashSet<>();
                int fromIndex = edges[i][0];
                int toIndex = edges[i][1];
                neighbours.add(toIndex);
                map.put(fromIndex, neighbours);
            } else {
                map.get(edges[i][0]).add(edges[i][1]);
            }

            if (!map.containsKey(edges[i][1])) {
                Set<Integer> neighbours = new HashSet<>();
                int fromIndex = edges[i][1];
                int toIndex = edges[i][0];
                neighbours.add(toIndex);
                map.put(fromIndex, neighbours);
            } else {
                map.get(edges[i][1]).add(edges[i][0]);
            }
        }

        return map;
    }
}

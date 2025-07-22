package graph;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;


/**
 There is an undirected tree with n nodes labeled from 0 to n - 1 and n - 1 edges.

 You are given a 2D integer array edges of length n - 1 where edges[i] = [ai, bi] indicates that there is an edge
 between nodes ai and bi in the tree. You are also given an integer array restricted which represents restricted nodes.

 Return the maximum number of nodes you can reach from node 0 without visiting a restricted node.

 Note that node 0 will not be a restricted node.


 Constraints:

 a.) 2 <= n <= 105
 b.) edges.length == n - 1
 c.) edges[i].length == 2
 d.) 0 <= ai, bi < n
 e.) ai != bi
 f.) edges represents a valid tree.
 g.) 1 <= restricted.length < n
 h.) 1 <= restricted[i] < n
 i.) All the values of restricted are unique.

 Source : Leetcode
 * */

public class ReachableNodesWithRestrictions {

    public static void main(String ar[]) {
        ReachableNodesWithRestrictions unit = new ReachableNodesWithRestrictions();
        int nodesCount = 7;
        int edges [][] = {{0, 1}, {0, 2}, {0, 5}, {0, 4}, {3, 2}, {6, 5}};
        int restricted[] = {4, 2, 1};

        System.out.println("Maximum number of nodes reachable from node 0 is " + unit.reachableNodes(nodesCount, edges, restricted));
    }

    public int reachableNodes(int n, int[][] edges, int[] restricted) {
        Map<Integer, List<Integer>> edgesMap = new HashMap<>();
        buildTree(edges, edgesMap);
        return processToFindMaxReachableNodes(edgesMap, 0, restricted);
    }

    private int processToFindMaxReachableNodes(Map<Integer, List<Integer>> edgesMap, int startIndex, int[] restrictedNodesArr) {
        Set<Integer> restrictedNodes = new HashSet<>();
                Arrays.stream(restrictedNodesArr).forEach( num -> {
                restrictedNodes.add(num);
        });

        Set<Integer> visitedNodes = new HashSet<>();
        visitedNodes.add(startIndex);

        process(startIndex, edgesMap, restrictedNodes, visitedNodes);
        return visitedNodes.size();
    }

    private void process(int parentIndex, Map<Integer, List<Integer>> edgesMap,
                         Set<Integer> restrictedNodes, Set<Integer> visitedNodes) {

        for (Integer childIndex : edgesMap.get(parentIndex)) {
            if (!visitedNodes.contains(childIndex) && !restrictedNodes.contains(childIndex)) {
                visitedNodes.add(childIndex);
                process(childIndex, edgesMap, restrictedNodes, visitedNodes);
            }
        }

    }

    private void buildTree(int[][] edges, Map<Integer, List<Integer>> edgesMap) {
        for (int i = 0; i < edges.length; i++) {
            int fromNode = edges[i][0];
            int toNode = edges[i][1];
            buildEdgesMap(fromNode, toNode, edgesMap);
        }
    }

    private void buildEdgesMap(int fromNode, int toNode, Map<Integer, List<Integer>> edgesMap) {
        if (!edgesMap.containsKey(fromNode)) {
            List<Integer> toNodes = new ArrayList<>();
            toNodes.add(toNode);
            edgesMap.put(fromNode, toNodes);
        } else {
            edgesMap.get(fromNode).add(toNode);
        }

        if (!edgesMap.containsKey(toNode)) {
            List<Integer> fromNodes = new ArrayList<>();
            fromNodes.add(fromNode);
            edgesMap.put(toNode, fromNodes);
        } else {
            edgesMap.get(toNode).add(fromNode);
        }
    }
}

package graph;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;

/**
 This problem aims at finding longest increasing path in a matrix, multiple paths could be possible
 We just need to return length of that path
 Path : https://leetcode.com/explore/interview/card/apple/350/trees-and-graphs/2049/
 */
public class LongestIncreasingPathInMatrixLeetCode {
    private int maxDistance = 0;

    static class Index {
        private int x;
        private int y;

        public Index(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int hashCode() {
            return this.x * this.y;
        }

        public boolean equals(Object indexNode) {
            Index node = (Index) indexNode;
            return (this.x == node.x && this.y == node.y) ? true : false;
        }
    }

    public int longestIncreasingPath(int[][] matrix) {
        Map<Index, Integer> exploredIndexes = new HashMap();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                Index node = new Index(i, j);
                findLongestIncreasingPath(node, matrix, exploredIndexes);
            }
        }
        return maxDistance;
    }

    private int findLongestIncreasingPath(Index node, int matrix[][], Map<Index, Integer> exploredIndexes) {
        if (!exploredIndexes.containsKey(node)) {
            List<Index> children = findChildren(node, matrix);
            if (children.isEmpty()) {
                exploredIndexes.put(node, 1);
                updateMaxDistance(1);
                return 1;
            } else {
                int distancePulledFromChild = 0;
                for (Index child : children) {
                    int distance = findLongestIncreasingPath(child, matrix, exploredIndexes);
                    if (distance > distancePulledFromChild) {
                        distancePulledFromChild = distance;
                    }
                }
                updateMaxDistance(distancePulledFromChild + 1);
                exploredIndexes.put(node, distancePulledFromChild + 1);
                return distancePulledFromChild + 1;
            }
        } else {
            return exploredIndexes.get(node);
        }
    }

    private void updateMaxDistance(int distance) {
        maxDistance = distance > maxDistance ? distance : maxDistance;
    }

    private List<Index> findChildren(Index parentNode, int matrix[][]) {
        List<Index> children = new ArrayList();
        if (parentNode.x + 1 < matrix.length && matrix[parentNode.x + 1][parentNode.y] > matrix[parentNode.x][parentNode.y]) {
            children.add(new Index(parentNode.x + 1, parentNode.y));
        }

        if (parentNode.x - 1 >= 0 && matrix[parentNode.x - 1][parentNode.y] > matrix[parentNode.x][parentNode.y]) {
            children.add(new Index(parentNode.x - 1, parentNode.y));
        }

        if (parentNode.y + 1 < matrix[0].length && matrix[parentNode.x][parentNode.y + 1] > matrix[parentNode.x][parentNode.y]) {
            children.add(new Index(parentNode.x, parentNode.y + 1));
        }

        if (parentNode.y - 1 >= 0 && matrix[parentNode.x][parentNode.y - 1] > matrix[parentNode.x][parentNode.y]) {
            children.add(new Index(parentNode.x, parentNode.y - 1));
        }
        return children;
    }
}

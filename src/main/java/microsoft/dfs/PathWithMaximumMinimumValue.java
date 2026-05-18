package microsoft.dfs;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class PathWithMaximumMinimumValue {

    private int globalSumCollected = 0;
    private int globalSmallestSum = 0;

    public static void main(String ar[]) {

    }

    public int maximumMinimumPath(int[][] grid) {
        Set<Node> visitedNodes = new HashSet<>();
        int startX = 0;
        int startY = 0;




        return 0;
    }

    private void process(Node parentNode, int sumCollected, int smallestNum, int [][] grid) {
        if (parentNode.x == grid.length - 1 && parentNode.y == grid[0].length - 1) {
            if (sumCollected > globalSumCollected) {
                globalSumCollected = sumCollected;
                globalSmallestSum = smallestNum;
            }
        }
    }

    static class Node {
        private int x;
        private int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return x == node.x && y == node.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
}

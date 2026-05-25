package microsoft.dp;

import java.util.*;

public class PacificAtlanticWaterFlow {

    private List<List<Integer>> result = new ArrayList<>();

    public List<List<Integer>> pacificAtlantic(int[][] heights) {

        if (heights.length == 1 && heights[0].length == 1) {
            List<Integer> intermittentResult = Arrays.asList(0, 0);
            result.add(intermittentResult);
        } else if (heights.length > 1 && heights[0].length == 1) {
            prepareResultOne(0, heights.length - 1,0);
        } else if (heights.length == 1 && heights[0].length > 1) {
            prepareResultTwo(0, heights[0].length - 1, 0);
        } else {
            processToFindNodesFromWhereWaterCanReachBothOceans(heights);
        }

        return result;
    }

    private void processToFindNodesFromWhereWaterCanReachBothOceans(int[][] heights) {
        boolean pacificVisited[][] = new boolean[heights.length][heights[0].length];
        boolean atlanticVisited[][] = new boolean[heights.length][heights[0].length];

        for (int i = 0; i < heights.length; i++) {
        Node node = new Node(i, 0);
        processOne(node, heights, pacificVisited);
        node = new Node(i, heights[0].length - 1);
        processOne(node, heights, atlanticVisited);
        }

        for (int j = 0; j < heights[0].length; j++) {
            Node node = new Node(0, j);
            processOne(node, heights, pacificVisited);
            node = new Node(heights.length - 1, j);
            processOne(node, heights, atlanticVisited);
        }

        for (int i = 0; i < heights.length; i++) {
            for (int j = 0; j < heights[0].length; j++) {
                if (pacificVisited[i][j] && atlanticVisited[i][j]) {
                    result.add(Arrays.asList(i, j));
                }
            }
        }
    }

    private void processOne(Node preparedNode, int[][] heights, boolean visited[][]) {
        visited[preparedNode.xValue][preparedNode.yValue] = true;

        List<Node> children = fetchChildren(preparedNode, heights);

        if (children.size() > 0 ) {
            for (Node child : children) {
                if (!visited[child.xValue][child.yValue]) {
                    processOne(child, heights, visited);
                }
            }
        }
    }

    private List<Node> fetchChildren(Node parentNode, int[][] heights) {
        List<Node> children = new ArrayList<>();
        int height = heights[parentNode.xValue][parentNode.yValue];

        if (parentNode.xValue + 1 < heights.length && heights[parentNode.xValue + 1][parentNode.yValue] <= height) {
            children.add(new Node(parentNode.xValue + 1, parentNode.yValue));
        }

        if (parentNode.yValue + 1 < heights[0].length && heights[parentNode.xValue][parentNode.yValue + 1] <= height) {
            children.add(new Node(parentNode.xValue, parentNode.yValue + 1));
        }

        if (parentNode.xValue - 1 >= 0 && heights[parentNode.xValue - 1][parentNode.yValue] <= height) {
            children.add(new Node(parentNode.xValue - 1, parentNode.yValue));
        }

        if (parentNode.yValue - 1 >= 0 && heights[parentNode.xValue][parentNode.yValue - 1] <= height) {
            children.add(new Node(parentNode.xValue, parentNode.yValue - 1));
        }

        return children;
    }

    private void prepareResultOne(int startX, int endX, int startY) {
        List<Integer> intermittentResult = new ArrayList<>();
        for (int i = startX; i <= endX; i++) {
            intermittentResult.add(i);
            intermittentResult.add(startY);
            result.add(intermittentResult);
            intermittentResult = new ArrayList<>();
        }
    }

    private void prepareResultTwo(int startY, int endY, int startX) {
        List<Integer> intermittentResult = new ArrayList<>();
        for (int i = startY; i <= endY; i++) {
            intermittentResult.add(startX);
            intermittentResult.add(i);
            result.add(intermittentResult);
            intermittentResult = new ArrayList<>();
        }
    }

    static class Node {
        private int xValue;
        private int yValue;

        public Node(int xValue, int yValue) {
            this.xValue = xValue;
            this.yValue = yValue;
        }

        public int hashCode() {
            return Objects.hash(xValue, yValue);
        }

        public boolean equals(Object obj) {
            if (obj == null) {
                return false;
            } else {
                Node object = (Node)obj;
                return this.xValue == object.xValue && this.yValue == object.yValue;
            }
        }
    }
}

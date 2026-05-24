package microsoft.stack;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class NumberOfPeopleThatCanBeSeenInGrid {

    public static void main(String ar[]) {
        NumberOfPeopleThatCanBeSeenInGrid unit = new NumberOfPeopleThatCanBeSeenInGrid();
        //int heights[][] = {{5, 6, 4}, {12, 8, 5}, {3, 1, 3}};

        int heights[][] = {{4,2,1,1,3}};
        int result [][] = unit.seePeople(heights);
        System.out.print(result);
    }

    public int[][] seePeople(int[][] heights) {
        Map<String, Stack<Node>> mapping = new HashMap<>();

        int result[][] = new int[heights.length][heights[0].length];

        computationProcess(mapping, heights, result);

        return result;
    }

    private void computationProcess(Map<String, Stack<Node>> mapping, int[][] heights, int result[][]) {
        for (int i = heights.length - 1; i >= 0; i--) {
            for (int j = heights[0].length - 1; j >= 0; j--) {
                if (i == heights.length - 1 && j == heights[0].length - 1) {
                    result[i][j] = 0;
                    String rowNum = "R" + i;
                    String colNum = "C" + j;
                    if (!mapping.containsKey(rowNum)) {
                        Node createdNode = new Node(heights[i][j]);
                        Stack<Node> stack = new Stack<>();
                        stack.push(createdNode);
                        mapping.put(rowNum, stack);
                    }
                    if (!mapping.containsKey(colNum)) {
                        Node createdNode = new Node(heights[i][j]);
                        Stack<Node> stack = new Stack<>();
                        stack.push(createdNode);
                        mapping.put(colNum, stack);
                    }
                } else {
                    String rowNum = "R" + i;
                    String colNum = "C" + j;
                    Stack<Node> rowStack = mapping.get(rowNum);
                    Stack<Node> colStack = mapping.get(colNum);

                    int rowCount = 0;
                    int colCount = 0;

                    if (rowStack != null) {
                        int val = heights[i][j];
                        if (val < rowStack.peek().value) {
                            rowCount++;
                            rowStack.add(new Node(val));
                        } else if (val > rowStack.peek().value) {
                            while (!rowStack.isEmpty() && val > rowStack.peek().value) {
                                rowCount++;
                                rowStack.pop();
                            }

                            if (!rowStack.isEmpty()) {
                                rowCount++;
                            }

                            rowStack.push(new Node(heights[i][j]));
                        } else {
                            rowCount++;
                        }
                    } else {
                        rowStack = new Stack<>();
                        rowStack.push(new Node(heights[i][j]));
                        mapping.put(rowNum, rowStack);
                    }


                    if (colStack != null) {
                        int val = heights[i][j];
                        if (val < colStack.peek().value) {
                            colCount++;
                            colStack.add(new Node(val));
                        } else if (val > colStack.peek().value) {
                            while (!colStack.isEmpty() && val > colStack.peek().value) {
                                colCount++;
                                colStack.pop();
                            }

                            if (!colStack.isEmpty()) {
                                colCount++;
                            }

                            colStack.push(new Node(heights[i][j]));
                        } else {
                            colCount++;
                        }
                    } else {
                        colStack = new Stack<>();
                        colStack.push(new Node(heights[i][j]));
                        mapping.put(colNum, colStack);
                    }

                    result[i][j] = rowCount + colCount;
                }
            }
        }
    }

    static class Node {
        private int value;

        public Node(int value) {
            this.value = value;
        }
    }
}

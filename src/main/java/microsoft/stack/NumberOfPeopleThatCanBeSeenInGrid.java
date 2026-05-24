package microsoft.stack;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
Problem : 2282
Link : https://leetcode.com/problems/number-of-people-that-can-be-seen-in-a-grid/description/?envType=problem-list-v2&envId=stack

You are given an m x n 0-indexed 2D array of positive integers heights where heights[i][j] is the height of the person
standing at position (i, j).

A person standing at position (row1, col1) can see a person standing at position (row2, col2) if:

The person at (row2, col2) is to the right or below the person at (row1, col1). More formally, this means that either row1 == row2
and col1 < col2 or row1 < row2 and col1 == col2.
Everyone in between them is shorter than both of them.
Return an m x n 2D array of integers answer where answer[i][j] is the number of people that the person at position (i, j) can see.


Constraints:-

a.) 1 <= heights.length <= 400
b.) 1 <= heights[i].length <= 400
c.) 1 <= heights[i][j] <= pow(10,5)

Time Complexity = O(M * N)
Space Complexity = O(M * N)
 * */

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

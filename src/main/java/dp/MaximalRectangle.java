package dp;

import java.util.Stack;

public class MaximalRectangle {
	
	public int maximalRectangle(char[][] matrix) {
		if (matrix.length == 0) {
			return 0;
		}
		int heights[] = new int[matrix[0].length];
		int maxArea = 0;
		for (int j = 0; j < heights.length; j++) {
			heights[j] = Integer.parseInt(String.valueOf(matrix[0][j]));
		}
		int computedArea = largestRectangleArea(heights);
		maxArea = computedArea >= maxArea ? computedArea : maxArea;
		
		for (int i = 1 ; i < matrix.length; i++) {
			for (int j = 0; j < heights.length; j++) {
				int val = Integer.parseInt(String.valueOf(matrix[i][j]));
				if (val == 0) {
					heights[j] = 0;
				} else {
					heights[j] = heights[j] + val;
				}
			}
			computedArea = largestRectangleArea(heights);
			maxArea = computedArea >= maxArea ? computedArea : maxArea;
		}
		
        return maxArea;
    }
	
	private int largestRectangleArea(int[] heights) {
		int maxArea = 0;
		Stack<Integer> stack = new Stack<Integer>();
		int i = 0;
		while (i < heights.length) {
			if (i == 0) {
				stack.push(0);
				i++;
			} else {
				if (stack.isEmpty() || heights[i] >= heights[stack.peek()]) {
					stack.push(i);
					i++;
				} else {
					int top = stack.peek();
					stack.pop();
					if (!stack.isEmpty()) {
						maxArea = updateMaxArea(heights[top] * (i - stack.peek() - 1), maxArea);
					} else {
						maxArea = updateMaxArea(heights[top] * i, maxArea);
					}
				}
			}
		}
		
		if (!stack.isEmpty()) {
			while (!stack.isEmpty()) {
				int top = stack.peek();
				stack.pop();
				if (!stack.isEmpty()) {
					maxArea = updateMaxArea(heights[top] * (i - stack.peek() - 1), maxArea);
				} else {
					maxArea = updateMaxArea(heights[top] * i, maxArea);
				}
			}
		}
        return maxArea;
    }

	private int updateMaxArea(int area, int maxArea) {
		 return area >= maxArea ? area : maxArea;
	}

}

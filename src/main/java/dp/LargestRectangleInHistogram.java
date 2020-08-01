package dp;

import java.util.Stack;

public class LargestRectangleInHistogram {
	
	public int largestRectangleArea(int[] heights) {
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

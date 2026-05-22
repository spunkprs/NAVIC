package microsoft.stack;

import java.util.Stack;

public class BuildingsWithOceanView {

    public int[] findBuildings(int[] heights) {
        Stack<Integer> stack = new Stack();


        int index = heights.length - 1;

        while (index >= 0) {
            if (stack.isEmpty()) {
                stack.push(index);
            } else {
                int topElement = heights[stack.peek()];
                if (heights[index] > topElement) {
                    stack.push(index);
                }
            }
            index--;
        }

        int result[] = new int[stack.size()];
        int prepIndex = 0;
        while (!stack.isEmpty()) {
            result[prepIndex] = stack.pop();
            prepIndex++;
        }

        return result;
    }
}

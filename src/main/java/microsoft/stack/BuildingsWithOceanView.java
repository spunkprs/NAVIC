package microsoft.stack;

import java.util.Stack;

/**
Problem : 1762

Link : https://leetcode.com/problems/buildings-with-an-ocean-view/description/?envType=problem-list-v2&envId=stack

There are n buildings in a line. You are given an integer array heights of size n that represents the heights of the buildings in the line.

The ocean is to the right of the buildings. A building has an ocean view if the building can see the ocean without obstructions.
Formally, a building has an ocean view if all the buildings to its right have a smaller height.

Return a list of indices (0-indexed) of buildings that have an ocean view, sorted in increasing order.


Constraints:-
a.) 1 <= heights.length <= pow(10,5)
b.) 1 <= heights[i] <= pow(10,9)
 * */

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

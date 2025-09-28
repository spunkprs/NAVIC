package agoda.monotonicstack;

import java.util.Stack;

/**
 Given an array of integers temperatures represents the daily temperatures, return an array answer such
 that answer[i] is the number of days you have to wait after the ith day to get a warmer temperature.
 If there is no future day for which this is possible, keep answer[i] == 0 instead.


 Example 1:

 Input: temperatures = [73,74,75,71,69,72,76,73]
 Output: [1,1,4,2,1,1,0,0]
 Example 2:

 Input: temperatures = [30,40,50,60]
 Output: [1,1,1,0]
 Example 3:

 Input: temperatures = [30,60,90]
 Output: [1,1,0]

 Source : LeetCode


 * */

public class DailyTemperatures {

    public static void main(String ar[]) {
        DailyTemperatures unit = new DailyTemperatures();
        int temperatures[] = {73,74,75,71,69,72,76,73};
        System.out.println("Result after processing temperature array is ");

        int result[] = unit.dailyTemperatures(temperatures);

        for (int i = 0; i < result.length; i++) {
            System.out.println("for index " + i + " result is " + result[i]);
        }

    }

    public int[] dailyTemperatures(int[] temperatures) {
        int result[] = new int[temperatures.length];
        if (temperatures.length == 1) {
            result[0] = 0;
        } else {
            Stack<Node> stack = new Stack<>();
            stack.add(new Node(temperatures[0], 0));

            for (int i = 1; i < temperatures.length; i++) {
                while (!stack.isEmpty() && stack.peek().value < temperatures[i]) {
                    int index = stack.peek().index;
                    result[index] = i - index;
                    stack.pop();
                }
                stack.push(new Node(temperatures[i], i));
            }
        }
        return result;
    }

    static class Node {
        private int value;
        private int index;

        public Node(int value, int index) {
            this.value = value;
            this.index = index;
        }
    }
}

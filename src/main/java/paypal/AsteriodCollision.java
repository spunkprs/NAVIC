package paypal;

import java.util.*;

/**
We are given an array asteroids of integers representing asteroids in a row.
The indices of the asteriod in the array represent their relative position in space.

For each asteroid, the absolute value represents its size, and the sign represents
its direction (positive meaning right, negative meaning left). Each asteroid moves at the same speed.

Find out the state of the asteroids after all collisions. If two asteroids meet, the smaller one will explode.
If both are the same size, both will explode. Two asteroids moving in the same direction will never meet.

Constraints:

1.) 2 <= asteroids.length <= pow(10,4)
2.) -1000 <= asteroids[i] <= 1000
3.) asteroids[i] != 0

Source : LeetCode


 * */

public class AsteriodCollision {

    public static void main(String ar[]) {
        AsteriodCollision unit = new AsteriodCollision();
        //int asteriods[] = {5,10,-5};
        int asteriods[] = {-2,1,1,-1};
        System.out.print("Resultant array post collision of asteroids is :: ");

        int resultArr[] = unit.asteroidCollision(asteriods);
        for (int i : resultArr) {
            System.out.println(i);
        }
    }

    public int[] asteroidCollision(int[] asteroids) {
        List<Integer> result = new ArrayList<>();
        Stack<Node> nodeStack = new Stack<>();

        for (int i = 0; i < asteroids.length; i++) {
            boolean negativeValueDomination = true;
            if (asteroids[i] < 0) {
                if (nodeStack.isEmpty()) {
                    result.add(asteroids[i]);
                } else {
                    while (!nodeStack.isEmpty()) {
                        if (nodeStack.peek().value > Math.abs(asteroids[i])) {
                            negativeValueDomination = false;
                            break;
                        } else if (nodeStack.peek().value < Math.abs(asteroids[i])) {
                            nodeStack.pop();
                        } else {
                            negativeValueDomination = false;
                            nodeStack.pop();
                            break;
                        }
                    }
                    if (nodeStack.isEmpty() && negativeValueDomination) {
                        result.add(asteroids[i]);
                    }
                }
            } else {
                nodeStack.add(new Node(asteroids[i], i));
            }
        }
        return convertListToArray(result, nodeStack);
    }

    private int[] convertListToArray(List<Integer> result, Stack<Node> stack) {

        int resultArr[] = new int[result.size() + stack.size()];

        for (int i = 0; i < result.size(); i++) {
            resultArr[i] = result.get(i);
        }

        if (!stack.isEmpty()) {
            int startIndex = resultArr.length - 1;
            while (!stack.isEmpty()) {
                resultArr[startIndex] = stack.pop().value;
                startIndex--;
            }
        }
        return resultArr;
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

package microsoft.stack;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class MinSwapsToMakeStringBalanced {

    public static void main(String ar[]) {
        MinSwapsToMakeStringBalanced unit = new MinSwapsToMakeStringBalanced();
        String s = "[[[]]]]][[";
        System.out.print("Min swaps to make string balanced is " + unit.minSwaps(s));
    }

    public int minSwaps(String s) {
        if (s.isEmpty()) {
            return 0;
        }
        char arr[] = s.toCharArray();
        return processToComputeMinSwaps(arr);
    }

    private int processToComputeMinSwaps(char[] arr) {
        int index = 0;
        int swaps = 0;
        Stack<Character> stack = new Stack<>();
        Queue<Character> list = new LinkedList<>();

        while (index < arr.length) {
            char ch = arr[index];
            if (ch == '[') {
                stack.push(ch);
            } else if (ch == ']') {
                if (!stack.isEmpty()) {
                    stack.pop();
                } else {
                    list.add(ch);
                }
            }
            index++;
        }

        if (stack.isEmpty()) {
            return 0;
        } else {
            swaps = 1;
            if (stack.size() > 1) {
                int mod = stack.size() % 2;
                if (mod == 0) {
                    swaps *= stack.size() / 2;
                } else {
                    swaps *= stack.size() / 2 + 1;
                }
            }
        }

        return swaps;
    }
}

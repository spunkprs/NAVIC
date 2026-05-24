package microsoft.stack;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
Problem : 1963
Link : https://leetcode.com/problems/minimum-number-of-swaps-to-make-the-string-balanced/description/?envType=problem-list-v2&envId=stack

You are given a 0-indexed string s of even length n. The string consists of exactly n / 2 opening brackets '[' and n / 2 closing brackets ']'.

A string is called balanced if and only if:

It is the empty string, or
It can be written as AB, where both A and B are balanced strings, or
It can be written as [C], where C is a balanced string.
You may swap the brackets at any two indices any number of times.

Return the minimum number of swaps to make s balanced.


Constraints:-

a.) n == s.length
b.) 2 <= n <= pow(10,6)
c.) n is even.
d.) s[i] is either '[' or ']'.
e.) The number of opening brackets '[' equals n / 2, and the number of closing brackets ']' equals n / 2.

Time Complexity = O(N)
Space Complexity = O(N)

Level : Medium
 * */

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

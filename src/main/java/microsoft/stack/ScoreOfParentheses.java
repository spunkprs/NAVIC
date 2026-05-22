package microsoft.stack;

import java.util.Stack;

/**
Problem : 856
Given a balanced parentheses string s, return the score of the string.

The score of a balanced parentheses string is based on the following rule:

"()" has score 1.
AB has score A + B, where A and B are balanced parentheses strings.
(A) has score 2 * A, where A is a balanced parentheses string.

Constraints:-

a.) 2 <= s.length <= 50
b.) s consists of only '(' and ')'.
c.) s is a balanced parentheses string.

 * */

public class ScoreOfParentheses {

    public static void main(String ar[]) {
        ScoreOfParentheses unit = new ScoreOfParentheses();
        String input = "((()))";
        System.out.print("Score of parentheses " + input + " is " + unit.scoreOfParentheses(input));
    }

    public int scoreOfParentheses(String s) {
        char arr[] = s.toCharArray();
        return processToFindScore(arr);
    }

    private int processToFindScore(char[] arr) {
        Stack<String> stack = new Stack<>();
        int index = 0;
        int result = 0;

        while (index < arr.length) {
            if (arr[index] == '(') {
                stack.push("(");
            } else {
                int sum = 0;
                while (stack.peek() != "(") {
                    sum += Integer.parseInt(stack.peek());
                    stack.pop();
                }
                stack.pop();
                if (sum == 0) {
                    stack.push("1");
                } else {
                    sum *= 2;
                    stack.push(String.valueOf(sum));
                }
            }
            index++;
        }

        while (!stack.isEmpty()) {
            result += Integer.parseInt(stack.pop());
        }
        return result;
    }
}

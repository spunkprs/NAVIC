package microsoft.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
You are given a string s that consists of lower case English letters and brackets.

Reverse the strings in each pair of matching parentheses, starting from the innermost one.

Your result should not contain any brackets

Constraints:-

a.) 1 <= s.length <= 2000
b.) s only contains lower case English characters and parentheses.
c.) It is guaranteed that all parentheses are balanced
 * */

public class ReverseSubStringsBetweenEachPairOfParantheses {

    public static void main(String ar[]) {
        ReverseSubStringsBetweenEachPairOfParantheses unit = new ReverseSubStringsBetweenEachPairOfParantheses();
        String s = "a(bcdefghijkl(mno)p)q";
        System.out.print("Reversed substring is " + unit.reverseParentheses(s));
    }

    public String reverseParentheses(String s) {
        char arr[] = s.toCharArray();
        Stack<String> stack = new Stack<>();
        processToReverseString(stack, arr);
        if (stack.size() == 1) {
            return stack.pop();
        } else {
            StringBuilder sb = new StringBuilder();
            List<String> intermittentResult = new ArrayList<>();

            while (!stack.isEmpty()) {
                intermittentResult.add(stack.peek());
                stack.pop();
            }

            for (int i = intermittentResult.size() - 1; i >= 0; i--) {
                sb.append(intermittentResult.get(i));
            }
            return sb.toString();
        }
    }

    private void processToReverseString(Stack<String> stack, char arr[]) {
        int index = 0;

        while (index < arr.length) {
            if (arr[index] == '(' || Character.isLowerCase(arr[index])) {
                stack.push(String.valueOf(arr[index]));
            } else if (arr[index] == ')') {
                StringBuilder sb = new StringBuilder();
                while (!stack.peek().equals("(")) {
                    String peekedString = stack.peek();
                    if (peekedString.length() > 1) {
                        StringBuilder intermittentSb = new StringBuilder(peekedString);
                        sb.append(intermittentSb.reverse());
                    } else {
                        sb.append(peekedString);
                    }
                    stack.pop();
                }
                stack.pop();
                stack.push(sb.toString());
            }
            index++;
        }
    }
}

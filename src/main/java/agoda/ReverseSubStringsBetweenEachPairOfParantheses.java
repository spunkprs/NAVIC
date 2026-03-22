package agoda;

import java.util.Stack;

/**
Problem : 1190

You are given a string s that consists of lower case English letters and brackets.

Reverse the strings in each pair of matching parentheses, starting from the innermost one.

Your result should not contain any brackets.


Constraints:-
a.) 1 <= s.length <= 2000
b.) s only contains lower case English characters and parentheses.
c.) It is guaranteed that all parentheses are balanced.
 * */

public class ReverseSubStringsBetweenEachPairOfParantheses {

    public static void main(String ar[]) {

        ReverseSubStringsBetweenEachPairOfParantheses unit = new ReverseSubStringsBetweenEachPairOfParantheses();

        String s = "s()uteawj((eg))";

        System.out.println("Reversed substring within brackets is " + unit.reverseParentheses(s));
    }

    public String reverseParentheses(String s) {
        if (!(s.contains("(") && s.contains(")"))) {
            return s;
        } else if (s.indexOf("(") > 0) {
            int startingIndexOfBracket = s.indexOf("(");
            String prefixString = s.substring(0, startingIndexOfBracket);
            return prefixString + parseInputAndPublishFinalResult(s.substring(startingIndexOfBracket, s.length()));
        } else {
            return parseInputAndPublishFinalResult(s);
        }
    }

    private String parseInputAndPublishFinalResult(String string) {
        char arr[] = string.toCharArray();
        int index = 0;
        Stack<Node> utilityStack = new Stack<>();

        String intermittentString = "";
        int intermittentStringStartIndex = -1;
        int intermittentStringEndIndex = -1;
        boolean flag = false;

        while (index < string.length()) {
            if (arr[index] == '(') {
                StringBuilder sb = new StringBuilder();
                Node node = new Node(index, sb);
                utilityStack.push(node);
                flag = true;
            } else if (arr[index] == ')') {
            Node poppedNode = utilityStack.pop();
            flag = false;

            if (intermittentString.isEmpty()) {
                intermittentStringStartIndex = poppedNode.startIndex;
                intermittentStringEndIndex = poppedNode.endIndex;
                intermittentString = poppedNode.sb.reverse().toString();
            } else {
                if (poppedNode.endIndex < intermittentStringStartIndex) {
                    poppedNode.sb.append(intermittentString);
                    intermittentString = poppedNode.sb.reverse().toString();
                    intermittentStringStartIndex = poppedNode.startIndex;
                } else if (poppedNode.startIndex > intermittentStringEndIndex) {
                    intermittentString += poppedNode.sb.reverse().toString();
                    intermittentStringEndIndex = poppedNode.endIndex;
                }
            }
            } else {
                if (flag) {
                    Node peekedNode = utilityStack.peek();
                    peekedNode.sb.append(arr[index]);
                    peekedNode.endIndex = index;
                } else {
                    if (!intermittentString.isEmpty()) {
                        if (index > intermittentStringEndIndex) {
                            intermittentString += arr[index];
                            intermittentStringEndIndex = index;
                        }
                    } else {
                        intermittentString += arr[index];
                        intermittentStringEndIndex = index;
                    }
                }
            }
            index++;
        }
        return intermittentString;
    }

    static class Node {
        private int startIndex;
        private int endIndex;
        private StringBuilder sb;

        public Node(int startIndex, StringBuilder sb) {
            this.startIndex = startIndex;
            this.sb = sb;
        }
    }
}

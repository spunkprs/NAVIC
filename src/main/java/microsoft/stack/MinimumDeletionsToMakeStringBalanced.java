package microsoft.stack;

import java.util.Stack;

public class MinimumDeletionsToMakeStringBalanced {

    public static void main(String ar[]) {
        MinimumDeletionsToMakeStringBalanced unit = new MinimumDeletionsToMakeStringBalanced();
        String s = "aababbab";
        System.out.print("Minimum deletions to make String balanced is " + unit.minimumDeletions(s));
    }

    public int minimumDeletions(String s) {
        char arr[] = s.toCharArray();
        int deletionCount = 0;
        Stack<Character> stack = new Stack<>();

        int index = 0;
        while (index < arr.length) {
            char ch = arr[index];
            if (stack.isEmpty()) {
                stack.push(ch);
            } else {
                char peekedCharacter = stack.peek();
                if (peekedCharacter == 'b' && ch == 'a') {
                    stack.pop();
                    deletionCount++;
                } else {
                    stack.push(ch);
                }
            }
            index++;
        }
        return deletionCount;
    }
}

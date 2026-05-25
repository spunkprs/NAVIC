package microsoft.stack;

import java.util.Stack;

/**
Problem : 1653
Link : https://leetcode.com/problems/minimum-deletions-to-make-string-balanced/description/?envType=problem-list-v2&envId=stack
You are given a string s consisting only of characters 'a' and 'b'.

You can delete any number of characters in s to make s balanced. s is balanced if there is no pair of
indices (i,j) such that i < j and s[i] = 'b' and s[j]= 'a'.

Return the minimum number of deletions needed to make s balanced.

Constraints:-

a.) 1 <= s.length <= 10^5
b.) s[i] is 'a' or 'b'

Level : Medium

Time Complexity = O(N), where N being length of String
Space Complexity = O(N)
 * */

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

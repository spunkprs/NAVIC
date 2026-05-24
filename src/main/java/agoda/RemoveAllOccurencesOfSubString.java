package agoda;

/**
Problem : 1910
Link : https://leetcode.com/problems/remove-all-occurrences-of-a-substring/description/?envType=problem-list-v2&envId=stack

Given two strings s and part, perform the following operation on s until all occurrences of the substring part are removed:

Find the leftmost occurrence of the substring part and remove it from s.
Return s after removing all occurrences of part.

A substring is a contiguous sequence of characters in a string.


Constraints:-
a.) 1 <= s.length <= 1000
b.) 1 <= part.length <= 1000
c.) s and part consists of lowercase English letters.

Time Complexity = O(N^2/M), where N is the length of word && M is the length of contained word
Space Complexity = O(N)
 * */

public class RemoveAllOccurencesOfSubString {

    public static void main(String ar[]) {
        RemoveAllOccurencesOfSubString unit = new RemoveAllOccurencesOfSubString();
        String word = "axxxxyyyyb";
        String containedWord = "xy";

        System.out.print("Word post removing all occurences of " + containedWord + " is " + unit.removeOccurrences(word, containedWord));
    }

    public String removeOccurrences(String s, String part) {
        StringBuilder sb = new StringBuilder(s);
        int startIndex = sb.indexOf(part);

        while (startIndex != -1) {
            sb.delete(startIndex, startIndex + part.length());
            startIndex = sb.indexOf(part);
        }
        return sb.toString();
    }
}

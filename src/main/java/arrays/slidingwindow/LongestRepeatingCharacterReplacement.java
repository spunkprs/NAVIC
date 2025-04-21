package arrays.slidingwindow;

/*
*
* Given a string s and an integer k, find the length of the longest substring in s,
where all characters are identical, after replacing, at most, k characters with any other lowercase English character.

Constraints:
1.) 1 ≤ s.length ≤ pow(10, 3)
2.) s consists of only lowercase English characters
3.) 0 ≤ k ≤ s.length
*
* */

public class LongestRepeatingCharacterReplacement {

    public static void main(String ar[]) {
        LongestRepeatingCharacterReplacement unit = new LongestRepeatingCharacterReplacement();
        String inputWord = "aabccbb";
        int k = 2;
        System.out.print("Length of longest substring where all characters are identical :: " + unit.longestRepeatingCharacterReplacement(inputWord, k));
    }

    public int longestRepeatingCharacterReplacement(String s, int k) {
        return -1;
    }

}

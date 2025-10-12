package leetcode75;

import java.util.LinkedList;
import java.util.Queue;

/**
Given a string s and an integer k, return the maximum number of vowel
letters in any substring of s with length k.

Vowel letters in English are 'a', 'e', 'i', 'o', and 'u'.

Constraints:-

a.) 1 <= s.length <= 105
b.) s consists of lowercase English letters.
c.) 1 <= k <= s.length


 * */

public class MaximumNumberOfVowelsInSubstringOfGivenLength {

    public static void main(String ar[]) {
        MaximumNumberOfVowelsInSubstringOfGivenLength unit =
                new MaximumNumberOfVowelsInSubstringOfGivenLength();

        String word = "leetcode";
        int k = 3;

        System.out.println("Maximum number of vowels in the string " + word +
                " having length of " + k + " is " + unit.maxVowels(word, k));
    }

    public int maxVowels(String s, int k) {
        int result = 0;
        int vowelCount = 0;
        Queue<Integer> queue = new LinkedList();
        char arr[] = s.toCharArray();

        int index = 0;

        while (index < k) {
            queue.add(index);
            if (isLetterVowel(arr[index])) {
                vowelCount++;
            }
            index++;
        }

        result = updateResult(result, vowelCount);

        if (k == s.length()) {
            return vowelCount;
        } else {
            int j = index;
            while(j < arr.length) {
                Integer peekedIndex = queue.peek();
                if (isLetterVowel(arr[peekedIndex])) {
                    vowelCount--;
                    result = updateResult(result, vowelCount);
                }

                if (isLetterVowel(arr[j])) {
                    vowelCount++;
                    result = updateResult(result, vowelCount);
                }

                queue.add(j);
                queue.poll();
                j++;
            }
            return result;
        }
    }

    private int updateResult(int intermimResult, int vowelCount) {
        return vowelCount >= intermimResult ? vowelCount : intermimResult;
    }

    private boolean isLetterVowel(char character) {
        if (character == 'a' || character == 'e' || character == 'i' || character == 'o' || character == 'u') {
            return true;
        }
        return false;
    }
}

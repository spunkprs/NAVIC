package leetcode75;

/**
 You are given two strings word1 and word2. Merge the strings by adding letters
 in alternating order, starting with word1. If a string is longer than the other,
 append the additional letters onto the end of the merged string.

 Return the merged string.

Constraints:--

1 <= word1.length, word2.length <= 100
word1 and word2 consist of lowercase English letters.
 * */

public class MergeStringsAlternatively {

    public static void main(String ar[]) {
        MergeStringsAlternatively unit = new MergeStringsAlternatively();
        String wordOne = "ab";
        String wordTwo = "pqrs";

        System.out.println("Merged string for provided strings " + wordOne + " and " + wordTwo + " is " +
                unit.mergeAlternately(wordOne, wordTwo));
    }


    public String mergeAlternately(String word1, String word2) {
        int lengthOne = word1.length();
        int lengthTwo = word2.length();

        int indexOne = 0;
        int indexTwo = 0;

        StringBuilder result = new StringBuilder();

        while(indexOne < lengthOne && indexTwo < lengthTwo) {
            result.append(word1.charAt(indexOne));
            result.append(word2.charAt(indexTwo));
            indexOne++;
            indexTwo++;
        }

        if (lengthOne > lengthTwo) {
            while (indexOne < lengthOne) {
                result.append(word1.charAt(indexOne));
                indexOne++;
            }
        } else if (lengthOne < lengthTwo) {
            while (indexTwo < lengthTwo) {
                result.append(word2.charAt(indexTwo));
                indexTwo++;
            }
        }
        return result.toString();
    }


}

package microsoft.arrays;

/**
Problem : 727
Link : https://leetcode.com/problems/minimum-window-subsequence/description/
Level : HARD
Given strings s1 and s2, return the minimum contiguous substring part of s1, so that s2 is a subsequence of the part.

If there is no such window in s1 that covers all characters in s2, return the empty string "".
If there are multiple such minimum-length windows, return the one with the left-most starting index.


Constraints:-

a.) 1 <= s1.length <= 2 * 10^4
b.) 1 <= s2.length <= 100
c.) s1 and s2 consist of lowercase English letters.
 * */


public class MinimumWindowSubSequence {

    private int startIndexFinal = -1;
    private int endIndexFinal = -1;

    public static void main(String ar[]) {
        MinimumWindowSubSequence unit = new MinimumWindowSubSequence();
        String s1 = "jmeqksfrsdcmsiwvaovztaqenprpvnbstl";
        String s2 = "l";

        System.out.print("Minimum window subsequence is " + unit.minWindow(s1, s2));
    }


    public String minWindow(String s1, String s2) {
        if (s1.length() < s2.length()) {
            return "";
        } else if (s1.length() == s2.length()) {
            if (s1.equals(s2)) {
                return s1;
            }
            return "";
        } else {
            processToFindMinWindow(s1, s2);
            if (startIndexFinal == -1 && endIndexFinal == -1) {
                return "";
            } else {
                return s1.substring(startIndexFinal, endIndexFinal + 1);
            }
        }
    }

    private void processToFindMinWindow(String s1, String s2) {
        char arrOne[] = s1.toCharArray();
        char arrTwo[] = s2.toCharArray();

        int startIndexOne = 0;
        int startIndexTwo = 0;
        int endIndexOne = 0;
        int endIndexTwo = 0;
        boolean foundFirstCharacterMatch = false;
        int length = Integer.MAX_VALUE;

        while (endIndexOne < arrOne.length) {

            if (endIndexTwo < arrTwo.length) {
                if (arrOne[endIndexOne] == arrTwo[endIndexTwo]) {
                    endIndexOne++;
                    endIndexTwo++;

                    if (!foundFirstCharacterMatch) {
                        foundFirstCharacterMatch = true;
                    }
                } else {
                    if (!foundFirstCharacterMatch) {
                        startIndexOne++;
                        endIndexOne = startIndexOne;
                    } else {
                        endIndexOne++;
                    }
                }
            } else {
                startIndexTwo = 0;
                endIndexTwo = 0;
                foundFirstCharacterMatch = false;
                length = updateLength(endIndexOne - 1, startIndexOne, length);
                if (endIndexOne - startIndexOne > s2.length() && s1.substring(startIndexOne, endIndexOne).contains(s2)) {
                    int startIndex = s1.indexOf(s2);
                    length = updateLength(endIndexOne - 1, startIndex, length);
                    break;
                }
            }
        }

        if (foundFirstCharacterMatch && endIndexTwo >= arrTwo.length) {
            length = updateLength(endIndexOne - 1, startIndexOne, length);
        }
    }

    private int updateLength(int endIndex, int startIndex, int length) {
        if (endIndex - startIndex + 1 < length) {
            endIndexFinal = endIndex;
            startIndexFinal = startIndex;
            return endIndex - startIndex + 1;
        }
        return length;
    }
}

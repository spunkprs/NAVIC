package microsoft.arrays;

import java.util.HashMap;
import java.util.Map;

/**
Problem : 1839

A string is considered beautiful if it satisfies the following conditions:

Each of the 5 English vowels ('a', 'e', 'i', 'o', 'u') must appear at least once in it.
The letters must be sorted in alphabetical order (i.e. all 'a's before 'e's, all 'e's before 'i's, etc.).
For example, strings "aeiou" and "aaaaaaeiiiioou" are considered beautiful, but "uaeio", "aeoiu", and "aaaeeeooo" are not beautiful.

Given a string word consisting of English vowels, return the length of the longest beautiful substring of word. If no such substring exists, return 0.

A substring is a contiguous sequence of characters in a string

Constraints:-

a.) 1 <= word.length <= 5 * 10^5
b.) word consists of characters 'a', 'e', 'i', 'o', and 'u'.

Time Complexity = O(N), where N being number of elements in the array
Space Complexity = O(1)
 * */

public class LongestSubStringOfAllVowelsInOrder {

    public static void main(String ar[]) {
        LongestSubStringOfAllVowelsInOrder unit = new LongestSubStringOfAllVowelsInOrder();
        String word = "aeiaaioaaaaeiiiiouuuooaauuaeiu";
        System.out.print("Length of longest substring is " + unit.longestBeautifulSubstring(word));
    }

    public int longestBeautifulSubstring(String word) {

        if (word.length() < 5) {
            return 0;
        } else {
           return processToFetchLongestBeautifulSubString(word);
        }
    }

    private int processToFetchLongestBeautifulSubString(String word) {
        char arr[] = word.toCharArray();
        int leftPointer = 0;
        int rightPointer = 0;
        int maxLength = 0;

        Map<Character, Character> map = new HashMap<>();
        map.put('a', 'e');
        map.put('e', 'i');
        map.put('i', 'o');
        map.put('o', 'u');
        boolean processStarted = false;
        char previousCharacter = 'X';

        while (rightPointer < word.length()) {
            if (!processStarted) {
                if (arr[rightPointer] != 'a') {
                    rightPointer++;
                    leftPointer++;
                } else {
                    rightPointer++;
                    previousCharacter = 'a';
                    processStarted = true;
                }
            } else {
                if (previousCharacter != 'u') {
                    if (arr[rightPointer] == map.get(previousCharacter) || arr[rightPointer] == previousCharacter) {
                        if (arr[rightPointer] == 'u') {
                            previousCharacter = 'u';
                            maxLength = Math.max(maxLength, rightPointer - leftPointer + 1);
                        } else {
                            previousCharacter = arr[rightPointer];
                        }
                        rightPointer++;
                    } else {
                        leftPointer = rightPointer;
                        processStarted = false;
                        previousCharacter = 'X';
                    }
                } else {
                    if (arr[rightPointer] == 'u') {
                        previousCharacter = 'u';
                        maxLength = Math.max(maxLength, rightPointer - leftPointer + 1);
                        rightPointer++;
                    } else {
                        leftPointer = rightPointer;
                        processStarted = false;
                        previousCharacter = 'X';
                    }
                }
            }
        }
        return maxLength;
    }
}

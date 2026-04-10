package microsoft.arrays;

import java.util.HashMap;
import java.util.Map;

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

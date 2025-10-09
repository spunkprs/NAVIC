package leetcode75;

/**
Given a string s, reverse only all the vowels in the string and return it.

The vowels are 'a', 'e', 'i', 'o', and 'u',
and they can appear in both lower and upper cases, more than once

Source : LeetCode
Level : Easy
Status : Accepted
 * */

public class ReverseVowelsOfString {

    public static void main(String ar[]) {
        ReverseVowelsOfString unit = new ReverseVowelsOfString();
        String input = "leetcode";
        System.out.println("String with reversed vowels for string " + input + " is "
                + unit.reverseVowels(input));
    }

    public String reverseVowels(String s) {
        char arr[] = s.toCharArray();
        if (arr.length > 1) {
            int leftIndex = 0;
            int rightIndex = arr.length - 1;

            while (leftIndex <= rightIndex) {
                if (isCharacterVowel(arr[leftIndex]) && isCharacterVowel(arr[rightIndex])) {
                    char temp = arr[rightIndex];
                    arr[rightIndex] = arr[leftIndex];
                    arr[leftIndex] = temp;
                    leftIndex++;
                    rightIndex--;
                } else if (!isCharacterVowel(arr[leftIndex]) && !isCharacterVowel(arr[rightIndex])) {
                    leftIndex++;
                    rightIndex--;
                } else if (isCharacterVowel(arr[leftIndex]) && !isCharacterVowel(arr[rightIndex])) {
                    rightIndex--;
                } else {
                    leftIndex++;
                }
            }
            return prepareResult(arr);
        }
        return s;
    }

    private String prepareResult(char arr[]) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            sb.append(arr[i]);
        }
        return sb.toString();
    }

    private boolean isCharacterVowel(char character) {
        if (character == 'a' || character == 'e' || character == 'i' || character == 'o' || character == 'u' || character == 'A' || character == 'E' || character == 'I' || character == 'O' || character == 'U') {
            return true;
        }
        return false;
    }


}

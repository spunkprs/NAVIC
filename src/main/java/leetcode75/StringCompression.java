package leetcode75;


/**
 Given an array of characters chars, compress it using the following algorithm:

 Begin with an empty string s. For each group of consecutive repeating characters in chars:

 If the group's length is 1, append the character to s.
 Otherwise, append the character followed by the group's length.
 The compressed string s should not be returned separately, but instead,
 be stored in the input character array chars. Note that group lengths that are 10 or longer
 will be split into multiple characters in chars.

 After you are done modifying the input array, return the new length of the array.

 You must write an algorithm that uses only constant extra space.

 Note: The characters in the array beyond the returned length do not matter and should be ignored.

 Source : LeetCode
 Level : Medium
 * */

public class StringCompression {

    public static void main(String ar[]) {
        StringCompression unit = new StringCompression();
        String word = "aaabbaa";
        System.out.print("Compressed string length is " + unit.compress(word.toCharArray()));
    }

    public int compress(char[] chars) {
        int startIndex = 0;
        int index = 0;
        int count = 0;

        while (index < chars.length - 1) {
            if (chars[index] == chars[index + 1]) {
                index++;
            } else {
                int diff = index - startIndex + 1;
                count++;
                if (diff > 1) {
                    count += String.valueOf(diff).length();
                    char arr[] = String.valueOf(diff).toCharArray();
                    for (int i = 0; i < arr.length; i++) {
                        chars[startIndex + 1 + i] = arr[i];
                    }
                }
                startIndex = index + 1;
                index++;
            }
        }

        if (index - startIndex > 0) {
            int diff = index - startIndex + 1;
            count++;
            if (diff > 1) {
                count += String.valueOf(diff).length();
                char arr[] = String.valueOf(diff).toCharArray();
                for (int i = 0; i < arr.length; i++) {
                    chars[startIndex + 1 + i] = arr[i];
                }
            }
        } else {
            count++;
        }
        return count;
    }
}

package agoda;

import java.util.ArrayList;
import java.util.List;


/**
Problem : 2734
Given a string s consisting of lowercase English letters. Perform the following operation:

Select any non-empty substring then replace every letter of the substring with the preceding letter of the English alphabet.
For example, 'b' is converted to 'a', and 'a' is converted to 'z'.
Return the lexicographically smallest string after performing the operation.


Typical String manipulation problem, have made use of greedy approach to solve the problem

Time Complexity = O(N), where N being number of elements in the input String
Space Complexity = O(N) , where N being number of elements in the input String
 * */

public class LexicographicallySmallestStringAfterOperation {

    public static void main(String ar[]) {
        LexicographicallySmallestStringAfterOperation unit = new LexicographicallySmallestStringAfterOperation();
        String s = "aaba";
        System.out.println("Lexicographically smallest string after performing operations is " + unit.smallestString(s));
    }

    public String smallestString(String s) {
        char arr[] = s.toCharArray();
        String result = "";

        if (s.length() == 1) {
            result = performOperation(arr, 0, arr.length - 1);
        } else {
            List<Integer> indexes = findExistenceOfAlphabet(arr, 'a');
            if (indexes.size() == 0) {
                return performOperation(arr, 0, arr.length - 1);
            } else {
                if (indexes.size() == 1) {
                    int index = indexes.get(0);
                    if (index == 0) {
                        result = String.valueOf('a');
                        if (1 <= arr.length - 1) {
                            result += performOperation(arr, 1, arr.length - 1);
                        }
                    } else {
                        result += performOperation(arr, 0, index - 1) + s.substring(index, arr.length);
                    }
                } else {
                    int firstIndex = indexes.get(0);
                    int secondIndex = indexes.get(1);
                    if (firstIndex != 0) {
                        result += performOperation(arr, 0, firstIndex - 1) + s.substring(firstIndex, arr.length);
                    } else if (firstIndex == 0) {
                        int difference = secondIndex - firstIndex;
                        if (difference > 1) {
                            result += 'a' + performOperation(arr, 1, secondIndex - 1) + s.substring(secondIndex, arr.length);
                        } else {
                            int index = 1;
                            boolean jump = false;
                            while (index <= indexes.size() - 1) {
                                secondIndex = indexes.get(index);
                                difference = secondIndex - firstIndex;
                                if (difference > 1) {
                                    jump = true;
                                    break;
                                } else {
                                    firstIndex = secondIndex;
                                    index++;
                                }
                            }

                            if (jump) {
                                result += s.substring(0, firstIndex + 1) + performOperation(arr, firstIndex + 1, secondIndex - 1);
                                if (secondIndex <= s.length() - 1) {
                                    result += s.substring(secondIndex, arr.length);
                                }
                            } else {
                                if (secondIndex == arr.length - 1) {
                                    result += s.substring(0, secondIndex) + performOperation(arr, secondIndex, secondIndex);
                                } else {
                                    result += s.substring(0, secondIndex + 1) + performOperation(arr, secondIndex + 1, arr.length - 1);
                                }
                            }
                        }
                    }
                }
            }
        }
        return result;
    }

    private String performOperation(char[] arr, int startIndex, int endIndex) {
        StringBuilder sb = new StringBuilder();
        for (int i = startIndex; i <= endIndex; i++) {
            int asciiCode = arr[i];
            char updatedChar = arr[i] == 'a' ? 'z' : (char) (asciiCode - 1);
            sb.append(updatedChar);
        }
        return sb.toString();
    }

    private List<Integer> findExistenceOfAlphabet(char[] arr, char character) {
        List<Integer> indexes = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == character) {
                indexes.add(i);
            }
        }
        return indexes;
    }
}

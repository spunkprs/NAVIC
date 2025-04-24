package arrays.slidingwindow;

/*
* A DNA sequence consists of nucleotides represented by the letters ‘A’, ‘C’, ‘G’, and ‘T’ only.
* For example, “ACGAATTCCG” is a valid DNA sequence.
*
* Given a string, s, that represents a DNA sequence, return all the 10-letter-long sequences (continuous substrings of exactly 10 characters) that appear more than once in s.
* You can return the output in any order.

Constraints:
1.) 1 ≤ s.length ≤ pow(10, 3)
2.) s[i] is either 'A', 'C', 'G', or 'T'.
*
* */


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RepeatedDNASequence {

    public static void main(String ar[]) {
        String input = "ACACACAT";
        RepeatedDNASequence unit = new RepeatedDNASequence();
        List<String> result = unit.findRepeatedDnaSequences(input);
        System.out.println("Repeated DNA sequences are following :: ");
        result.forEach(x -> {
            System.out.println(x);
        });
    }

    public List<String> findRepeatedDnaSequences(String s) {
        char arr[] = s.toCharArray();
        int lengthOfSubstring = 5;
        if (arr.length <= lengthOfSubstring - 1) {
            return new ArrayList<>();
        } else {
            return processToFindRepeatedDNASequences(arr, lengthOfSubstring);
        }
    }

    private List<String> processToFindRepeatedDNASequences(char[] arr, int lengthOfSubstring) {
        //Number of potential substring will be arr.length - lengthOfSubstring + 1
        int startIndex = 0;
        int endIndex = arr.length - lengthOfSubstring;
        Map<String, Integer> map = new HashMap<>();
        int count = 0;
        String word = "";

        while(startIndex <= endIndex) {
            if (count == 0) {
                word = prepareWord(arr, lengthOfSubstring, startIndex);
                pushWordToMap(word, map);
                count++;
            } else {
                StringBuilder sb = new StringBuilder(word);
                word = sb.deleteCharAt(0).append(arr[startIndex + lengthOfSubstring - 1]).toString();
                pushWordToMap(word, map);
            }
            startIndex++;
        }
        return prepareResult(map);
    }

    private List<String> prepareResult(Map<String, Integer> map) {
        List<String> resultantStrings = new ArrayList<>();
        map.keySet().forEach(key -> {
            if (map.get(key) > 1) {
                resultantStrings.add(key);
            }
        });
        return resultantStrings;
    }

    private String prepareWord(char[] arr, int lengthOfSubstring, int startIndex) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = startIndex; i <= startIndex + lengthOfSubstring - 1; i++) {
            stringBuilder.append(arr[i]);
        }
        return stringBuilder.toString();
    }

    private void pushWordToMap(String word, Map<String, Integer> map) {
        if (!map.containsKey(word)) {
            map.put(word, 1);
        } else {
            map.put(word, map.get(word) + 1);
        }
    }
}

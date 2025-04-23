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
import java.util.List;

public class RepeatedDNASequence {

    public static void main(String ar[]) {
        String input = "";
        RepeatedDNASequence unit = new RepeatedDNASequence();
        List<String> result = unit.findRepeatedDnaSequences(input);
        System.out.println("Repeated DNA sequences are following :: ");
        result.forEach(x -> {
            System.out.println(x);
        });
    }

    public List<String> findRepeatedDnaSequences(String s) {
        char arr[] = s.toCharArray();
        if (arr.length <= 9) {
            return new ArrayList<>();
        } else {
            return processToFindRepeatedDNASequences(arr);
        }
    }

    private List<String> processToFindRepeatedDNASequences(char[] arr) {
        return new ArrayList<>();
    }

}

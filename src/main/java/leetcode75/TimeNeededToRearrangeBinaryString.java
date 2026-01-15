package leetcode75;

/**
Problem : 2380
You are given a binary string s. In one second, all occurrences of "01" are simultaneously replaced with "10". This process repeats until no occurrences of "01" exist.

Return the number of seconds needed to complete this process

 Constraints:-

a.) 1 <= s.length <= 1000
b.) s[i] is either '0' or '1'
 * */

public class TimeNeededToRearrangeBinaryString {

    public int secondsToRemoveOccurrences(String s) {
        String intermittentString = s;

        int result = 0;

        while (intermittentString.contains("01")) {
            intermittentString = intermittentString.replace("01", "10");
            result++;
        }
        return result;
    }
}

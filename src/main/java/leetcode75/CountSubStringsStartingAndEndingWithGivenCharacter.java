package leetcode75;

import java.util.ArrayList;
import java.util.List;

public class CountSubStringsStartingAndEndingWithGivenCharacter {

    public static void main(String ar[]) {
        CountSubStringsStartingAndEndingWithGivenCharacter unit = new CountSubStringsStartingAndEndingWithGivenCharacter();
    }

    public long countSubstrings(String s, char c) {
        char arr[] = s.toCharArray();
        List<Integer> positions = new ArrayList();
        long count = 0;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == c) {
                positions.add(i);
            }
        }

        int startIndex = 0;
        int lastIndex = positions.size() - 1;

        count+= positions.size();
        while (startIndex < lastIndex) {
            long diff = lastIndex - (startIndex + 1) + 1;
            count += diff;
            startIndex++;
        }
        return count;
    }
}

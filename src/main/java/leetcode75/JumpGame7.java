package leetcode75;

import java.util.*;

/**
 You are given a 0-indexed binary string s and two integers minJump and maxJump. In the beginning,
 you are standing at index 0, which is equal to '0'. You can move from index i to index j if the
 following conditions are fulfilled:

 a.) i + minJump <= j <= min(i + maxJump, s.length - 1), and
 b.) s[j] == '0'.
 Return true if you can reach index s.length - 1 in s, or false otherwise

 Constraints:-

 a.) 2 <= s.length <= 105
 b.) s[i] is either '0' or '1'.
 c.) s[0] == '0'
 d.) 1 <= minJump <= maxJump < s.length

 * */

public class JumpGame7 {

    public static void main(String ar[]) {
        JumpGame7 unit = new JumpGame7();
        String s = "011010";
        //String s = "01101110";
        int minJump = 2;
        int maxJump = 3;
        System.out.println(unit.canReach(s, minJump, maxJump));
    }

    public boolean canReach(String s, int minJump, int maxJump) {
        char arr[] = s.toCharArray();
        Map<Integer, Boolean> map = new HashMap<>();
        if (arr[arr.length - 1] == '1') {
            return false;
        }
        processToFindCanReachFromSourceToDestination(0, arr, minJump, maxJump, map);
        return map.get(0);
    }

    private boolean processToFindCanReachFromSourceToDestination(int parentIndex, char[] arr, int minJump, int maxJump,
                                                              Map<Integer, Boolean> map) {

        Set<Boolean> results = new HashSet<>();
        if (parentIndex == arr.length - 1 && arr[parentIndex] == '0') {
            map.put(parentIndex, true);
            return map.get(parentIndex);
        } else if (parentIndex < arr.length - 1) {
            List<Integer> childIndexes = fetchChildIndexes(parentIndex, arr, minJump, maxJump);

            for (int index : childIndexes) {
                    if (!map.containsKey(index)) {
                        boolean result = processToFindCanReachFromSourceToDestination(index, arr, minJump, maxJump, map);
                        results.add(result);
                    } else {
                        boolean result = map.get(index);
                        results.add(result);
                        if (result) {
                            break;
                        }
                    }
            }
        }

        if (results.contains(true)) {
            map.put(parentIndex, true);
            return map.get(parentIndex);
        }
        map.put(parentIndex, false);
        return map.get(parentIndex);
    }

    private List<Integer> fetchChildIndexes(int parentIndex, char[] arr, int minJump, int maxJump) {
        List<Integer> childIndexes = new ArrayList<>();
        for (int j = parentIndex + minJump; j <= Math.min(parentIndex + maxJump, arr.length - 1); j++) {
            if (arr[j] == '0') {
                childIndexes.add(j);
            }
        }
        return childIndexes;
    }
}

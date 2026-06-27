package microsoft.arrays;

import java.util.HashMap;
import java.util.Map;

/**
Problem : 567
Link : https://leetcode.com/problems/permutation-in-string/description/

Given two strings s1 and s2, return true if s2 contains a permutation of s1, or false otherwise.

In other words, return true if one of s1's permutations is the substring of s2.

Constraints:-

a.) 1 <= s1.length, s2.length <= 10^4
b.) s1 and s2 consist of lowercase English letters

 * */

public class PermutationInString {

    public static void main(String ar[]) {
        PermutationInString unit = new PermutationInString();
        String sOne = "intention";
        String sTwo = "execution";

        System.out.print(sTwo + " contains substring of " + sOne + " " + unit.checkInclusion(sOne, sTwo));
    }

    public boolean checkInclusion(String s1, String s2) {
        if (s1.length() == s2.length()) {
            Map<Character, Integer> mapOne = prepareMap(s1.toCharArray());
            Map<Character, Integer> mapTwo = prepareMap(s2.toCharArray());
            return compareMaps(mapOne, mapTwo);
        } else if (s1.length() < s2.length()) {
            char arrOne[] = s1.toCharArray();
            char arrTwo[] = s2.toCharArray();
            return processToCheckInclusion(arrOne, arrTwo);
        } else {
            return false;
        }
    }

    private boolean processToCheckInclusion(char[] arrOne, char[] arrTwo) {
        boolean result = false;
        int sumFetchedOne = 0;
        int sumFetchedTwo = 0;

        Map<Character, Integer> mapOne = prepareMap(arrOne);
        Map<Character, Integer> mapTwo = new HashMap<>();

        for (char ch : arrOne) {
            sumFetchedOne += ch;
        }

        for (int i = 0; i < arrOne.length; i++) {
            sumFetchedTwo += arrTwo[i];
            if (mapTwo.containsKey(arrTwo[i])) {
                mapTwo.put(arrTwo[i], mapTwo.get(arrTwo[i]) + 1);
            } else {
                mapTwo.put(arrTwo[i], 1);
            }
        }

        int j = arrOne.length - 1;

        while (j < arrTwo.length) {
            if (sumFetchedOne != sumFetchedTwo) {
            sumFetchedTwo -= arrTwo[j - (arrOne.length - 1)];
            if (mapTwo.containsKey(arrTwo[j - (arrOne.length - 1)])) {
                mapTwo.put(arrTwo[j - (arrOne.length - 1)], Math.max(0, mapTwo.get(arrTwo[j - (arrOne.length - 1)]) - 1));
            }
            if (j + 1 < arrTwo.length) {
                j++;
                sumFetchedTwo += arrTwo[j];
                if (mapTwo.containsKey(arrTwo[j])) {
                    mapTwo.put(arrTwo[j], mapTwo.get(arrTwo[j]) + 1);
                } else {
                    mapTwo.put(arrTwo[j], 1);
                }
            } else {
                break;
            }
            } else {
                if (compareMaps(mapOne, mapTwo)) {
                    result = true;
                    break;
                } else {
                    sumFetchedTwo -= arrTwo[j - (arrOne.length - 1)];
                    if (mapTwo.containsKey(arrTwo[j - (arrOne.length - 1)])) {
                        mapTwo.put(arrTwo[j - (arrOne.length - 1)], Math.max(0, mapTwo.get(arrTwo[j - (arrOne.length - 1)]) - 1));
                    }
                    if (j + 1 < arrTwo.length) {
                        j++;
                        sumFetchedTwo += arrTwo[j];
                        if (mapTwo.containsKey(arrTwo[j])) {
                            mapTwo.put(arrTwo[j], mapTwo.get(arrTwo[j]) + 1);
                        } else {
                            mapTwo.put(arrTwo[j], 1);
                        }
                    } else {
                        break;
                    }
                }
            }
        }
        return result;
    }

    private boolean compareMaps(Map<Character, Integer> mapOne, Map<Character, Integer> mapTwo) {
            for (Character ch : mapOne.keySet()) {
                if (!mapTwo.containsKey(ch) || mapTwo.get(ch) != mapOne.get(ch)) {
                    return false;
                }
            }
        return true;
    }

    private Map<Character, Integer> prepareMap(char[] arr) {
        Map<Character, Integer> map = new HashMap<>();
        for (Character ch : arr) {
            if (map.containsKey(ch)) {
                map.put(ch, map.get(ch) + 1);
            } else {
                map.put(ch, 1);
            }
        }
        return map;
    }
}

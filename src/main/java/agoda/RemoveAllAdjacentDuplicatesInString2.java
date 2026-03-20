package agoda;

import java.util.HashMap;
import java.util.Map;

public class RemoveAllAdjacentDuplicatesInString2 {

    public static void main(String ar[]) {
        RemoveAllAdjacentDuplicatesInString2 unit = new RemoveAllAdjacentDuplicatesInString2();
        //String s = "deeedbbcccbdaa";
        String s = "aaaa";
        //int k = 3;
        int k = 2;
        System.out.print(unit.removeDuplicates(s, k));
    }

    public String removeDuplicates(String s, int k) {

        char arr[] = s.toCharArray();
        int i = 0;
        int windowSize = 0;
        Map<Character, Integer> map = new HashMap<>();
        int windowMinIndex = Integer.MAX_VALUE;
        int windowMaxIndex = Integer.MIN_VALUE;

        while (!s.isEmpty() && i < s.length()) {
            if (windowSize == k) {
                if (map.size() == 1) {
                    if (s.length() == windowSize) {
                        s = "";
                    } else {
                        if (windowMinIndex > 0 && windowMaxIndex < s.length() - 1) {
                            String preString = s.substring(0, windowMinIndex);
                            String postString = s.substring(windowMaxIndex + 1, s.length());
                            s = preString + postString;
                        } else if (windowMinIndex == 0 && windowMaxIndex < s.length() - 1) {
                            s = s.substring(windowMaxIndex + 1, s.length());
                        } else if (windowMinIndex > 0 && windowMaxIndex == s.length() - 1) {
                            s = s.substring(0, windowMinIndex);
                        }
                        arr = s.toCharArray();
                        i = 0;
                        windowSize = 0;
                        windowMinIndex = Integer.MAX_VALUE;
                        windowMaxIndex = Integer.MIN_VALUE;
                        map = new HashMap<>();
                        if (windowSize < k) {
                            pushElementIntoMap(arr[i], map);
                            windowSize++;
                            windowMinIndex = i < windowMinIndex ? i : windowMinIndex;
                            windowMaxIndex = i > windowMaxIndex ? i : windowMaxIndex;
                        }
                    }
                } else {
                    removeElementFromMap(arr[windowMinIndex], map);
                    windowSize--;
                    windowMinIndex++;
                }
            } else if (windowSize < k) {
                pushElementIntoMap(arr[i], map);
                windowSize++;
                windowMinIndex = i < windowMinIndex ? i : windowMinIndex;
                windowMaxIndex = i > windowMaxIndex ? i : windowMaxIndex;
            }

            if (windowSize < k  && i + 1 < s.length()) {
                i++;
            } else if (windowSize < k && i + 1 == s.length()) {
                break;
            }
        }

        return s;
    }

    private void removeElementFromMap(char character, Map<Character, Integer> map) {
        if (map.get(character) > 1) {
            map.put(character, map.get(character) - 1);
        } else if (map.get(character) == 1) {
            map.remove(character);
        }
    }

    private void pushElementIntoMap(char character, Map<Character, Integer> map) {
        if (!map.containsKey(character)) {
            map.put(character, 1);
        } else {
            map.put(character, map.get(character) + 1);
        }
    }
}

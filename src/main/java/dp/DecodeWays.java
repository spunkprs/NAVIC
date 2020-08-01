package dp;

import java.util.HashMap;
import java.util.Map;

public class DecodeWays {


    public int numDecodings(String s) {
        char arr[] = s.toCharArray();
        Map<String, Integer> map = new HashMap<String, Integer>();
        if (arr.length >= 2) {
            return parseAndDecode(arr, "", -1, map);
        } else if (s.equals("0")) {
            return 0;
        }
        return 1;
    }

    private int parseAndDecode(char[] arr, String indexTillNow, int index, Map<String, Integer> map) {
                int result = 0;

                if (map.containsKey(indexTillNow)) {
                    result += map.get(indexTillNow);
                } else {
                    int childIndexOne = index + 1;
                    int childIndexTwo = index + 2;
                    if (childIndexOne <= arr.length - 1 && Integer.parseInt(String.valueOf(arr[childIndexOne])) > 0) {
                        int num = parseAndDecode(arr, String.valueOf(childIndexOne), childIndexOne, map);
                        result+= num;
                        map.put(indexTillNow, result);
                    }

                    if (childIndexTwo <= arr.length - 1) {
                        int num = Integer.parseInt(String.valueOf(arr[childIndexOne]) + String.valueOf(arr[childIndexTwo]));
                        String tillIndex = String.valueOf(childIndexOne) + String.valueOf(childIndexTwo);
                        if (num <= 26 && num >= 10) {
                            if (map.containsKey(tillIndex)) {
                                result+= map.get(tillIndex);
                            } else {
                                int res = parseAndDecode(arr, String.valueOf(childIndexOne) + String.valueOf(childIndexTwo), childIndexTwo, map);
                                result += res;
                                map.put(indexTillNow, result);
                            }
                        }
                    }

                    if (index == arr.length - 1) {
                        map.put(indexTillNow, 1);
                        result += 1;
                    }
                }
        return result;
        }
    }


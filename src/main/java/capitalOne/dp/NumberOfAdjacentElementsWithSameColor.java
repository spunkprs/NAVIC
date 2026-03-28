package capitalOne.dp;

import java.util.*;

/**
Problem : 2672

You are given an integer n representing an array colors of length n where all elements are set to 0's meaning uncolored.
You are also given a 2D integer array queries where queries[i] = [indexi, colori].

For the ith query:

Set colors[indexi] to colori.
Count the number of adjacent pairs in colors which have the same color (regardless of colori).

Return an array answer of the same length as queries where answer[i] is the answer to the ith query

Constraints:-

a.) 1 <= n <= 10^5
b.) 1 <= queries.length <= 10^5
c.) queries[i].length == 2
d.) 0 <= indexi <= n - 1
e.) 1 <=  colori <= 10^5

Brute Force :-
 a.) Time Complexity = O(queries.length * n)
 b.) Space Complexity = O(n)

 Optimal Approach :-
 a.) Time Complexity = O(queries.length) [In the below solution could have made used of simple integer array instead of HashMap]
 b.) Space Complexity = O(n)
 * */

public class NumberOfAdjacentElementsWithSameColor {

    public static void main(String ar[]) {
        NumberOfAdjacentElementsWithSameColor unit = new NumberOfAdjacentElementsWithSameColor();
        int n = 4;
        int queries[][] = {{0,2},{1,2},{3,1},{1,1},{2,1}};
        int resultArr[] = unit.colorTheArray(n, queries);
        Arrays.stream(resultArr).forEach(x-> {
            System.out.println(x);
        });
    }

    public int[] colorTheArray(int n, int[][] queries) {
        List<Integer> resultList = new ArrayList<>();

        Map<Integer, Integer> map = new HashMap<>();

        processToColorTheArray(queries, n, resultList, map);

        return resultList.stream().mapToInt(x-> x).toArray();
    }

    private void processToColorTheArray(int[][] queries, int n, List<Integer> resultList, Map<Integer, Integer> map) {
        for (int i = 0; i < queries.length; i++) {
            int index = queries[i][0];
            int color = queries[i][1];

            if (resultList.isEmpty()) {
                resultList.add(0);
                map.put(index, color);
            } else {
                if (!map.containsKey(index - 1) && !map.containsKey(index + 1)) {
                    int result = resultList.get(resultList.size() - 1);
                    resultList.add(result);
                    map.put(index, color);
                } else if (!map.containsKey(index - 1) && map.containsKey(index + 1)) {
                    if (map.containsKey(index)) {
                        int result = resultList.get(resultList.size() - 1);
                        if (map.get(index) == map.get(index + 1)) {
                            result--;
                            if (color == map.get(index + 1)) {
                                result++;
                            }
                        } else {
                            if (color == map.get(index + 1)) {
                                result++;
                            }
                        }
                        resultList.add(result);
                    } else {
                        int result = resultList.get(resultList.size() - 1);
                        if (color == map.get(index + 1)) {
                            result++;
                        }
                        resultList.add(result);
                    }
                    map.put(index, color);
                } else if (map.containsKey(index - 1) && !map.containsKey(index + 1)) {
                    if (map.containsKey(index)) {
                        int result = resultList.get(resultList.size() - 1);
                        if (map.get(index) == map.get(index - 1)) {
                            result--;
                            if (color == map.get(index - 1)) {
                                result++;
                            }
                        } else {
                            if (color == map.get(index - 1)) {
                                result++;
                            }
                        }
                        resultList.add(result);
                    } else {
                        int result = resultList.get(resultList.size() - 1);
                        if (color == map.get(index - 1)) {
                            result++;
                        }
                        resultList.add(result);
                    }
                    map.put(index, color);
                } else if (map.containsKey(index - 1) && map.containsKey(index + 1)) {
                    if (map.containsKey(index)) {
                        int result = resultList.get(resultList.size() - 1);

                        if (map.get(index) == map.get(index - 1)) {
                            result--;
                            if (color == map.get(index - 1)) {
                                result++;
                            }
                        } else {
                            if (color == map.get(index - 1)) {
                                result++;
                            }
                        }

                        if (map.get(index) == map.get(index + 1)) {
                            result--;
                            if (color == map.get(index + 1)) {
                                result++;
                            }
                        } else {
                            if (color == map.get(index + 1)) {
                                result++;
                            }
                        }
                        resultList.add(result);
                        map.put(index, color);
                    } else {
                        int result = resultList.get(resultList.size() - 1);
                        if (color == map.get(index + 1)) {
                            result++;
                        }
                        if (color == map.get(index - 1)) {
                            result++;
                        }
                        resultList.add(result);
                    }
                    map.put(index, color);
                }
            }
        }
    }
}

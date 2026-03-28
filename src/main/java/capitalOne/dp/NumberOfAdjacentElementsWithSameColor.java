package capitalOne.dp;

import java.util.*;

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

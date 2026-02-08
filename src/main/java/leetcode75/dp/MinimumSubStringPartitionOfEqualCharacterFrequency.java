package leetcode75.dp;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class MinimumSubStringPartitionOfEqualCharacterFrequency {

    public static void main(String ar[]) {
        //String str = "ababcc";
        //String str = "fabccddg";
        String str = "abababaccddb";
        //String str = "bb";
        MinimumSubStringPartitionOfEqualCharacterFrequency unit = new MinimumSubStringPartitionOfEqualCharacterFrequency();
        System.out.println("Minimum partition substring of equal frequency against each character in the substring is " + unit.minimumSubstringsInPartition(str));
    }

    public int minimumSubstringsInPartition(String s) {
        char arr[] = s.toCharArray();
        int helperArr[][] = new int[arr.length][26];
        updateHelperArr(helperArr, arr);
        Map<Pair, Integer> map = new HashMap<>();
        return processToPartitionArray(arr, 0, arr.length - 1, helperArr, map);
    }

    private int processToPartitionArray(char[] arr, int startIndex, int endIndex, int helperArr[][], Map<Pair, Integer> map) {
        int result = Integer.MAX_VALUE;
        if (isPartitionNeeded(startIndex, endIndex, helperArr, arr)) {
            for (int i = startIndex; i < endIndex; i++) {
                Pair pOne = new Pair(startIndex, i);
                Pair pTwo = new Pair(i + 1, endIndex);
                int rOne = Integer.MAX_VALUE;
                int rTwo = Integer.MAX_VALUE;

                if (!map.containsKey(pOne)) {
                   rOne = processToPartitionArray(arr, pOne.startIndex, pOne.endIndex, helperArr, map);
                } else {
                    rOne = map.get(pOne);
                }

                if (!map.containsKey(pTwo)) {
                    rTwo = processToPartitionArray(arr, pTwo.startIndex, pTwo.endIndex, helperArr, map);
                } else {
                    rTwo = map.get(pTwo);
                }

                int sum = rOne + rTwo;
                result = Math.min(result, sum);
            }
            map.put(new Pair(startIndex, endIndex), result);
        } else {
            result = 1;
            map.put(new Pair(startIndex, endIndex), result);
        }
      return result;
    }

    private boolean isPartitionNeeded(int startIndex, int endIndex, int[][] helperArr, char[] arr) {
        boolean result = false;
        if (startIndex == endIndex) {
            return false;
        }
        int frequency = 0;
        int difference = 0;
        if (startIndex == 0) {
            int countArr[] = helperArr[endIndex];
            for (int i = 0; i <= 25; i++) {
                if (countArr[i] != 0) {
                    if (frequency == 0) {
                        frequency = countArr[i];
                    } else {
                        result = frequency != countArr[i];
                        if (result) {
                            break;
                        }
                    }
                }
            }
        } else {
            int countArr[] = helperArr[endIndex];
            int countArrPrevious[] = helperArr[startIndex - 1];
            for (int i = 0; i <= 25; i++) {
                if (countArr[i] - countArrPrevious[i] != 0) {
                    if (frequency == 0) {
                        frequency = countArr[i] - countArrPrevious[i];
                    } else {
                        result = frequency != countArr[i] - countArrPrevious[i];
                        if (result) {
                            break;
                        }
                    }
                }
            }
            difference = helperArr[startIndex - 1][arr[startIndex - 1] - 97];
        }
        if (!result) {
            return endIndex - startIndex + 1 == helperArr[endIndex][arr[endIndex] - 97] - difference;
        }
        return result;
    }

    private void updateHelperArr(int[][] helperArr, char[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (i == 0) {
                int index = arr[i] - 97;
                helperArr[i][index] = 1;
            } else {
                int currentIndex = arr[i] - 97;
                helperArr[i][currentIndex] = 1;
                for (int k = 0; k <= 25; k++) {
                    helperArr[i][k] += helperArr[i - 1][k];
                }
            }
        }
    }

    static class Pair {
        private int startIndex;
        private int endIndex;

        public Pair(int startIndex, int endIndex) {
            this.startIndex = startIndex;
            this.endIndex = endIndex;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair pair = (Pair) o;
            return startIndex == pair.startIndex && endIndex == pair.endIndex;
        }

        @Override
        public int hashCode() {
            return Objects.hash(startIndex, endIndex);
        }
    }
}

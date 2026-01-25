package leetcode75.morganstanley.dp;

public class MinimumSubStringPartitionOfEqualCharacterFrequency {

    public int minimumSubstringsInPartition(String s) {
        char arr[] = s.toCharArray();
        int mapping[][] = prepareFrequencyMapAgainstEachIndex(arr);

        for (int i = 0; i < arr.length; i++) {

        }

        return 0;
    }

    private int[][] prepareFrequencyMapAgainstEachIndex(char[] arr) {
        int mapping[][] = new int[arr.length][26];

        int indexOfCharacter = arr[0] - 'a';
        int value = mapping[0][indexOfCharacter];
        value += 1;
        mapping[0][indexOfCharacter] = value;

        for (int i = 1; i < arr.length; i++) {
            indexOfCharacter = arr[i] - 'a';
            mapping[i][indexOfCharacter] = 1;

            for (int j = 0; j <= 25; j++) {
                int previousValue = mapping[i - 1][j];
                int currentValue = mapping[i][j];
                currentValue += previousValue;
                mapping[i][j] = currentValue;
            }
        }
        return mapping;
    }
}

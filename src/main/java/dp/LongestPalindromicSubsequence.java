package dp;

public class LongestPalindromicSubsequence {
	
	public int longestPalindromeSubseq(String s) {
		char arr[] = s.toCharArray();
		int tmpArray[][] = new int[arr.length + 1][arr.length];
		int lengthOfMaximumSubsequence = 1;
		
		for (int j = 0; j < tmpArray[0].length; j++) {
			tmpArray[1][j] = 1;
		}
		
		for (int i = 2; i < tmpArray.length; i++) {
			for (int j = i - 1; j < tmpArray[0].length; j++) {
				if (i == 2) {
					if (arr[j] == arr[j - 1]) {
						tmpArray[i][j] = 2;
						lengthOfMaximumSubsequence = updateLengthOfMaximumSubsequence(lengthOfMaximumSubsequence, 2);
					} else {
						tmpArray[i][j] = 1;
						lengthOfMaximumSubsequence = updateLengthOfMaximumSubsequence(lengthOfMaximumSubsequence, 1);
					}
				} else {
					int numOne = tmpArray[i-1][j-1];
					int numTwo = tmpArray[i-1][j];
					int result = Math.max(numOne, numTwo);
					if (arr[j] == arr[j - i + 1]) {
						int resultTwo = 2 + tmpArray[i-2][j-1];
						int updatedResult = Math.max(result, resultTwo);
						tmpArray[i][j] = updatedResult;
						lengthOfMaximumSubsequence = updateLengthOfMaximumSubsequence(lengthOfMaximumSubsequence, updatedResult);
					} else {
						tmpArray[i][j] = result;
						lengthOfMaximumSubsequence = updateLengthOfMaximumSubsequence(lengthOfMaximumSubsequence, result);
					}
				}
			}
		}
		
        return lengthOfMaximumSubsequence;
    }

	private int updateLengthOfMaximumSubsequence(int lengthOfMaximumSubsequence, int num) {
		return num > lengthOfMaximumSubsequence ? num : lengthOfMaximumSubsequence;
	}

}

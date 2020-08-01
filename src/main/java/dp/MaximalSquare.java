package dp;

public class MaximalSquare {
	
	private int maximumOnes = 0;
	
	public int maximalSquare(char[][] matrix) {
		if (matrix.length == 0) {
			return 0;
		}
		int cache[][] = new int[matrix.length][matrix[0].length];
		processCache(matrix, cache);
        return maximumOnes * maximumOnes;
    }

	private void processCache(char[][] matrix, int[][] cache) {
		
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[0].length; j++) {
				if (j == 0 || i == 0) {
					if (matrix[i][j] == '1') {
						updateMaximumOnes(1);
					}
					cache[i][j] = Integer.parseInt(String.valueOf(matrix[i][j]));
				} else {
					if (matrix[i][j] == '0') {
						cache[i][j] = 0;
					} else {
						if (matrix[i][j] == '1') {
							if (cache[i - 1][j - 1] == 0 || cache[i - 1][j] == 0 || cache[i][j - 1] == 0) {
								cache[i][j] = 1;
								updateMaximumOnes(cache[i][j]);
							} else if (cache[i - 1][j - 1] >= 1 && cache[i - 1][j] >= 1 && cache[i][j - 1] >= 1) {
								int numOne = Math.min(cache[i - 1][j - 1], cache[i - 1][j]);
								int numTwo = Math.min(numOne, cache[i][j - 1]);
								cache[i][j] = numTwo + 1;
								updateMaximumOnes(cache[i][j]);
							}
						}
					}
				}
			}
		}
	}

	private void updateMaximumOnes(int num) {
		maximumOnes = num >= maximumOnes ? num : maximumOnes;
	}

}

package dp;

public class KnapsackProblem {
	
	private int maxValueObtained = Integer.MIN_VALUE;
	
	public int findMaxiumValueObtainedInKnapsack(int weights[], int valueOfWeights[], int totalWeight) {
		int matrix[][] = new int [weights.length + 1][totalWeight + 1];
		processToPopulateMatrix(matrix, weights, valueOfWeights);
		return maxValueObtained;
	}

	private void processToPopulateMatrix(int[][] matrix, int[] weights, int valueOfWeights[]) {
		for (int i = 1; i <= weights.length - 1; i++) {
			for (int j = 1; j < matrix[0].length; j++) {
				if (weights[i - 1] < j) {
					int a = matrix[i - 1][j];
					int b = valueOfWeights[i - 1] + matrix[i - 1][j - weights[i - 1]];
					matrix[i][j] = Math.max(a, b);
					updateMaxValue(matrix[i][j]);
				} else if (weights[i - 1] == j) {
					matrix[i][j] = Math.max(matrix[i - 1][j], valueOfWeights[i - 1]);
					updateMaxValue(matrix[i][j]);
				} else {
					matrix[i][j] = matrix[i - 1][j];
					updateMaxValue(matrix[i][j]);
				}
			}
		}
	}

	private void updateMaxValue(int value) {
		if (value > maxValueObtained) {
			maxValueObtained = value;
		}
	}
}

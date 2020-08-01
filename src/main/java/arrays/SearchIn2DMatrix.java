package arrays;

public class SearchIn2DMatrix {
	
	public boolean searchMatrix(int[][] matrix, int target) {
		
		try {
			int columns = matrix[0].length;
			int rows = matrix.length;
			
			if (rows > 0 && columns > 0) {
				int i = 0, j = columns - 1;
				while (i >= 0 && i < rows && j >= 0 && j < columns) {
					 if (matrix[i][j] == target) {
						 return true;
					 } else if (matrix[i][j] > target) {
						 j--;
					 } else if (matrix[i][j] < target) {
						 i++;
					 }
				}
		        return false;
			}
			return false;
		} catch (Exception e) {
			return false;
		}
    }

}

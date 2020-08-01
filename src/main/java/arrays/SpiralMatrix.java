package arrays;

import java.util.ArrayList;
import java.util.List;

public class SpiralMatrix {
	

	public List<Integer> spiralOrder(int[][] matrix) {
		
		List<Integer> result = new ArrayList<Integer>();
        
        if (matrix.length >= 1 && matrix[0].length >= 1) {
        	if (matrix.length == 1) {
                for (int j = 0; j < matrix[0].length; j++) {
                    result.add(matrix[0][j]);
                }
            } else if (matrix[0].length == 1) {
                for (int j = 0; j < matrix.length; j++) {
                    result.add(matrix[j][0]);
                }
            } else {
                processToPrepareElementsInSpiralOrder(matrix, result);
            }
        }
        
        
        return result;
    }
	
	
	private void processToPrepareElementsInSpiralOrder(int matrix[][], List<Integer> result) {
        
        int numberOfElements = matrix.length * matrix[0].length;
        
        int num = 0;
        int startRow = 0;
        int startColumn = 0;
        int factor = 0;
        
        while (num < numberOfElements) {
            
            num +=prepareElementsForIncreasingColumn(matrix, startRow + factor, startColumn + factor, factor, result);
            if (num < numberOfElements) {
                num += prepareElementsForIncreasingRow(matrix, startRow + 1 + factor, matrix[0].length - factor - 1, factor, result);
            }
            
            if (num < numberOfElements) {
                num +=prepareElementsForDecreasingColumn(matrix, matrix.length - 1 - factor, matrix[0].length - 1 - factor - 1, factor, result);
            }
            
            if (num < numberOfElements) {
                num +=prepareElementsForDecreasingRow(matrix, matrix.length - 1 - factor - 1, factor, factor, result);
            }
            factor++;
        }
    }
    
    private int prepareElementsForIncreasingColumn(int matrix[][], int startRow, int startColumn, int factor, List<Integer> result) {
        int increment = 0;
        for (int j = startColumn; j <= matrix[0].length - factor - 1; j++) {
            result.add(matrix[startRow][j]);
            increment++;
        }
        return increment;
    }
    
    private int prepareElementsForIncreasingRow(int matrix[][], int startRow, int startColumn, int factor, List<Integer> result) {
        int increment = 0;
        for (int i = startRow; i <= matrix.length - factor - 1; i++) {
            result.add(matrix[i][startColumn]);
            increment++;
        }
        return increment;
    }
    
     private int prepareElementsForDecreasingColumn(int matrix[][], int startRow, int startColumn, int factor, List<Integer> result) {
        int increment = 0;
        for (int j = startColumn; j >= 0 + factor; j--) {
            result.add(matrix[startRow][j]);
            increment++;
        }
        return increment;
    }
    
    private int prepareElementsForDecreasingRow(int matrix[][], int startRow, int startColumn, int factor, List<Integer> result) {
        int increment = 0;
        for (int i = startRow; i >= factor + 1; i--) {
            result.add(matrix[i][startColumn]);
            increment++;
        }
        return increment;
    }

}

package arrays;

import java.util.ArrayList;
import java.util.List;

public class DiagonalTraversalOfMatrix {
	
public int[] findDiagonalOrder(int[][] matrix) {
		int result[] = null;
		try {
			int numberOfElements = matrix.length * matrix[0].length;
	        
	        int initialRow = 0;
	        int initialColumn = 0;
	        
	        result = new int[numberOfElements];
	        
	        if (numberOfElements > 0) {
	        	if (matrix.length == 1 || matrix[0].length == 1) {
	        		if (matrix.length == 1) {
	        			int index = 0;
	        			for (int j = 0; j < matrix[0].length; j++) {
	        				result[index] = matrix[0][j];
	        				index++;
	        			}
	        		} else {
	        			int index = 0;
	        			for (int j = 0; j < matrix.length; j++) {
	        				result[index] = matrix[j][0];
	        				index++;
	        			}
	        		}
	        	} else {
	        		processToFindDiagonalOrder(result, 0, initialRow, initialColumn, matrix);	
	        	}
	        }
		} catch(Exception e) {
			int resultArray[] = {};
			return resultArray;
		}
		return result;
    }

private void processToFindDiagonalOrder(int result[], int index, int initialRow, int initialColumn, int matrix[][])     {
    
	int minRow = 0;
	int maxRow = matrix.length - 1;
	
	int minColumn = 0;
	int maxColumn = matrix[0].length - 1;
	
	while (index < matrix.length * matrix[0].length) {
        
    	while(initialRow >=0 && initialColumn < matrix[0].length) {
            result[index] = matrix[initialRow][initialColumn];
            index++;
            if (initialRow == minRow) {
            	if (initialColumn < maxColumn) {
            		initialColumn++;	
            	} else {
            		initialRow++;
            	}
            	break;
            } else if (initialColumn == maxColumn) {
            	initialRow++;
            	break;
            }
            initialRow--;
            initialColumn++;
        }
        
        
        while (initialRow < matrix.length && initialColumn >= 0) {
            result[index] = matrix[initialRow][initialColumn];
            index++;
            if (initialColumn == minColumn) {
            	if (initialRow < maxRow) {
            		initialRow++;	
            	} else {
            		initialColumn++;
            	}
            	break;
            } else if (initialRow == maxRow) {
            	initialColumn++;
            	break;
            }
            initialRow++;
            initialColumn--;
        }  
    }
}

}

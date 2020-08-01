package dp;

public class PaintHouseTwo {
	
public int minCostII(int[][] costs) {
	
		if (costs.length > 0 ) {
			int tmpArray[][] = new int[costs.length][costs[0].length];
	        
	        int minimum = 0, secondMinimum = 0;
	        int minimumIndex = 0, secondMinimumIndex = 0;
	        
	        for (int i = 0; i < costs[0].length; i++) {
	        	if (i == 0) {
	        		minimum = costs[costs.length - 1][i];
	        		secondMinimum = minimum;
	        		minimumIndex = 0;
	        		secondMinimumIndex = 0;
	        	} else if (i == 1) {
	        		if (costs[costs.length - 1][i] < minimum) {
	        			secondMinimum = minimum;
	        			minimum = costs[costs.length - 1][i];
	        			minimumIndex = 1;
	        		} else {
	        			secondMinimum = costs[costs.length - 1][i];
	        			secondMinimumIndex = 1;
	        		}
	        	} else {
	        		if (costs[costs.length - 1][i] < minimum) {
	        			secondMinimum = minimum;
	        			secondMinimumIndex = minimumIndex;
	        			minimum = costs[costs.length - 1][i];
	        			minimumIndex = i;
	        		} else if (costs[costs.length - 1][i] > minimum && costs[costs.length - 1][i] < secondMinimum) {
	        			secondMinimum = costs[costs.length - 1][i];
	        			secondMinimumIndex = i;
	        		} else if (costs[costs.length - 1][i] == minimum) {
	        			secondMinimum = minimum;
	        			secondMinimumIndex = i;
	        		}
	        	}
	            tmpArray[costs.length - 1][i] = costs[costs.length - 1][i];
	        }
	        
	        for (int k = costs.length - 2; k >= 0; k--) {
	        	for (int i = 0; i < costs[0].length; i++) {
	        		if (i != minimumIndex) {
	        			tmpArray[k][i] = tmpArray[k + 1][minimumIndex] + costs[k][i];
	        		} else {
	        			tmpArray[k][i] = tmpArray[k + 1][secondMinimumIndex] + costs[k][i];
	        		}
	        	}
	        	Pair p = updateMinimumAndSecondMiniumIndex(tmpArray, k);
	        	minimumIndex = p.minimumIndex;
	        	secondMinimumIndex = p.secondMinimumIndex;
	        }
	        
	        return tmpArray[0][minimumIndex];
		} else {
			return 0;
		}
    }

private Pair updateMinimumAndSecondMiniumIndex(int[][] tmpArray, int k) {
	int minimumIndex = 0, secondMinimumIndex = 0;
	int minimum = 0, secondMinimum = 0;
	
	for (int i = 0; i < tmpArray[k].length; i++) {
    	if (i == 0) {
    		minimum = tmpArray[k][i];
    		secondMinimum = minimum;
    		minimumIndex = 0;
    		secondMinimumIndex = 0;
    	} else if (i == 1) {
    		if (tmpArray[k][i] < minimum) {
    			secondMinimum = minimum;
    			minimum = tmpArray[k][i];
    			minimumIndex = 1;
    		} else {
    			secondMinimum = tmpArray[k][i];
    			secondMinimumIndex = 1;
    		}
    	} else {
    		if (tmpArray[k][i] < minimum) {
    			secondMinimum = minimum;
    			secondMinimumIndex = minimumIndex;
    			minimum = tmpArray[k][i];
    			minimumIndex = i;
    		} else if (tmpArray[k][i] > minimum && tmpArray[k][i] < secondMinimum) {
    			secondMinimum = tmpArray[k][i];
    			secondMinimumIndex = i;
    		} else if (tmpArray[k][i] == minimum) {
    			secondMinimum = minimum;
    			secondMinimumIndex = i;
    		}
    	}
    }
	
	return new Pair(minimumIndex, secondMinimumIndex);
	
}

class Pair {
	int minimumIndex;
	int secondMinimumIndex;
	
	Pair(int minimumIndex, int secondMinimumIndex) {
		this.minimumIndex = minimumIndex;
		this.secondMinimumIndex = secondMinimumIndex;
	}
}


}

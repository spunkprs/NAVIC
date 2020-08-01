package dp;

import java.math.BigInteger;

public class NumberOfDiceRollsWithTargetSum {
	
	private int totatlNumberOfWays = 0;
	
	public int numRollsToTarget(int d, int f, int target) {
		/*
		 * processToComputeNumberOfWaysApproachOne(d, f, target, 1, 0); return
		 * totatlNumberOfWays;
		 */
		int divisor = (int)Math.pow(10, 9) + 7;
		BigInteger bigIntegerDivisor = new BigInteger(String.valueOf(divisor));
		BigInteger result = processToComputeNumberOfWaysApproachTwo(d, f, target);
		if (result == null) {
			return 0;
		}
		result = result.mod(bigIntegerDivisor);
		return result.intValue();
    }
	
	
	
	private void processToComputeNumberOfWaysApproachOne(int d, int f, int target, int depth, int sumTillNow) {
        for (int i = 1; i <= f; i++) {
            if (d != depth) {
                if (sumTillNow + i < target) {
                	processToComputeNumberOfWaysApproachOne(d, f, target, depth + 1, sumTillNow + i);
                }
            } else {
                if (sumTillNow + i == target) {
                    totatlNumberOfWays++;
                }
            }
        }
    }
	
	private BigInteger processToComputeNumberOfWaysApproachTwo(int dice, int faces, int targetSum) {
        BigInteger matrix[][] = new BigInteger[dice + 1][targetSum + 1];
        
        for (int j = 1; j <= faces && j <= targetSum; j++) {
        	matrix[1][j] = new BigInteger("1");
        }
        
        for (int i = 2; i <= matrix.length - 1; i++) {
        	for (int j = 1; j <= targetSum; j++) {
        		for (int k = 1; k < j && k <= faces; k++) {
        			BigInteger num = matrix[i - 1][j - k];
        			if (num != null) {
        				if (matrix[i][j] == null) {
        					matrix[i][j] = num;
        				} else {
        					matrix[i][j] = matrix[i][j].add(num);
        				}
        					
        			}
        		}
        	}
        }
        return matrix[matrix.length - 1][matrix[0].length - 1];
    }
}

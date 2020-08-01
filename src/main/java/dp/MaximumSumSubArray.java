package dp;

public class MaximumSumSubArray {
	
	public int maxSubArrayApproachOne(int[] nums) {
		
		if (nums.length > 1) {
			int tmpSpace[][] = new int[nums.length + 1][nums.length];
			int maxSum = Integer.MIN_VALUE;
			for (int j = 0; j < tmpSpace[0].length; j++) {
				tmpSpace[1][j] = nums[j];
				maxSum = updateMaxSum(maxSum, nums[j]);
			} 
			
			for (int i = 2; i < tmpSpace.length; i++) {
				for (int j = i - 1; j < tmpSpace[0].length; j++) {
					int sum = tmpSpace[i - 1][j - 1] + tmpSpace[1][j];
					maxSum = updateMaxSum(maxSum, sum);
					tmpSpace[i][j] = sum;
				}
			}
	        return maxSum;
		} else {
			return nums[0];
		}
    }
	
	public int maxSubArrayApproachTwo(int[] nums) {
		
		if (nums.length > 1) {
			int tmpSpace[] = new int[nums.length];
			int maxSum = Integer.MIN_VALUE;
			
			tmpSpace[0] = nums[0];
			maxSum = updateMaxSum(maxSum, tmpSpace[0]);
			
			for (int i = 1; i < nums.length; i++) {
				if (nums[i] > tmpSpace[i - 1] + nums[i]) {
					tmpSpace[i] = nums[i];
				} else {
					tmpSpace[i] = tmpSpace[i - 1] + nums[i];
				}
				maxSum = updateMaxSum(maxSum, tmpSpace[i]);
			}
			
	        return maxSum;
		} else {
			return nums[0];
		}
    }

	private int updateMaxSum(int maxSum, int sum) {
		return sum > maxSum ? sum : maxSum;
	}

} 

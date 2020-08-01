package dp;

import java.util.HashMap;
import java.util.Map;

public class HouseRobber {
	
	public int rob(int[] nums) {
		
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		int maxAmountOfMoneyRobbed = 0;
		
		if (nums.length > 2) {
			
			map.put(0, nums[0]);
			maxAmountOfMoneyRobbed = updateMaxAmountOfMoneyToBeRobbed(maxAmountOfMoneyRobbed, map.get(0));
			map.put(1, Math.max(nums[1], map.get(0)));
			maxAmountOfMoneyRobbed = updateMaxAmountOfMoneyToBeRobbed(maxAmountOfMoneyRobbed, map.get(1));
			
			for (int i = 2; i < nums.length; i++) {
						int sumOfValue = map.get(i - 2) + nums[i];
						int previousValue = map.get(i - 1);
						
						if (previousValue >= sumOfValue) {
							map.put(i, previousValue);
							maxAmountOfMoneyRobbed = updateMaxAmountOfMoneyToBeRobbed(maxAmountOfMoneyRobbed, previousValue);
						} else {
							map.put(i, sumOfValue);
							maxAmountOfMoneyRobbed = updateMaxAmountOfMoneyToBeRobbed(maxAmountOfMoneyRobbed, sumOfValue);
						}
			}
			
		} else if (nums.length == 1) {
			return nums[0];
		} else if (nums.length == 2) {
			int numOne = nums[0];
			int numTwo = nums[1];
			return numOne >= numTwo ? numOne : numTwo;
		}
        return maxAmountOfMoneyRobbed;
    }
	
	public int robTwo(int[] nums) {
		
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		int maxAmountOfMoneyRobbedOne = 0;
		int maxAmountOfMoneyRobbed = 0;
		int maxAmountOfMoneyRobbedTwo = 0;
		
		if (nums.length > 2) {
			
			map.put(0, nums[0]);
			maxAmountOfMoneyRobbedOne = updateMaxAmountOfMoneyToBeRobbed(maxAmountOfMoneyRobbedOne, map.get(0));
			map.put(1, Math.max(nums[1], map.get(0)));
			maxAmountOfMoneyRobbedOne = updateMaxAmountOfMoneyToBeRobbed(maxAmountOfMoneyRobbedOne, map.get(1));
			
			for (int i = 2; i < nums.length - 1; i++) {
						int sumOfValue = map.get(i - 2) + nums[i];
						int previousValue = map.get(i - 1);
						
						if (previousValue >= sumOfValue) {
							map.put(i, previousValue);
							maxAmountOfMoneyRobbedOne = updateMaxAmountOfMoneyToBeRobbed(maxAmountOfMoneyRobbedOne, previousValue);
						} else {
							map.put(i, sumOfValue);
							maxAmountOfMoneyRobbedOne = updateMaxAmountOfMoneyToBeRobbed(maxAmountOfMoneyRobbedOne, sumOfValue);
						}
			}
			
			map = new HashMap<Integer, Integer>();
			
			map.put(1, nums[1]);
			maxAmountOfMoneyRobbedTwo = updateMaxAmountOfMoneyToBeRobbed(maxAmountOfMoneyRobbedTwo, map.get(1));
			map.put(2, Math.max(map.get(1), nums[2]));
			maxAmountOfMoneyRobbedTwo = updateMaxAmountOfMoneyToBeRobbed(maxAmountOfMoneyRobbedTwo, map.get(2));
			
			if (nums.length > 3) {
				for (int i = 3; i < nums.length; i++) {
					int sumOfValue = map.get(i - 2) + nums[i];
					int previousValue = map.get(i - 1);
					
					if (previousValue >= sumOfValue) {
						map.put(i, previousValue);
						maxAmountOfMoneyRobbedTwo = updateMaxAmountOfMoneyToBeRobbed(maxAmountOfMoneyRobbedTwo, previousValue);
					} else {
						map.put(i, sumOfValue);
						maxAmountOfMoneyRobbedTwo = updateMaxAmountOfMoneyToBeRobbed(maxAmountOfMoneyRobbedTwo, sumOfValue);
					}
				}
			}
			maxAmountOfMoneyRobbed = maxAmountOfMoneyRobbedOne >= maxAmountOfMoneyRobbedTwo ? maxAmountOfMoneyRobbedOne : maxAmountOfMoneyRobbedTwo;
			
		} else if (nums.length == 1) {
			return nums[0];
		} else if (nums.length == 2) {
			int numOne = nums[0];
			int numTwo = nums[1];
			return numOne >= numTwo ? numOne : numTwo;
		}
        return maxAmountOfMoneyRobbed;
    }

	

	private int updateMaxAmountOfMoneyToBeRobbed(int maxAmountOfMoneyRobbed, int amountOFMoney) {
		return amountOFMoney > maxAmountOfMoneyRobbed ? amountOFMoney : maxAmountOfMoneyRobbed;
	}
	
	 

}

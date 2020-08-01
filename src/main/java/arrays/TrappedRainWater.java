package arrays;

public class TrappedRainWater {
	
	public int trap(int[] height) {
		int leftArray[] = new int[height.length];
		int rightArray[] = new int[height.length];
		if (height.length >= 1) {
			populateLeftArray(leftArray, height);
			populateRightArray(rightArray, height);
			int area = 0;
			for (int i = 0; i < height.length; i++) {
				area += Math.min(leftArray[i], rightArray[i]) - height[i];
			}
	        return area;
		}
		return 0;
    }

	private void populateRightArray(int[] rightArray, int[] height) {
		rightArray[height.length - 1] = height[height.length - 1];
		for (int i = height.length - 2; i >=0; i --) {
			if (height[i] > rightArray[i + 1]) {
				rightArray[i] = height[i];
			} else {
				rightArray[i] = rightArray[i + 1];
			}
		}
	}

	private void populateLeftArray(int[] leftArray, int[] height) {
		leftArray[0] = height[0];
		for (int i = 1; i < height.length; i++) {
			if (leftArray[i - 1] < height[i]) {
				leftArray[i] = height[i];
			} else {
				leftArray[i] = leftArray[i - 1];
			}
		}
	}

}

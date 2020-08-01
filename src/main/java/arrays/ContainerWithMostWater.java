package arrays;

public class ContainerWithMostWater {
	
	public int maxArea(int[] height) {
		int leftIndex = 0;
		int rightIndex = height.length - 1;
		
		int maxArea = 0;
		
		while (leftIndex != rightIndex) {
			int computedArea = 0;
			int min = Math.min(height[leftIndex], height[rightIndex]);
			computedArea = min * (rightIndex - leftIndex);
			maxArea = computedArea > maxArea ? computedArea : maxArea;
			if (height[leftIndex] > height[rightIndex]) {
				rightIndex--;
			} else {
				leftIndex++;
			}
		}
        return maxArea;
    }

}

package arrays;

import java.util.Deque;
import java.util.LinkedList;

public class SlidingWindowMaximum {
	
	public int[] maxSlidingWindow(int[] nums, int k) {
		
		if (nums.length > 0 && nums.length >= k) {
			int result[] = new int[nums.length - k + 1];
			Deque<Integer> deque = new LinkedList<Integer>();
			deque.add(0);
			for (int i = 1; i < k; i++) {
				while (!deque.isEmpty() && nums[deque.peekLast()] <= nums[i]) {
					deque.pollLast();
				}
				deque.addLast(i);
			}
			int p = 0;
			result[p] = nums[deque.peekFirst()];
			
			for (int i = k; i < nums.length; i++) {
				p++;
				while (!deque.isEmpty() && deque.peekFirst() <= i - k) {
					deque.pollFirst();
				}
				
				while (!deque.isEmpty() && nums[deque.peekLast()] <= nums[i]) {
					deque.pollLast();
				}
				deque.addLast(i);
				result[p] = nums[deque.peekFirst()];
			}
	        return result;
		} else {
				int arr[] = new int[0];
				return arr;
		}
		
		
    }

}

package arrays;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.PriorityQueue;

public class SplitArrayIntoConsecutiveSubsequences {
	
	public boolean isPossible(int[] nums) {
		
		int numberOfSubsequences = 0;
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		for (int i = 0; i < nums.length; i++) {
			if (!map.containsKey(nums[i])) {
				map.put(nums[i], 1);
				numberOfSubsequences = updateNumberOfSubsequences(1, numberOfSubsequences);
			} else {
				map.put(nums[i], map.get(nums[i]) + 1);
				numberOfSubsequences = updateNumberOfSubsequences(map.get(nums[i]), numberOfSubsequences);
			}
		}
		
		PriorityQueue<SplitSubSequences> priorityQueue = new PriorityQueue<SplitArrayIntoConsecutiveSubsequences.SplitSubSequences>(new SplitSubSequencesComparator());
		
		for (int i = 0; i < nums.length; i++) {
			 if (priorityQueue.size() < numberOfSubsequences) {
				 priorityQueue.add(new SplitSubSequences(i, 1));
			 } else {
				 SplitSubSequences splitSubSequences = priorityQueue.poll();
				 splitSubSequences.lastIndex = i;
				 splitSubSequences.size = splitSubSequences.size + 1;
				 priorityQueue.add(splitSubSequences);
			 }
			 
		}
		
		boolean result = true;
		
		Iterator<SplitSubSequences> iterator = priorityQueue.iterator();
		
		while (iterator.hasNext()) {
			if (iterator.next().size < 3) {
				result = false;
				break;
			}
		}
		
        return result;
    }

	private int updateNumberOfSubsequences(int num, int numberOfSubsequences) {
		return num > numberOfSubsequences ? num : numberOfSubsequences;
	}
	
	class SplitSubSequences {
		int lastIndex;
		int size;
		
		public SplitSubSequences(int lasIndex, int size) {
			this.lastIndex = lasIndex;
			this.size = size;
		}
	}
	
	
	class SplitSubSequencesComparator implements Comparator<SplitSubSequences> {

		@Override
		public int compare(SplitSubSequences o1, SplitSubSequences o2) {
			if (o1.size < o2.size) {
				return -1;
			} else if (o1.size > o2.size) {
				return 1;
			} else {
				if (o1.lastIndex < o2.lastIndex) {
					return -1;
				} else {
					return 1;
				}
			}
		}
	}

}

package arrays;

import java.util.Comparator;
import java.util.PriorityQueue;

public class MedianFromDataStream {
	
	private PriorityQueue<Integer> maxHeap;
	private PriorityQueue<Integer> minHeap;
	
	public MedianFromDataStream() {
		minHeap = new PriorityQueue<Integer>(new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				return o1 < o2 ? -1 : o1 > o2 ? 1 : 0;
			}
		});
		
		maxHeap = new PriorityQueue<Integer>(new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				return o1 < o2 ? 1 : o1 > o2 ? -1 : 0;
			}
		});
	}
	
	public void addNum(int num) {
		int maxHeapSize = maxHeap.size();
		int minHeapSize = minHeap.size();
		if (maxHeapSize == minHeapSize) {
			if (maxHeapSize == 0) {
				maxHeap.add(num);
			} else {
				int minHeapTop = minHeap.peek();
				int maxHeapTop = maxHeap.peek();
				if (num <= maxHeapTop) {
					maxHeap.add(num);
				} else {
					minHeap.add(num);
					minHeapTop = minHeap.poll();
					maxHeap.add(minHeapTop);
				}
			}
		} else {
			maxHeap.add(num);
			maxHeapSize = maxHeap.size();
			minHeapSize = minHeap.size();
			if (maxHeapSize - minHeapSize > 1) {
				int maxHeapTop = maxHeap.poll();
				minHeap.add(maxHeapTop);
			}
		}
    }
    
    public double findMedian() {
    	int maxHeapSize = maxHeap.size();
    	int minHeapSize = minHeap.size();
    	
    	if ((maxHeapSize + minHeapSize) % 2 == 0) {
    		return (maxHeap.peek() + minHeap.peek())/2.0;
    	} else {
    		return maxHeap.peek();
    	}
    }

}

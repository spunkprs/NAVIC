package greedy;

import java.util.PriorityQueue;

public class MinimumCostToConnectSticks {
	
	public int connectSticks(int[] sticks) {
		PriorityQueue<Integer> priorityQueue = new PriorityQueue<Integer>();
		for (Integer num : sticks) {
			priorityQueue.add(num);
		}
		
		return processToFetchMinimumCostToConnectSticks(priorityQueue);
    }

	private int processToFetchMinimumCostToConnectSticks(PriorityQueue<Integer> priorityQueue) {
		 
		int element = 0;
		int sum = 0;
		int finalSum = 0;
		
		while (priorityQueue.size() > 1) {
			 element = priorityQueue.poll();
			 sum += element;
			 element = priorityQueue.poll();
			 sum += element;
			 if (priorityQueue.size() == 0) {
				 finalSum += sum;
				 sum = 0;
				 break;
			 } else {
				 finalSum += sum;
				 priorityQueue.add(sum);
				 sum = 0;
			 }
		}
		return finalSum;
	}

}

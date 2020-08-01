package graph;

import java.util.Comparator;
import java.util.PriorityQueue;

public class ShortestSourceToDestinationLeetCode {
	
	private int shortestSourceToDestinationPath = Integer.MAX_VALUE;
	
	public int shortestSourceToDestinationPathSum(int[][] matrix, int destinationX, int destinationY) {
		ShortestPathComparisonObject origin = new ShortestPathComparisonObject(0, 0);
		PriorityQueue<ShortestPathComparisonObject> priorityQueue = new PriorityQueue<ShortestPathComparisonObject>(new MinimumSumPathComparator());
		priorityQueue.add(origin);
		processToComputeShortestSourceToDestination(priorityQueue, matrix, destinationX, destinationY);
		return shortestSourceToDestinationPath;
	}
	
	private void processToComputeShortestSourceToDestination(PriorityQueue<ShortestPathComparisonObject> priorityQueue, int[][] matrix, int destinationX, int destinationY) {
		
		while (!priorityQueue.isEmpty()) {
			ShortestPathComparisonObject parent = priorityQueue.peek();
			if (parent.abcissa == destinationX && parent.ordinate == destinationY) {
				if (parent.pathSum < shortestSourceToDestinationPath) {
					shortestSourceToDestinationPath = parent.pathSum;
				}
			} else {
				pushElementsToQueue(parent, matrix, destinationX, destinationY, priorityQueue, parent.pathSum);
				matrix[parent.abcissa][parent.ordinate] = -1;
				}
			
			priorityQueue.poll();
		}
	}

	private void pushElementsToQueue(ShortestPathComparisonObject parent, int[][] matrix, int destinationX, int destinationY, PriorityQueue<ShortestPathComparisonObject> priorityQueue, int sumPath) {
		if (parent.abcissa + 1 < matrix.length && matrix[parent.abcissa + 1][parent.ordinate] == 1) {
			ShortestPathComparisonObject shortestPathComparisonObject = new ShortestPathComparisonObject(parent.abcissa + 1, parent.ordinate);
			shortestPathComparisonObject.pathSum = sumPath + 1;
			priorityQueue.add(shortestPathComparisonObject);
			
		}
		
		if (parent.abcissa - 1 >= 0 && matrix[parent.abcissa - 1][parent.ordinate] == 1) {
			ShortestPathComparisonObject shortestPathComparisonObject = new ShortestPathComparisonObject(parent.abcissa - 1, parent.ordinate);
			shortestPathComparisonObject.pathSum = sumPath + 1;
			priorityQueue.add(shortestPathComparisonObject);
		}
		
		if (parent.ordinate + 1 < matrix[0].length && matrix[parent.abcissa][parent.ordinate + 1] == 1) {
			ShortestPathComparisonObject shortestPathComparisonObject = new ShortestPathComparisonObject(parent.abcissa, parent.ordinate + 1);
			shortestPathComparisonObject.pathSum = sumPath + 1;
			priorityQueue.add(shortestPathComparisonObject);
		}
		
		if (parent.ordinate - 1 >= 0 && matrix[parent.abcissa][parent.ordinate - 1] == 1) {
			ShortestPathComparisonObject shortestPathComparisonObject = new ShortestPathComparisonObject(parent.abcissa, parent.ordinate - 1);
			shortestPathComparisonObject.pathSum = sumPath + 1;
			priorityQueue.add(shortestPathComparisonObject);
		}
	}

	class ShortestPathComparisonObject {
		int pathSum;
		int abcissa;
		int ordinate;
		
		
		public ShortestPathComparisonObject (int abcissa, int ordinate) {
			this.abcissa = abcissa;
			this.ordinate = ordinate;
		}
	}
	
	class MinimumSumPathComparator implements Comparator<ShortestPathComparisonObject> {

		@Override
		public int compare(ShortestPathComparisonObject o1, ShortestPathComparisonObject o2) {
			return o1.pathSum < o2.pathSum ? -1 : o1.pathSum > o2.pathSum ? 1 : 0;
		}
		
	}

}

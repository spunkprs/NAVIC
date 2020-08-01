package graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class PrimImplementation {
	
	public int findMinimumSpanningTreeSum(int adjacencyMatrix[][]) {
		Set<Integer> set = new HashSet<Integer>();
		Map<Integer, PriorityQueueNode> map = new HashMap<Integer, PriorityQueueNode>();
		PriorityQueue<PriorityQueueNode> priorityQueue = new PriorityQueue<PriorityQueueNode>();
		PriorityQueueNode root = new PriorityQueueNode(0, 0, true);
		map.put(root.getNum(), root);
		priorityQueue.add(root);
	    return processToComputeMinimumSpanningTree(map, priorityQueue, set, adjacencyMatrix);
	}
	
	
	private int processToComputeMinimumSpanningTree(Map<Integer, PriorityQueueNode> map, PriorityQueue<PriorityQueueNode> priorityQueue, Set<Integer> set, int adjacencyMatrix[][]) {
		int minimumSpanningTreeSum = 0;	
		while (!priorityQueue.isEmpty()) {
				PriorityQueueNode node = priorityQueue.peek();
				priorityQueue.poll();
				if (node.isValid()) {
					addChildrenToQueueAndUpdate(adjacencyMatrix, set, node.getNum(), priorityQueue, map);
					minimumSpanningTreeSum += node.getAmount();
					set.add(node.getNum());
				}
				
			}
		return minimumSpanningTreeSum;
	}

	private void addChildrenToQueueAndUpdate(int[][] adjacencyMatrix, Set<Integer> set, int parent, PriorityQueue<PriorityQueueNode> priorityQueue, Map<Integer, PriorityQueueNode> map) {
		for (int j = 0; j < adjacencyMatrix[0].length; j++) {
			if (adjacencyMatrix[parent][j] != 0 && !set.contains(j)) {
				if (map.containsKey(j)) {
					PriorityQueueNode node = map.get(j);
					if (node.getAmount() > adjacencyMatrix[parent][j]) {
						node.setValid(false);
						node = new PriorityQueueNode(j, adjacencyMatrix[parent][j], true);
						map.put(j, node);
						priorityQueue.add(node);
					}
				} else {
					PriorityQueueNode node = new PriorityQueueNode(j, adjacencyMatrix[parent][j], true);
					map.put(j, node);
					priorityQueue.add(node);
				}
			}
		}
		
	}

	/*
	 * class PriorityQueueNode implements Comparable<PriorityQueueNode>{ private int
	 * num; private int amount; private boolean isValid;
	 * 
	 * public PriorityQueueNode(int num, int amount, boolean isValid) { this.num =
	 * num; this.amount = amount; this.isValid = isValid; }
	 * 
	 * @Override public int compareTo(PriorityQueueNode node) { return this.amount <
	 * node.amount ? -1 : this.amount > node.amount ? 1 : 0; } }
	 */

}

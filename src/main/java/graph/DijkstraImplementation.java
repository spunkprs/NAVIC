package graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class DijkstraImplementation {
	
	public Map<Integer, Integer> findMinimumDistanceFromSourceNodeToAllOthers(int adjacencyMatrix[][]) {
		Map<Integer, Integer> resultMap = new HashMap<Integer, Integer>();
		Map<Integer, PriorityQueueNode> map = new HashMap<Integer, PriorityQueueNode>();
		Set<Integer> set = new HashSet<Integer>();
		PriorityQueue<PriorityQueueNode> priorityQueue = new PriorityQueue<PriorityQueueNode>();
		PriorityQueueNode root = new PriorityQueueNode(0, 0, true);
		priorityQueue.add(root);
		
		map.put(root.getNum(), root);
		
		while (!priorityQueue.isEmpty()) {
			PriorityQueueNode head = priorityQueue.poll();
			if (head.isValid()) {
				set.add(head.getNum());
				resultMap.put(head.getNum(), head.getAmount());
				findChildrenAndUpdatePriorityQueue(adjacencyMatrix, map, resultMap, set, head.getNum(), priorityQueue);	
			}
		}
		return resultMap;
	}

	private void findChildrenAndUpdatePriorityQueue(int[][] adjacencyMatrix, Map<Integer, PriorityQueueNode> map,
			Map<Integer, Integer> resultMap, Set<Integer> set, int parent, PriorityQueue<PriorityQueueNode> priorityQueue) {
		
		for (int j = 0; j < adjacencyMatrix[0].length; j++) {
			if (adjacencyMatrix[parent][j] != 0 && !set.contains(j)) {
				if (map.containsKey(j)) {
					PriorityQueueNode node = map.get(j);
					if (node.getAmount() > resultMap.get(parent) + adjacencyMatrix[parent][j]) {
						node.setValid(false);
						node = new PriorityQueueNode(j, resultMap.get(parent) + adjacencyMatrix[parent][j], true);
						map.put(node.getNum(), node);
						priorityQueue.add(node);
					}
				} else {
					PriorityQueueNode node = new PriorityQueueNode(j, resultMap.get(parent) + adjacencyMatrix[parent][j], true);
					map.put(node.getNum(), node);
					priorityQueue.add(node);
				}
			}
		}
		
	}

}

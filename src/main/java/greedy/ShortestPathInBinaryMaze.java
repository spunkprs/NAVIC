package greedy;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class ShortestPathInBinaryMaze {
	
	private Map<Pair, Node> map = new HashMap<Pair, Node>();
	private Pair destination = null;
	
	public int fetchShortestPathInBinaryMaze(int matrix[][], int xOne, int yOne) {
		Pair origin = new Pair(0, 0);
		
		Set<Pair> exploredNodes = new HashSet<Pair>();
		destination = new Pair(xOne, yOne);
		
		PriorityQueue<Node> priorityQueue = new PriorityQueue<ShortestPathInBinaryMaze.Node>(new Comparator<Node>() {
			@Override
			public int compare(Node o1, Node o2) {
				if (o1.cost < o2.cost) {
					return -1;
				} else if (o1.cost > o2.cost) {
					return 1;
				}
				return 0;
			}
		});
		
		int minCostPath = 0;
		
		Node originNode = new Node(origin, 0);
		priorityQueue.add(originNode);
		map.put(originNode.pair, originNode);
		while (!priorityQueue.isEmpty()) {
			addChildrenToQueue(priorityQueue.peek(), matrix, exploredNodes, priorityQueue);
			Node polledNode = priorityQueue.remove();
			exploredNodes.add(polledNode.pair);
			if (polledNode.pair.equals(destination)) {
				minCostPath = polledNode.cost;
				break;
			}
		}
		return minCostPath;
	}
	
	
	private void addChildrenToQueue(Node parentNode, int matrix[][], Set<Pair> exploredNodes, PriorityQueue<Node> priorityQueue) {
		 
		if (parentNode.pair.abcissa + 1 < matrix.length && matrix[parentNode.pair.abcissa + 1][parentNode.pair.ordinate] == 1) {
			Pair p = new Pair(parentNode.pair.abcissa + 1, parentNode.pair.ordinate);
			if (!exploredNodes.contains(p)) {
				if (!map.containsKey(p)) {
					Node node = new Node(p, parentNode.cost + 1);
					priorityQueue.add(node);
					map.put(p, node);
				} else {
					Node existingChildNode = map.get(p);
					if (existingChildNode.cost > parentNode.cost + 1) {
						existingChildNode.cost = parentNode.cost + 1;
					}
				}
			}
		}
		
		if (parentNode.pair.abcissa - 1 >= 0 && matrix[parentNode.pair.abcissa - 1][parentNode.pair.ordinate] == 1) {
			Pair p = new Pair(parentNode.pair.abcissa - 1, parentNode.pair.ordinate);
			if (!exploredNodes.contains(p)) {
				if (!map.containsKey(p)) {
					Node node = new Node(p, parentNode.cost + 1);
					priorityQueue.add(node);
					map.put(p, node);
				} else {
					Node existingChildNode = map.get(p);
					if (existingChildNode.cost > parentNode.cost + 1) {
						existingChildNode.cost = parentNode.cost + 1;
					}
				}
			}
		}
		
		if (parentNode.pair.ordinate - 1 >= 0 && matrix[parentNode.pair.abcissa][parentNode.pair.ordinate - 1] == 1) {
			Pair p = new Pair(parentNode.pair.abcissa, parentNode.pair.ordinate - 1);
			if (!exploredNodes.contains(p)) {
				if (!map.containsKey(p)) {
					Node node = new Node(p, parentNode.cost + 1);
					priorityQueue.add(node);
					map.put(p, node);
				} else {
					Node existingChildNode = map.get(p);
					if (existingChildNode.cost > parentNode.cost + 1) {
						existingChildNode.cost = parentNode.cost + 1;
					}
				}
			}
		}
		
		if (parentNode.pair.ordinate + 1 < matrix[0].length && matrix[parentNode.pair.abcissa][parentNode.pair.ordinate + 1] == 1) {
			Pair p = new Pair(parentNode.pair.abcissa, parentNode.pair.ordinate + 1);
			if (!exploredNodes.contains(p)) {
				if (!map.containsKey(p)) {
					Node node = new Node(p, parentNode.cost + 1);
					priorityQueue.add(node);
					map.put(p, node);
				} else {
					Node existingChildNode = map.get(p);
					if (existingChildNode.cost > parentNode.cost + 1) {
						existingChildNode.cost = parentNode.cost + 1;
					}
				}
			}
		}
	}


	class Node {
		Pair pair;
		int cost;
		public Node(Pair pair, int cost) {
			this.cost = cost;
			this.pair = pair;
		}
	}
	
	
	class Pair {
		int abcissa;
		int ordinate;
		
		public Pair(int abcissa, int ordinate) {
			this.abcissa = abcissa;
			this.ordinate = ordinate;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getEnclosingInstance().hashCode();
			result = prime * result + abcissa;
			result = prime * result + ordinate;
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Pair other = (Pair) obj;
			if (!getEnclosingInstance().equals(other.getEnclosingInstance()))
				return false;
			if (abcissa != other.abcissa)
				return false;
			if (ordinate != other.ordinate)
				return false;
			return true;
		}

		private ShortestPathInBinaryMaze getEnclosingInstance() {
			return ShortestPathInBinaryMaze.this;
		}
	}

}

package graph;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class ShortestPathToGetAllKeys {
	
	private Set<Character> setOfAllKeys = new HashSet<Character>();
	private Set<Character> setOfKeysObtainedSoFar = new HashSet<Character>();
	private Map<Character, Character> map = new HashMap<Character, Character>();
	private PriorityQueue<Node> priorityQueue = null;
	private Map<Pair, Node> mapOne = new HashMap<Pair, Node>();
	private int minDistance = Integer.MIN_VALUE;
	private Set<Pair> consideredPairs = new HashSet<Pair>();
	
	public int shortestPathAllKeys(String[] grid) {
		char gridArray[][] = new char[grid.length][grid[0].length()];
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length(); j++) {
				char arr[] = grid[i].toCharArray();
				gridArray[i][j] = arr[j];
				if (arr[j] >=97 && arr[j] <= 122) {
					setOfAllKeys.add(arr[j]);
				}
			}
		}
		
		populateMap();
		processToFindShortestPathToGetAllKeys(gridArray);
		if (setOfAllKeys.size() == setOfKeysObtainedSoFar.size()) {
			return minDistance;
		}
        return -1;
    }

	private void processToFindShortestPathToGetAllKeys(char[][] gridArray) {
		for (int i = 0; i < gridArray.length; i++) {
			for (int j = 0; j < gridArray[0].length; j++) {
				if (gridArray[i][j] == '@') {
					priorityQueue = new PriorityQueue<Node>(new Comparator<Node>() {

						@Override
						public int compare(Node o1, Node o2) {
							return o1.distance < o2.distance ? -1 : o1.distance > o2.distance ? 1 : 0;
						}
						
					});
					proceedFurther(gridArray, i, j);
					return;
				}
			}
		}
		
	}

	private void proceedFurther(char[][] gridArray, int i, int j) {
		Pair p = new Pair(i, j);
		Node node = new Node(p);
		
		node.distance = 0;
		node.isStillConsiderable = true;
		
		priorityQueue.add(node);
		mapOne.put(p, node);
		while (!priorityQueue.isEmpty()) {
			Node deletedNode = priorityQueue.poll();
			consideredPairs.add(deletedNode.pair);
			char ch = gridArray[deletedNode.pair.abcissa][deletedNode.pair.ordinate];
			
			
			if (deletedNode.isStillConsiderable) {
				if ( ch >= 97 && ch <= 122) {
					if (!setOfKeysObtainedSoFar.contains(ch)) {
						setOfKeysObtainedSoFar.add(ch);
					}
					if (deletedNode.distance > minDistance) {
						minDistance = deletedNode.distance;
					}
				}
				addChildren(deletedNode, gridArray);
			}
		}
	}

	private void addChildren(Node node, char[][] gridArray) {
		int parentX = node.pair.abcissa;
		int parentY = node.pair.ordinate;
		
		if (parentX + 1 <= gridArray.length - 1) {
			Pair p = new Pair(parentX + 1, parentY);
			if (!consideredPairs.contains(p) && gridArray[parentX + 1][parentY] != '#') {
				char ch = gridArray[parentX + 1][parentY];
				if (((ch >= 65 && ch<= 90) && setOfKeysObtainedSoFar.contains(map.get(ch))) || (ch >= 97 && ch <= 122) || (ch == '.')) {
					Node childNode = new Node(p);
					childNode.distance = node.distance + 1;
					childNode.isStillConsiderable = true;
					updateAlreadyExistingNodes(childNode, p);
				}
			}
		}
		
		if (parentX - 1 >= 0) {
			Pair p = new Pair(parentX - 1, parentY);
			if (!consideredPairs.contains(p) && gridArray[parentX - 1][parentY] != '#') {
				char ch = gridArray[parentX - 1][parentY];
				if (((ch >= 65 && ch<= 90) && setOfKeysObtainedSoFar.contains(map.get(ch))) || (ch >= 97 && ch <= 122) || (ch == '.')) {
					Node childNode = new Node(p);
					childNode.distance = node.distance + 1;
					childNode.isStillConsiderable = true;
					updateAlreadyExistingNodes(childNode, p);
				}
			}
		}
		
		if (parentY - 1 >= 0) {
			Pair p = new Pair(parentX, parentY - 1);
			if (!consideredPairs.contains(p) && gridArray[parentX][parentY - 1] != '#') {
				char ch = gridArray[parentX][parentY - 1];
				if (((ch >= 65 && ch<= 90) && setOfKeysObtainedSoFar.contains(map.get(ch))) || (ch >= 97 && ch <= 122) || (ch == '.')) {
					Node childNode = new Node(p);
					childNode.distance = node.distance + 1;
					childNode.isStillConsiderable = true;
					updateAlreadyExistingNodes(childNode, p);
				}
			}
		}
		
		if (parentY + 1 <= gridArray[0].length - 1) {
			Pair p = new Pair(parentX, parentY + 1);
			if (!consideredPairs.contains(p) && gridArray[parentX][parentY + 1] != '#') {
				char ch = gridArray[parentX][parentY + 1];
				if (((ch >= 65 && ch<= 90) && setOfKeysObtainedSoFar.contains(map.get(ch))) || (ch >= 97 && ch <= 122) || (ch == '.')) {
					Node childNode = new Node(p);
					childNode.distance = node.distance + 1;
					childNode.isStillConsiderable = true;
					updateAlreadyExistingNodes(childNode, p);
				}
			}
		}
	}

	private void updateAlreadyExistingNodes(Node newChildNode, Pair existingPair) {
		
		if (mapOne.containsKey(existingPair)) {
			Node existingChildNode = mapOne.get(existingPair);
			if (existingChildNode.distance > newChildNode.distance) {
				existingChildNode.isStillConsiderable = false;
				priorityQueue.add(newChildNode);
				mapOne.put(existingPair, newChildNode);
			}
		} else {
			priorityQueue.add(newChildNode);
		}
	}

	private void populateMap() {
		map.put('A', 'a');
		map.put('B', 'b');
		map.put('C', 'c');
		map.put('D', 'd');
		map.put('E', 'e');
		map.put('F', 'f');
		map.put('G', 'g');
		map.put('H', 'h');
		map.put('I', 'i');
		map.put('J', 'j');
		map.put('K', 'k');
		map.put('L', 'l');
		map.put('M', 'm');
		map.put('N', 'n');
		map.put('O', 'o');
		map.put('P', 'p');
		map.put('Q', 'q');
		map.put('R', 'r');
		map.put('S', 's');
		map.put('T', 't');
		map.put('U', 'u');
		map.put('V', 'v');
		map.put('W', 'w');
		map.put('X', 'x');
		map.put('Y', 'y');
		map.put('Z', 'z');
		
	}
	
	class Node {
		private Pair pair;
		private int distance;
		private boolean isStillConsiderable;
		
		public Node (Pair pair) {
			this.pair = pair;
		}
	}
	
	class Pair {
		private int abcissa;
		private int ordinate;
		
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

		private ShortestPathToGetAllKeys getEnclosingInstance() {
			return ShortestPathToGetAllKeys.this;
		}
	}

}

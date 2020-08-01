package binarytree;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MinimumTimeToCollectAllApples {
	
	private Map<Integer, Pair> map = new HashMap<>();
	private GenericTreeNode root = null;
	
	public int minTime(int n, int[][] edges, List<Boolean> hasApple) {
		root = null;
		boolean flag = false;
		for (int i = 0; i < hasApple.size(); i++) {
			if (hasApple.get(i)) {
				flag = true;
				break;
			}
		}
		if (flag) {
			return processToBuildTreeAndComputeMinimumTimeToCollectApples(edges, hasApple);
		} 
		return 0;
    }

	private int processToBuildTreeAndComputeMinimumTimeToCollectApples(int[][] edges, List<Boolean> hasApple) {
		buildTree(edges, hasApple);
		GenericTreeNode parentNode = root;
		PairTwo pairTwo = processToComputeMinimumTimeToCollectApples(parentNode, 0);
		if (pairTwo != null) {
			return pairTwo.distance;
		}
		return 0;
	}
	
	
	private PairTwo processToComputeMinimumTimeToCollectApples(GenericTreeNode node, int distanceTillNow) {
		if (node != null) {
			boolean parentFlag = false;
			List<GenericTreeNode> children = node.getListOfChildren();
			if (children.isEmpty() && map.get(node.getVal()).hasApple) {
				return new PairTwo(distanceTillNow + 1, true);
			} else if (!children.isEmpty()) {
				for (GenericTreeNode childNode : children) {
					PairTwo pair = processToComputeMinimumTimeToCollectApples(childNode, distanceTillNow + 1);
					if (pair.flag) {
						parentFlag = true;
						distanceTillNow = pair.distance;
					}
				}
				if (parentFlag) {
					if (root != node) {
						return new PairTwo(distanceTillNow + 1, parentFlag);
					} else {
						return new PairTwo(distanceTillNow, parentFlag);
					}
				} else {
					if (!parentFlag && map.get(node.getVal()).hasApple) {
						parentFlag = true;
						return new PairTwo(distanceTillNow + 1, parentFlag);
					}
					return new PairTwo(distanceTillNow, parentFlag);
				}
			} else if (children.isEmpty() && !map.get(node.getVal()).hasApple) {
				return new PairTwo(distanceTillNow, false);
			}
		}
		
		return null;
	}

	private void buildTree(int[][] edges, List<Boolean> hasApple) {
		for (int i = 0; i < edges.length; i++) {
			int parent = edges[i][0];
			int child = edges[i][1];
			if (!map.containsKey(parent)) {
				if (root == null) {
					GenericTreeNode parentNode = new GenericTreeNode(parent);
					root = parentNode;
				}
				
				if (!map.containsKey(parent) && map.containsKey(child)) {
					GenericTreeNode childNode = map.get(child).node;
					GenericTreeNode parentNode = new GenericTreeNode(parent);
					childNode.getListOfChildren().add(parentNode);
					map.put(parent, new Pair(parentNode, hasApple.get(parent)));
					continue;
				}
				map.put(parent, new Pair(root, hasApple.get(parent)));
			}
			
			if (!map.containsKey(child)) {
				map.put(child, new Pair(new GenericTreeNode(child), hasApple.get(child)));
			}
			
			Pair p = map.get(parent);
			GenericTreeNode parentNode = p.node;
			GenericTreeNode childNode = map.get(child).node;
			
			parentNode.getListOfChildren().add(childNode);
		}
	}
	
	class PairTwo {
		int distance;
		boolean flag;
		
		PairTwo(int distance, boolean flag) {
			this.distance = distance;
			this.flag = flag;
		}
	}
	
	class Pair {
		private GenericTreeNode node;
		private boolean hasApple;
		
		public Pair(GenericTreeNode node, boolean hasApple) {
			this.hasApple = hasApple;
			this.node = node;
		}
	}

}

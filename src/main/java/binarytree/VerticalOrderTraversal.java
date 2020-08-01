package binarytree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.TreeSet;

public class VerticalOrderTraversal {
	
	private int minVerticalCorordinate = Integer.MAX_VALUE;
	private int maxVerticalCorordinate = Integer.MIN_VALUE;
	
	private Map<Integer, Set<TreeNodeInformation>> map = new HashMap<Integer, Set<TreeNodeInformation>>();
	
	public List<List<Integer>> verticalTraversal(TreeNode root) {
		populateMapApproachOne(root, 0, 0);
		return prepareResult();
    }

	private List<List<Integer>> prepareResult() {
		List<List<Integer>> result = new ArrayList<List<Integer>>();
		for (int i = minVerticalCorordinate; i <= maxVerticalCorordinate; i++) {
			result.add(prepareList(map.get(i)));
		}
		return result;
	}

	private List<Integer> prepareList(Set<TreeNodeInformation> treeSet) {
		List<Integer> resultantList = new ArrayList<Integer>();
		for (TreeNodeInformation treeNodeInformation : treeSet) {
			resultantList.add(treeNodeInformation.node.val);
		}
		return resultantList;
	}

	
	private void populateMapApproachOne(TreeNode node, int depth, int verticalCoordinate) {
		if (node != null) {
			Queue<TreeNodeInformation> queue = new LinkedList<TreeNodeInformation>();
			queue.add(new TreeNodeInformation(node, depth, verticalCoordinate));
			
			while (!queue.isEmpty()) {
				
				TreeNodeInformation nodeInformation = queue.peek();
				updateMinAndMaxVericalCoordinates(nodeInformation.verticalCoordinate);
				
				if (map.containsKey(nodeInformation.verticalCoordinate)) {
					Set<TreeNodeInformation> treeSet = map.get(nodeInformation.verticalCoordinate);
					treeSet.add(nodeInformation);
				} else {
					Set<TreeNodeInformation> treeSet = new TreeSet<TreeNodeInformation>();
					treeSet.add(nodeInformation);
					map.put(nodeInformation.verticalCoordinate, treeSet);
				}
				
				TreeNode leftNode = nodeInformation.node.left;
				TreeNode rightNode = nodeInformation.node.right;
				
				if (leftNode != null) {
					queue.add(new TreeNodeInformation(leftNode, nodeInformation.depth + 1, nodeInformation.verticalCoordinate - 1));
				}
				
				if (rightNode != null) {
					queue.add(new TreeNodeInformation(rightNode, nodeInformation.depth + 1, nodeInformation.verticalCoordinate + 1));
				}
				
				queue.poll();
			}
		}
	}

	private void updateMinAndMaxVericalCoordinates(int verticalCoordinate) {
		if (verticalCoordinate < minVerticalCorordinate) {
			minVerticalCorordinate = verticalCoordinate;
		}
		
		if (verticalCoordinate > maxVerticalCorordinate) {
			maxVerticalCorordinate = verticalCoordinate;
		}
	}
	
	class TreeNodeInformation implements Comparable<TreeNodeInformation> {
		private TreeNode node;
		private int depth;
		private int verticalCoordinate;
		
		private TreeNodeInformation(TreeNode node, int depth, int verticalCoordinate) {
			this.depth = depth;
			this.node = node;
			this.verticalCoordinate = verticalCoordinate;
		}

		@Override
		public int compareTo(TreeNodeInformation object) {
			if (this.depth < object.depth) {
				return -1;
			} else if (this.depth > object.depth) {
				return 1;
			} else {
					return this.node.val < object.node.val ? -1 : this.node.val > object.node.val ? 1 : 0;
				}
			}
		}
	}



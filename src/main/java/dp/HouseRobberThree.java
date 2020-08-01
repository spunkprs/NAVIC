package dp;

import java.util.HashMap;
import java.util.Map;

public class HouseRobberThree {
	
	public int rob(TreeNode root) {
		
		int result = 0;
		Map<TreeNode, Node> mapOne = new HashMap<TreeNode, Node>();
		Map<TreeNode, Node> mapTwo = new HashMap<TreeNode, Node>();
		
		if (root != null) {
			if (root.left != null && root.right != null) {
				processTree(root.left, mapOne);
				processTree(root.right, mapTwo);
				if (mapOne.get(root.left).isLeaf && mapTwo.get(root.right).isLeaf) {
					result = Math.max(root.val, mapOne.get(root.left).valueOne + mapTwo.get(root.right).valueOne);
				} else if (!mapOne.get(root.left).isLeaf && mapTwo.get(root.right).isLeaf) {
					result = Math.max(root.val + mapOne.get(root.left).valueTwo, mapOne.get(root.left).valueOne + mapTwo.get(root.right).valueOne);
				} else if (mapOne.get(root.left).isLeaf && !mapTwo.get(root.right).isLeaf) {
					result = Math.max(root.val + mapTwo.get(root.right).valueTwo, mapTwo.get(root.right).valueOne + mapOne.get(root.left).valueOne);
				} else {
					int valOne = mapOne.get(root.left).valueOne + mapTwo.get(root.right).valueOne;
					int valTwo = mapOne.get(root.left).valueTwo + mapTwo.get(root.right).valueTwo + root.val;
					result = Math.max(valOne, valTwo);
				}
			} else if (root.left == null && root.right != null) {
				processTree(root.right, mapTwo);
				if (mapTwo.get(root.right).isLeaf) {
					result = Math.max(root.val, mapTwo.get(root.right).valueOne);
				} else {
					result = Math.max(mapTwo.get(root.right).valueOne, mapTwo.get(root.right).valueTwo + root.val);
				}
			} else if (root.right == null && root.left != null) {
				processTree(root.left, mapOne);
				if (mapOne.get(root.left).isLeaf) {
					result = Math.max(root.val, mapOne.get(root.left).valueOne);
				} else {
					result = Math.max(mapOne.get(root.left).valueOne, mapOne.get(root.left).valueTwo + root.val);
				}
			} else if (root.left == null && root.right == null) {
				result = root.val;
			}
	        return result;
		} else {
			return 0;
		}
    }
	
	private void processTree(TreeNode node, Map<TreeNode, Node> map) {
		
		if (node != null) {
			TreeNode leftNode = node.left;
			
			TreeNode rightNode = node.right;
			
			if (leftNode == null && rightNode == null) {
				map.put(node, new Node(node, node.val, node.val, true));
			} else {
				processTree(leftNode, map);
				processTree(rightNode, map);
				
				if (leftNode != null && rightNode != null) {
					if (map.get(leftNode).isLeaf && map.get(rightNode).isLeaf) {
						int value = map.get(leftNode).valueOne + map.get(rightNode).valueOne;
						if (node.val >= value) {
							Node n = new Node(node, node.val, value, false);
							n.setConsidered(true);
							map.put(node, n);
						} else {
							map.put(node, new Node(node, value, value, false));
						}
					} else {
						if (!map.get(leftNode).isLeaf && map.get(rightNode).isLeaf) {
							int valueOne = map.get(leftNode).valueTwo + node.val;
							int valueTwo = map.get(leftNode).valueOne + map.get(rightNode).valueOne;
							if (valueOne >= valueTwo) {
								Node n = new Node(node, valueOne, valueTwo, false);
								n.setConsidered(true);
								map.put(node, n);
							} else {
								map.put(node, new Node(node, valueTwo, valueTwo, false));
							}
						} else if (map.get(leftNode).isLeaf && !map.get(rightNode).isLeaf) {
							int valueOne = map.get(rightNode).valueTwo + node.val;
							int valueTwo = map.get(rightNode).valueOne + map.get(leftNode).valueOne;
							if (valueOne >= valueTwo) {
								Node n = new Node(node, valueOne, valueTwo, false);
								n.setConsidered(true);
								map.put(node, n);
							} else {
								map.put(node, new Node(node, valueTwo, valueTwo, false));
							}
						} else {
							if (!map.get(leftNode).isConsidered && !map.get(rightNode).isConsidered) {
								int valueOne = node.val;
								int valueTwo = map.get(leftNode).valueTwo + map.get(rightNode).valueTwo;
									Node n = new Node(node, valueOne + valueTwo, valueTwo, false);
									n.setConsidered(true);
									map.put(node, n);
							} else if (!map.get(leftNode).isConsidered && map.get(rightNode).isConsidered) {
								int valueOne = map.get(leftNode).valueTwo + map.get(rightNode).valueTwo + node.val;
								int valueTwo = map.get(rightNode).valueOne + map.get(leftNode).valueOne;
								if (valueTwo >= valueOne) {
									map.put(node, new Node(node, valueTwo, valueTwo, false));
								} else {
									Node n = new Node(node, valueOne, valueTwo, false);
									n.setConsidered(true);
									map.put(node, n);
								}
								
							} else if (map.get(leftNode).isConsidered && !map.get(rightNode).isConsidered) {
								int valueOne = map.get(leftNode).valueTwo + map.get(rightNode).valueTwo + node.val;
								int valueTwo = map.get(rightNode).valueOne + map.get(leftNode).valueOne;
								if (valueTwo >= valueOne) {
									map.put(node, new Node(node, valueTwo, valueTwo, false));
								} else {
									Node n = new Node(node, valueOne, valueTwo, false);
									n.setConsidered(true);
									map.put(node, n);
								}
							} else if (map.get(leftNode).isConsidered && map.get(rightNode).isConsidered) {
								int valueOne = map.get(leftNode).valueOne + map.get(rightNode).valueOne;
								int valueTwo = map.get(leftNode).valueTwo + map.get(rightNode).valueTwo + node.val;
								if (valueOne >= valueTwo) {
									map.put(node, new Node(node, valueTwo, valueTwo, false));
								} else {
									Node n = new Node(node, valueOne, valueTwo, false);
									n.setConsidered(true);
									map.put(node, n);
								}
							}
						}
					}
				} else if (leftNode == null && rightNode != null) {
					
					if (map.get(rightNode).isLeaf) {
						Node childNode = map.get(rightNode);
						if (node.val >= childNode.valueOne) {
							map.put(node, new Node(node, node.val, childNode.valueOne, false));
						} else {
							map.put(node, new Node(node, childNode.valueOne, childNode.valueOne, false));
						}
					} else {
						Node childNode = map.get(rightNode);
						if (node.val + childNode.valueTwo >= childNode.valueOne) {
							map.put(node, new Node(node, node.val + childNode.valueTwo, childNode.valueOne, false));
						} else {
							map.put(node, new Node(node, childNode.valueOne, childNode.valueOne, false));
						}
					}
					
				} else if (rightNode == null && leftNode != null) {
					if (map.get(leftNode).isLeaf) {
						Node childNode = map.get(leftNode);
						if (node.val >= childNode.valueOne) {
							map.put(node, new Node(node, node.val, childNode.valueOne, false));
						} else {
							map.put(node, new Node(node, childNode.valueOne, childNode.valueOne, false));
						}
					} else {
						Node childNode = map.get(leftNode);
						if (node.val + childNode.valueTwo >= childNode.valueOne) {
							map.put(node, new Node(node, node.val + childNode.valueTwo, childNode.valueOne, false));
						} else {
							map.put(node, new Node(node, childNode.valueOne, childNode.valueOne, false));
						}
					}
				}
			}
		}
	}

	public static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;
		
		public TreeNode(int value) {
			this.val = value;
		}
		
		public void setLeft(TreeNode left) {
			this.left = left;
		}
		public void setRight(TreeNode right) {
			this.right = right;
		}
	}
	
	class Node {
		TreeNode node;
		int valueOne;
		int valueTwo;
		boolean isLeaf;
		boolean isConsidered;
		
		Node(TreeNode node, int valueOne, int valueTwo, boolean isLeaf) {
			this.node = node;
			this.valueOne = valueOne;
			this.valueTwo = valueTwo;
			this.isLeaf = isLeaf;
		}

		public void setConsidered(boolean isConsidered) {
			this.isConsidered = isConsidered;
		}
	}
}

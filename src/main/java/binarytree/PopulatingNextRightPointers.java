package binarytree;

public class PopulatingNextRightPointers {
	
	private Node rootNode = null;
	
	public static class Node {
	    public int val;
	    public Node left;
	    public Node right;
	    public Node next;

	    public Node() {}
	    
	    public Node(int _val) {
	        val = _val;
	    }

	    public Node(int _val, Node _left, Node _right, Node _next) {
	        val = _val;
	        left = _left;
	        right = _right;
	        next = _next;
	    }
	}
	
	public Node connect(Node root) {
		
		if (root != null) {
			rootNode = root;
			connectNodeTwo(rootNode);
		}
        return root;
    }
	
	private void connectNode(Node node) {
		if (node != null) {
			Node leftNode = node.left;
			Node rightNode = node.right;
			
			if (leftNode != null && rightNode != null && leftNode.next == null) {
				leftNode.next = rightNode;
			}
			
			
			if (leftNode != null || rightNode != null) {
				if (node != rootNode) {
					Node nextRightNode = node.next;
					if (nextRightNode != null) {
						while (nextRightNode != null) {
							if (nextRightNode.left != null || nextRightNode.right != null) {
								break;
							} else {
								nextRightNode = nextRightNode.next;
							}
						}
						if (nextRightNode != null) {
							if (rightNode != null && nextRightNode.left != null) {
								rightNode.next = nextRightNode.left;
							} else if (rightNode != null && nextRightNode.left == null && nextRightNode.right != null) {
								rightNode.next = nextRightNode.right;
							} else if (rightNode == null && leftNode != null && nextRightNode.left != null) {
								leftNode.next = nextRightNode.left;
							} else if (rightNode == null && leftNode != null && nextRightNode.left == null && nextRightNode.right != null) {
								leftNode.next = nextRightNode.right;
							}
							Node nextRightNodeLeftChild = nextRightNode.left;
							Node nextRightNodeRightChild = nextRightNode.right;
							if (nextRightNodeLeftChild != null && nextRightNodeRightChild != null && nextRightNodeLeftChild.next == null) {
								nextRightNodeLeftChild.next = nextRightNodeRightChild;
							}
						}
						
					}
				}
			}
			
			connectNode(leftNode);
			connectNode(rightNode);
		}
    }
	
	
	private void connectNodeTwo(Node node) {
		if (node != null) {
			if (node == rootNode) {
				if (node.left != null && node.right != null) {
					node.left.next = node.right;
				}
			}
			processRecursively(node.right);
			processRecursively(node.left);
		}
    }
	
	private void processRecursively(Node node) {
		if (node != null) {
			if (node.left == null && node.right == null) {
				return;
			}
			if (node.left != null && node.right != null) {
				node.left.next = node.right;
			}
			Node currentNode = node;
			Node nextNode = currentNode.next;
			while (nextNode != null) {
				if (nextNode.left != null || nextNode.right != null) {
					break;
				} else {
					nextNode = nextNode.next;
				}
			}
			
			if (nextNode != null) {
				if (currentNode.right != null) {
					if (nextNode.left != null) {
						currentNode.right.next = nextNode.left;
					} else if (nextNode.right != null) {
						currentNode.right.next = nextNode.right;
					}
				} else if (currentNode.right == null && currentNode.left != null) {
					if (nextNode.left != null) {
						currentNode.left.next = nextNode.left;
					} else if (nextNode.right != null) {
						currentNode.left.next = nextNode.right;
					}
				}
			}
			processRecursively(node.right);
			processRecursively(node.left);
		}
	}

	private void connectNodeOne(Node node) {
		if (node != null) {
			if (node != rootNode) {
				connectNodeOne(iterateOverAndProcess(node));
			} else {
				Node leftNode = node.left;
				Node rightNode = node.right;
				if (leftNode != null && rightNode != null) {
					leftNode.next = rightNode;
				}
				
				if (leftNode != null) {
					connectNodeOne(leftNode);
				} else if (rightNode != null) {
					connectNodeOne(rightNode);
				}
			}
		}
    }

	private Node iterateOverAndProcess(Node node) {
		Node nextNode = node.next;
		boolean flag = false;
		Node nodeToBereturned = null;
		
		while (node != null && nextNode != null) {
			
			Node leftNodeOne = node.left;
			Node rightNodeOne = node.right;
			
			if (leftNodeOne != null && rightNodeOne != null) {
				leftNodeOne.next = rightNodeOne;
			}
			
			
			if (node.right != null && nextNode.left != null) {
				node.right.next = nextNode.left;
			} else if (node.left != null && node.right == null && nextNode.left != null) {
				node.left.next = nextNode.left;
			} else if (node.left != null && node.right == null && nextNode.left == null && nextNode.right != null) {
				node.left.next = nextNode.right;
			} else if (node.right != null && nextNode.left == null && nextNode.right != null) {
				node.right.next = nextNode.right;
			}
			
			Node leftNodeOneNext = nextNode.left;
			Node rightNodeOneNext = nextNode.right;
			
			if (leftNodeOneNext != null && rightNodeOneNext != null) {
				leftNodeOneNext.next = rightNodeOneNext;
			}
			
			if (leftNodeOne != null && !flag) {
				nodeToBereturned = leftNodeOne;
				flag = true;
			}
			
			if (rightNodeOne != null && !flag) {
				nodeToBereturned = rightNodeOne;
				flag = true;
			}
			
			if (leftNodeOneNext != null && !flag) {
				nodeToBereturned = leftNodeOneNext;
				flag = true;
			}
			
			if (rightNodeOneNext != null && !flag) {
				nodeToBereturned = rightNodeOneNext;
				flag = true;
			}
			
			if ((leftNodeOne != null || rightNodeOne != null) && (leftNodeOneNext != null || rightNodeOneNext != null)) {
				node = nextNode;
				nextNode = node.next;
			} else if ((leftNodeOne != null || rightNodeOne != null) && (leftNodeOneNext == null && rightNodeOneNext == null)) {
				nextNode = nextNode.next;
			}
			
			if (leftNodeOne == null && rightNodeOne == null && leftNodeOneNext == null && rightNodeOneNext == null) {
				node = nextNode;
				nextNode = node.next;
			}
			
		}
		
		return nodeToBereturned;
	}

}

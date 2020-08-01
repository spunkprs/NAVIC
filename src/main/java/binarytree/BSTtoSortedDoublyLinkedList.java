package binarytree;

public class BSTtoSortedDoublyLinkedList {
	
	private Node headNode = null;
	private Node headNodeTemp = null;
	
	public Node treeToDoublyList(Node node) {
		if (node != null) {
			processToConvertTreeIntoDLL(node);
			headNode.right = headNodeTemp;
			headNodeTemp.left = headNode;	
		}
        return headNodeTemp;
    }

	private void processToConvertTreeIntoDLL(Node node) {
		if (node != null) {
			Node leftNode = node.left;
			Node rightNode = node.right;
			
			if (leftNode == null && rightNode == null) {
				if (headNode == null) {
					headNode = node;
					headNodeTemp = node;
				} else {
					updateHeadNode(node);
				}
			} else if (leftNode == null && rightNode != null) {
				if (headNode == null) {
					headNode = node;
					headNodeTemp = node;
				} else {
					updateHeadNode(node);
				}
				processToConvertTreeIntoDLL(rightNode);
			} else {
				processToConvertTreeIntoDLL(leftNode);
				updateHeadNode(node);
				processToConvertTreeIntoDLL(rightNode);
			}
		}
	}
	
	private void updateHeadNode(Node node) {
		if (headNode != null) {
			headNode.right = node;
			node.left = headNode;
			headNode = headNode.right;
		}
	}
}

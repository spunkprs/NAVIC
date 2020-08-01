package binarytree;

import java.util.ArrayList;
import java.util.List;

public class BSTIterator {
	
	private List<Integer> numList = new ArrayList<Integer>();
	private int currentPointer = 0;
	
	public BSTIterator(Node root) {
        populateNumList(root);
    }
	
	private void populateNumList(Node node) {
		if (node != null) {
			Node leftNode = node.left;
			Node rightNode = node.right;
			
			populateNumList(leftNode);
			numList.add(node.data);
			populateNumList(rightNode);
		}
	}

	public int next() {
	int val = numList.get(currentPointer);
	this.currentPointer++;
    return val;    
    }
	
	public boolean hasNext() {
		if (currentPointer < numList.size()) {
			return true;
		}
		return false;
    }
}
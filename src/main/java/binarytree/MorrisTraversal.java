package binarytree;

import java.util.ArrayList;
import java.util.List;

public class MorrisTraversal {
	
	public List<Integer> inorderTraversal(TreeNode root) {
		List<Integer> result = new ArrayList<Integer>();
		TreeNode current = root;
		
		while (current != null) {
			if (current.left != null) {
				boolean flag = findInorderPredecessor(current.left, current);
				if (!flag) {
					current = current.left;
				} else {
					result.add(current.val);
					current = current.right;
				}
			} else if (current.right != null) {
				result.add(current.val);
				current = current.right;
			} else {
				result.add(current.val);
				current = current.right;
			}
		}
        return result;
    }

	private boolean findInorderPredecessor(TreeNode node, TreeNode parentNode) {
		boolean flag = false;
		while (node != null) {
			if (node.right == null) {
				node.right = parentNode;
				break;
			} else {
				if (node.right != parentNode) {
					node = node.right;
				} else {
					flag = true;
					node.right = null;
					break;
				}
			}
		}
		return flag;
	}

}

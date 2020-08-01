package binarytree;

public class DeleteLeavesWithGivenValue {
	
	public TreeNode removeLeafNodes(TreeNode root, int target) {
         boolean result = processToRemoveLeafNodes(root, target);
         if (result) {
        	 return null;
         }
         return root;
    }
	
	private boolean processToRemoveLeafNodes(TreeNode node, int target) {
        if (node != null) {
            TreeNode leftNode = node.left;
            TreeNode rightNode = node.right;
            
            if (leftNode == null && rightNode == null) {
                if (node.val == target) {
                    return true;
                }
                return false;
            } else if (leftNode != null && rightNode != null) {
                boolean flagOne = processToRemoveLeafNodes(leftNode, target);
                if (flagOne) {
                	node.left = null;
                }
                boolean flagTwo = processToRemoveLeafNodes(rightNode, target);
                if(flagTwo) {
                	node.right = null;
                }
                
                if (flagOne && flagTwo && node.left == null && node.right == null && node.val == target) {
                	return true;
                }
                return false;
            } else if (leftNode != null && rightNode == null) {
                boolean flag = processToRemoveLeafNodes(leftNode, target);
                if (flag) {
                    node.left = null;
                }  
                if (flag && node.left == null && node.right == null && node.val == target) {
                	return true;
                }
                return false;
            } else {
                boolean flag = processToRemoveLeafNodes(rightNode, target);
                if (flag) {
                    node.right = null;
                }
                if (flag && node.left == null && node.right == null && node.val == target) {
                	return true;
                }
                return false;
            }
        }
        return false;
    }

}

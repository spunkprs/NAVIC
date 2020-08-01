package binarytree;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BoundaryTraversal {
	
	private List<Integer> result = new ArrayList<Integer>();
    private Set<TreeNode> set = new HashSet<TreeNode>();
    private TreeNode rootNode = null;
    
    public List<Integer> boundaryOfBinaryTree(TreeNode root) {
        rootNode = root;
        processLeftBoundary(root);
        processLeafNodes(root);
        processRightBoundary(root);
        return result;
    }
	
	private void processLeftBoundary(TreeNode node) {
        if (node != null) {
            
            if (!set.contains(node)) {
                set.add(node);
                result.add(node.val);
            }
            
            TreeNode leftNode = node.left;
            TreeNode rightNode = node.right;
            
            if (leftNode != null && rightNode != null) {
                processLeftBoundary(leftNode);
            } else if (leftNode != null && rightNode == null) {
                processLeftBoundary(leftNode);
            } else if (leftNode == null && rightNode != null) {
                if (node != rootNode) {
                    processLeftBoundary(rightNode);
                }
            }
        }
    }
    
    private void processLeafNodes(TreeNode node) {
        if (node != null) {
            TreeNode leftNode = node.left;
            TreeNode rightNode = node.right;
            
            if (leftNode == null && rightNode == null && !set.contains(node)) {
                result.add(node.val);
                set.add(node);
            }
            
            processLeafNodes(leftNode);
            processLeafNodes(rightNode);
        }
    }
    
    private void processRightBoundary(TreeNode node) {
        if (node != null) {
            
            TreeNode leftNode = node.left;
            TreeNode rightNode = node.right;
            
            if (leftNode != null && rightNode != null) {
                processRightBoundary(rightNode);
            } else if (leftNode == null && rightNode != null) {
                processRightBoundary(rightNode);
            } else if (rightNode == null && leftNode != null) {
                if (rootNode != node) {
                    processRightBoundary(leftNode);
                }
            }
            
            if (!set.contains(node)) {
                set.add(node);
                result.add(node.val);
            }
        }
    }

}

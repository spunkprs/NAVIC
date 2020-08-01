package binarytree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LowestCommonAncsetorOfDeepestLeaves {

	
	private Map<Integer, List<TreeNode>> map = new HashMap<Integer, List<TreeNode>>();
    private int maxDepth = Integer.MIN_VALUE;
    private boolean isLeafFound = false;
    
    
    public TreeNode lcaDeepestLeaves(TreeNode root) {
        
        processToFindDeepestLeaves(root, 0);
        List<TreeNode> listOfNodes = map.get(maxDepth);
        if (listOfNodes.size() == 1) {
            return listOfNodes.get(0);
        } else {
        	return processToFindLowestCommonAncestor(listOfNodes.get(0), listOfNodes.get(listOfNodes.size() - 1), root);
        }
    }
    
    private TreeNode processToFindLowestCommonAncestor(TreeNode leafOne, TreeNode leafTwo, TreeNode node) {
        List<TreeNode> traversedListOne = new ArrayList<TreeNode>();
        List<TreeNode> traversedListTwo = new ArrayList<TreeNode>();
         
        prepareTraversedList(node, leafOne, traversedListOne); 
        isLeafFound = false;
        prepareTraversedList(node, leafTwo, traversedListTwo);
         
         int index = 0;
         while (index < traversedListOne.size()) {
             if (traversedListOne.get(index) == traversedListTwo.get(index)) {
                 index++;
             } else {
                 break;
             }
         }
         return traversedListOne.get(index - 1);
     }
     
     
     private boolean prepareTraversedList(TreeNode node, TreeNode leaf, List<TreeNode> traversedList) {
         if (node != null) {
             if (node != leaf) {
                 traversedList.add(node);
                 TreeNode leftNode = node.left;
                 TreeNode rightNode = node.right;
                 boolean flagOne = prepareTraversedList(leftNode, leaf, traversedList);
                 if (flagOne && !isLeafFound) {
                     traversedList.remove(traversedList.size() - 1);
                 }
                 
                 if (!isLeafFound) {
                 boolean flagTwo = prepareTraversedList(rightNode, leaf, traversedList);
                     if (flagTwo && !isLeafFound) {
                         traversedList.remove(traversedList.size() - 1);
                     }
                 }
                 
             } else {
                 traversedList.add(leaf);
                 isLeafFound = true;
             }
         }
         
         if (node != null) {
             return true;
         } 
         return false;
     }
     
     private void processToFindDeepestLeaves(TreeNode node, int depth) {
         if (node != null) {
             
             TreeNode leftNode = node.left;
             TreeNode rightNode = node.right;
             
             if (leftNode == null && rightNode == null) {
                 if (map.containsKey(depth) && depth == maxDepth) {
                     List<TreeNode> list = map.get(depth);
                     list.add(node);
                 } else {
                     if (depth > maxDepth) {
                         maxDepth = depth;
                         List<TreeNode> list = new ArrayList<TreeNode>();
                         list.add(node);
                         map.clear();
                         map.put(depth, list);
                     }
                 }
             } else {
                 processToFindDeepestLeaves(leftNode, depth + 1);
                 processToFindDeepestLeaves(rightNode, depth + 1);
             }
         }
     }
	

}

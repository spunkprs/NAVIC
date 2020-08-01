package binarytree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NodesAtDistanceKApproachTwo {
	
	
	private List<Integer> resultantList = new ArrayList<Integer>();
    private boolean flag = false;
    private int upwardDistance = 0;
    private TreeNode updatedTargetNode;
    private Map<TreeNode, Pair> map = new HashMap<TreeNode, Pair>();
	
	
	public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        
        if (root != target) {
            processToComputeNodesAtDistanceKPhaseOne(target, K, 0);
            processToComputeNodesAtDistanceKPhaseTwo(root, target, K);
            for (TreeNode keyNode : map.keySet()) {
                Pair p = map.get(keyNode);
                if (p.lengthTobeCovered == 0) {
                	resultantList.add(keyNode.val);
                } else {
                	if (p.lengthTobeCovered > 0 && p.description.equals("left")) {
                        processToComputeNodesAtDistanceKPhaseOne(keyNode.left, p.lengthTobeCovered, 1);
                    } else if (p.lengthTobeCovered > 0 && p.description.equals("right")) {
                        processToComputeNodesAtDistanceKPhaseOne(keyNode.right, p.lengthTobeCovered, 1);
                    }
                }
            }
        } else {
            processToComputeNodesAtDistanceKPhaseOne(root, K, 0);
        }
        return resultantList;
    }
	
	private void processToComputeNodesAtDistanceKPhaseTwo(TreeNode node, TreeNode target, int distance) {
        if (node != null) {
            if (node != target && !flag) {
                
                TreeNode leftNode = node.left;
                TreeNode rightNode = node.right;
                
                processToComputeNodesAtDistanceKPhaseTwo(leftNode, target, distance);
                processToComputeNodesAtDistanceKPhaseTwo(rightNode, target, distance);
                if (flag) {
                    upwardDistance++;
                    if (leftNode != null && leftNode == updatedTargetNode) {
                        Pair p = new Pair("right", distance - upwardDistance);
                        map.put(node, p);
                    } else if (rightNode != null && rightNode == updatedTargetNode) {
                        Pair p = new Pair("left", distance - upwardDistance);
                        map.put(node, p);
                    }
                    updatedTargetNode = node;
                }
                
            } else if (node == target) {
                flag = true;
                updatedTargetNode = target;
            }
        }
    }
    
    private void processToComputeNodesAtDistanceKPhaseOne(TreeNode node, int k, int distanceCovered)        {
        
        if (node != null) {
            
            if (distanceCovered == k) {
                resultantList.add(node.val);
            } else {
            
                TreeNode leftNode = node.left;
                TreeNode rightNode = node.right;    
                
                if (leftNode != null) {
                    processToComputeNodesAtDistanceKPhaseOne(leftNode, k, distanceCovered + 1);
                }
                
                if (rightNode != null) {
                    processToComputeNodesAtDistanceKPhaseOne(rightNode, k, distanceCovered + 1);
                }
            }
        }
    }
	
	class Pair {
        private String description;
        private int lengthTobeCovered;
        
        public Pair(String description, int lengthTobeCovered) {
            this.description = description;
            this.lengthTobeCovered = lengthTobeCovered;
        }
    }

}

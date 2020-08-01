package binarytree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TreeAncestor {
	
	
	private GenericTreeNode root = null;
    private List<GenericTreeNode> list = new ArrayList<GenericTreeNode>();
    private boolean shallContinue = true;
    private Map<Integer, GenericTreeNode> map = new HashMap<Integer, GenericTreeNode>();
	
	 public TreeAncestor(int n, int[] parent) {
		 	GenericTreeNode[] nodeArray = new GenericTreeNode[n];
	        buildTree(parent, nodeArray);
	    }
	    
	    public int getKthAncestor(int node, int k) {
	    	list = new ArrayList<GenericTreeNode>();
	    	shallContinue = true;
	    	GenericTreeNode rootNode = root;
		/*
		 * processToFetchKthAncestor(node, rootNode); int size = list.size() - 1; if (k
		 * > size) { return -1; } else { return list.get(size - k).getVal(); }
		 */
	    	return processToFetchKthAncestorApproachTwo(node, k);
	    }
	    
	    private void processToFetchKthAncestor(int node, GenericTreeNode treeNode) {
	        if (treeNode != null && shallContinue) {
	            
	            if (treeNode.getVal() != node) {
	            	list.add(treeNode);
	            	for (GenericTreeNode childNode : treeNode.getListOfChildren()) {
	            		processToFetchKthAncestor(node, childNode);
	            	}
	            	if (shallContinue) {
	            		list.remove(list.size() - 1);
	            	}
	            } else {
	            	list.add(treeNode);
	            	shallContinue = false;
	            }
	        }
	    }
	    
	    private int processToFetchKthAncestorApproachTwo(int node, int k) {
	        if (map.containsKey(node)) {
	        	GenericTreeNode startingNode = map.get(node);
	        	int distance = 0;
	        	
	        	while (distance <= k && startingNode != null) {
	        		distance++;
	        		startingNode = startingNode.getParent();
	        		if (distance == k) {
	        			break;
	        		}
	        	}
	        	if (startingNode == null) {
	        		return -1;
	        	} else {
	        		return startingNode.getVal();
	        	}
	        }
	        return -1;
	    }
	    
	    private void buildTree(int parent[], GenericTreeNode[] nodeArray) {
	        for (int i = 0; i < parent.length; i++) {
	            if (parent[i] == -1) {
	                root = new GenericTreeNode(0, null);
	                nodeArray[0] = root;
	            } else {
	            	GenericTreeNode parentNode = nodeArray[parent[i]];
	            	GenericTreeNode node = new GenericTreeNode(i, parentNode);
	                nodeArray[i] = node;
	                List<GenericTreeNode> listOfChildren = parentNode.getListOfChildren();
	                listOfChildren.add(node);
	            }
	            map.put(i, nodeArray[i]);
	        }
	    }
}

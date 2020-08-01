package binarytree;

import java.util.ArrayList;
import java.util.List;

public class GenericTreeNode {
	
	private int val;
	private List<GenericTreeNode> listOfChildren;
	private GenericTreeNode parent;
	
	public GenericTreeNode(int val, GenericTreeNode parentNode) {
		this.val = val;
		this.listOfChildren = new ArrayList<GenericTreeNode>();
		this.parent = parentNode;
	}
	
	public GenericTreeNode(int val) {
		this.val = val;
		this.listOfChildren = new ArrayList<GenericTreeNode>();
	}
	
	public int getVal() {
		return val;
	}
	public List<GenericTreeNode> getListOfChildren() {
		return listOfChildren;
	}
	
	
	public GenericTreeNode getParent() {
		return parent;
	}
	public void setParent(GenericTreeNode parent) {
		this.parent = parent;
	}
	
	
}

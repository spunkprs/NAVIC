package stack;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class MinimumRemovesToMakeValidParantheses {
	
	
	public String minRemoveToMakeValid(String s) {
		
		Queue<Node> queue = new LinkedList<Node>();
		Stack<Node> stack = new Stack<Node>();
		
		char arr[] = s.toCharArray();
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == ')') {
				if (!stack.isEmpty()) {
					stack.pop();
				} else {
					queue.add(new Node(arr[i], i));
				}
			}
			
			if (arr[i] == '(') {
				stack.add(new Node(arr[i], i));
			}
		}
		
		while (!stack.isEmpty()) {
			Node node = stack.pop();
			arr[node.indexInArray] = '0';
		}
		
		while (!queue.isEmpty()) {
			Node node = queue.poll();
			arr[node.indexInArray] = '0';
		}
		String result = new String();
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] != '0') {
				result += arr[i];
			}
		}
		
	return result;
    }


	class Node {
    private Character ch;
    private int indexInArray;
    //private boolean markForRemoval;
    
    public Node(Character ch, int indexInArray) {
        this.ch = ch;
        this.indexInArray = indexInArray;
        //this.markForRemoval = markForRemoval;
    }
	}

}

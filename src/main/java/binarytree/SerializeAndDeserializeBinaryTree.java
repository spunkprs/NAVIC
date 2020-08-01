package binarytree;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class SerializeAndDeserializeBinaryTree {
	
	 	public String serialize(TreeNode root) {
	 		 StringBuffer sb = processToSerialize(root, new StringBuffer());
	 		 return sb.toString();
	    }

		private StringBuffer processToSerialize(TreeNode node, StringBuffer serialize) {
			
			if (node == null) {
				serialize.append("X");
				serialize.append(",");
			} else {
				
				serialize.append(String.valueOf(node.val));
				serialize.append(",");
				
				processToSerialize(node.left, serialize);
				processToSerialize(node.right, serialize);
			}
			return serialize;
		}
		
		
		public TreeNode deserialize(String data) {
			Queue<String> queue = new LinkedList<String>(Arrays.asList(data.split(",")));
			return processToDeserialize(queue);
	    }

		private TreeNode processToDeserialize(Queue<String> queue) {
			String element = queue.poll();
			if (element != null) {
				if (element.equals("X")) {
					return null;
				} else {
					TreeNode node = new TreeNode(Integer.parseInt(element));
					TreeNode left = processToDeserialize(queue);
					TreeNode right = processToDeserialize(queue);
					node.left = left;
					node.right = right;
					return node;
				}
			}
			return null;
		}

}

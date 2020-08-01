package binarytree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class SerializeAndDeserializeNaryTree {
	
	public static class Node {
	    public int val;
	    public List<Node> children;

	    public Node() {}

	    public Node(int _val) {
	        val = _val;
	    }

	    public Node(int _val, List<Node> _children) {
	        val = _val;
	        children = _children;
	    }
	}
	
	
	public String serialize(Node root) {
		StringBuffer sb = new StringBuffer();
		Queue<PairForSerializationAndDesrialization> queue = new LinkedList<PairForSerializationAndDesrialization>();
		PairForSerializationAndDesrialization node = new PairForSerializationAndDesrialization(root, 1, null);
		queue.add(node);
		processToSerialize(queue, sb);
        return sb.toString();
    }
	
	public Node deserialize(String data) {
		List<String> serializedData = new ArrayList<String>(Arrays.asList(data.split(",")));
		Map<Integer, PairForSerializationAndDesrialization> map = new LinkedHashMap<Integer, PairForSerializationAndDesrialization>();
		Node root = null;
		return processToDeserialize(serializedData, map, root);
    }


	private Node processToDeserialize(List<String> serializedData, Map<Integer, PairForSerializationAndDesrialization> map, Node root) {
		int i = 0;
		while (i < serializedData.size()) {
			int num = Integer.parseInt(serializedData.get(i));
			String depth = serializedData.get(i + 1);
			depth = depth.replaceAll("D", "");
			depth = depth.trim();
			int numDepth = Integer.parseInt(depth);
			String parent = serializedData.get(i + 2);
			if (parent.equals("N")) {
				root = new Node(num);
				PairForSerializationAndDesrialization rootNode = new PairForSerializationAndDesrialization(root, numDepth, null);
				map.put(num, rootNode);
			} else {
				int parentValue = Integer.parseInt(parent);
				Node parentNode = map.get(parentValue).node;
				Node childNode = new Node(num);
				PairForSerializationAndDesrialization pairForSerializationAndDesrializationChildNode = new PairForSerializationAndDesrialization(childNode, numDepth, parentNode);
				map.put(num, pairForSerializationAndDesrializationChildNode);
				if (parentNode.children == null) {
					List<Node> children = new ArrayList<SerializeAndDeserializeNaryTree.Node>();
					children.add(childNode);
					parentNode.children = children;
				} else {
					parentNode.children.add(childNode);
				}
			}
			i+=3;
		}
		return root;
	}

	private void processToSerialize(Queue<PairForSerializationAndDesrialization> queue, StringBuffer sb) {
		while (!queue.isEmpty()) {
			PairForSerializationAndDesrialization parentNode = queue.peek();
			List<Node> children = parentNode.node.children;
			if (children != null) {
				for (Node child : children) {
					PairForSerializationAndDesrialization pairForSerializationChild = new PairForSerializationAndDesrialization(child, parentNode.depth + 1, parentNode.node);
					queue.add(pairForSerializationChild);
				}
			}
			parentNode = queue.poll();
			appendToStringBuffer(sb, parentNode);
		}
	}
	
	
	private void appendToStringBuffer(StringBuffer sb, PairForSerializationAndDesrialization pairForSerializationNode) {
		sb.append(pairForSerializationNode.node.val);
		sb.append(",");
		sb.append("D" + pairForSerializationNode.depth);
		sb.append(",");
		if (pairForSerializationNode.parent == null) {
			sb.append("N");
		} else {
			sb.append(pairForSerializationNode.parent.val);
		}
		sb.append(",");
	}


	class PairForSerializationAndDesrialization {
		Node node;
		int depth;
		Node parent;
		
		public PairForSerializationAndDesrialization(Node node, int depth, Node parent) {
			this.depth = depth;
			this.node = node;
			this.parent = parent;
		}
	}
}

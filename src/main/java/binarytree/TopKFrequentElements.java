package binarytree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TopKFrequentElements {
	
	private Map<Integer, LinkedListNode> mapOne = new HashMap<Integer, LinkedListNode>();
	private Map<Integer, LinkedListNode> mapTwo = new HashMap<Integer, LinkedListNode>();
	private int highestFrequency = 0;
	
	public List<Integer> topKFrequent(int[] nums, int k) {
		for (int i = 0; i < nums.length; i++) {
			if (mapOne.containsKey(nums[i])) {
				LinkedListNode node = mapOne.get(nums[i]);
				updateMaps(node, node.frequency + 1);
			} else {
				LinkedListNode node = new LinkedListNode();
				node.value = nums[i];
				node.frequency = 1;
				node.previous = node;
				updateHigestFrequency(node.frequency);
				mapOne.put(nums[i], node);
				if (mapTwo.containsKey(node.frequency)) {
					LinkedListNode nodeHead = mapTwo.get(node.frequency);
					LinkedListNode taildNode = nodeHead.previous;
					taildNode.next = node;
					node.previous = taildNode;
					nodeHead.previous = node;
				} else {
					mapTwo.put(node.frequency, node);
				}
			}
		}
		List<Integer> result = prepareResult(k);
		if (!result.isEmpty()) {
			return result;
		} else {
			for (int i = 0; i < nums.length ; i++) {
				result.add(nums[i]);
			}
			return result;
		}
    }
	
	private void updateHigestFrequency(int frequency) {
		if (frequency > highestFrequency) {
			highestFrequency = frequency;
		}
	}

	private List<Integer> prepareResult(int k) {
		List<Integer> result = new ArrayList<Integer>();
		int frequency = highestFrequency;
		while (k >= 1) {
			LinkedListNode headNode = mapTwo.get(frequency);
			if (headNode != null) {
				while (headNode != null) {
					result.add(headNode.value);
					headNode = headNode.next;
					k--;
				}
			}
			frequency--;
		}
		return result;
	}

	private void updateMaps(LinkedListNode node, int updatedFrequency) {
		LinkedListNode headNode = mapTwo.get(node.frequency);
		if (headNode.value == node.value) {
			if (headNode.previous == headNode) {
				mapTwo.remove(node.frequency);
			} else {
				LinkedListNode nextNode = headNode.next;
				nextNode.previous = headNode.previous;
				mapTwo.put(node.frequency, nextNode);
			}
		} else if (headNode.previous.value == node.value) {
			LinkedListNode previousNode = headNode.previous;
			LinkedListNode prevNode = previousNode.previous;
			prevNode.next = null;
			headNode.previous = prevNode;
		} else {
			LinkedListNode previousNode = node.previous;
			LinkedListNode nextNode = node.next;
			previousNode.next = nextNode;
			nextNode.previous = previousNode;
		}
		
		node.frequency = updatedFrequency;
		updateHigestFrequency(node.frequency);
		
		if (mapTwo.containsKey(updatedFrequency)) {
			headNode = mapTwo.get(updatedFrequency);
			LinkedListNode tailNode = headNode.previous;
			tailNode.next = node;
			node.previous = tailNode;
			node.next = null;
			headNode.previous = node;
		} else {
			node.previous = node;
			node.next = null;
			mapTwo.put(updatedFrequency, node);
		}
	}

	class LinkedListNode {
		int value;
		int frequency;
		LinkedListNode previous;
		LinkedListNode next;
	}
}





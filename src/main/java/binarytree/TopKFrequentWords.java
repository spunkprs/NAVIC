package binarytree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TopKFrequentWords {
	
	
	private Map<String, LinkedListNode> mapOne = new HashMap<String, LinkedListNode>();
	private Map<Integer, Pair> mapTwo = new HashMap<Integer, Pair>();
	private int highestFrequency = 0;
	
	public List<String> topKFrequent(String[] words, int k) {
		for (int i = 0; i < words.length; i++) {
			if (mapOne.containsKey(words[i])) {
				LinkedListNode node = mapOne.get(words[i]);
				updateMaps(node, node.frequency + 1);
			} else {
				LinkedListNode node = new LinkedListNode();
				node.word = words[i];
				node.frequency = 1;
				node.previous = node;
				updateHigestFrequency(node.frequency);
				mapOne.put(words[i], node);
				if (mapTwo.containsKey(node.frequency)) {
					Pair p = mapTwo.get(node.frequency);
					LinkedListNode nodeHead = p.linkedListNode;
					LinkedListNode taildNode = nodeHead.previous;
					taildNode.next = node;
					node.previous = taildNode;
					nodeHead.previous = node;
					p.length = p.length + 1;
					
				} else {
					Pair p = new Pair(1);
					p.linkedListNode = node;
					mapTwo.put(node.frequency, p);
				}
			}
		}
		List<String> result = prepareResult(k);
		if (!result.isEmpty()) {
			return result;
		} else {
			for (int i = 0; i < words.length ; i++) {
				result.add(words[i]);
			}
			return result;
		}
    }
	
	
	public List<String> topKFrequentApproachTwo(String[] words, int k) {
		Map<String, Integer> countMap = new HashMap();
        for (String word: words) {
            if (countMap.containsKey(word)) {
            	countMap.put(word, countMap.get(word) + 1);
            } else {
            	countMap.put(word, 1);
            }
        }
        
        List<String> candidates = new ArrayList(countMap.keySet());
        Collections.sort(candidates, new Comparator<String>() {
			@Override
			public int compare(String o1, String o2) {
				if (countMap.get(o2) > countMap.get(o1)) {
					return 1;
				} else if (countMap.get(o2) < countMap.get(o1)) {
					return -1;
				} else {
					return o1.compareTo(o2);
				}
			}
		});
		return candidates.subList(0, k);
    }
	
	
	private List<String> prepareResult(int k) {
		List<String> result = new ArrayList<String>();
		int frequency = highestFrequency;
		while (k >= 1) {
			Pair p = mapTwo.get(frequency);
			if (p != null) {
				LinkedListNode headNode = p.linkedListNode;
				if (k >= p.length) {
					if (headNode != null) {
						List<String> interimResult = new ArrayList<String>();
						while (headNode != null) {
							interimResult.add(headNode.word);
							headNode = headNode.next;
						}
						k -= p.length;
						Collections.sort(interimResult);
						result.addAll(interimResult);
					}
				} else {
					if (headNode != null) {
						List<String> interimResult = new ArrayList<String>();
						while (headNode != null) {
							interimResult.add(headNode.word);
							headNode = headNode.next;
						}
						Collections.sort(interimResult);
						interimResult = updateInterimResult(k, interimResult);
						k -= p.length;
						result.addAll(interimResult);
					}
				}
				frequency--;
			}
		}
		return result;
	}
	
	private List<String> updateInterimResult(int k, List<String> result) {
		List<String> interimResult = new ArrayList<String>();
		for (int i = 0; i < k; i++) {
			interimResult.add(result.get(i));
		}
		return interimResult;
	}

	private void updateMaps(LinkedListNode node, int updatedFrequency) {
		Pair p = mapTwo.get(node.frequency);
		LinkedListNode headNode = p.linkedListNode;
		if (headNode.word.equals(node.word)) {
			if (headNode.previous == headNode) {
				mapTwo.remove(node.frequency);
			} else {
				LinkedListNode nextNode = headNode.next;
				nextNode.previous = headNode.previous;
				p.length = p.length - 1;
				p.linkedListNode = nextNode;
				//mapTwo.put(node.frequency, nextNode);
			}
		} else if (headNode.previous.word.equals(node.word)) {
			LinkedListNode previousNode = headNode.previous;
			LinkedListNode prevNode = previousNode.previous;
			prevNode.next = null;
			headNode.previous = prevNode;
			p.length = p.length - 1;
		} else {
			LinkedListNode previousNode = node.previous;
			LinkedListNode nextNode = node.next;
			previousNode.next = nextNode;
			nextNode.previous = previousNode;
			p.length = p.length - 1;
		}
		
		node.frequency = updatedFrequency;
		updateHigestFrequency(node.frequency);
		
		if (mapTwo.containsKey(updatedFrequency)) {
			p = mapTwo.get(updatedFrequency);
			headNode = p.linkedListNode;
			LinkedListNode tailNode = headNode.previous;
			tailNode.next = node;
			node.previous = tailNode;
			node.next = null;
			headNode.previous = node;
			p.length = p.length + 1;
		} else {
			p = new Pair(1);
			node.previous = node;
			node.next = null;
			p.linkedListNode = node;
			mapTwo.put(updatedFrequency, p);
		}
	}
	
	private void updateHigestFrequency(int frequency) {
		if (frequency > highestFrequency) {
			highestFrequency = frequency;
		}
	}
	
	class LinkedListNode {
		String word;
		int frequency;
		LinkedListNode next;
		LinkedListNode previous;
	}
	
	class Pair {
		int length;
		LinkedListNode linkedListNode;
		Pair(int length) {
			this.length = length;
		}
	}

}



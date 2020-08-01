package dp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class WordLadder {
	
	
	public int ladderLengthBFSApproach(String beginWord, String endWord, List<String> wordList) {
		Set<String> wordSet = new HashSet<String>(wordList);
		Set<String> visitedWords = new HashSet<String>();
		Map<String, Integer> map = new HashMap<String, Integer>();
		
		if (!wordSet.contains(endWord)) {
			return 0;
		} else {
			Queue<String> queue = new LinkedList<String>();
			queue.add(beginWord);
			visitedWords.add(beginWord);
			map.put(beginWord, 0);
			while (!queue.isEmpty()) {
				
					String wordToBePolled = queue.peek();
					if (wordToBePolled.equals(endWord)) {
						return map.get(endWord) + 1;
					}
					for (int i = 0; i < wordToBePolled.length(); i++) {
						char charArray[] = wordToBePolled.toCharArray();
						for (char b = 'a'; b <= 'z'; b++) {
							charArray[i] = b;
							String newWord = new String(charArray);
								if (wordSet.contains(newWord) && !wordToBePolled.equals(newWord) && !visitedWords.contains(newWord)) {
									queue.add(newWord);
									visitedWords.add(newWord);
									map.put(newWord, map.get(wordToBePolled) + 1);
								}
						}
					}
				queue.poll();
			}
		}
		return 0;
    }
	
	public int ladderLength(String beginWord, String endWord, List<String> wordList) {
		Set<Integer> usedIndexes = new HashSet<Integer>();
		int result = processToFetchLadderLength(beginWord, endWord, wordList, usedIndexes);
		return result == Integer.MAX_VALUE ? 0 : result;
    }

	private int processToFetchLadderLength(String beginWord, String endWord, List<String> wordList, Set<Integer> usedIndexes) {
		if (!beginWord.equals(endWord)) {
			List<Pair> pairs = fetchWordsThatCanBeCompared(beginWord, wordList, usedIndexes);
			int min = Integer.MAX_VALUE;
			if (!pairs.isEmpty()) {
				for (Pair p : pairs) {
					usedIndexes.add(p.index);
					min = Math.min(min, processToFetchLadderLength(p.word, endWord, wordList, usedIndexes));
					usedIndexes.remove(p.index);
				}
				if (min == Integer.MAX_VALUE) {
					return min;
				} else {
					return min + 1;
				}
			}
			return min;
		} else {
			return 1;
		}
	}

	private List<Pair> fetchWordsThatCanBeCompared(String beginWord, List<String> wordList, Set<Integer> usedIndexes) {
		List<Pair> result = new ArrayList<Pair>();
		for (int i = 0; i < wordList.size(); i++) {
			if (!usedIndexes.contains(i)) {
				String wordToBeComparedWith = wordList.get(i);
				char beginWordArray[] = beginWord.toCharArray();
				char wordToBeComparedWithArray[] = wordToBeComparedWith.toCharArray();
				int count = 0;
				for (int j = 0; j < beginWordArray.length; j++) {
					if (beginWordArray[j] != wordToBeComparedWithArray[j]) {
						count++;
					}
				}
				if (count == 1) {
					result.add(new Pair(wordToBeComparedWith, i));
				}
			}
		}
		return result;
	}
	
	class Pair {
		String word;
		int index;
		
		Pair(String word, int index) {
			this.word = word;
			this.index = index;
		}
	}

}

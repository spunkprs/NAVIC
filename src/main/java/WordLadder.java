import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordLadder {

    private int length = Integer.MAX_VALUE;
    private int lengthOne = 1;

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        boolean shallProceed = false;
        for (String word : wordList) {
            if (word.equals(endWord)) {
                shallProceed = true;
                break;
            }
        }
        if (!shallProceed) {
            return 0;
        } else {
            Map<String, Boolean> map = insertWordsInToMap(wordList);
            map.put(beginWord, false);
            Map<String, Integer> memoryMap = new HashMap<String, Integer>();
            memoryMap.put(endWord, 1);
            processToComputeLengthOfTransformation(beginWord, endWord, wordList, map, memoryMap);
            return length;
        }
    }

    private Map<String, Boolean> insertWordsInToMap(List<String> wordList) {
        Map<String, Boolean> map = new HashMap<String, Boolean>();
        for (String word : wordList) {
            map.put(word, false);
        }
        return map;
    }

    private int processToComputeLengthOfTransformation(String beginWord, String endWord, List<String> wordList, Map<String, Boolean> map, Map<String, Integer> memoryMap) {
        if (!beginWord.equals(endWord)) {
            for (String word : wordList) {
                if (!map.get(word) && computeDifference(beginWord, word) == 1) {
                    lengthOne++;
                    map.put(word, true);
                    if (memoryMap.containsKey(word)) {
                    	memoryMap.put(beginWord, memoryMap.get(word) + 1);
                    	return memoryMap.get(beginWord);
                    } else {
                    	Integer val = processToComputeLengthOfTransformation(word, endWord, wordList, map, memoryMap);
                    	if (val != null) {
                    		if (memoryMap.get(beginWord) != null) {
                    			if (val.intValue() < memoryMap.get(beginWord)) {
                    				memoryMap.put(beginWord, val);
                    			}
                    		} else {
                    			memoryMap.put(beginWord, val + 1);
                    		}
                    	}
					}
                    map.put(word, false);
                    lengthOne--;
                }
            }
        } else {
            if (lengthOne < length) {
                length = lengthOne;
            }
        }
        return memoryMap.get(beginWord);
    }

    private int computeDifference(String beginWord, String word) {
        char arrOne[] = beginWord.toCharArray();
        char arrTwo[] = word.toCharArray();
        int diff = 0;
        for (int i = 0; i < arrOne.length; i++) {
            if (arrOne[i] != arrTwo[i]) {
                diff++;
            }
            if (diff > 1) {
                break;
            }
        }
        return diff;
    }
}

package dp;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
 
public class WordBreakProblem {
	
	private Set<String> dictionary = new HashSet<String>();
	private Map<String, Boolean> map = new HashMap<String, Boolean>();
	
	public boolean wordBreak(String s, List<String> wordDict) {
		
		for (String word : wordDict) {
			dictionary.add(word);
		}
		return processToComputeWordBreak(s, 0);
    }

	private boolean processToComputeWordBreak(String word, int startIndex) {
		int length = 1;
		boolean result = false;
		boolean finalResult = false;
			for (int i = startIndex; i < word.length(); i++) {
				String subString = word.substring(startIndex, startIndex + length);
				String remainingWord = "";
				if (startIndex + length == word.length()) {
					remainingWord = "";
				} else {
					remainingWord = word.substring(startIndex + length, word.length());
				}
				
				boolean intermediateResult = false;
				if (!remainingWord.isEmpty()) {
					if (!map.containsKey(remainingWord)) {
						intermediateResult = processToComputeWordBreak(remainingWord, 0);
						map.put(remainingWord, intermediateResult);
					} else {
						intermediateResult = map.get(remainingWord);
					}
				} else {
					intermediateResult = true;
				}
				
				result = dictionary.contains(subString) && intermediateResult;
				if (result) {
					finalResult = result;
					break;
				} else {
					finalResult = finalResult && result; 
				}
				length++;
			}
		map.put(word, finalResult);
		return map.get(word);
	}
}

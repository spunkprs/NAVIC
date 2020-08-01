package dp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class LongestStringChain {
	
	private int minimumLength = 0;
	
	public int longestStrChain(String[] words) {
		
	 Set<String> wordSetOne = new HashSet<String>();
	 Set<String> wordSetTwo = new HashSet<String>();
	 Map<String, Integer> map = new HashMap<String, Integer>();
	 
	 List<String> wordList = Arrays.asList(words);
	 Collections.sort(wordList, new LengthComparator());
	 minimumLength = wordList.get(0).length();
	 
	 for (String word : wordList) {
		 if (!wordSetOne.contains(word)) {
			 wordSetOne.add(word);
		 }
	 }
	 int maxNumber = 0;
	 for (int i = wordList.size() - 1; i >= 0; i--) {
		 if (!wordSetTwo.contains(wordList.get(i))) {
			 int result = processToFindLongestStringChain(wordSetOne, wordList.get(i), map, wordSetTwo);
			 if (result > maxNumber) {
				 maxNumber = result;
			 }
		 }
	 }
     return maxNumber;   
    }

	private int processToFindLongestStringChain(Set<String> wordSetOne, String word, Map<String, Integer> map, Set<String> wordSetTwo) {
		if (!map.containsKey(word)) {
			List<String> children = fetchChildren(word, wordSetOne);
			if (children.isEmpty() && wordSetOne.contains(word)) {
				map.put(word, 1);
				wordSetTwo.add(word);
				return map.get(word);
			} else {
				int maxNumber = 0;
				for (String child : children) {
					int result = processToFindLongestStringChain(wordSetOne, child, map, wordSetTwo);
					if (result > maxNumber) {
						maxNumber = result;
					}
				}
				map.put(word, maxNumber + 1);
				wordSetTwo.add(word);
				return maxNumber + 1;
			}
		} else {
			return map.get(word);
		}
	}
	
	private List<String> fetchChildren(String word, Set<String> wordSetOne) {
		List<String> children = new ArrayList<String>();
		if (word.length() > minimumLength) {
			for (int i = 0; i < word.length(); i++) {
				StringBuilder sb = new StringBuilder(word);
				String updatedWord = sb.deleteCharAt(i).toString();
				if (wordSetOne.contains(updatedWord)) {
					children.add(updatedWord);
				}
			}
		}
		return children;
	}

	class LengthComparator implements Comparator<String> {

		@Override
		public int compare(String o1, String o2) {
			return o1.length() < o2.length() ? -1 : o1.length() > o2.length() ? 1 : 0;
		}
		
	}
}

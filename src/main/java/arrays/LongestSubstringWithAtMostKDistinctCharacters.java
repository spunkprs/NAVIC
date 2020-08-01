package arrays;

import java.util.HashMap;
import java.util.Map;

public class LongestSubstringWithAtMostKDistinctCharacters {
	
	public int lengthOfLongestSubstringKDistinct(String s, int k) {
		if (k > s.length() && !s.isEmpty()) {
			return 1;
		} else if (s.isEmpty()) {
			return 0;
		}
		return processToComputeLengthOfLongestSubStringWithKDistinctCharacters(s.toCharArray(), k);
    }

	private int processToComputeLengthOfLongestSubStringWithKDistinctCharacters(char[] charArray, int k) {
		Map<Character, Integer> map = new HashMap<Character, Integer>();
		int maxLength = 0; 
		int fromIndex = charArray.length - 1;
		for (int i = charArray.length - 1; i >= 0; i--) {
			if (map.size() < k) {
				if (map.containsKey(charArray[i])) {
					map.put(charArray[i], map.get(charArray[i]) + 1);
				} else {
					map.put(charArray[i], 1);
				}
				if (map.size() == k) {
					maxLength = updateMaxLength(i, fromIndex, maxLength);
				}
			} else if (map.size() == k) {
				if (map.containsKey(charArray[i])) {
					map.put(charArray[i], map.get(charArray[i]) + 1);
				} else {
					map.put(charArray[i], 1);
				}
				
				if (map.size() == k) {
					maxLength = updateMaxLength(i, fromIndex, maxLength);
				}
				
				if (map.size() > k) {
					while (map.size() != k) {
						if (map.get(charArray[fromIndex]) == 1) {
							map.remove(charArray[fromIndex]);
						} else {
							map.put(charArray[fromIndex], map.get(charArray[fromIndex]) - 1);
						}
						fromIndex--;
					}
					maxLength = updateMaxLength(i, fromIndex, maxLength);
				}
			} 
			//toIndex--;
		}
		return maxLength;
	}

	private int updateMaxLength(int toIndex, int fromIndex, int maxLength) {
		if ((fromIndex - toIndex + 1) > maxLength) {
			maxLength = fromIndex - toIndex + 1;
		}
		return maxLength;
	} 

}

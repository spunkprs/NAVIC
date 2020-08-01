import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class SortCharactersByFrequency {
	
	public String frequencySort(String s) {
		Map<Integer, HashSet<Character>> mapOne = new HashMap<Integer, HashSet<Character>>();
		Map<Character, Integer> mapTwo = new HashMap<Character, Integer>();
		
		char arr[] = s.toCharArray();
		int maxFrequency = 0;
		for (Character c : arr) {
			if (mapTwo.containsKey(c)) {
				HashSet<Character> set = mapOne.get(mapTwo.get(c));
				if (set.contains(c)) {
					set.remove(c);
				}
				
				int updatedFrequency = mapTwo.get(c) + 1;
				maxFrequency = updateMaxFrequency(maxFrequency, updatedFrequency);
				mapTwo.put(c, updatedFrequency);
				
				if (mapOne.containsKey(updatedFrequency)) {
					HashSet<Character> hashSet = mapOne.get(updatedFrequency);
					if (!hashSet.contains(c)) {
						hashSet.add(c);
					}
				} else {
					HashSet<Character> hashSet = new HashSet<Character>();
					hashSet.add(c);
					mapOne.put(updatedFrequency, hashSet);
				}
				
			} else {
				mapTwo.put(c, 1);
				maxFrequency = updateMaxFrequency(maxFrequency, 1);
				if (!mapOne.containsKey(1)) {
					HashSet<Character> set = new HashSet<Character>();
					set.add(c);
					mapOne.put(1, set);
				} else {
					HashSet<Character> set = mapOne.get(1);
					if (!set.contains(c)) {
						set.add(c);
					}
				}
			}
		}
        return prepareSortedString(mapOne, maxFrequency);
    }

	private String prepareSortedString(Map<Integer, HashSet<Character>> mapOne, int maxFrequency) {
		StringBuilder sb = new StringBuilder();
		for (int i = maxFrequency; i >= 1; i--) {
			if (mapOne.containsKey(i)) {
				HashSet<Character> hashSet = mapOne.get(i);
				for (Character ch : hashSet) {
					for (int k = 1; k <= i; k++) {
						sb.append(ch);
					}
				}
			}
		}
		return sb.toString();
	} 

	private int updateMaxFrequency(int maxFrequency, int number) {
		if (number > maxFrequency) {
			return number;
		}
		return maxFrequency;
	}

}

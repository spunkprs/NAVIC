package prudential;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class QuestionOne {
	
	private static List<Integer> resultList = new ArrayList<Integer>();
	private static Map<String, Boolean> helperMap = new HashMap<String, Boolean>();

	public static void main(String[] args) throws Exception {
		
		List<String> input = new ArrayList<String>();
		input.add("abc");
		scatterPalindrome(input);
		for (int i = 0; i < resultList.size(); i++) {
			System.out.println(resultList.get(i));
		} 

	}
	
	
	private static List<Integer> scatterPalindrome(List<String> strToEvaluate) {
		int i = 0;
		for (String word : strToEvaluate) {
			processToComeUpWithWords(word, i);
			i++;
		}
		return resultList;
	}


	private static void processToComeUpWithWords(String word, int index) {
		int result = 0;
		for (int i = 0; i < word.length(); i++) {
			for (int j = i; j < word.length(); j++) {
				String resultantWord = word.substring(i, j + 1);
				if (!helperMap.containsKey(resultantWord) && processToCheckIfWordCanBeScaterredPalinderome(resultantWord)) {
					result++;
				} else if (helperMap.containsKey(resultantWord)) {
					result++;
				}
			}
		}
		resultList.add(index, result);
	}


	private static boolean processToCheckIfWordCanBeScaterredPalinderome(String resultantWord) {
		if (resultantWord.length() == 1) {
			helperMap.put(resultantWord, true);
			return true;
		} else if (resultantWord.length() % 2 == 0) {
			if (resultantWord.length() == 2) {
				char arr[] = resultantWord.toCharArray();
				if (arr[0] == arr[1]) {
					helperMap.put(resultantWord, true);
					return true;
				} else {
					return false;
				}
			} else {
				Map<Character, Integer> map = new HashMap<Character, Integer>();
				char arr[] = resultantWord.toCharArray();
				for (int i = 0; i < arr.length; i++) {
					if (!map.containsKey(arr[i])) {
						map.put(arr[i], 1);
					} else {
						map.put(arr[i], map.get(arr[i]) + 1);
					}
				}
				
				int numberOfUniqueKeys = map.keySet().size();
				if (arr.length % numberOfUniqueKeys == 0) {
					helperMap.put(resultantWord, true);
					return true;
				} else {
					return false;
				}
			}
		} else {
			char arr[] = resultantWord.toCharArray();
			Map<Character, Integer> map = new HashMap<Character, Integer>();
			for (int i = 0; i < arr.length; i++) {
				if (!map.containsKey(arr[i])) {
					map.put(arr[i], 1);
				} else {
					map.put(arr[i], map.get(arr[i]) + 1);
				}
			}
			
			int numberOfUniqueKeys = map.keySet().size();
			if (numberOfUniqueKeys == 1) {
				helperMap.put(resultantWord, true);
				return true;
			} else {
				
				if (numberOfUniqueKeys == arr.length) {
					return false;
				}
				
				int maxFrequency = Integer.MIN_VALUE;
				int minFrequency = Integer.MAX_VALUE;
				
				for (Character c : map.keySet()) {
					int frequency = map.get(c);
					if (frequency > maxFrequency) {
						maxFrequency = frequency;
					}
					
					if (frequency < minFrequency) {
						minFrequency = frequency;
					}
				}
				
				if (maxFrequency % 2 != 0) {
					for (Character c : map.keySet()) {
						int frequency = map.get(c);
						if (frequency != maxFrequency && frequency != maxFrequency - 1) {
							return false;
						}
					}
					helperMap.put(resultantWord, true);
					return true;
				} else {
					int count = 0;
					for (Character c : map.keySet()) {
						int frequency = map.get(c);
						if (frequency == maxFrequency - 1) {
							count++;
						}
					}
					if (count == 1) {
						helperMap.put(resultantWord, true);
						return true;
					} else {
						return false;
					}
				}
			}
		}
		//return false;
	}

}

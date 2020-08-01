import java.util.ArrayList;
import java.util.List;

public class AllAnagramsInString {
	
	public List<Integer> findAnagrams(String s, String p) {
		List<Integer> result = new ArrayList<Integer>();
		char arrOne[] = p.toCharArray();
		char arrTwo[] = s.toCharArray();
		int asciiSumOne = 0;
		int asciiSumTwo = 0;
		
		for (int i = 0; i < arrOne.length; i++) {
			asciiSumOne += arrOne[i];
		}
		
		int j = arrTwo.length - 1;
		int k = arrTwo.length - arrOne.length;
		
		while (k >= 0 && j >= arrOne.length - 1) {
			if (asciiSumTwo == 0) { 
				for (int i = k; i < arrTwo.length; i++) {
					asciiSumTwo += arrTwo[i];
				}
				if (asciiSumTwo == asciiSumOne) {
					result.add(k);
				}
			} else {
				asciiSumTwo-= arrTwo[j + 1];
				asciiSumTwo += arrTwo[k];
				if (asciiSumTwo == asciiSumOne) {
					result.add(k);
				}
			}
			k--;
			j--;
		}
        return result;
    }
	
	public List<Integer> findAnagramsApproachTwo(String s, String p) {
		List<Integer> result = new ArrayList<Integer>();
		int maxLength = 26;
		
		int countArrOne[] = new int[maxLength];
		int countArrTwo[] = new int[maxLength];
		
		for (int i = 0; i < p.length(); i++) {
			countArrOne[p.charAt(i) - 97]++;
		}
		
		int j = s.length() - 1;
		int k = s.length() - p.length();
		boolean flag = false;
		
		while (k >= 0 && j >= p.length() - 1) {
			if (!flag) {
				for (int i = s.length() - 1; i >= k; i--) {
					countArrTwo[s.charAt(i) - 97]++;
				}
				flag = true;
				compare(countArrOne, countArrTwo, k, result);
			} else {
				countArrTwo[s.charAt(k) - 97]++;
				countArrTwo[s.charAt(j + 1) - 97]--;
				compare(countArrOne, countArrTwo, k, result);
			}
			k--;
			j--;
		}
        return result;
    }

	private void compare(int[] countArrOne, int[] countArrTwo, int index, List<Integer> result) {
		boolean flag = false;
		for (int k = 0; k < countArrOne.length; k++) {
			if (countArrOne[k] != countArrTwo[k]) {
				flag = true;
				break;
			}
		}
		
		if (!flag) {
			result.add(index);
		}
		
	}

}

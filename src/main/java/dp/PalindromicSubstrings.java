package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class PalindromicSubstrings {
	
	public int countSubstrings(String s) {
		int numberOfPalindromicSubstrings = 0;
		if (!s.isEmpty()) {
			char arr[] = s.toCharArray();
			numberOfPalindromicSubstrings = arr.length;
			int tmpSpace[][] = new int[arr.length + 1][arr.length];
			
			for (int j = 0; j < arr.length; j++) {
				tmpSpace[1][j] = 1;
			} 
			
			for (int i = 2; i < tmpSpace.length; i++) {
				for (int j = i - 1; j < tmpSpace[0].length; j++) {
					if (i == 2) {
						if (arr[j] == arr[j - 1]) {
							tmpSpace[i][j] = 1;
							numberOfPalindromicSubstrings++;
						}
					} else {
						int k = i - 2;
						if (arr[j] == arr[j-i+1] && tmpSpace[k][j - 1] == 1) {
							tmpSpace[i][j] = 1;
							numberOfPalindromicSubstrings++;
						}
					}
				}
			}
		}
		return numberOfPalindromicSubstrings;
    }
	
	
	public static void main(String ar[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PalindromicSubstrings ps = new PalindromicSubstrings();
		System.out.println(ps.countSubstrings(br.readLine()));
		System.out.println(ps.fetchDistinctSubstrings(br.readLine()));
	}
	
	public int fetchDistinctSubstrings(String s) {
		//int numberOfPalindromicSubstrings = 0;
		Set<String> set = new HashSet<String>();
		
		if (!s.isEmpty()) {
			char arr[] = s.toCharArray();
			//numberOfPalindromicSubstrings = 0;
			int tmpSpace[][] = new int[arr.length + 1][arr.length];
			
			for (int j = 0; j < arr.length; j++) {
				tmpSpace[1][j] = 1;
				set.add(String.valueOf(arr[j]));
			} 
			
			for (int i = 2; i < tmpSpace.length; i++) {
				for (int j = i - 1; j < tmpSpace[0].length; j++) {
					if (i == 2) {
						if (arr[j] == arr[j - 1]) {
							tmpSpace[i][j] = 1;
							String str = String.valueOf(arr[j - 1]) + String.valueOf(arr[j]);
							set.add(str);
							//numberOfPalindromicSubstrings++;
						}
					} else {
						int k = i - 2;
						if (arr[j] == arr[j-i+1] && tmpSpace[k][j - 1] == 1) {
							tmpSpace[i][j] = 1;
							set.add(prepareString(i, j, arr));
							//numberOfPalindromicSubstrings++;
						}
					}
				}
			}
		}
		return set.size();
    }


	private String prepareString(int i, int j, char arr[]) {
		String str = "";
		for (int k = j - i + 1; k <= j; k++) {
			str += String.valueOf(arr[k]);
		}
		return str;
	}

}
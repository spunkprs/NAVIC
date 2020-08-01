import java.util.ArrayList;
import java.util.List;

public class LongestPalindromicSubstring {
	
	private List<String> result = new ArrayList<String>();

    public String longestPalindrome(String s) {
    	int matrix[][] = new int[s.length()][s.length()];
    	char arr[] = s.toCharArray();
    	
    	processToFindLongestPalindromicSubstring(matrix, arr, s);
        return result.get(0);
    }

	private void processToFindLongestPalindromicSubstring(int[][] matrix, char[] arr, String s) {
		for (int diff = 1; diff <= matrix.length; diff++) {
			int x = 0;
			int y = x + diff - 1;
			while (y < matrix.length) {
				if (x == y) {
					matrix[x][y] = 1;
					result.add(s.substring(x, y + 1));
				} else {
					if (arr[x] != arr[y]) {
						matrix[x][y] = 0;
					} else {
						if (y - x > 1) {
							if (matrix[x + 1][y - 1] == 1) {
								matrix[x][y] = 1;
								updateResult(s, x, y);
							}
						} else {
							matrix[x][y] = 1;
							updateResult(s, x, y);
						} 
					}
				}
				x++;
				y = x + diff - 1;
				
			}
		}
	}

	private void updateResult(String s, int x, int y) {
		String capturedString = result.get(0);
		String resultToBe = s.substring(x, y + 1);
		if (resultToBe.length() > capturedString.length()) {
			result = new ArrayList<String>();
			result.add(resultToBe);
		}
	}
}

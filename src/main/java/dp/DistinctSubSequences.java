package dp;

public class DistinctSubSequences {
	
	public int numDistinct(String s, String t) {
		char inputArrOne[] = s.toCharArray();
		char inputArrTwo[] = t.toCharArray();
		int tmpArray[][] = new int[inputArrTwo.length + 1][inputArrOne.length];
		
		
		if (s.equals(t)) {
			return 1;
		} else if (t.length() < s.length()) {
			for (int j = 0; j < inputArrOne.length; j++) {
				if (inputArrTwo[0] == inputArrOne[j]) {
					if (j == 0) {
						tmpArray[1][j] = 1;
					} else {
						tmpArray[1][j] = tmpArray[1][j - 1] + 1;
					}
				} else {
					if (j != 0) {
						tmpArray[1][j] = tmpArray[1][j - 1];
					} 
				}
				
			}
		
			for (int i = 2; i < tmpArray.length; i++) {
				for (int j = i - 1; j < tmpArray[0].length; j++) {
					if (inputArrTwo[i - 1] == inputArrOne[j]) {
						if (tmpArray[i - 1][j - 1] > 0) {
							tmpArray[i][j] = tmpArray[i - 1][j - 1] + tmpArray[i][j - 1];
							
						}
					} else {
						tmpArray[i][j] = tmpArray[i][j - 1];
						
					}
				}
			}
	        return tmpArray[tmpArray.length - 1][tmpArray[0].length - 1];
		} else {
			return 0;
		}
    }

}

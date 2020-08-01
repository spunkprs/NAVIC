package arrays;

public class ReverseWordsInString {
	
	public String reverseWords(String s) {
		StringBuilder result = new StringBuilder();
        s = s.trim();
        String words[] = s.split(" ");
        
        for (int i = words.length - 1; i >= 0; i--) {
        	if (!words[i].isEmpty()) {
        		result.append(words[i]);
                result.append(" ");
        	}
        }
        return result.toString().trim();
    }

}

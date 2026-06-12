package microsoft.slidingWindow;

/**
Problem : 1208
Link : https://leetcode.com/problems/get-equal-substrings-within-budget/description/?envType=company&envId=jpmorgan&favoriteSlug=jpmorgan-all

You are given two strings s and t of the same length and an integer maxCost.

You want to change s to t. Changing the ith character of s to ith character of t costs |s[i] - t[i]| (i.e., the absolute difference
between the ASCII values of the characters).

Return the maximum length of a substring of s that can be changed to be the same as the corresponding substring of t with a cost
less than or equal to maxCost. If there is no substring from s that can be changed to its corresponding substring from t, return 0.

Constraints:-

a.) 1 <= s.length <= 10^5
b.) t.length == s.length
c.) 0 <= maxCost <= 10^6
d.) s and t consist of only lowercase English letters.

Time Complexity = O(N)
Space Complexity = O(N), ideally shall be O(1) but converting String to char arr is getting considered as O(N) instead
 * */

public class GetEqualSubStringsWithInBudget {

    public static void main(String ar[]) {
        GetEqualSubStringsWithInBudget unit = new GetEqualSubStringsWithInBudget();
        String s = "anryddgaqpjdw";
        String t = "zjhotgdlmadcf";
        int maxCost = 5;

        //String s = "aacd";
        //String t = "aade";
        //int maxCost = 0;

        System.out.println("Length of longest substring is " + unit.equalSubstring(s, t, maxCost));
    }

    public int equalSubstring(String s, String t, int maxCost) {

        char arrOne[] = s.toCharArray();
        char arrTwo[] = t.toCharArray();

        int indexOne = 0;
        int indexTwo = 0;
        int initialSum = 0;
        int resultLength = 0;

        while (indexTwo < arrOne.length) {
            initialSum += Math.abs(arrOne[indexTwo] - arrTwo[indexTwo]);
            if (initialSum <= maxCost) {
                int intermittentLength = indexTwo - indexOne + 1;
                resultLength = intermittentLength > resultLength ? intermittentLength : resultLength;
            } else {
                while (initialSum > maxCost) {
                    initialSum -=  Math.abs(arrOne[indexOne] - arrTwo[indexOne]);
                    indexOne++;
                }
            }
            indexTwo++;
        }

        return resultLength;
    }
}

package agoda;

/**
You are given a string s and an integer k, a k duplicate removal consists of choosing k adjacent and equal letters from s and removing them,
causing the left and the right side of the deleted substring to concatenate together.

We repeatedly make k duplicate removals on s until we no longer can.

Return the final string after all such duplicate removals have been made. It is guaranteed that the answer is unique.

Constraints:-

a.) 1 <= s.length <= 10^5
b.) 2 <= k <= 10^4
c.) s only contains lowercase English letters.
 * */

public class RemoveAllAdjacentDuplicatesInString2 {

    public static void main(String ar[]) {
        RemoveAllAdjacentDuplicatesInString2 unit = new RemoveAllAdjacentDuplicatesInString2();
        String s = "deeedbbcccbdaa";
        //String s = "aaaa";
        int k = 3;
        //int k = 2;
        System.out.print(unit.removeDuplicates(s, k));
    }

    public String removeDuplicates(String s, int k) {
        StringBuilder sb = new StringBuilder(s);
        int counter[] = new int[sb.length()];
        for (int i = 0; i < sb.length(); i++) {
            if (i == 0 || sb.charAt(i) != sb.charAt(i - 1)) {
                counter[i] = 1;
            } else if (sb.charAt(i) == sb.charAt(i - 1)) {
                counter[i] = counter[i - 1] + 1;
                if (counter[i] == k) {
                    sb.delete(i - k + 1, i + 1);
                    i = i - k;
                }
            }
        }
        return sb.toString();
    }
}

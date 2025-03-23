package arrays.twopointers;


/*
Given a character array s, reverse the order of the words.

A word is defined as a sequence of non-space characters. The words in s will be separated by a single space.

Problem shall be solved using constant extra space i.e no usage of extra space is allowed
* */


public class ReverseWordsInAString {
    /*
    *
    * Problem would be solved in two steps which are following :-
    * Step 1 : Reverse the characters of the entire string
    * Step 2 : Now hence characters of individual words are also reversed, we need to re reverse the characters of the individual words to get desired results
    *
    * Time Complexity = O(n)
    * Space Complexity = O(1)
    * */


    public static void main(String ar[]) {
        //Input
        char [] arrayOfCharacters = {'t','h','e', ' ','s','k','y', ' ','i','s', ' ','b','l','u','e'};

        //char [] arrayOfCharacters = {'t'};

        ReverseWordsInAString unit = new ReverseWordsInAString();

        unit.reverseWords(arrayOfCharacters);

    }

    public void reverseWords(char[] s) {
        if (s.length > 0) {
            reverseCharactersInTheString(s, 0, s.length - 1);
            int k = 0;
            boolean startIndexIsSet = false;
            int startIndex = 0;
            while (k < s.length && s.length > 1) {

                if (!startIndexIsSet && s[k] != ' ') {
                    startIndex = k;
                    startIndexIsSet = true;
                }

                if (s[k] != ' ' && (k + 1 == s.length || s[k + 1] == ' ')) {
                    reverseCharactersInTheString(s, startIndex, k);
                    startIndexIsSet = false;
                }
                k++;
            }
        }
        System.out.println("String post reversal of words " + String.valueOf(s));
    }

    private void reverseCharactersInTheString(char[] s, int i, int j) {
        while (i < j) {
            char temp = s[i];
            s[i] = s[j];
            s[j] = temp;
            i++;
            j--;
        }
    }
}

package microsoft.twpointer;

public class GetEqualSubStringsWIthInBudget {

    public static void main(String ar[]) {
        GetEqualSubStringsWIthInBudget unit = new GetEqualSubStringsWIthInBudget();
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

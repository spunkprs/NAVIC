public class MergeSortImplementation {

    private static String[] tempArray;

    public static void main(String ar[]) {
        String input[] = {"abc", "pqr", "stt", "npp", "apq", "ba"};
        tempArray = new String[input.length];
        mergeSortProcess(input, 0, input.length - 1);

        for (String str : input) {
            System.out.println(str);
        }
    }

    private static void mergeSortProcess(String input[], int startIndex, int endIndex) {
        int midIndex = (startIndex + endIndex) / 2;

        if (startIndex != midIndex) {
            mergeSortProcess(input, startIndex, midIndex);
        }

        if (midIndex + 1 != endIndex) {
            mergeSortProcess(input, midIndex + 1, endIndex);
        }

        mergePart(input, startIndex, midIndex, endIndex);
        copyElements(input, startIndex, endIndex);
    }

    private static void copyElements(String[] input, int startIndex, int endIndex) {
        for (int i = startIndex; i <= endIndex; i++) {
            input[i] = tempArray[i];
        }
    }

    private static void mergePart(String[] input, int startIndex, int midIndex, int endIndex) {
        int index = startIndex;
        int i = startIndex;
        int j = midIndex + 1;
        while (i <= midIndex || j <= endIndex) {
            if (i <= midIndex && j <= endIndex) {
                String wordOne = input[i];
                String wordTwo = input[j];
                if (wordOne.compareTo(wordTwo) <= 0) {
                    tempArray[index] = wordOne;
                    i++;
                } else {
                    tempArray[index] = wordTwo;
                    j++;
                }
                index++;
            } else if (i <= midIndex && j > endIndex) {
                tempArray[index] = input[i];
                i++;
                index++;
            } else if (i > midIndex && j <= endIndex) {
                tempArray[index] = input[j];
                j++;
                index++;
            }
        }
    }
}

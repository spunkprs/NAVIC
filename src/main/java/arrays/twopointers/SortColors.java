package arrays.twopointers;


/*
*
* Given an array, colors, which contains a combination of the following three elements:
*
0(representing red)
1(representing white)
2(representing blue)

Sort the array in place so that the elements of the same color are adjacent, with the colors in the order of red, white, and blue.
To improve your problem-solving skills, do not utilize the built-in sort function.
*
*
* Following cases shall also be taken into consideration :-
* a.) When array has only 0's
* b.) When array has only 1's
* c.) When array has only 2's
* d.) When array has only combination of above
* e.) When length of array is 1
* */

import java.util.Arrays;

public class SortColors {

    public static void main(String ar[]) {
        SortColors unit = new SortColors();

        int colors[] = {2, 1, 0, 0, 0, 1, 1, 2};
        Arrays.stream(unit.sortColors(colors)).forEach(x -> System.out.println(x));
    }

    public int[] sortColors (int[] colors) {
        if (colors.length > 1) {
            int indexOfNonZero = sortColorsOne(colors, 0, colors.length - 1);
            if (indexOfNonZero != -1) {
                sortColorsTwo(colors, indexOfNonZero, colors.length - 1);
            } else {
                sortColorsTwo(colors, 0, colors.length - 1);
            }
        }
        return colors;
    }

    private int sortColorsOne(int []colors, int startIndex, int endIndex) {
        int i = startIndex;
        int j = endIndex;
        int indexOfNonZero = -1;

        while (i < j) {

            if ((colors[i] == 1 || colors[i] == 2) && colors[j] == 0) {
                int temp = colors[i];
                colors[i] = colors[j];
                colors[j] = temp;
                i++;
                j--;
                continue;
            }

            if (colors[i] == 0) {
                i++;
            }

            if (colors[j] == 1 || colors[j] == 2) {
                j--;
            }

        }

        if ((colors[i] == colors[j]) && (colors[j] == 0)) {
            if (i < colors.length - 1) {
                indexOfNonZero = i + 1;
            } else {
                indexOfNonZero = i;
            }
        }
        return indexOfNonZero;
    }

    private void sortColorsTwo(int []colors, int startIndex, int endIndex) {
        int i = startIndex;
        int j = endIndex;

        while (i < j) {

            if (colors[i] == 2 && colors[j] == 1) {
                int temp = colors[i];
                colors[i] = colors[j];
                colors[j] = temp;
                i++;
                j--;
                continue;
            }

            if (colors[i] == 1) {
                i++;
            }

            if (colors[j] == 2) {
                j--;
            }

        }
    }

}

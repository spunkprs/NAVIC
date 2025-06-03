package arrays.twopointers;

/**
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
            /*
            Good refresher of memory that array variables are also treated as reference in Java, hence we are able to return same variable colors
            which we are getting from above && we have void return type for method sortColorsFinalApproach
            *
            */
            sortColorsFinalApproach(colors, 0, colors.length - 1);
        }
        return colors;
    }

    private void sortColorsFinalApproach(int[] colors, int startIndex, int endIndex) {
        int i = startIndex;
        int j = endIndex;

        while (i < j && i <= endIndex && j <= endIndex) {
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

        if (i == j) {
            if (colors[i] == 0) {
                startIndex = i + 1;
            } else {
                startIndex = i;
            }
        } else if (i > j) {
            startIndex = i;
        }

        i = startIndex;
        j = endIndex;

        while (i < j && i <= endIndex && j <= endIndex) {
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

public class RotateMatrix {

    public static void rotate(int[][] matrix) {

        int size = matrix.length;

        int max_limit = size % 2 == 0 ? size * size : size * size - 1;

        int jump = size - 1;

        int numberOfElements = 0;

        int i = 0, j = 0;
        int initialX = i;
        int initialY = j;
        int k = 0;
        int incrementFactor = 1;
        int tempCounter = 0;
        while (numberOfElements < max_limit) {
            int temp = 0, num = 0;
            temp = matrix[i][j];
            incrementFactor = 1;
            while (k <= size - 2 - tempCounter && incrementFactor <= 4) {
                num = matrix[j][jump - i];
                matrix[j][jump - i] = temp;
                temp = num;
                incrementFactor++;
                numberOfElements++;
                int tmp = i;
                i = j;
                j = jump - tmp;
            }
            k++;
            j++;
            if (k > size - 2 - tempCounter) {
                tempCounter++;
                initialX++;
                initialY++;
                i = initialX;
                j = initialY;
                k = initialY;
            }
        }
    }

    public static void main(String ar[]) {
        int arr[][] = {{1, 2, 3, 4}, {5, 6, 7, 8}, {0, 1, 2, 3 }, {1, 8, 7, 2}};
        int arrOne[][] = {{1, 2, 3, 4, 5}, {6, 7, 8, 9, 0}, {5, 4, 3, 2, 1}, {0, 0, 0, 0, 0}, {6, 7, 8, 9 ,4}};
        rotate(arr);
    }
}

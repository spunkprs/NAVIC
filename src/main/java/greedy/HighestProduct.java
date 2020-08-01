package greedy;

import java.util.ArrayList;
import java.util.Collections;

public class HighestProduct {

    public int maxp3(ArrayList<Integer> A) {
        Collections.sort(A);
        int size = A.size();
        int numberOfPositiveNumbers = 0;
        int numberOfNegativeNumbers = 0;
        for (int i = size - 1; i >= 0; i--) {
            if (A.get(i) >= 0) {
                numberOfPositiveNumbers++;
            } else {
                numberOfNegativeNumbers++;
            }
        }

        if (numberOfPositiveNumbers == size || numberOfNegativeNumbers == size || numberOfNegativeNumbers == 1) {
            return A.get(size - 1) * A.get(size - 2) * A.get(size - 3);
        }

        if (numberOfNegativeNumbers >= 2) {
            if (numberOfPositiveNumbers >= 3) {
                int productOne = A.get(size - 1) * A.get(size - 2) * A.get(size - 3);
                int productTwo = A.get(0) * A.get(1) * A.get(size - 1);
                return productOne > productTwo ? productOne : productTwo;
            } else {
                return A.get(0) * A.get(1) * A.get(size - 1);
            }
        }
        return 0;
    }
}

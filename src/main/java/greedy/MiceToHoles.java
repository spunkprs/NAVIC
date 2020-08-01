package greedy;

import java.util.ArrayList;
import java.util.Collections;

public class MiceToHoles {

    public int mice(ArrayList<Integer> A, ArrayList<Integer> B) {
        Collections.sort(A);
        Collections.sort(B);
        int diff = Integer.MIN_VALUE;
        for (int i = 0; i < A.size(); i++) {
            if (A.get(i) >= 0 && B.get(i) >= 0 || (A.get(i) < 0 && B.get(i) < 0)) {
                int val = Math.abs((A.get(i) - B.get(i)));
                if (val > diff) {
                    diff = val;
                }
            } else if (A.get(i) >= 0 && B.get(i) < 0 || (A.get(i) < 0 && B.get(i) >= 0)) {
                int val = Math.abs(A.get(i) + B.get(i));
                if (val > diff) {
                    diff = val;
                }
            }
        }
        return diff;
    }
}

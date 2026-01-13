package leetcode75;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class CanPairShoes {

    public static void main(String ar[]) {
        CanPairShoes unit = new CanPairShoes();
        int input[][] = {{0, 21}, {1, 23}, {1, 21}, {0, 23}};
        System.out.print("Can pair shoes " + unit.compute(input));
    }

    public boolean compute(int input[][]) {
        Map<Pair, Integer> map = new HashMap<>();
        for (int i = 0; i < input.length; i++) {
            int shoeType = input[i][0];
            int shoeSize = input[i][1];

            Pair pOne = new Pair(shoeType, shoeSize);
            Pair pCompliment = new Pair(shoeType == 0 ? 1: 0, shoeSize);

            if (!map.containsKey(pOne) && !map.containsKey(pCompliment)) {
                map.put(pOne, 1);
            } else if (map.containsKey(pOne) && !map.containsKey(pCompliment)) {
                map.put(pOne, map.get(pOne) + 1);
            } else if (map.containsKey(pCompliment)) {
                if (map.get(pCompliment) == 1) {
                    map.remove(pCompliment);
                } else {
                    map.put(pCompliment, map.get(pCompliment) - 1);
                }
            }
        }
        return map.isEmpty();
    }

    static class Pair {
        int shoeType;
        int shoeSize;

        public Pair(int shoeType, int shoeSize) {
            this.shoeType = shoeType;
            this.shoeSize = shoeSize;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair pair = (Pair) o;
            return shoeType == pair.shoeType && shoeSize == pair.shoeSize;
        }

        @Override
        public int hashCode() {
            return Objects.hash(shoeType, shoeSize);
        }
    }
}

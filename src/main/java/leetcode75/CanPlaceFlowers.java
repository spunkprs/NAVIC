package leetcode75;

/**
 You have a long flowerbed in which some of the plots are planted, and some are not.
 However, flowers cannot be planted in adjacent plots.

 Given an integer array flowerbed containing 0's and 1's, where 0 means empty and 1
 means not empty, and an integer n, return true if n new flowers can be planted in
 the flowerbed without violating the no-adjacent-flowers rule and false otherwise.

 Level : Easy
 Source : LeetCode
 Status : Accepted
 * */

public class CanPlaceFlowers {

    public static void main(String ar[]) {
        CanPlaceFlowers unit = new CanPlaceFlowers();

        int arr[] = {0, 0, 1, 0, 0};
        int n = 1;

        System.out.println(unit.canPlaceFlowers(arr, n));
    }

    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        int placedFlowers = 0;

        if (n > 0) {
            if (flowerbed.length == 1) {
                if (flowerbed[0] == 0 && n == 1) {
                    return true;
                }
                return false;
            } else {
                for (int i = 0; i < flowerbed.length; i++) {
                    if (i == 0) {
                        if (flowerbed[i] == 0 && flowerbed[i + 1] == 0) {
                            placedFlowers++;
                            flowerbed[i] = 1;
                            if (placedFlowers == n) {
                                return true;
                            }
                        }
                    } else if (i > 0 && i < flowerbed.length - 1) {
                        if (flowerbed[i - 1] == 0 && flowerbed[i] == 0 &&flowerbed[i + 1] == 0) {
                            placedFlowers++;
                            flowerbed[i] = 1;
                            if (placedFlowers == n) {
                                return true;
                            }
                        }
                    } else {
                        if (flowerbed[i - 1] == 0 && flowerbed[i] == 0) {
                            placedFlowers++;
                            flowerbed[i] = 1;
                            if (placedFlowers == n) {
                                return true;
                            }
                        }
                    }
                }
            }

            if (placedFlowers == n) {
                return true;
            }
            return false;
        }
        return true;
    }
}

package dp;

/**
 You are playing a game with integers. You start with the integer 1 and you want to reach the integer target.

 In one move, you can either:

 Increment the current integer by one (i.e., x = x + 1).
 Double the current integer (i.e., x = 2 * x).
 You can use the increment operation any number of times, however, you can only use the double operation at most maxDoubles times.

 Given the two integers target and maxDoubles, return the minimum number of moves needed to reach target starting with 1

 Constraints:-
 1.) 1 <= target <= pow(10,9)
 2.) 0 <= maxDoubles <= 100

 Time Complexity = O(log(target))
 Space Complexity = Time Complexity

 Source : LeetCode
 * */

public class MinimumMovesToReachTargetScore {

    public static void main(String ar[]) {
        MinimumMovesToReachTargetScore unit = new MinimumMovesToReachTargetScore();
        int target = 10;
        int maxDoubles = 4;

        System.out.print("Minimum moves required to reach target is : " + unit.minMoves(target, maxDoubles));
    }

    public int minMoves(int target, int maxDoubles) {

        if (maxDoubles == 0) {
            return target - 1;
        } else {
            return fetchMinimumMoves(target, maxDoubles);
        }
    }

    private int fetchMinimumMoves(int target, int maxDoubles) {
        int startValue = target;
        int endValue = 1;
        int minMoves = 0;

        while (startValue != endValue) {
            if (startValue % 2 == 0 && maxDoubles >= 1) {
                startValue /= 2;
                maxDoubles--;
                minMoves++;
            } else if (startValue % 2 != 0) {
                startValue -=1;
                minMoves++;
            } else if (startValue % 2 == 0 && maxDoubles == 0) {
                minMoves += startValue - 1;
                startValue = endValue;
            }
        }
        return minMoves;
    }
}

package microsoft.dp;

import java.util.Arrays;
import java.util.HashSet;

public class MinimumCostForTickets {

    public static void main(String ar[]) {
        MinimumCostForTickets unit = new MinimumCostForTickets();
        int days[] = {1,2,3,4,5,6,7,8,9,10,30,31};
        int costs[] = {2, 7, 15};

        System.out.print("Minimum cost for tickets is " + unit.mincostTickets(days, costs));
    }

    public int mincostTickets(int[] days, int[] costs) {
        // The last day on which we need to travel.
        int lastDay = days[days.length - 1];
        int dp[] = new int[lastDay + 1];
        Arrays.fill(dp, -1);

        HashSet<Integer> isTravelNeeded = new HashSet<>();

        for (int day : days) {
            isTravelNeeded.add(day);
        }
        return solve(dp, days, costs, 1, isTravelNeeded);
    }

    private int solve(int[] dp, int[] days, int[] costs, int dayOfTravel, HashSet<Integer> isTravelNeeded) {
        // If we have iterated over travel days, return 0.
        if (dayOfTravel > days[days.length - 1]) {
            return 0;
        }

        // If we don't need to travel on this day, move on to next day.
        if (!isTravelNeeded.contains(dayOfTravel)) {
            return solve(dp, days, costs, dayOfTravel + 1, isTravelNeeded);
        }

        // If already calculated, return from here with the stored answer.
        if (dp[dayOfTravel] != -1) {
            return dp[dayOfTravel];
        }

        int oneDay = costs[0] + solve(dp, days, costs, dayOfTravel + 1, isTravelNeeded);
        int sevenDay = costs[1] + solve(dp, days, costs, dayOfTravel + 7, isTravelNeeded);
        int thirtyDay = costs[2] + solve(dp, days, costs, dayOfTravel + 30, isTravelNeeded);

        // Store the cost with the minimum of the three options.
        return dp[dayOfTravel] = Math.min(oneDay, Math.min(sevenDay, thirtyDay));
    }
}

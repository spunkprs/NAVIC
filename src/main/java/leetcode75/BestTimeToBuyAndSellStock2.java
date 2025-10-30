package leetcode75;

public class BestTimeToBuyAndSellStock2 {

    public int maxProfit(int[] prices) {
        int maxProfit = 0;

        if (prices.length == 1) {
            return 0;
        } else {
            int j = 1;
            int sum = 0;
            while (j < prices.length) {
                sum = prices[j] - prices[j - 1];
                if (sum >= 0) {
                    maxProfit += sum;
                }
                j++;
            }
        }
        return maxProfit;
    }
}

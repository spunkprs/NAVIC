package leetcode75;


/**
Problem : 122

You are given an integer array prices where prices[i] is the price of a given
stock on the ith day.

On each day, you may decide to buy and/or sell the stock. You can only hold at
most one share of the stock at any time. However, you can sell and buy the stock multiple times on the same day,
ensuring you never hold more than one share of the stock.

Find and return the maximum profit you can achieve

Constraints:-

a.) 1 <= prices.length <= 3 * pow(10,4)
b.) 0 <= prices[i] <= pow(10,4)

Level : Medium
Time Complexity = O(n)
Space Complexity = O(1)
 * */

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

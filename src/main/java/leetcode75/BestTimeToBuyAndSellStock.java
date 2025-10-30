package leetcode75;

/**
Problem : 121
You are given an array prices where prices[i] is the price of a given stock on the ith day.

You want to maximize your profit by choosing a single day to buy one stock and choosing a
different day in the future to sell that stock.

Return the maximum profit you can achieve from this transaction.
If you cannot achieve any profit, return 0

Constraints:-

a.) 1 <= prices.length <= pow(10,5)
b.) 0 <= prices[i] <= pow(10,4)


Level : Easy
Time Complexity = O(n)
Space Complexity = O(1)
 * */

public class BestTimeToBuyAndSellStock {

    public int maxProfit(int[] prices) {
        int result = Integer.MIN_VALUE;

        if (prices.length == 1) {
            return 0;
        } else {
            int leftIndex = 0;
            int rightIndex = 1;

            while (rightIndex < prices.length) {
                if (prices[rightIndex] <= prices[leftIndex]) {
                    leftIndex = rightIndex;
                    rightIndex += 1;
                } else {
                    result = prices[rightIndex] - prices[leftIndex] > result ? prices[rightIndex] - prices[leftIndex] : result;
                    rightIndex++;
                }
            }

        }
        return result != Integer.MIN_VALUE ? result : 0;
    }
}

package leetcode75;

/**
Problem : 714
 You are given an array prices where prices[i] is the price of a given stock on the ith day,
 and an integer fee representing a transaction fee.

 Find the maximum profit you can achieve. You may complete as many transactions as you like,
 but you need to pay the transaction fee for each transaction.

 Note:

 You may not engage in multiple transactions simultaneously (i.e., you must sell the stock before you buy again).
 The transaction fee is only charged once for each stock purchase and sale

 Constraints:-

a.) 1 <= prices.length <= 5 * 10^4
b.) 1 <= prices[i] < 5 * 10^4
c.) 0 <= fee < 5 * 10^4

Time Complexity : O(N)
Space Complexity : O(2 * N) ~ O(N)

---Nice problem of DP----

References : https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/editorial/
 * */

public class BestTimeToBuyAndSellStockWithTxnFees {

    public int maxProfit(int[] prices, int fee) {
        int holdArr[] = new int[prices.length];
        int freeArr[] = new int[prices.length];

        if (prices.length == 1) {
            return 0;
        }

        holdArr[0] = prices[0] * -1;
        freeArr[0] = 0;

        for (int i = 1; i < prices.length; i++) {
            holdArr[i] = Math.max(holdArr[i - 1], freeArr[i - 1] - prices[i]);
            freeArr[i] = Math.max(freeArr[i - 1], holdArr[i - 1] + prices[i] - fee);
        }
        return freeArr[prices.length - 1];
    }
}

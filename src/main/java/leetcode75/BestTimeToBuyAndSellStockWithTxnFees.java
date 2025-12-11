package leetcode75;

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

package leetcode75;

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

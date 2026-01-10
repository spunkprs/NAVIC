package leetcode75;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
Problem : 322
You are given an integer array coins representing coins of different denominations and an integer amount representing a total amount of money.
Return the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any combination of the coins, return -1.
You may assume that you have an infinite number of each kind of coin

Constraints:-

a.) 1 <= coins.length <= 12
b.) 1 <= coins[i] <= 2^31 - 1
c.) 0 <= amount <= 10^4
 * */

public class CoinChange {

    public static void main(String ar[]) {
        CoinChange unit = new CoinChange();
        //int coins[] = {1, 2, 5};
        //int amount = 11;

        int coins[] = {1};
        int amount = 0;
        System.out.println("Fewest number of coins required to cater amount of " + amount + " is " + unit.coinChange(coins, amount));
    }

    public int coinChange(int[] coins, int amount) {
        if (amount == 0) {
            return 0;
        }
        Set<Integer> set = populateCoinChange(coins);
        Map<Integer, Integer> map = new HashMap<>();
        processToFindCoinChangeIteratively(set, amount, map);
        int result = map.get(amount);
        return result == Integer.MAX_VALUE ? -1 : result;
    }

    private void processToFindCoinChangeIteratively(Set<Integer> set, int amount, Map<Integer, Integer> map) {
        for (int i = 1; i <= amount; i++) {
            if (set.contains(i)) {
                map.put(i, 1);
            } else {
                for (int j = i - 1; j >= 1; j--) {
                    int changeValue = map.get(j);
                    Pair result = provideMinimumChangeAmount(set, i - j);
                    if (changeValue != Integer.MAX_VALUE && result.flag) {
                        if (!map.containsKey(i)) {
                            int value = changeValue + result.minimumCoins;
                            map.put(i, value);
                        } else {
                            int existingValue = map.get(i);
                            int value = changeValue + result.minimumCoins;
                            if (value < existingValue) {
                                map.put(i, value);
                            }
                        }
                    }
                }
                if (!map.containsKey(i)) {
                    map.put(i, Integer.MAX_VALUE);
                }
            }
        }
    }

    private Pair provideMinimumChangeAmount(Set<Integer> set, int amount) {
        Pair result = new Pair(false, Integer.MAX_VALUE);
        for (int coin : set) {
            if (amount % coin == 0) {
                result.flag = true;
                result.minimumCoins = result.minimumCoins < amount / coin ? result.minimumCoins : amount / coin;
            }
        }
        return result;
    }

    static class Pair {
        private boolean flag;
        private int minimumCoins;

        public Pair(boolean flag, int minimumCoins) {
            this.flag = flag;
            this.minimumCoins = minimumCoins;
        }
    }

    private Set<Integer> populateCoinChange(int[] coins) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < coins.length; i++) {
            set.add(coins[i]);
        }
        return set;
    }
}

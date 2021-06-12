package Knapsack_problem;

/**
 * you are given an integer array coins representing coins of
 * different denominations（不同面值的硬币） and an integer amount representing a total amount of money（总价）.
 *
 * Return the fewest number of coins（最少硬币个数） that you need to make up that amount（组成总和）.
 * If that amount of money cannot be made up by any combination of the coins, return -1.
 *
 * You may assume that you have an infinite number of each kind of coin（每种硬币无限个）.
 */
public class CoinChange {
}

class Solution {
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount+1];
        for(int i=0;i<amount+1;i++)
        {
            dp[i]=Integer.MAX_VALUE;
        }
        dp[0]=0;
        for(int i=0;i<coins.length;i++)
        {
            int coin = coins[i];
            for(int j=coin;j<amount+1;j++)
            {
                //相当于dp[j]=min(dp[j],1+dp[j-coin])
                //注意不要用1+dp[][]
                //因为可能会溢出
                if(dp[j]-1>dp[j-coin])
                {
                    dp[j]=1+dp[j-coin];
                }
            }
        }
        return dp[amount]==Integer.MAX_VALUE?-1:dp[amount];
    }
}
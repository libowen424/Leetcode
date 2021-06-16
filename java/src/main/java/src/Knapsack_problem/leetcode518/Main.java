package Knapsack_problem.leetcode518;

public class Main {
    public static void main(String[] args) {
        System.out.println(new Solution1().change(5,new int[]{1, 2, 5}));
    }
}

/**
 * 完全背包问题
 * dp[i][j]表示考虑前i个物品，且总金额不超过j的方法个数
 * dp[o][j]表示，不同任何物品，金额不超过j的方法个数，我们初始化为dp[0][0]=1,dp[0][1...amount]=0;表示金额不超过0有一种，表示其他金额，不存在可能性
 * dp[i][j]=dp[i-1][j](不使用第i枚硬币，即j<curCoin时，不使用)+∑(1<=k<=j/curCoin)dp[i-1][j-k*curCoin]
 */
class Solution {
    public int change(int amount, int[] coins) {
        int len = coins.length;
        int[][] dp = new int[len+1][amount+1];
        dp[0][0]=1;
        for(int i=1;i<=len;i++)
        {
            int curCoin = coins[i-1];
            for(int j=0;j<=amount;j++)
            {
                dp[i][j]=dp[i-1][j];
                for(int k=1;k<=(int)(j/curCoin);k++)
                {
                    dp[i][j]+=dp[i-1][j-k*curCoin];
                }
            }
        }
        return dp[len][amount];
    }
}

/**
 * 优化为一维
 * 注意j的遍历要倒序，因为j依赖于j-1，倒序不会覆盖
 */
class Solution1 {
    public int change(int amount, int[] coins) {
        int[] dp = new int[amount+1];
        dp[0]=1;
        int len=coins.length;
        for(int i=1;i<=len;i++)
        {
            int curCoin = coins[i-1];
            for(int j=amount;j>=0;j--)
            {
                for(int k=1;k<=(int)(j/curCoin);k++)
                {
                    dp[j]+=dp[j-k*curCoin];
                }
            }
        }
        return dp[amount];
    }
}
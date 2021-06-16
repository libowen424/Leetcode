package Knapsack_problem.leetcode879;


public class Main {
}

/**
 * 这是一个考察二维限制的问题
 * 一般的背包问题，只有一个限制，两个维度分别是物品数量和容量限制
 * 本题中的限制有人数不超过n，最小利润不小于minProfit，再加上工作数，三个维度
 * dp[i][j][k]表示前i项工作，选择了j个员工，工作利润至少为k的计划数
 * 最后的结果为 ∑dp[length][i][minProfit]
 */
class Solution {
    public int profitableSchemes(int n, int minProfit, int[] group, int[] profit) {
        int length = group.length;
        int MOD = (int)1e9 + 7;
        int [][][] dp = new int[length+1][n+1][minProfit+1];
        //i==0很好理解，因为当前还没有工作进行
        //j==0意为没有使用员工，如果不为0，计划数就不等于1了
        //k==0，利润为0，至少有一种计划，即什么都不做
        dp[0][0][0]=1;
        for(int i=1;i<=length;i++)
        {
            int members = group[i-1],earn = profit[i-1];
            for(int j=0;j<=n;j++)
            {
                for(int k=0;k<=minProfit;k++)
                {
                    //如果人数不够，一定无法进行该项任务，等于上一次任务的工作数
                    if(j<members)
                    {
                        dp[i][j][k]=dp[i-1][j][k];
                    }
                    //人数够，有两种选择，做或者不做
                    else
                    {
                        //不做，等于dp[i-1][j][k]
                        //做，等于dp[i-1][j-members][加上本次利润后可以达到minProfit的值]，特别注意，当本次earn已经足够minProfit时，这个k可以取0
                        dp[i][j][k]=(dp[i-1][j][k]+dp[i-1][j-members][Math.max(0,k-earn)])%MOD;
                    }
                }
            }
        }
        int sum=0;
        for(int j=0;j<=n;j++)
        {
            sum = (sum+dp[length][j][minProfit])% MOD;
        }
        return sum;
    }
}


class Solution1 {
    /**
     * 跟一般的背包问题一样，dp[i][j][k]只依赖于dp[i-1][...][...]，因此可以简化为二维数组
     * dp[j][k]表示当前工作数下，工作人数为j，利润最少为k
     */
    public int profitableSchemes(int n, int minProfit, int[] group, int[] profit) {
        int length = group.length;
        int MOD = (int)1e9 + 7;
        int [][] dp = new int[n+1][minProfit+1];
        //对于最小工作利润为 00 的情况，无论当前在工作的员工有多少人，我们总能提供一种方案，
        // 所以初始化 dp[i][0] = 1。
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 1;
        }
        for(int i=1;i<=length;i++) {
            int members = group[i - 1], earn = profit[i - 1];
            for (int j = n; j >= members; j--) {
                for (int k = minProfit; k >= 0; k--) {
                    dp[j][k] = (dp[j][k] + dp[j - members][Math.max(0, k - earn)]) % MOD;
                }
            }
        }
        return dp[n][minProfit];
    }

}


/**
 * 注意，两种方式中最后求结果时一个累加，一个直接输入j==n的值；
 * 同时在初始化的时候也是一个只初始化[0][0]，一个将[0.。n][0]全部初始化了
 * 这意味着，这两种思路中j的含义是不同的
 * 只初始化一个+累加输出结果， j意为确切地有j名员工在工作
 * 初始化0...n+输出[n][minProfit], j意为有有不多于j名员工在工作
 */
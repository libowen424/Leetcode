package src.leetcode877;

public class Solution {

    /**
     * dp[i][j]表示从i到j，先手选者所能取得的最大利益（注意，这个先手者可以是任一个人）
     * 当i==j时，表示当前只有一个可选，因此dp[i][i]=piles[i]
     * 当i>j时，没有意义，不考虑
     * 当i<j时，dp[i][j]=max(piles[i]+dp[i+1][j],piles[j]+dp[i][j-1])
     */
    public boolean stoneGame(int[] piles) {
        int n = piles.length;
        int[][] dp = new int[n][n];
        for(int i=0;i<n;i++)
        {
            dp[i][i]=piles[i];
        }
        for(int i=0;i<n-1;i++)
        {
            for(int j=1;j<n;j++)
            {
                dp[i][j]=Math.max(piles[i]-dp[i+1][j],piles[j]-dp[i][j-1]);
            }
        }
        return dp[0][n-1]>0;
    }

    /**
     * 可以看到计算dp[i][j]的值只和dp[i+1][j]以及dp[i][j-1]，有关所以可以优化为一维
     * 因为会与dp[i+1][j]相关，所以i的遍历要从大到小，相当于本次的值来覆盖上一次的
     * 因为会与dp[i][j-1]相关，所以j的遍历从小到大
     */
    public boolean stoneGame1(int[] piles) {
        int length = piles.length;
        int[] dp = new int[length];
        for(int i=0;i<length;i++)
        {
            dp[i]=piles[i];
        }
        for(int i=length-2;i>=0;i--)
        {
            for(int j=i+1;j<length;j++)
            {
                dp[j]=Math.max(piles[i]-dp[j],piles[j]-dp[j-1]);
            }
        }
        return dp[length-1]>0;
    }
}

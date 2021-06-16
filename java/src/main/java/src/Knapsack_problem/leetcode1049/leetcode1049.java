package Knapsack_problem.leetcode1049;

public class leetcode1049 {
    public static void main(String[] args) {
        System.out.println(new Solution().lastStoneWeightII(new int[]{31,26,33,21,40}));
    }
}


class Solution {
    public int lastStoneWeightII(int[] stones) {
        int sum=0;
        for(int i:stones)
        {
            sum+=i;
        }
        int t=sum/2;
        int num = stones.length;
        int[] dp = new int[t+1];
        for(int i=0;i<num;i++)
        {
            int cur = stones[i];
            for(int j=t;j>=cur;j--)
            {
                dp[j]=Math.max(dp[j],dp[j-cur]+cur);
            }
        }
        return Math.abs(sum-dp[t]-dp[t]);
    }
}
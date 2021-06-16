package leetcode279;

public class Main {
    public static void main(String[] args) {
        System.out.println(new Solution1().numSquares(13));
    }
}

/**
 * 本质上还是一个完全背包问题
 * dp[i][j]表示用了前i个数，表示j所需要的最小数字个数
 * 要特别注意的就是，如果用了k次某个数，则dp[i][j]要+k
 */
//这段代码超时了
class Solution {
    public int numSquares(int n) {
        int latestSqrtRes=(int)Math.sqrt(n);
        int[][] dp = new int[latestSqrtRes+1][n+1];
        for(int j=1;j<=n;j++)
        {
            dp[1][j]=j;
        }
        for(int i=2;i<=latestSqrtRes;i++)
        {
            int curPower = i*i;
            for(int j=0;j<=n;j++)
            {
                int useJNum = Integer.MAX_VALUE;
                for(int k=1;k<=(int)(j/curPower);k++)
                {
                    useJNum=Math.min(useJNum,dp[i-1][j-k*curPower]+k);
                }
                dp[i][j]=Math.min(dp[i-1][j],useJNum);
            }
        }
        return dp[latestSqrtRes][n];
    }
}

/**
 * 首先进行初始化，所有的情况中，全为1时，所需数字最多，即dp[j]=j
 * 然后考虑所有可能的完全平方数，t*t<=n
 * 每考虑一个完全平方数都要对dp从t*t到n全部做一次筛选
 * 要注意的时，为什么不用考虑多个t*t的问题，因为我们是从小数考虑到大数的，在大数时，如果多个平方数的较小值，则在小数时就已经更新过了
 */
class Solution1 {
    public int numSquares(int n) {
        int[] dp = new int[n+1];//dp[0]=0
        for(int j=1;j<=n;j++)
        {
            dp[j]=j;
        }
        for(int t=2;t*t<=n;t++)
        {
            int power = t*t;
            for(int k=power;k<=n;k++)
            {
                dp[k]=Math.min(dp[k],dp[k-power]+1);
            }
        }
        return dp[n];
    }
}
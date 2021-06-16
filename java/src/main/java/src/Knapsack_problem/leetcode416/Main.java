package Knapsack_problem.leetcode416;

/**
 * Given a non-empty array nums containing only positive integers, find if the array can be partitioned into two subsets such that the sum of elements in both subsets is equal.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println(new Solution().canPartition(new int[]{1,2,3,5}));
    }
}

/**
 * 将每个数字值作为价值，最后判断总和是否等于target
 */
class Solution {
    public boolean canPartition(int[] nums) {
        int sum=0;
        for(int num:nums)
        {
            sum+=num;
        }
        if(sum%2!=0)
        {
            return false;
        }
        int target = sum/2;
        int[] dp = new int[target+1];
        dp[0]=1;
        for(int i=0;i<nums.length;i++)
        {
            int num = nums[i];
            for(int j=target;j>=num;j--)
            {
                dp[j]=Math.max(dp[j],dp[j-num]+num);
            }
        }
        return dp[target]==target;
    }
}

/**
 * 每个数字的价值视为1，即是否添加进去的意思
 * 初始化的时候只有dp[0]=0，其他的视作-inf
 */
 class Solution1 {
    public boolean canPartition(int[] nums) {
        int sum=0;
        for(int num:nums)
        {
            sum+=num;
        }
        if(sum%2!=0)
        {
            return false;
        }
        int target = sum/2;
        int[] dp = new int[target+1];
        for(int i=0;i<target+1;i++)
        {
            dp[i]=Integer.MIN_VALUE;
        }
        dp[0]=0;
        for(int i=0;i<nums.length;i++) {
            int num = nums[i];
            for (int j = target; j >= num; j--) {
                dp[j]=Math.max(dp[j],dp[j-num]+1);
            }
        }
        return dp[target]>0;
    }
}

/**
 * 再进一步来说，只是要求能不能划分的问题
 * dp[i][j]的含义可以直接作为能否表达
 * 则只有dp[0]初始化为true，其他的初始化为false
 */
class Solution2 {
    public boolean canPartition(int[] nums) {
//        for(int i = 1; i <= n; i++)
//            for(int j = capacity; j >= nums[i-1]; j--)
//                dp[j] = dp[j] || dp[j - nums[i-1]];
//
//        return dp[capacity];
        return false;
    }
}
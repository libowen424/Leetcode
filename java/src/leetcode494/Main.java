package leetcode494;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        new Solution1().findTargetSumWays(new int[]{0,0,0,0,0,0,0,0,1},1);
    }
}

/**
 * 单纯的DFS
 */
class Solution2 {
    public int findTargetSumWays(int[] nums, int target) {
        return dfs(nums,target,0,0);
    }
    int dfs(int[] nums,int t,int u,int cur)
    {
        if(u==nums.length)
        {
            return cur==t?1:0;
        }
        int left = dfs(nums,t,u+1,cur+nums[u]);
        int right = dfs(nums,t,u+1,cur-nums[u]);
        return left+right;

    }
}

/**
 * 记忆化搜索
 * 不难发现，其实DFS过程中，只有数值下标u和当前结果cur是可变参数，我们可以以它们作为记忆容器的两个维度，返回值是路径个数
 * 由于cur存在负值，为了方便，不用静态数组，用哈希表
 */
class Solution3 {
    public int findTargetSumWays(int[] nums, int target) {
        return dfs(nums,target,0,0);
    }
    Map<String,Integer> cache = new HashMap<>();
    int dfs(int[] nums,int t,int u,int cur)
    {
        String key = u+"_"+cur;
        if(cache.containsKey(key))
        {
            return cache.get(key);
        }
        if(u==nums.length)
        {
            cache.put(key,cur==t?1:0);
            return cache.get(key);
        }
        int left = dfs(nums,t,u+1,cur+nums[u]);
        int right = dfs(nums,t,u+1,cur-nums[u]);
        cache.put(key,left+right);
        return cache.get(key);
    }
}

/**
 * 等价于记忆搜索，数值下标u这个维度相当于是每次重新赋值更新一次
 */
class Solution {
    public int findTargetSumWays(int[] nums, int target) {
        HashMap<Integer,Integer> map = new HashMap();

        map.put(nums[0],1);
        //特别注意这里，因为当第一个为0的时候，要记录两次
//        map.put(-nums[0],1);
        map.put(-nums[0],map.getOrDefault(-nums[0],0)+1);

        for(int i=1;i<nums.length;i++)
        {
            int num = nums[i];
            HashMap<Integer,Integer> map1 = new HashMap();
            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                int addResult = entry.getKey()+num;
                map1.put(addResult,map1.getOrDefault(addResult,0)+entry.getValue());
                int subResult = entry.getKey()-num;
                map1.put(subResult,map1.getOrDefault(subResult,0)+entry.getValue());
            }
            map=map1;
        }
        System.out.println(map.getOrDefault(target, -1));
        return -1;
    }
}


class Solution1 {
    public int findTargetSumWays(int[] nums, int target) {
        int sum=0;
        int len = nums.length;
        for(int i=0;i<len;i++)
        {
            sum+=Math.abs(nums[i]);
        }
        if(Math.abs(target)>sum)
        {
            return 0;
        }
        int cols = 2*sum+1;
        int[][] dp = new int[len][cols];
        dp[0][nums[0]+sum]++;
        dp[0][-nums[0]+sum]++;
        for(int i=1;i<len;i++)
        {
            for(int j=0;j<cols;j++)
            {
                int lastRowNum = dp[i-1][j];
                if(lastRowNum!=0)
                {
                    dp[i][j+nums[i]]=dp[i][j+nums[i]]==0?lastRowNum:dp[i][j+nums[i]]+lastRowNum;
                    dp[i][j-nums[i]]=dp[i][j-nums[i]]==0?lastRowNum:dp[i][j-nums[i]]+lastRowNum;
                }
            }
        }
        System.out.println(dp[len-1][target+sum]);
        return dp[len-1][target+sum];
    }
}
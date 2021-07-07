package src.LCP07;

import java.util.*;

public class Solution {
    public int numWays(int n, int[][] relation, int k) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        for(int i=0;i<relation.length;i++)
        {
            int from = relation[i][0];
            int to = relation[i][1];
            List<Integer> toList = map.getOrDefault(from,new ArrayList<>());
            toList.add(to);
            map.put(from,toList);
        }
        Queue<Integer> first = new ArrayDeque<>(),second=new ArrayDeque<>();
        first.add(0);
        for(int j=0;j<k;j++)
        {
            while(!first.isEmpty())
            {
                int cur = first.poll();
                List<Integer> toList = map.getOrDefault(cur,new ArrayList<>());
                for(Integer i:toList)
                {
                    second.add(i);
                }
            }
            first=second;
            second=new ArrayDeque<>();
        }
        int res=0;
        for(Integer i:first)
        {
            if(i==n-1)
                res++;
        }
        return res;
    }

    /**
     * DFS
     */
    List<List<Integer>> edges;
    int ways, n, k;
    public int numWays1(int n, int[][] relation, int k) {
        edges = new ArrayList<List<Integer>>();
        ways = 0;
        n = n;
        k = k;
        for (int i = 0; i < n; i++) {
            edges.add(new ArrayList<Integer>());
        }
        for (int[] edge : relation) {
            int src = edge[0], dst = edge[1];
            edges.get(src).add(dst);
        }
        dfs(0,0);
        return ways;
    }
    public void dfs(int index, int steps) {
        if (steps == k) {
            if (index == n - 1) {
                ways++;
            }
            return;
        }
        List<Integer> list = edges.get(index);
        for (int nextIndex : list) {
            dfs(nextIndex, steps + 1);
        }
    }

    /**
     * 动态规划
     * dp[i][j]表示经过i轮，到达玩家j的路线数
     */
    public int numWays2(int n, int[][] relation, int k) {
        int[][] dp = new int[k+1][n];
        dp[0][0]=1;
        for(int i=0;i<k;i++)
        {
            for(int[] edge : relation) {
               int src = edge[0],dst = edge[1];
               dp[i+1][dst] += dp[i][src];
            }
        }
        return dp[k][n-1];
    }

        public static void main(String[] args) {
        System.out.println(new Solution().numWays(3,new int[][]{{0,2},{2,1}},2));
    }
}

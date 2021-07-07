package src.leetcode990;

import src.leetcode909.Solution;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class Solution1 {
    /**
     * 我们可以看到，二维的board会导致诸多问题
     * 我们可以将其抽象为一维
     */
    int n;
    int[] nums;
    public int snakesAndLadders(int[][] board) {
        n = board.length;
        if (board[0][0] != -1) return -1;
        nums = new int[n * n + 1];
        boolean isRight = true;
        //从最后一行的最左边一个开始
        for (int i = n - 1, idx = 1; i >= 0; i--) {
            for(int j=(isRight?0:n-1);isRight?j<n:j>=0;j+=(isRight?1:-1))
            {
                nums[idx++] = board[i][j];
            }
            isRight = !isRight;
        }
        int ans = bfs();
        return ans;
    }
    int bfs()
    {
        Deque<Integer> d = new ArrayDeque<>();
        Map<Integer,Integer> m = new HashMap<>();
        d.addLast(1);
        m.put(1,0);
        while(!d.isEmpty())
        {

            int poll = d.pollFirst();
            int step = m.get(poll);
            if(poll==n*n) return step;
            for(int i=1;i<=6;i++)
            {
                int np = poll+i;
                //注意这里的顺序
                if(np<=0||np>n*n) continue;
                if(nums[np]!=-1) np=nums[np];
                if(m.containsKey(np)) continue;
                m.put(np,step+1);
                d.addLast(np);
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[][] test = new int[][]{{-1,-1,-1,-1,-1,-1},{-1,-1,-1,-1,-1,-1},{-1,-1,-1,-1,-1,-1},{-1,35,-1,-1,13,-1},
                {-1,-1,-1,-1,-1,-1},{-1,15,-1,-1,-1,-1}
        };
        System.out.println(new Solution1().snakesAndLadders(test));
    }
}
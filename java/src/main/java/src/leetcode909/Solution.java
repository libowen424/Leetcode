package src.leetcode909;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class Solution {
    int[][] b;
    int n;
    public int snakesAndLadders(int[][] board) {
        b=board;
        n=board.length;
        Deque<Integer> d = new ArrayDeque<>();
        boolean[] visited = new boolean[n*n+1];
        Map<Integer,Integer> map = new HashMap<>();

        d.addLast(1);
        visited[1]=true;
        map.put(1,0);
        while(!d.isEmpty())
        {
            /**
             * 这里没读懂题
             * 题意是说，如果遇到蛇和梯子，这一轮直接飞过去，但只能飞一次
             * 所以判断要在循环中做
             */
            int curIndex = d.pollFirst();
            int curStep=map.get(curIndex);
            int boardNum = getBoard(curIndex);
            if(curIndex==n*n)
            {
                return curStep;
            }
            if(boardNum!=-1&&!visited[boardNum])
            {
                d.addLast(boardNum);
                map.put(boardNum,curStep+1);
                visited[boardNum]=true;
            }
            else if(boardNum==-1)
            {
                for(int i=1;i<=6&&curIndex+i<=36;i++)
                {
                    if(!visited[curIndex+i])
                    {
                        d.addLast(curIndex+i);
                        map.put(curIndex+i,curStep+1);
                        visited[curIndex+i]=true;
                    }
                }
            }
        }
        return -1;
    }
    int getBoard(int index)
    {
        int row = (int) Math.ceil((float)index/n);
        int x = n-row;
        int col = index-n*(row-1);
        int y = row%2==0?n-col:col-1;
        return b[x][y];
    }

    public static void main(String[] args) {
        int[][] test = new int[][]{{-1,-1,-1,-1,-1,-1},{-1,-1,-1,-1,-1,-1},{-1,-1,-1,-1,-1,-1},{-1,35,-1,-1,13,-1},
                {-1,-1,-1,-1,-1,-1},{-1,15,-1,-1,-1,-1}
        };
        System.out.println(new Solution().snakesAndLadders(test));
    }
}

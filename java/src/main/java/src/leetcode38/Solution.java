package src.leetcode38;

import java.util.HashSet;
import java.util.Set;

public class Solution {
    public String[] permutation(String s) {
        char[] charArr = s.toCharArray();
        Set<String> result = new HashSet<String>();
        boolean[] visited = new boolean[charArr.length];
        dfs(visited,charArr,"",result);
        return result.toArray(new String[0]);//不是很熟悉的语法
    }
    public void dfs(boolean[] visited,char[] charArr,String curStr,Set<String> result)
    {
        if(curStr.length()==charArr.length)
        {
            result.add(curStr);
            return;
        }
        for(int i=0;i<charArr.length;i++)
        {
            if(visited[i]) continue;
            visited[i]=true;
            dfs(visited,charArr,curStr+String.valueOf(charArr[i]),result);
            visited[i]=false;//特别注意，dfs后要回复原状态。因为这是在一个for循环中，下一次循环会服用这个visited
        }
    }
}

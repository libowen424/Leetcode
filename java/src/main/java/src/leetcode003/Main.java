package src.leetcode003;

import java.util.*;

public class Main{
    public static void main(String[] args) {
        System.out.println(
        new Solution().lengthOfLongestSubstring( "zwnigfunjwz")
        );
    }
}

/**
 * 错了两次才写对的问题
 * 思路是用一个map记录所有字符最近出现的位置
 * curBeginIndex用来记录当前从那个索引开始
 * 向右遍历过程中，curMax是由字符是否出现以及出现位置决定的
 * 特别注意，如果字符曾经出现过，则需要判断出现位置和当前开始位置的前后关系
 */
class Solution {
    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> m = new HashMap();
        int maxLength = 0;
        int curBeginIndex = 0;
        int tmpMaxLength=0;
        for(int i=0;i<s.length();i++)
        {
            char c = s.charAt(i);
            if(m.containsKey(c))
            {
                int beforeIndex = m.get(c);
                if(beforeIndex>=curBeginIndex)
                {
                    tmpMaxLength=i-beforeIndex;
                    curBeginIndex=beforeIndex+1;
                }
                else
                {
                    tmpMaxLength=i-curBeginIndex+1;
                }
                m.put(c,i);
            }
            else
            {
                tmpMaxLength++;
                m.put(c,i);
            }
            if(tmpMaxLength>maxLength)
            {
                maxLength=tmpMaxLength;
            }
        }
        return maxLength;
    }
}

/**
 * 利用队列，形成一种滑动窗口的形式
 * 如果没有重复，就一直添加，如果出现重复，就记录当前长度，并从头开始弹出
 */
class soliton1{
    public int lengthOfLongestSubstring(String s) {
        int left = 0,right=0;

        Queue<Character> queue = new LinkedList();
//        Set<Character> set = new HashSet();
        int maxLen=0;
        for(int i=0;i<s.length();i++)
        {
            char c = s.charAt(i);
            if(queue.contains(c))
            {
                maxLen=queue.size();
//                set.
                queue.poll();
            }
            else
            {
//                right++;
                queue.add(c);
            }
        }

        return -1;
    }
}

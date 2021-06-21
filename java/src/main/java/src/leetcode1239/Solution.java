package src.leetcode1239;

import java.util.List;
import java.util.Map;

public class Solution {
    /**
     * 从arr的第一个字符开始，每次都有两个选择（加入或者不加入）
     * 因此可以将所有情况抽象为一棵二叉树
     * 以回溯的思想来看
     * 如果选择加入，产生的结果串长度为：当前字符串长度+继续向下遍历的长度（将当前字符串加入已出现的字符记录中）
     * 如果选择不加入，产生的结果串长度为：继续向下遍历的长度（不将当前字符串加入已出现的字符记录中）
     * 所谓回溯的思想就是0->1->2->...->n->n-1->...->0
     *
     * 因为要记录所有出现的字符，我们可以构建一个长度26的哈希表来记录
     */
    public int maxLength(List<String> arr) {
        int[] alphabet = new int[26];
        return dfs(arr,0,alphabet);
    }
    int dfs(List<String> arr,int childIndex,int[] m)
    {

        if(arr.size()==childIndex)
        {
            return 0;
        }
        //在isUnique中可能会改变字符表，我们用t来保存可能会改变后的状态
        int[] t = m.clone();
        if(isUnique(arr.get(childIndex),t))
        {
            int curLen = arr.get(childIndex).length();
            int len1 = curLen+dfs(arr,childIndex+1,t);
            int len2 = dfs(arr,childIndex+1,m);
            return Math.max(len1,len2);
        }
        //如果当前字符串已经重复了，直接遍历下一个
        return dfs(arr,childIndex+1,m);
    }
    public boolean isUnique(String s,int[] t)
    {
        for(int i=0;i<s.length();i++)
        {
            if(t[s.charAt(i)-'a']==0) {
                t[s.charAt(i) - 'a']++;
            }else
            {
                return false;
            }
        }
        return true;
    }

    /**
     * 我们知道，一共只有26个字母，也就是说最多只需要26位来存储每个字符的存在情况
     * 我们可以使用一个int来存储状态，初始化为0
     * 每来一个字符c，可以通过c-'a'获取它对应的位，然后判断存储状态的int值对应位是否为1
     * 但是java没有int类型的指针，所以我们还是用一个数组来保存，只不过只存放一个int
     */
    public int maxLength1(List<String> arr) {
        int[] alphabet = new int[1];
        return dfs1(arr,0,alphabet);
    }
    int dfs1(List<String> arr,int childIndex,int[] m)
    {
        if(arr.size()==childIndex)
        {
            return 0;
        }
        //在isUnique中可能会改变字符表，我们用t来保存可能会改变后的状态
        int[] t = m.clone();
        if(isUnique1(arr.get(childIndex),t))
        {
            int curLen = arr.get(childIndex).length();
            int len1 = curLen+dfs1(arr,childIndex+1,t);
            int len2 = dfs1(arr,childIndex+1,m);
            return Math.max(len1,len2);
        }
        //如果当前字符串已经重复了，直接遍历下一个
        return dfs1(arr,childIndex+1,m);
    }
    public boolean isUnique1(String s,int[] t)
    {
        for(int i=0;i<s.length();i++)
        {
            int ch = s.charAt(i)-'a';
            if(((t[0]>>ch)&1)==0) {
                t[0] |= (1<<ch);
            }else
            {
                return false;
            }
        }
        return true;    }
}
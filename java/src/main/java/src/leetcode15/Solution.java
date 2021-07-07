package src.leetcode15;

public class Solution {
    //调用api
    //注意 0b开头表示二进制 0开头表示八进制 0x开头表示16进制
    public int hammingWeight(int n) {
        return Integer.bitCount(n);
    }

    //bitCount源码简化
    //分组分析
    //时间复杂度：O(logk)，这个做法最大作用是处理更大位数会更快
    public int hammingWeight1(int n) {
        n = (n & 0x55555555) + ((n >>> 1)  & 0x55555555);
        n = (n & 0x33333333) + ((n >>> 2)  & 0x33333333);
        n = (n & 0x0f0f0f0f) + ((n >>> 4)  & 0x0f0f0f0f);
        n = (n & 0x00ff00ff) + ((n >>> 8)  & 0x00ff00ff);
        n = (n & 0x0000ffff) + ((n >>> 16) & 0x0000ffff);
        return n;
    }

    //利用n&(-n)取出n的最低位
    public int hammingWeight2(int n) {
        int result = 0;
        while(n!=0)
        {
            n-= n&(-n);
            result++;
        }
        return result;
    }

    //循环遍历（0(k)复杂度，k是位数）
    public int hammingWeight3(int n) {
        int ans = 0;
        while (n != 0) {
            ans += (n & 1);
            n >>>= 1;
        }
        return ans;
    }


}

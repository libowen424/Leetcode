package src.leetcode483;


public class Solution {
    /**
     * (n)10 = k的0次+k的1次+...+k的Len-1次
     * 即k和Len单调性相反
     * Len=1时，k=n，（3<=n<=10^18），无法满足每一位都是1，不符合题意
     * Len=2时，k=n-1，符合题意，因此k<=n-1
     * 又k>=2，因此可得Len最大值是k=2时
     * Len<=log2(n+1)
     * 综上：2<=k<=n-1  2<=Len<=log2(n+1)
     * 又有n=k的0次+k的1次+...+k的Len-1次<1*k的0次+(Len-1)*k的1次+...<(k+1)的Len-1次（二项式定理）
     * 即k的len-1次<n<(k+1)的len-1次
     * k<n开Len-1次方<K+1
     * 即对于每一个n和Len，n开Len-1次方的整数部分就是k
     * 我们只需要遍历Len，得到k，检查k是否符合条件
     * @param n
     * @return
     */
    public String smallestGoodBase(String n) {
        long m = Long.parseLong(n);//n有可能无法用int保存
        int maxLen = (int)(Math.log(m+1)/Math.log(2));//maxLen
        for(int len = maxLen;len>=3;len--)//遍历len
        {
            //开根号后取整数部分
            long k = (long)Math.pow(m,1.0/(len-1));
            long res = 0;
            //检查k是否符合
            for(int i=0;i<len;i++)
            {
                res = res*k+1;
            }
            if(res == m)
            {
                return String.valueOf(k);
            }
        }
        //不存在这样的k，就直接返回m-1
        return String.valueOf(m-1);
    }

    /**
     * 可以看到遍历len时，单调性是已知的，可以根据单调性采用二分法
     */
    public String smallestGoodBase1(String n) {
        long m = Long.parseLong(n);
        int maxLen = (int)(Math.log(m+1)/Math.log(2));
        for(int len = maxLen;len>=3;len--)//遍历len
        {

        }
        return null;
    }
}
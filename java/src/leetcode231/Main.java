package leetcode231;

public class Main{
    public static void main(String[] args)
    {
        System.out.println(new Solution().isPowerOfTwo(16));
    }
}
class Solution {
    public boolean isPowerOfTwo(int n) {
        //2的幂一定是最高位为1
        return n>0&&Integer.bitCount(n)==1;
    }
}

class Solution1 {
    public boolean isPowerOfTwo(int n) {
        //利用bitlow特性
        //即x&(-x)等于x的最低位表示的值，对于2的幂次，这个值一定是本身
        return n > 0 && (n & -n) == n;
    }
}
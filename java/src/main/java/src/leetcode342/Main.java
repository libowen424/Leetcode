package leetcode342;

public class Main {
    public static void main(String[] args) {
        System.out.println(new Solution().isPowerOfFour(5));
    }
}

/**
 * 本想仿照231（2的幂）进行处理，判断n是否为2次幂并且n/2是2次幂
 *  但这样只能保证n是2的幂次的两倍无法保证n是2的幂次，不合理
 */
class Solution {
    public boolean isPowerOfFour(int n) {
        int N = n/2;
        return (n>0&&(n&(n-1))==0)&&(N==0||(N>=2&&(N&(N-1))==0));
    }
}

/**
 * 1 = 0x1
 * 4 = 0x100
 * 16 = 0x10000
 * 64 = 0x1000000
 * 即4的幂次的数1出现的位置为 0 2 4 6
 * 因此如果是4的幂次，只需与0x1010 1010 1010 ... 即 0xaaaaa...做与就一定是0
 * 但此时无法保证1的个数一定为1，结合231 2的幂，再判断n&(n-1)==0即可判断1的个数是否为1
 * 其实n&(n-1)也就等价于判断是否为偶数
 */
class Solution1 {
    public boolean isPowerOfFour(int n) {
        return n>0 && (n&(n-1))==0 && (n&0xaaaaaaaa)==0;
    }
}

/**
 * n要是4的幂，就一定是2的幂
 * n如果已经是2的幂了，比如n=2的x次方
 * x为偶数=》n是4的幂
 * x为奇数=》n= 2的2y+1次方 2y+1=x
 * 4的幂除3，余数一定为1（展开式易知）
 * 2的2y+1次方即 2*4的y次方除3，余数一定为2（展开式易知）
 * 因此先判断是否为2的幂，再通过余数判断是否是4的幂
 */
class Solution2 {
    public boolean isPowerOfFour(int n) {
        return n>0 && (n&(n-1))==0 && n%3==1;
    }
}
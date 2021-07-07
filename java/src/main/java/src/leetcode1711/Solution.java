package src.leetcode1711;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    /**
     * 暴力解法
     *
     * @param deliciousness
     * @return
     */
    public int countPairs(int[] deliciousness) {
        int res = 0;
        for (int i = 0; i < deliciousness.length; i++) {
            for (int j = i + 1; j < deliciousness.length; j++) {
                int count = deliciousness[i] + deliciousness[j];
                if (count != 0 && (count & (count - 1)) == 0) {
                    res++;
                    res %= 1000000007;
                }
            }
        }
        return res;
    }

    /**
     * 上面的暴力解法的问题是相同delicious的元素会被重复计算
     * 如果使用哈希表存储数组中每个元素出现的次数，就可以避免重复计算
     * 同时，如果边遍历边插入哈希表，哈希表中已有的元素的下标一定小于当前元素的下标，
     * 因此任意一对元素之和等于 2 的幂的元素都不会被重复计算。
     * <p>
     * 还有一个问题是遍历过程中，怎么找到该元素对应的2的幂的元素个数
     * 1. 遍历已有的哈希表，判断每一个是否是和为2的幂(超时)
     * 2. 遍历所有的2的幂（最大是maxVal*2），获取差的个数（因为每次后移都是*2，因此遍历时间为logn不会超时）
     */
    public int countPairs1(int[] deliciousness) {
        final int MOD = 1000000007;
        int maxVal = 0;
        for (int val : deliciousness) {
            maxVal = Math.max(maxVal, val);
        }
        int maxSum = maxVal * 2;
        int pairs = 0;
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        int n = deliciousness.length;
        for (int i = 0; i < n; i++) {
            int val = deliciousness[i];
//            for (int sum = 1; sum <= maxSum; sum <<= 1) {
//                int count = map.getOrDefault(sum - val, 0);
//                pairs = (pairs + count) % MOD;
//            }
//            map.put(val, map.getOrDefault(val, 0) + 1);
            for(Map.Entry<Integer,Integer> entry:map.entrySet())
            {
                int count = val+entry.getKey();
                if(count != 0 && (count&(count-1))==0)
                {
                    pairs+=entry.getValue();
                }
            }
            map.put(val, map.getOrDefault(val, 0) + 1);
        }
        return pairs;
    }
}
package src.leetcode149;

import java.util.HashMap;
import java.util.Map;

/**
 * 我的想法是，每个线段y=kx+b由k,b唯一标识
 * 用一个map，key是k,b组成的集合，value是出现的个数
 * 但问题是集合类型存储是引用类型，如何进行比较的问题
 *
 * 但我们也可以不用集合，将k，b组合成一个string
 */
public class Solution {

    /**
     * 朴素枚举
     * 每两个点确定一条直线，计算这条直线会经过多少点
     * <p>
     * 一开始对k=j+1这样的初始化有疑问，会认为k应该从0开始遍历，剔除i、j即可
     * 因为i、j构成的直线所过的点 为什么不考虑j之前的点呢？
     * 但实际上，如果j之前的点被i、j构成的直线所过，那么之前就一定被记录过了
     * 这也可以看出，这种做法有很大的优化空间
     *
     * @param points
     * @return
     */
    public int maxPoints(int[][] points) {
        int n = points.length;
        int ans = 1;
        for (int i = 0; i < n; i++) {
            int[] x = points[i];
            for (int j = i + 1; j < n; j++) {
                int[] y = points[j];
                int cnt = 2;
                for (int k = j + 1; k < n; k++) {
                    int[] p = points[k];
                    int s1 = (y[1] - x[1]) * (p[0] - y[0]);
                    int s2 = (p[1] - y[1]) * (y[0] - x[0]);
                    if (s1 == s2) cnt++;
                }
                ans = Math.max(ans, cnt);
            }
        }
        return ans;
    }

    /**
     * 最开始的思路，不过将k、b组合成string保存
     * 还有一点不同就是，直线的起点i从0到n，终点是从i+1到n
     * 每个起点都有一个map，取其中最多。
     * 这样会不会导致i较靠后时，j没有考虑到所有可能性？
     * 其实不会，因为如果i靠后时的直线i、j和之前的重合，之前就被考虑到了
     * 还要注意到，y=kx+b不能表示所有直线
     * @param points
     * @return
     */
    public int maxPoints1(int[][] points) {
        int n = points.length;
        int ans = 1;
        for(int i=0;i<n;i++)
        {
            Map<String, Integer> map = new HashMap<>();
            int max = 0;
            for(int j=i+1;j<n;j++)
            {
                int x1 = points[i][0], y1 = points[i][1], x2 = points[j][0], y2 = points[j][1];
                int a = x1 - x2, b = y1 - y2;
                int k = gcd(a, b);
                String key = (a / k) + "_" + (b / k);
                map.put(key, map.getOrDefault(key, 0) + 1);
                max = Math.max(max, map.get(key));
            }
            ans = Math.max(ans, max + 1);//注意要+1，因为map的初始值是0，每次+1
        }
        return ans;
    }
    int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}
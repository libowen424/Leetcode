package src.leetcode1833;

import java.util.Arrays;
import java.util.PriorityQueue;

public class Solution {
    /**
     * 当成背包问题O(MN)时间复杂度必会超时
     * 而且，我们要求解的是最大数量，每个被选择物品的贡献就是1
     * 我们直接选择价格低的就可以买到更多
     */
    public int maxIceCream(int[] costs, int coins) {
        int n = costs.length;
        int[][] dp = new int[n][coins + 1];
        for (int j = 1; j <= coins; j++) {
            dp[0][j] = j >= costs[0] ? 1 : 0;
        }
        for (int i = 1; i < n; i++) {
            int curCost = costs[i];
            for (int j = 1; j <= coins; j++) {
                if (j >= curCost) {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - curCost] + 1);
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[n - 1][coins];
    }

    public int maxIceCream1(int[] costs, int coins) {
        int n = costs.length;
        int[] dp = new int[coins + 1];
        for (int i = 0; i < n; i++) {
            int curCost = costs[i];
            for (int j = coins; j >= 0; j--) {
                if (j >= curCost) {
                    dp[j] = Math.max(dp[j], dp[j - curCost] + 1);
                } else {
                    dp[j] = dp[j];
                }
            }
        }
        return dp[coins];
    }

    /**
     * 在给定硬币数量 \textit{coins}coins 的情况下，要买到最多的雪糕，应该买最便宜的雪糕，理由如下。
     *
     * 假设购买最便宜的雪糕，在总价格不超过coins 的情况下最多可以购买 k 支雪糕。
     * 如果将k 支最便宜的雪糕中的任意一支雪糕替换成另一支雪糕，
     * 则替换后的雪糕的价格大于或等于替换前的雪糕的价格，
     * 因此替换后的总价格大于或等于替换前的总价格，
     * 允许购买的雪糕数量不可能超过 k。
     * 因此可以买到的雪糕的最大数量为 k.
     */
    /**
     * 由此可以得到贪心的解法：对数组costs 排序，
     * 然后按照从小到大的顺序遍历数组元素，对于每个元素，
     * 如果该元素不超过剩余的硬币数，则将硬币数减去该元素值，
     * 表示购买了这支雪糕，当遇到一个元素超过剩余的硬币数时，
     * 结束遍历，此时购买的雪糕数量即为可以购买雪糕的最大数量。
     */
    public int maxIceCream2(int[] costs, int coins) {
        Arrays.sort(costs);
        int count = 0;
        int n = costs.length;
        for (int i = 0; i < n; i++) {
            int cost = costs[i];
            if (coins >= cost) {
                coins -= cost;
                count++;
            } else {
                break;
            }
        }
        return count;
    }

    /**
     * 利用大顶堆
     * 将已经买了的最贵的雪糕放在对顶，遍历costs，每次买一根雪糕，并将这个雪糕的金额如堆，如果总的金额超了，那就将堆顶最贵的给踢出来。
     */
    public int maxIceCream3(int[] costs, int coins) {
        int res = 0, sum = 0;
        // 定义一个大顶堆，优先队列的底层原理就是一个堆排序哦
        PriorityQueue<Integer> q = new PriorityQueue<>((a, b) -> b - a);
        // 遍历所有价格
        for(int cur : costs) {
            // 买下当前这个雪糕
            sum += cur;
            q.offer(cur);
            res ++;

            if(sum > coins) {
                // 如果总的价格超了，那就踢出堆顶的雪糕
                sum -= q.poll();
                res --;
            }
        }

        return res;
    }
}
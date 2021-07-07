package src.leetcode401;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    /**
     * 枚举时分
     * 因为小时和分钟数都是固定的，我们可以枚举每一种可能，判断1的和是否为turnedOn
     *
     * @param turnedOn
     * @return
     */
    public List<String> readBinaryWatch(int turnedOn) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 60; j++) {
                if (Integer.bitCount(i) + Integer.bitCount(j) == turnedOn) {
                    list.add(i + ":" + (j < 10 ? "0" : "") + j);
                }
            }
        }
        return list;
    }

    /**
     * 二进制枚举
     * 开关灯组合一共只有2的10次方=1024种
     * 其中高4位是小时，低6位是分钟
     * 如果小时和分钟的值在[0,11]或[0,59]之间，且1的个数为turnedOn
     * 即加入答案
     */
    public List<String> readBinaryWatch1(int turnedOn) {
        List<String> ans = new ArrayList<String>();
        for(int i=0;i<1024;++i)
        {
            int h=i>>6,m=i&63;//用位运算取出高4位和低6位
            if(h<12&&m<60&&Integer.bitCount(i)==turnedOn)
            {
                ans.add(h+":"+(m<10?"0":"")+h);
            }
        }
        return ans;
    }

    /**
     * 回溯法
     * 总体思路：
     * 1. 在10个灯中选num个灯点亮，如果选择的灯所组成的时间已不合理（小时超过11，分钟超过59）就进行剪枝
     * 2. 也就是从0到10先选一个灯亮，再选当前灯的后面的灯亮，再选后面的灯的后面的灯亮，一直到num个灯点满
     */

    //小时数组和分钟数组，用来方便计算当前时分
    int[] hours = new int[]{1, 2, 4, 8, 0, 0, 0, 0, 0, 0};
    int[] minutes = new int[]{0, 0, 0, 0, 1, 2, 4, 8, 16, 32};
    List<String> res = new ArrayList<>();

    public List<String> readBinaryWatch2(int num) {
        backtrack(num, 0, 0, 0);
        return res;
    }

    void backtrack(int num,int index,int hour,int minute)
    {
        if(hour > 11 || minute > 59 || 10-num<index)
            return;
        if(num == 0){
            StringBuilder sb = new StringBuilder();
            sb.append(hour).append(':');
            if (minute < 10) {
                sb.append('0');
            }
            sb.append(minute);
            res.add(sb.toString());
            return;
        }
        for(int i=index;i<10;i++)
        {
            //每轮循环
            // 1. 减少一个需要点灯的数量
            // 2. 从当前已点亮的灯后进行选取（前面的已经选过了）
            // 3. 增加hour和minute
            backtrack(num-1,i+1,hour+hours[i],minute+minutes[i]);
        }
    }

}
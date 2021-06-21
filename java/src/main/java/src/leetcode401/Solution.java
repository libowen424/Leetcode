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
}
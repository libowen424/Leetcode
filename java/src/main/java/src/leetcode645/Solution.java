package src.leetcode645;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Solution {
    public int[] findErrorNums(int[] nums) {
        int length = nums.length;
        int[] res = new int[2];
        int repeatIndex = -1;
        Map<Integer, Integer> map = new HashMap();
        for (int i = 0; i < length; i++) {
            if (!map.containsKey(nums[i])) {
                map.put(nums[i], i);
            } else {
                res[0] = nums[i];
                repeatIndex = i;
            }
        }
        while (map.containsKey(repeatIndex + 1)) {
            repeatIndex = map.get(repeatIndex + 1);
        }
        res[1] = repeatIndex + 1;
        return res;
    }

//    public int[] findErrorNums1(int[] nums) {
//        Set set = new HashSet<Integer>(nums);
//
//    }
}
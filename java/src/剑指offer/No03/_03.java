package 剑指offer.No03;

import java.util.HashSet;
import java.util.Set;

public class _03 {
    public static void main(String[] args)
    {
//        int[] a = new int[]{2,3,1,0,2,5,3};
//        System.out.println((new Solution1()).findRepeatNumber(a));
            System.out.println((new Solution1()).findRepeatNumber(new int[]{2,3,1,0,2,5,3}));

}


}
/**
 * 利用hash表的性质
 */
class Solution {
    public int findRepeatNumber(int[] nums) {
        Set<Integer> dic = new HashSet<>();
        for(int num : nums)
        {
            if(dic.contains(num))
                return num;
        }
        return -1;
    }
}

/**
 * 利用题目中所说n个数字，大小是0~n-1
 * 如果不重复，则一定是数字和下标相对应，则遍历过程中将数字交换到对应下标
 * 只要出现了交换过程中两个一样的，即为重复的
 */
class Solution1 {
    public int findRepeatNumber(int[] nums) {
        for(int i=0;i<nums.length;i++)
        {
            while(nums[i]!=i)
            {
                int num = nums[i];
                if(nums[i]==nums[num])
                {
                    return num;
                }
                nums[i]=nums[num];
                nums[num]=num;
            }
        }
        return -1;
    }
}
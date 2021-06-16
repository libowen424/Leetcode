package src.leetcode852;

public class Solution {
    /**
     * 二分的思想
     */
    public int peakIndexInMountainArray(int[] arr) {
        int len = arr.length;
        int midIndex = (int)(len/2);
        while(midIndex>0||midIndex<len-1)
        {
            if(arr[midIndex]>arr[midIndex-1]&&arr[midIndex]>arr[midIndex+1])
            {
                return midIndex;
            }
            else if(arr[midIndex]>arr[midIndex-1])
            {
                midIndex=midIndex+1;
            }
            else
            {
                midIndex=midIndex-1;
            }
        }
        return -1;
    }

    /**
     * 三分的思想，专门用来解决有峰值的问题，每次否决1/3
     */
    public int peakIndexInMountainArray1(int[] arr) {
        int l = 0,r = arr.length-1;
        while(l<r)
        {
            int m1 = l+(r-l)/3,m2=r-(r-l)/3;
            if(arr[m1]>arr[m2])
            {
                r=m2-1;
            }
            else
            {
                l=m1+1;
            }
        }
        return r;
    }
}

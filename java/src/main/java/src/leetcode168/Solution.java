package src.leetcode168;

public class Solution {
    public static void main(String[] args) {
        new Solution().convertToTitle(701);
    }

    /**
     * 本质上就是26进制，不用的是范围是[1,26]
     * 例如
     * 正常的26进制：AB=26*0+26*1 注意，A表示0时，可以在数字前面填写任意多个
     * 本题的26进制：AB=26*(0+1)+1*(1+1) 注意A不再表示0了，不能随意添加
     *
     * 所以我们需要在处理时，每次提前-1，再对应到相应字母
     * 为什么要在总数基础上-1？因为相较于正常的26进制，每次处理，我们确实多处理了1
     * @param columnNumber
     * @return
     */
    public String convertToTitle(int columnNumber) {
        StringBuilder sb = new StringBuilder();
        while(columnNumber!=0)
        {
            --columnNumber;
            int cur = columnNumber%26;
            char c = (char)('A'+cur);
            sb.append(c);
            columnNumber/=26;
        }
        return sb.reverse().toString();
    }
}

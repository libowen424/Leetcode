package src.leetcode008;

import java.util.HashMap;
import java.util.Map;

public class Solution {
    /**
     * 主要犯下的几个错误：
     * 1。将char或者string转int/long时要parseXX函数，而不是简答的(XXX)强转
     * 2. 字符串转整数后int不一定放得下，要用long
     * 3. 用long也不一定存的下，所以最后判断一下字符串的位数
     *
     *
     *              也可以用这种思想来判断溢出和获取整数
     *             // 题目中说：环境只能存储 32 位大小的有符号整数，因此，需要提前判：断乘以 10 以后是否越界
     *             if (res > Integer.MAX_VALUE / 10 || (res == Integer.MAX_VALUE / 10 && (currChar - '0') > Integer.MAX_VALUE % 10)) {
     *                 return Integer.MAX_VALUE;
     *             }
     *             if (res < Integer.MIN_VALUE / 10 || (res == Integer.MIN_VALUE / 10 && (currChar - '0') > -(Integer.MIN_VALUE % 10))) {
     *                 return Integer.MIN_VALUE;
     *             }
     *
     */
    public int myAtoi(String s) {
        char[] chars = s.toCharArray();
        boolean checkSpace = true;
        boolean isNum = true;
        boolean checkFlag = true;
        int flag = 1;
        StringBuilder sb = new StringBuilder();
        int num=0;
        for(int i=0;i<chars.length;i++)
        {
            char c = chars[i];
            if(checkSpace&&c==' ')
            {
                continue;
            }
            else if(!checkSpace&&c==' ')
            {
                break;
            }
            else if(checkFlag&&(c=='-'||c=='+'))
            {
                flag = c=='-'?-1:1;
                checkFlag=false;
                checkSpace=false;
            }
            else if(isNum&&(c>='0'&&c<='9')) {
                checkSpace = false;
                checkFlag = false;
                if (sb.length() != 0 || c != '0') {
                    sb.append(c);
                }
            }
            else
            {
                break;
            }
        }
        if(sb.length()!=0)
        {
            if(sb.length()>10)
            {
                num=flag>0?Integer.MAX_VALUE:Integer.MIN_VALUE;
            }
            else {
                long parseRes = flag * Long.parseLong(sb.toString());
                num = parseRes > 0 ? (int) Math.min(parseRes, Integer.MAX_VALUE) : (int) Math.max(parseRes, Integer.MIN_VALUE);
            }
        }
        return num;
    }


    public int myAtoi1(String s) {
        int len = s.length();
        char[] chars = s.toCharArray();
        int index = 0;
        //1.去除前导空格
        while(index<len&&chars[index]==' ')
        {
            index++;
        }
        //2.如果已遍历完成
        if(index==len)
        {
            return 0;
        }
        //3.出现符号
        int sign = 1;
        char firstSignChar = chars[index];
        if(firstSignChar=='+')
        {
            index++;
        }
        else if(firstSignChar=='-')
        {
            index++;
            sign=-1;
        }
        //4.后续的数字进行转换
        int res = 0;
        while(index<len)
        {
            char curChar = chars[index];
            if(curChar>'9'||curChar<'0')
            {
                break;
            }
            //为了保证最后的结果是32位大小
            if(res>Integer.MAX_VALUE/10||(res==Integer.MAX_VALUE/10&&(curChar - '0') > Integer.MAX_VALUE % 10))
            {
                return Integer.MAX_VALUE;
            }

            if (res < Integer.MIN_VALUE / 10 || (res == Integer.MIN_VALUE / 10 && (curChar - '0') > -(Integer.MIN_VALUE % 10))) {
                return Integer.MIN_VALUE;
            }


            // 4.2 合法的情况下，才考虑转换，每一步都把符号位乘进去
            res = res * 10 + sign * (curChar - '0');
            index++;

        }
        return res;
    }

    /**
     * 对于这种每一步都有明确指示的题目，可以考虑有限状态机
     * 用表格列出每种状态的变化，考虑到所有情况即可
     *              ' '         +/-     int_number      other
     * start        start       signed  int_number      end
     * signed       end         end     int_number      end
     * int_number   end          end     ine_number     end
     * end          end         end     end             end
     */
    public int myAtoi2(String s) {
        Automaton automaton = new Automaton();
        int length = s.length();
        for(int i=0;i<length;i++)
        {
            //依次将各个字符传入
            automaton.get(s.charAt(i));
        }
        return (int)(automaton.sign*automaton.ans);
    }
}

class Automaton{
    public int sign = 1;
    public long ans = 0;
    private String state = "start";
    private Map<String,String[]> table = new HashMap<String, String[]>(){{
        put("start",new String[]{"start","signed","int_number","end"});
        put("signed",new String[]{"end","end","int_number","end"});
        put("int_number",new String[]{"end","end","int_number","end"});
        put("end",new String[]{"end","end","end","end"});
    }};
    private int getCol(char c)
    {
        if(c==' ')
        {
            return 0;
        }
        else if(c=='+'||c=='-')
        {
            return 1;
        }
        else if(Character.isDigit(c))
        {
            return 2;
        }
        else
        {
            return 3;
        }
    }
    //根据传入字符，修改状态，并根据状态修改结果
    public String  get(char c)
    {
        state = table.get(state)[getCol(c)];
        if(state.equals("int_number"))
        {
            ans = ans*10+c-'0';
            ans = sign == 1 ? Math.min(ans, (long) Integer.MAX_VALUE) : Math.min(ans, -(long) Integer.MIN_VALUE);
        }
        else if(state.equals("signed"))
        {
            sign = c=='+'?1:-1;
        }
        return state;
    }
}

package leetcode316;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Solution {
    public static void main(String[] args) {
        System.out.println(removeDuplicateLetters("cbacdcbc"));
    }
    public static String removeDuplicateLetters(String s) {
        int length = s.length();
        if(length==0)
        {
            return s;
        }
        Stack ret = new Stack();
        for(int i =0;i<length;++i)
        {
            char c = s.charAt(i);
            boolean flag = true;//判断是否删除后一个
            if(!ret.isEmpty()&&ret.contains(c))
            {
                List<Character> popElements = new ArrayList();
                while(!ret.isEmpty())
                {
                    char temp = (char)ret.pop();
                    if(c-temp>0)
                    {
                        flag=false;
                        popElements.add(temp);
                    }
                    else if(c-temp<0)
                {
                    flag=true;
                    popElements.add(temp);

                }
                    else if(temp==c)
                    {
                        if(flag)
                        {
                            popElements.add(temp);
                        }
                        for(int j = popElements.size()-1;j>=0;j--)
                        {
                            ret.push(popElements.get(j));
                        }
                        if(!flag)
                        {
                            ret.push(c);
                        }
                        break;
                    }
                }
            }
            else
            {
                ret.push(c);
            }
        }
        StringBuilder sb = new StringBuilder();
        while(!ret.isEmpty())
        {
            sb.append((char)ret.pop());
        }
        return sb.reverse().toString();
    }
}

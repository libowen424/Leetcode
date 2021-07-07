package src.leetcode726;

import java.util.*;

public class Main {
    public String countOfAtoms(String formula) {
        Map<String,Integer> map = getMap(formula);
        List<Map.Entry<String,Integer>> list = new ArrayList<Map.Entry<String,Integer>>(map.entrySet());
        Collections.sort(list,new Comparator<Map.Entry<String, Integer> >() {
            // 升序排序
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o1.getKey().charAt(0)-o2.getKey().charAt(0);
            }
        });
        StringBuilder sb = new StringBuilder();
        for(Map.Entry<String,Integer> mapping:list){
            sb.append(mapping.getKey());
            if(mapping.getValue()>1) {
                sb.append(mapping.getValue());
            }
        }
        return String.valueOf(sb);
    }
    Map<String,Integer> getMap(String formula)
    {
        char[] chars = formula.toCharArray();
        int len = chars.length;
        Map<String,Integer> curMap = new HashMap<>();
        StringBuilder curString = new StringBuilder();
        int curNum = 0;
        int leftIndex=-1;
        int rightIndex=-1;
        for(int i=0;i<len;)
        {
            if(chars[i]=='(')
            {
                int leftBracket = 1;
                if(curString.length()>0)
                {
                    curMap.put(String.valueOf(curString),curMap.getOrDefault(String.valueOf(curString),0)+(curNum==0?1:curNum));
                }
                curString=new StringBuilder();
                curNum=0;

                leftIndex=i;
                i++;
                while(i<len)
                {
                    if(chars[i]=='(')
                    {
                        leftBracket++;
                    }
                    else if(chars[i]==')')
                    {
                        leftBracket--;
                    }
                    i++;
                    if(leftBracket==0)
                    {
                        i--;
                        break;
                    }
                }
            }
            else if(chars[i]==')')
            {
                rightIndex=i;
                Map<String,Integer> backet = getMap(formula.substring(leftIndex+1,rightIndex));
                i++;
                int backetNum = 0;
                while(i<len&&chars[i]>='0'&&chars[i]<='9')
                {
                    backetNum=backetNum*10+(chars[i]-'0');
                    i++;
                }

                for (Map.Entry<String, Integer> entry : backet.entrySet()) {
                    curMap.put(entry.getKey(), curMap.getOrDefault(entry.getKey(),0)+(backetNum==0?entry.getValue():entry.getValue()*backetNum));
                }
            }
            else if(chars[i]>='A'&&chars[i]<='Z')
            {
                if(curString.length()>0)
                {
                    curMap.put(String.valueOf(curString),curMap.getOrDefault(String.valueOf(curString),0)+(curNum==0?1:curNum));
                }
                curString=new StringBuilder();
                curString.append(chars[i]);
                curNum=0;
                i++;
            }
            else if(chars[i]>='a'&&chars[i]<='z')
            {
                curString.append(chars[i]);
                i++;
            }
            else if(chars[i]>='0'&&chars[i]<='9')
            {
                curNum=curNum*10+(chars[i]-'0');
                i++;
            }
        }
        if(curString.length()>0){
            curMap.put(String.valueOf(curString),curMap.getOrDefault(String.valueOf(curString),0)+(curNum==0?1:curNum));
        }
        return curMap;
    }
}

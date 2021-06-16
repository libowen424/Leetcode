package leetcode621;


import java.util.HashMap;
import java.util.Map;

public class Solution {
    public static void main(String[] args) {
        System.out.println(leastInterval(new char[]{'A','A','A','A','A','A','B','C','D','E','F','G'}, 2));
    }
    public static int leastInterval(char[] tasks, int n) {
        if(n==0)
        {
            return tasks.length;
        }
        Map<Character,Integer> map = new HashMap();
        Map<Character,Integer> map1 = new HashMap();
        for(int i = 0 ;i<tasks.length;++i)
        {
            map.put(tasks[i],map.getOrDefault(tasks[i],0)+1);
            map1.put(tasks[i],0);
        }
        int result = 0;
        for(int i = 0;i<tasks.length;++i)
        {
            boolean flag = true;
            for (Map.Entry<Character, Integer> entry : map.entrySet()) {
                if(entry.getValue()>0&&map1.get(entry.getKey())<=0)
                {
                    result++;
                    map.put(entry.getKey(),entry.getValue()-1);
                    map1.put(entry.getKey(),n);
                    for (Map.Entry<Character, Integer> entry1 : map1.entrySet()) {
                        if (entry1.getValue() > 0 && entry1.getKey() != entry.getKey()) {
                            map1.put(entry1.getKey(), entry1.getValue() - 1);
                        }
                    }
                    flag=false;
                    break;
                }
            }
            while(flag)
            {
                result++;
                for (Map.Entry<Character, Integer> entry : map1.entrySet()) {
                    if(entry.getValue()>0)
                    {
                        map1.put(entry.getKey(),entry.getValue()-1);
                    }else if(entry.getValue()==0&&map.get(entry.getKey())>0)
                    {
                        flag=false;
                    }
                }
            }
        }
        return result;
    }
}

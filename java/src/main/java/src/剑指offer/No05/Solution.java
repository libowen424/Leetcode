package src.剑指offer.No05;

public class Solution {
    public String replaceSpace(String s) {
        char[] charList = s.toCharArray();
        StringBuilder stringBuilder = new StringBuilder();
        for(int i=0;i<charList.length;i++)
        {
            char cur = charList[i];
            if(cur!=' ')
            {
                stringBuilder.append(cur);
            }
            else
            {
                stringBuilder.append("%20");
            }
        }
        return stringBuilder.toString();
    }
}

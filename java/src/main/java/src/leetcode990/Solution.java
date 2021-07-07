package src.leetcode990;

public class Solution {
    public boolean equationsPossible(String[] equations) {
        UnionFin u = new UnionFin(26);
        for(String s : equations)
        {
            if(s.charAt(1)=='=')
            {
                int index1 = s.charAt(0)-'a';
                int index2 = s.charAt(3)-'a';
                u.union(index1,index2);
            }
        }
        for(String s : equations) {
            if (s.charAt(1) != '=') {
                int index1 = s.charAt(0)-'a';
                int index2 = s.charAt(3)-'a';
                if(u.find(index1)==u.find(index2))
                {
                    return false;
                }
            }
        }
        return true;
    }
}

class UnionFin{
    private int[] id;
    public UnionFin(int n)
    {
        id = new int[n];
        for(int i=0;i<n;i++)
            id[i]=i;
    }
    public int find(int p)
    {
        while(p!=id[p])
            p=id[p];
        return p;
    }
    public void union(int p,int q)
    {
        int pRoot = find(p);
        int qRoot = find(q);

        if(pRoot == qRoot) return;

        id[pRoot] = qRoot;
    }
}
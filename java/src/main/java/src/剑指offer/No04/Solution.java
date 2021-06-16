package 剑指offer.No04;

public class Solution {

    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        if(matrix.length==0||matrix[0].length==0)
        {
            return false;
        }
        int x=0,y=matrix[0].length;
        while(x>=matrix.length||y<0)
        {
            int cur = matrix[x][y];
            if(target>cur)
            {
                x++;
            }
            else if(cur==target)
            {
                return true;
            }
            else {
                y--;
            }
        }
        return false;
    }
}

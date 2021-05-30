package leetcode134;

public class Main {
    public static int canCompleteCircuit(int[] gas, int[] cost) {
        int n = gas.length;
        for(int i=0;i<n;i++){
            int curGas=0;
            int j=0;
            for(;j<n;j++){
                curGas+=gas[(i+j)%n];
                if(curGas<cost[(i+j)%n]){
                    break;
                }else{
                    curGas-=cost[(i+j)%n];
                }
            }
            if(j==n){
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        canCompleteCircuit(new int[]{1,2,3,4,5},new int[]{3,4,5,1,2});
    }
}

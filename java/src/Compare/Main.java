package Compare;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        rangeSum(new int[]{1, 2, 3, 4},4,1,10);
//        Integer[] a = new Integer[]{12,4,5,1,57,34};
//        List<Integer> b = new ArrayList<Integer>();
//        b.add(12);
//        b.add(4);
//        b.add(5);
//        b.add(1);
//        b.add(57);
//        b.add(34);
//        Collections.sort(b, new Comparator<Integer>() {
//            @Override
//            public int compare(Integer o1, Integer o2) {
//                return -(o1-o2);
//            }
//        });
//
//        Arrays.sort(a,new Comparator<Integer>(){
//            @Override
//            public int compare(Integer o1, Integer o2) {
//                return -(o1-o2);
//            }
//        });
//        for(int i:a) {
//            System.out.println(i);
//        }
//
//        int[][] points = new int[][]{{2,1},{4,3},{2,2}};
//        Arrays.sort(points,new Comparator<int[]>(){
//            public int compare(int[] o1,int[] o2){
//                return o1[0]*o1[0]+o1[1]*o1[1]-o2[0]*o2[0]-o2[1]*o2[1];
//            }
//        });
    }
//    public int[] relativeSortArray(int[] arr1, int[] arr2) {
//        Map<Integer,Integer> map = new HashMap<Integer,Integer>();
//        int length2=arr2.length;
//        for(int i=0;i<length2;++i){
//            map.put(arr2[i],i);
//        }
//        /**
//         * Array.sort中重写比较方法需要传入类对象，传基本类型时不行的，所以需要将int[]类型的arr1转化为Integer[]类型
//         */
////        Arrays.sort(,new Comparator<Integer>(){
////            @Override
////            public int compare(Integer o1, Integer o2) {
////                if(map.containsKey(o1)){
////                    return map.containsKey(o2)?map.get(o1)-map.get(o2):1;
////                }else{
////                    return map.containsKey(o2)?-1:o1-o2;
////                }            }
////
////        });
//    }

//public int[][] allCellsDistOrder(int R, int C, int r0, int c0) {
//    int[][] ret = new int[R*C][2];
//    for(int i=0;i<R;i++){
//        for(int j=0;j<C;j++){
//            ret[i*C+j]= new int[]{i, j};
//        }
//    }
//    Arrays.sort(ret,new Comparator<int[]>(){
//        public int compare(int[]o1,int[] o2){
//            return Math.abs(o1[0]-ro)+Math.abs(o1[1]-c0)-Math.abs(o2[0]-ro)-Math.abs(o2[1]-c0);
//        }
//    });
//    return ret;
//}
public int[][] allCellsDistOrder(int R, int C, int r0, int c0) {
    int[][] direction={{1,1} ,{1,-1},{-1,-1},{-1,1}};
    int maxDis=Math.max(r0 , R-1-r0) +Math.max( c0 , C-1-c0); //最远就是到四个角的位置
    int[][] result=new int[R*C][2];
    int index=0;
    result[index++]=new int[]{r0,c0};//先将距离为0的，即自己加入
    for(int dis=1; dis<=maxDis ;dis++){
        int i=r0-dis ,j=c0;//从正上方开始,方向依次是  *\  /*  \.  ./  （*.表示当前方向起始位置）
        for(int dc=0;dc<4;dc++){ //四个方向，每个方向走dis次
            for(int count =0;count<dis;count++){
                i+=direction[dc][0];
                j+=direction[dc][1];
                if(i>=0 && i<R && j>=0 && j<C) result[index++]=new int[]{i,j};
            }
        }

    }
    return result;
}

    public static int rangeSum(int[] nums, int n, int left, int right) {
        List<Integer> list = new ArrayList<>();
        for(int i=0;i<n;++i)
        {
            int temp=nums[i];
            list.add(temp);
            for(int j=i+1;j<n;j++){
                list.add(temp+nums[j]);
                temp=temp+nums[j];
            }
        }
        Collections.sort(list);
        System.out.println(list.size());
        int result=0;
        for(int j=left-1;j<right;++j){
            System.out.println(list.get(j));
            result+=list.get(j);
        }
        return result;
    }

}

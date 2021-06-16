package MergeSort;

public class Main {
    public static void main(String[] args) {
        int[] arr={1,3,32,2,55,21};
        mergeSort(arr);
        for(int i:arr){
            System.out.println(i+" ");
        }
    }
    public static void merge(int[] arr,int L,int mid,int R){
        int[] help = new int[R-L+1];//辅助数组
        int i=0;//辅助数组下标
        int p1=L;//p1相当于左侧指针
        int p2=mid+1;//p2相当于右侧指针、
        while (p1<=mid&&p2<=R){
            help[i++]=arr[p1]<arr[p2]?arr[p1++]:arr[p2++];
        }
        //p2越界时，将p1的数组直接移过来
        while (p1<=mid){
            help[i++]=arr[p1++];
        }
        //p1越界时，将p2的数组直接移过来
        while (p2<=R){
            help[i++]=arr[p2++];
        }
        //将排序结果拷贝到原数组
        for(i=0;i<help.length;i++){
            arr[L+i]=help[i];
        }
    }
    //递归排序
    public static void sortProcess(int[] arr,int L,int R){
        if(L==R){
            return;
        }
        int mid=L+(R-L)/2;
        //递归过程，左右分别排好序
        sortProcess(arr,L,mid);
        sortProcess(arr,mid+1,R);
        merge(arr,L,mid,R);
    }
    public static void mergeSort(int[] arr){
        if(arr==null||arr.length<2){
            return;
        }
        sortProcess(arr,0,arr.length-1);
    }
}

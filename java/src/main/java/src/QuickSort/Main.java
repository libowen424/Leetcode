package QuickSort;

public class Main {
    public static void main(String[] args) {

    }

    /**
     * 拔萝卜
     * @param arr
     * @param front
     * @param last
     * @return
     */
    public static int finMid(int[] arr,int front,int last){
        int pivot = arr[front];
        while (front<last){
            //一定是last先动
            while (front<last&&pivot<=arr[last]){
                last--;
            }
            arr[front] = arr[last];
            while (front<last&pivot>=arr[front]){
                front++;
            }
            arr[last]=arr[front];
        }
        arr[front]=pivot;
        return front;
    }

    /**
     * 填坑法实现快速排序，即利用上面的拔萝卜的原理，进行递归调用
     * @param a
     * @param front
     * @param behind
     */
    public static void quickSort(int[] a,int front,int behind){
        if(front<behind){
            int i,j,x;
            i=front;
            j=behind;
            x=a[i];
            while (i<j){
                while (i<j&&a[j]>=x){
                    j--;
                }
                a[i]=a[j];
                while (i<j&&a[i]<=x){
                    i++;
                }
                a[j]=a[i];
            }
            a[i]=x;
            //i相当于是本次递归的中间标志
            quickSort(a,front,i-1);
            quickSort(a,i+1,behind);
        }
    }

    public static void quickSortDP(int[] a,int begin,int end){
        if(begin>=end)
            return;
        int pivot=a[begin];
        int m=begin,n=end;
        while(m<n){
            //右边先动
            while (m<n&&a[n]>=pivot){
                n--;
            }
            while (m<n&&a[m]<=pivot){
                m++;
            }
            if(n>m){
                swap(a,m,n);
            }
        }
        //最后将基准和m,n相等的位置互换
        //因为总是右边先动，所以可以保证此时相等位置上的数字是小于基准的
        a[begin]=a[m];
        a[m]=pivot;
        //递归左半数组
        quickSortDP(a,begin,n-1);
        //递归右半数组
        quickSortDP(a,n+1,end);
    }
    public static void swap(int[] a,int index1,int index2){
        int temp = a[index1];
        a[index1]=a[index2];
        a[index2]=temp;
    }
}

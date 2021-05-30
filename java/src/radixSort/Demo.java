package radixSort;

import java.util.Arrays;


import java.util.Scanner;
/**
 * 队列结构
 * @author Fairy2016
 *
 */
class Queue {
    int data[];
    int front;
    int rear;
}
/**
 * 基数排序
 * @author Fairy2016
 *
 */
public class Demo {

    public static void sort(int a[]) {
        int n=a.length;
        //求最大位数
        int count = getNumberCount(getMax(a, n));
        //十个队列，分别存储数位数值为0-9的元素
        Queue [] queues = new Queue[10];
        //各队列初始化
        for(int i = 0; i < 10; i++) {
            queues[i] = new Queue();
            queues[i].data = new int[n];
            queues[i].front = queues[i].rear = -1;
        }
        int m = 1;//m控制取第几位（从个位开始取直到count）
        while(count > 0) {
            for(int i = 0; i < n; i++) {
                int t = a[i] / m % 10;
                //根据数值分配入队
                queues[t].data[++queues[t].rear] = a[i];
            }
            //从队号0-9顺序出队收集元素
            int s = 0;
            for(int j = 0; j < 10; j++) {
                while(queues[j].front != queues[j].rear) {
                    a[s++] = queues[j].data[++queues[j].front];
                }
                //收集后队列清空，方便下一趟排序
                queues[j].front = queues[j].rear = -1;
            }
            m *= 10;
            count --;
        }
    }
    /**
     * 筛选出最大元素方便确定位数
     * @param a
     * @param n
     * @return
     */
    public static int getMax(int a[], int n) {
        int max=a[0];
        for(int i = 1; i < n; i++) {
            if(a[i] > max) {
                max = a[i];
            }
        }
        return max;
    }

    /**
     * 求排序元素的最大位数
     * @param t
     * @return
     */
    public static int getNumberCount(int t) {
        int count = 1;
        t = t / 10;
        while(t != 0) {
            count++;
            t /= 10;
        }
        return count;
    }

    public static void Print(int a[]) {
        int n=a.length;
        for(int i = 0; i < n; i++) {
            System.out.print(a[i]+" ");
        }
    }

    public static void main(String args[]) {
        int a[]=new int[]{3,6,9,1,23};
        sort(a);
                Print(a);

    }
}

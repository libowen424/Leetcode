package PriorityQueue;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) {
        int[] array = {9,8,7,6,5,4,3,2,1};
        System.out.println(Solution(array,4));

    }
    public static ArrayList<Integer> Solution(int[] array, int k) {
        ArrayList<Integer> result = new ArrayList<>();
        int length = array.length;
        if (k > length || k <= 0) {
            return result;
        }
        PriorityQueue<Integer> maxheap = new PriorityQueue<Integer>(k, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {

                return o2.compareTo(o1);
            }
        });
        for (int i = 0; i < length; i++) {
            if (maxheap.size() != k){
                maxheap.offer(array[i]);
            }
            else if(array[i] < maxheap.peek()){
                maxheap.poll();
                maxheap.offer(array[i]);
            }

        }


        for(Integer num : maxheap){
            result.add(num);
        }
        return result;
    }

}

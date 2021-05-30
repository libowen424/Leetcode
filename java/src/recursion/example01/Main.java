package recursion.example01;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        new Recursion().combinationSum(new ArrayList<>(),new int[]{2,3,5},8);
    }

}

class Recursion {
    /**
    有分支污染
     **/
    public void combinationSum(List<Integer> cur, int sums[], int target) {
        //终止条件必须要有
        //当target等于0的时候，说明我们找到了一组组合，我们就把他打印出来
        if (target == 0) {
            System.out.println(Arrays.toString(cur.toArray()));
            return;
        }
        //逻辑处理(可有可无，是情况而定)
        for (int i = 0; i < sums.length; i++) {
            //逻辑处理(可有可无，是情况而定)
            //如果当前值大于target我们就不要选了
            if (target < sums[i])
                continue;
            //否则我们就把他加入到集合
            cur.add(sums[i]);
            // 递归调用(递归调用必须要有)
            combinationSum(cur, sums, target - sums[i]);
        }
    }
    /**
    新建分支来解决污染
     **/
    public void combinationSum1(List<Integer> cur, int sums[], int target) {
        //终止条件必须要有
        //当target等于0的时候，说明我们找到了一组组合，我们就把他打印出来
        if (target == 0) {
            System.out.println(Arrays.toString(cur.toArray()));
            return;
        }
        //逻辑处理(可有可无，是情况而定)
        for (int i = 0; i < sums.length; i++) {
            //逻辑处理(可有可无，是情况而定)
            //如果当前值大于target我们就不要选了
            if (target < sums[i])
                continue;
            //由于list是引用传递，所以我们新建一个list
            List<Integer> list = new ArrayList<>(cur);
            //否则我们就把他加入到集合
            list.add(sums[i]);
            // 递归调用(递归调用必须要有)
            combinationSum(list, sums, target - sums[i]);
        }
    }
    /**
     * 通过回溯解决
     * 即从分支1执行到分支2的时候要把分支1的数据给删除，
     */
    public void combinationSum2(List<Integer> cur, int sums[], int target) {
        //终止条件必须要有
        //当target等于0的时候，说明我们找到了一组组合，我们就把他打印出来
        if (target == 0) {
            System.out.println(Arrays.toString(cur.toArray()));
            return;
        }
        //逻辑处理(可有可无，是情况而定)
        for (int i = 0; i < sums.length; i++) {
            //逻辑处理(可有可无，是情况而定)
            //如果当前值大于target我们就不要选了
            if (target < sums[i])
                continue;
            //把sums[i]加入到集合中，参与下一轮递归
            cur.add(sums[i]);
            // 递归调用(递归调用必须要有)
            combinationSum(cur, sums, target - sums[i]);
            //sums[i]用完之后要删除
            cur.remove(cur.size()-1);
        }
    }

}
package src.leetcode451;

import java.util.*;

public class Solution {
    /**
     * 最常规的思想
     * 用map存储出现频率，再根据频率进行排序
     * 注意根据频率排序时，排序对象是字符，标准是频率
     *
     * @param s
     * @return
     */
    public String frequencySort(String s) {
        int len = s.length();
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            int num = map.getOrDefault(c, 0) + 1;
            map.put(c, num);
        }
        List<Character> list = new ArrayList<Character>(map.keySet());
        Collections.sort(list, (a, b) -> map.get(b) - map.get(a));
        StringBuilder sb = new StringBuilder();
        int size = list.size();
        for (int i = 0; i < size; i++) {
            char c = list.get(i);
            int frequency = map.get(c);
            for (int j = 0; i < frequency; j++) {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    /**
     * 桶排序
     * 如果以字母为桶，会出现的问题是后续还要进行排序
     * 可以以频率为桶
     */
    public String frequencySort1(String s) {
        Map<Character, Integer> map = new HashMap<>();
        int maxFeq = 0;
        int len = s.length();
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            int fre = map.getOrDefault(c, 0) + 1;
            map.put(c, fre);
            maxFeq = Math.max(maxFeq, fre);
        }
        StringBuffer[] buckets = new StringBuffer[maxFeq + 1];
        for (int i = 0; i <= maxFeq; i++) {
            buckets[i] = new StringBuffer();
        }
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            char c = entry.getKey();
            int frequency = entry.getValue();
            buckets[frequency].append(c);
        }
        StringBuffer sb = new StringBuffer();
        for (int i = maxFeq; i > 0; i--) {
            StringBuffer bucket = buckets[i];
            int size = bucket.length();
            for (int j = 0; j < size; j++) {
                for (int k = 0; k < i; k++) {
                    sb.append(bucket.charAt(j));
                }
            }
        }
        return sb.toString();
    }

    /**
     * 优先队列
     */
    class Node {
        char c;
        int v;

        Node(char _c, int _v) {
            c = _c;
            v = _v;
        }
    }

    public String frequencySort2(String s) {
        char[] cs = s.toCharArray();
        Map<Character, Integer> map = new HashMap<>();
        for (char c : cs) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        PriorityQueue<Node> q = new PriorityQueue<>((a,b)->{
            if (b.v != a.v) return b.v - a.v;
            return a.c - b.c;
        });
        for (char c : map.keySet()) {
            q.add(new Node(c, map.get(c)));
        }
        StringBuilder sb = new StringBuilder();
        while (!q.isEmpty()) {
            Node poll = q.poll();
            int k = poll.v;
            while (k-- > 0) sb.append(poll.c);
        }
        return sb.toString();
    }
}
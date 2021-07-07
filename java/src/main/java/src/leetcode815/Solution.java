package src.leetcode815;

import java.util.*;

/**
 * 抽象每个路线为一个点，不同路线之间存在公共车站则为其增加一条权为1的无向边
 */
public class Solution {
    /**
     * 于是抽象为在边权为1的图上求最短路径，直接可用BFS
     * 起始时将「起点车站」所能进入的「路线」进行入队，每次从队列中取出「路线」时，查看该路线是否包含「终点车站」：
     * * 包含「终点车站」：返回进入该线路所花费的距离
     * * 不包含「终点车站」：遍历该路线所包含的车站，将由这些车站所能进入的路线，进行入队
     * <p>
     * 注意细节：由于是求最短路，同一路线重复入队是没有意义的，因此将新路线入队前需要先判断是否曾经入队。
     */
    public int numBusesToDestination(int[][] routes, int source, int target) {
        if (source == target) return 0;
        return bfs(routes, source, target);
    }

    int bfs(int[][] routes, int source, int target) {
        //记录某个车站可以进入的路线
        Map<Integer, Set<Integer>> map = new HashMap();
        //队列存的是经过的路线
        Deque<Integer> d = new ArrayDeque<>();
        //记录进入该路线所使用的距离
        Map<Integer, Integer> m = new HashMap<>();
        int n = routes.length;
        //初始化
        for (int i = 0; i < n; i++)//遍历所有路线
        {
            for (int station : routes[i])//遍历每条路线的所有车站
            {
                if (station == source) {
                    //如果路线包含入口，加入经过路线的队列
                    d.addLast(i);
                    //记录进入这个路线的距离为1
                    m.put(i, 1);
                }
                //记录每个站对应的所有路线
                Set<Integer> set = map.getOrDefault(station, new HashSet<>());
                set.add(i);
                map.put(station, set);
            }
        }
        while (!d.isEmpty()) {
            //取出当前所在的路线
            int poll = d.pollFirst();
            //进入该路线所花费的距离
            int step = m.get(poll);

            //遍历该路线所包含的车站
            for (int station : routes[poll]) {
                // 如果是终点，返回进入该路线花费的距离即可
                if (station == target) return step;

                //如果不是终点，将该车站可以经过的线路，加入队列
                Set<Integer> lines = map.get(station);
                if (lines == null) continue;
                for (int nr : lines) {
                    //要注意，发起的路线中已经走过的不用加入队列，因为之前走过的路径一定更短
                    if (!m.containsKey(nr)) {
                        m.put(nr, step + 1);
                        d.add(nr);
                    }
                }
            }
        }
        return -1;
    }

    /**
     * 有了上面的思路，我们还可以用双向BFS
     * 如果「遇到公共的路线」或者「当前路线包含了目标位置」，说明找到了最短路径
     * 另外我们知道，双向 BFS 在无解的情况下不如单向 BFS。
     * 因此我们可以先使用「并查集」进行预处理，判断「起点」和「终点」是否连通，如果不联通，直接返回 -1，有解才调用双向 BFS。
     */
    static int N = (int) 1e6 + 10;
    static int[] p = new int[N];//并查集

    int find(int x) {
        if (p[x] != x) p[x] = find(p[x]);
        return p[x];
    }

    void union(int a, int b) {
        p[find(a)] = p[find(b)];
    }

    boolean query(int a, int b) {
        return find(a) == find(b);
    }

    public int numBusesToDestination1(int[][] routes, int source, int target) {
        if(source==target) return 0;
        for(int i=0;i<N;i++) p[i]=i;//初始化并查集
        //合并并查集
        for(int[] r:routes)
        {
            for(int location:r)
            {
                union(location,r[0]);
            }
        }
        if(!query(source,target)) return -1;//如果合并之后source和target不在一个集合，即不互通
        int ans = Dbfs(routes, source, target);
        return ans;
    }
    int Dbfs(int[][] routes, int source, int target)
    {
        Map<Integer,Set<Integer>> map = new HashMap<>();
        Deque<Integer> d1 = new ArrayDeque<>(),d2= new ArrayDeque<>();
        Map<Integer,Integer> m1 = new HashMap<>(),m2=new HashMap<>();
        int n=routes.length;
        for(int i=0;i<n;i++)
        {
            for(int station : routes[i])
            {
                if(station==source)
                {
                    d1.addLast((i));
                    m1.put(i,1);
                }
                if(station==target)
                {
                    d2.addLast(i);
                    m2.put(i,1);
                }
                Set<Integer> set = map.getOrDefault(station,new HashSet<>());
                set.add(i);
                map.put(station,set);
            }
        }
        //如果起点发起的路线和终点发起的路线有重合
        Set<Integer> s1 = map.get(source),s2=map.get(target);
        Set<Integer> tot = new HashSet<>();
        tot.addAll(s1);
        tot.retainAll(s2);
        if(!tot.isEmpty()) return 1;

        //双向BFS
        while(!d1.isEmpty()&&!d2.isEmpty())
        {
            int res = -1;
            if(d1.size()<=d2.size())
            {
                res = update(d1,m1,m2,routes,map);
            }
            else
            {
                res = update(d2,m2,m1,routes,map);
            }
            if(res!=-1) return res;
        }
        return 0x3f3f3f3f;
    }
    int update(Deque<Integer> d,Map<Integer,Integer> cur,Map<Integer,Integer>other,int[][] routes,Map<Integer,Set<Integer>> map)
    {
        // 取出当前所在的路线，与进入该路线所花费的距离
        int poll = d.pollFirst();
        int step = cur.get(poll);

        //遍历该路线所包含的车站
        for(int station:routes[poll])
        {
            Set<Integer> lines = map.get(station);
            if(lines==null) continue;
            for(int nr:lines)
            {
                if(cur.containsKey(nr)) continue;
                if(other.containsKey(nr)) return step+other.get(nr);
                cur.put(nr,step+1);
                d.add(nr);
            }
        }
        return -1;
    }
}
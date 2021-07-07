# 介绍

并查集是一种数据结构, 常用于描述集合,经常用于解决此类问题:某个元素是否属于某个集合,或者 某个元素 和 另一个元素是否同属于一个集合。

```java
public class UnionFind {
    private int[] id;//数组里存的数字代表所属的集合。
    private int count;//集合个数

    public UnionFind(int N) { //初始化
        count = N;
        id = new int[N];
        //初始化为id[i]=i,即每个数字都自成立一个小组。
        for(int i = 0; i < N; i++) id[i] = i;
    }

    public int getCount() {
        return count;
    }

    public boolean connected(int p, int q) {//判断p、q是否同属同一个集合
        return find(p) == find(q);
    }

    public int find(int p) {//返回id[p]
        return id[p];
    }

    public void union(int p, int q){//联合,p被q吞并
        int pRoot = find(p);
        int qRoot = find(q);

        if(pRoot == qRoot) return;//判断是否同属一个集合

        for(int i = 0; i < id.length; i++)//p的集合被q的集合吞并掉
            if(id[i] == pRoot)//p集合的所有值都修改为q
                id[i] = qRoot;
        count--;
    }
}
```

## 例子

先初始化一个数组。初始时数组内的值与数组的下角标一致。即每个数字都自成立一个小组。

0属于第0个小组(集合)，1属于第1个小组(集合)，2属于第2个小组(集合)..........

![img](https://gitee.com/lbw427/blog-img/raw/master//img/1251417-20171211102517724-1218869510.jpg)

让 5和6进行组队。5里的值就变为6了。含义就是：5放弃了第5小组，加入到了第6小组。5和6属于第6小组。

![img](https://images2017.cnblogs.com/blog/1251417/201712/1251417-20171211103325459-891011955.jpg)

接下来 让1 和2 进行组队。1的下角标就变为2了。含义就是：1和2都属于第2小组。

![img](https://images2017.cnblogs.com/blog/1251417/201712/1251417-20171211103639224-1401185135.jpg)

接下来让 2 3进行组队：2想和3进行组队，2就带着原先的所有队友，加入到了3所在的队伍。看下面arr[1] == arr[2]==arr[3]==3，意思就是1 2 3 都属于第3小组。

![img](https://gitee.com/lbw427/blog-img/raw/master//img/1251417-20171211104227537-1430924066.jpg)

接下来 1 和 4 进行组队：1就带着原先所有的队友一起加入到4所在的队伍中了。看下面arr[1] == arr[2]==arr[3]==arr[4]==4，意思就是1 2 3 4都属于第4小组。

![img](https://images2017.cnblogs.com/blog/1251417/201712/1251417-20171211104701365-1950190234.jpg)

接下来1 和 5进行组队:1就带着原先所有的队友一起加入到5所在的队伍中。5在哪个队伍呢？ 因为arr[5]==6，所以5在第6小组。1就带着所有队友进入了小组6。

看下面arr[1] == arr[2]==arr[3]==arr[4]==arr[5]==arr[6]==6，意思就是1 2 3 4 5 6都属于第6小组。

 ![img](https://images2017.cnblogs.com/blog/1251417/201712/1251417-20171211105227724-1476777413.jpg)

 将这个例子的并查集用树形表示来，如下图所示：

![img](https://gitee.com/lbw427/blog-img/raw/master//img/1251417-20171211120349399-1965529145.jpg)

# 快union 慢Find

上面的合并代码里，每次合并p\q，就需要遍历所有元素，将所有p小组的都加入q中，这样的合并操作太低效了，合并一次就O(n)。

**修改find和union**

原先的数组中存的是小组号(或者队长的编号)，而现在数组中存的是自己的‘大哥’的编号。

每个元素都可以去认一个大哥去保护自己，避免被欺负。只能认一个大哥...不能认多个

```java
public int find(int p) {
        while(p != id[p])//一直找到最终的大哥
            p = id[p];
        return p;
}
    
public void union(int p, int q){
        int pRoot = find(p);
        int qRoot = find(q);

        if(pRoot == qRoot) return;

        id[pRoot] = qRoot;
        count--;
}
```

## 例子

初始情况如下：每个元素里的内容就是自己的下角标（编号）。表示自己就是自己大哥，表示很自由，不从属于任何人。如下图所示

![img](https://images2017.cnblogs.com/blog/1251417/201712/1251417-20171211112810915-225494625.jpg)

连接5 6 ： 后来5号总是受欺负，认6号为大哥，自己的内容就变为6了。如下图所示

![img](https://gitee.com/lbw427/blog-img/raw/master//img/1251417-20171211113034493-2136523670.jpg)

连接1 2：后来1号发现自己单着也不行，认2号为大哥，自己的内容就变为2了。如下图所示

![img](https://gitee.com/lbw427/blog-img/raw/master//img/1251417-20171211113312134-417733441.jpg)

连接2 3：后来2号发现自己能力有限，就投奔了3号。

解读一下数组内的含义：arr[1]==2,表示元素1的大哥是2号；arr[2]==3,表示元素2的大哥是3号。所以元素1的老大哥其实是3号。

![img](https://gitee.com/lbw427/blog-img/raw/master//img/1251417-20171211113547696-1454818681.jpg)

连接1和4：1号想和4号成为一个小组，怎么办呢？只需要让自己的‘最终老大哥’加入到4号所在的小组就行了。所以1号就撮合自己的‘最终老大’3号，让3号认4号为大哥。（4号是什么来头呢？4号是4号的‘最终老大’,4号自己就是自己的最终老大）。从此1 2 3 4这些元素都是一家子了。

![img](https://gitee.com/lbw427/blog-img/raw/master//img/1251417-20171211193123555-1960092682.jpg)

上面这个情况其实用树形结构表示就更加形象了：

连接1 和 4就是把1所在的根指向4。

![img](https://gitee.com/lbw427/blog-img/raw/master//img/1251417-20171211115334415-262808633.jpg)

 连接1 5 ： 1号想和5号成为一个小组，怎么办呢？只需要让自己的‘最终老大哥’加入到5号所在的小组就行了。所以1号就撮合自己的‘最终老大’4号，让4号认6号为大哥。（6号是什么来头呢？6号是5号的‘最终老大’）。如下图所示。从此1 2 3 4 5 6这些元素都是一家子了。![img](https://gitee.com/lbw427/blog-img/raw/master//img/1251417-20171211122119993-118294115.jpg)

# 快Union 快Find

## 基于重量

其实上面讲的union函数，没有采取合理的手段去进行合并。每次都以第二个元素为主，每次合并两个集合都让第二个元素的根来继续充当合并之后的根。这样很可能达到线性的链表的状态。

合理的合并方法是根据哪个手下多来看

```java
public class UnionFind {
    private int[] parent;
    private int[] weight;
    private int size;
 
    public UnionFind(int size) {
        this.parent = new int[size];
        this.weight = new int[size];
        this.size = size;
        for (int i = 0; i < size; i++) {
            this.parent[i] = i;
            this.weight[i] = 1;
        }
    }

    public int find(int element) {
        while (element != parent[element]) {
            element = parent[element];
        }
        return element;
    }
    
    public boolean isConnected(int firstElement, int secondElement) {
        return find(firstElement) == find(secondElement);
    }
    
    public void unionElements(int firstElement, int secondElement) {
        int firstRoot = find(firstElement);
        int secondRoot = find(secondElement);

        //如果已经属于同一个集合了，就不用再合并了。
        if (firstRoot == secondRoot) {
            return;
        }

        if (weight[firstRoot] > weight[secondRoot]) {
            parent[secondRoot] = firstRoot;
            weight[firstRoot] += weight[secondRoot];
        } else {//weight[firstRoot] <= weight[secondRoot]
            parent[firstRoot] = secondRoot;
            weight[secondRoot] += weight[firstRoot];
        }
    }
    
```

![img](https://gitee.com/lbw427/blog-img/raw/master//img/1251417-20171211155924071-1698868742.jpg)

# 快Union 快Find

## 基于高度

上面介绍的是，当两个集合合并时，谁的重量大，谁就来当合并之后的根。是比以前好多了。但还是有并查集深度太深的问题。并查集越深，就越接近线性，find函数就越接近O(n)

所以有了这种基于高度的union。合并时，谁的深度深，谁就是新的根。这样集合的深度最多是最大深度的集合的深度，而不会让深度增加。

```java
public class UnionFind {
    private int[] parent;
    private int[] height;
    int size;
 
    public UnionFind(int size) {
        this.size = size;
        this.parent = new int[size];
        this.height = new int[size];
        for (int i = 0; i < size; i++) {
            parent[i] = i;
            height[i] = 1;
        }
    }
 
    public int find(int element) {
        while (element != parent[element]) {
            element = parent[element];
        }
        return element;
    }
 
    public boolean isConnected(int firstElement, int secondElement) {
        return find(firstElement) == find(secondElement);
    }
 
    public void unionElements(int firstElement, int secondElement) {
        int firstRoot = find(firstElement);
        int secondRoot = find(secondElement);
 
        if (height[firstRoot] < height[secondRoot]) {
            parent[firstRoot] = secondRoot;
            } else if (height[firstRoot] > height[secondRoot]) 
        {
            parent[secondRoot] = firstRoot;
        } else//两个集合的高度一样时，随便一个指向另一个，心集合高度+1
        {
            parent[firstRoot] = secondRoot;
            height[secondRoot] += 1;
        }
    }
}
```

## 例子

![img](https://gitee.com/lbw427/blog-img/raw/master//img/1251417-20171211161906884-1008955698.jpg)

而两个集合高度相等时，哪个根来当新集合的根已经无所谓了，只需要让其中一个指向另一个就好了。然后会发现深度加了一层，所以新集合的根的高度就得+1

![img](https://gitee.com/lbw427/blog-img/raw/master//img/1251417-20171211163639946-1899393365.jpg)

# 路径压缩

路径压缩就是处理并查集中的深的结点。

实现方法很简单，就是在find函数里加上一句 parent[element] = parent[parent[element]];就好了，就是让当前结点指向自己父亲的父亲，减少深度，同时还没有改变根结点的weight(非根节点的weight改变了无所谓)。

注：只能在基于重量的并查集上改find函数，而不能在基于高度的并查集上采用这种路径压缩。因为路径压缩后根的重量不变，但高度会变，然而高度改变后又不方便重新计算。

```java
public int find(int element) {
    while (element != parent[element]) {
        parent[element] = parent[parent[element]];
        element = parent[element];
    }
    return element;
}
```


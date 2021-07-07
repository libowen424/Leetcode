# 概述

[字符串匹配](https://en.wikipedia.org/wiki/String_searching_algorithm)是计算机的基本任务之一。

举例来说，有一个字符串"BBC ABCDAB ABCDABCDABDE"，我想知道，里面是否包含另一个字符串"ABCDABD"？

许多算法可以完成这个任务，[Knuth-Morris-Pratt算法](https://en.wikipedia.org/wiki/Knuth–Morris–Pratt_algorithm)（简称KMP）是最常用的之一。

# 暴力匹配

有一个文本串S，和一个模式串P，现在要查找P在S中的位置，怎么查找呢？

如果用暴力匹配的思路，并假设现在文本串S匹配到 i 位置，模式串P匹配到 j 位置，则有：

* 如果当前字符匹配成功（即S[i] == P[j]），则i++，j++，继续匹配下一个字符；
* 如果失配（即S[i]! = P[j]），令i = i - (j - 1)，j = 0。相当于每次匹配失败时，i 回溯，j 被置为0。

 举个例子，如果给定文本串S“BBC ABCDAB ABCDABCDABDE”，和模式串P“ABCDABD”，现在要拿模式串P去跟文本串S匹配，整个过程如下所示：

![img](https://gitee.com/lbw427/blog-img/raw/master//img/bg2013050103.png)

![img](https://gitee.com/lbw427/blog-img/raw/master//img/bg2013050104.png)

![img](https://gitee.com/lbw427/blog-img/raw/master//img/bg2013050105.png)

![img](https://gitee.com/lbw427/blog-img/raw/master//img/bg2013050106.png)

。。。

![img](https://gitee.com/lbw427/blog-img/raw/master//img/bg2013050107.png)

![img](https://gitee.com/lbw427/blog-img/raw/master//img/bg2013050108.png)

# KMP算法

与朴素算法不同，朴素算法是当遇到不匹配字符时，向后移动一位继续匹配；

而KMP算法是当遇到不匹配字符时，不是简单的向后移一位字符，而是根据前面**已匹配的字符数和模式串前缀和后缀的最大相同字符串长度**数组next的元素来确定向后移动的位数，所以KMP算法的时间复杂度比朴素算法的要少，并且是线性时间复杂度，即预处理时间复杂度是O(m)，匹配时间复杂度是O(n)。

在上面的例子中

![img](http://www.ruanyifeng.com/blogimg/asset/201305/bg2013050107.png)

当出现不同的字符时，不是简单地从当前匹配的下一位开始

因为你其实知道前面六个字符"ABCDAB"的情况的。

我们可以根据前缀和后缀的最长共有元素长度来决定后移的位数

 "前缀"指除了最后一个字符以外，一个字符串的全部头部组合；"后缀"指除了第一个字符以外，一个字符串的全部尾部组合。

"部分匹配值"就是"前缀"和"后缀"的最长的共有元素的长度。

以"ABCDABD"为例：

```
　　－　"A"的前缀和后缀都为空集，共有元素的长度为0；

　　－　"AB"的前缀为[A]，后缀为[B]，共有元素的长度为0；

　　－　"ABC"的前缀为[A, AB]，后缀为[BC, C]，共有元素的长度0；

　　－　"ABCD"的前缀为[A, AB, ABC]，后缀为[BCD, CD, D]，共有元素的长度为0；

　　－　"ABCDA"的前缀为[A, AB, ABC, ABCD]，后缀为[BCDA, CDA, DA, A]，共有元素为"A"，长度为1；

　　－　"ABCDAB"的前缀为[A, AB, ABC, ABCD, ABCDA]，后缀为[BCDAB, CDAB, DAB, AB, B]，共有元素为"AB"，长度为2；

　　－　"ABCDABD"的前缀为[A, AB, ABC, ABCD, ABCDA, ABCDAB]，后缀为[BCDABD, CDABD, DABD, ABD, BD, D]，共有元素的长度为0。
```



![img](https://gitee.com/lbw427/blog-img/raw/master//img/bg2013050109.png)

"部分匹配"的实质是，有时候，字符串头部和尾部会有重复。比如，"ABCDAB"之中有两个"AB"，那么它的"部分匹配值"就是2（"AB"的长度）。搜索词移动的时候，第一个"AB"向后移动4位（字符串长度-部分匹配值），就可以来到第二个"AB"的位置。

即：失配时，**模式串向右移动的位数为：已匹配字符数 - 失配字符的上一位字符所对应的最大长度值**

![img](https://gitee.com/lbw427/blog-img/raw/master//img/20140721223539765)

i就不用动了

我们发现，当匹配到一个字符失配时，其实没必要考虑当前失配的字符，更何况我们每次失配时，都是看的**失配字符的上一位字符**对应的最大长度值。如此，便引出了next 数组（可以解决第一位失配的问题，最后一位的最大匹配数没有意义，因为要不已经匹配成功，要不就继续从上一位的最大匹配数继续）。

即失配时，模式串向右移动的位数为：失配字符所在位置 - 失配字符对应的next 值

## 算法详解

KMP算法的核心就是求next数组next数组求解方法：

- next[0] = -1。
- 如果已知next[j] = k,如何求出next[j+1]呢?具体算法如下:
  1. 如果p[j] = p[k], 则next[j+1] = next[k] + 1;
  2. 如果p[j] != p[k], 则令k=next[k],如果此时p[j]==p[k],则next[j+1]=k+1,如果不相等,则继续递归前缀索引,令 k=next[k],继续判断,直至k=-1(即k=next[0])或者p[j]=p[k]为止

KMP的算法流程：

* 假设现在文本串S匹配到 i 位置，模式串P匹配到 j 位置
  * 如果j = -1，或者当前字符匹配成功（即S[i] == P[j]），都令i++，j++，继续匹配下一个字符；
  * 如果j != -1，且当前字符匹配失败（即S[i] != P[j]），则令 i 不变，j = next[j]。此举意味着失配时，模式串P相对于文本串S向右移动了j - next [j] 位。
    * 换言之，当匹配失败时，模式串向右移动的位数为：失配字符所在位置 - 失配字符对应的next 值，即**移动的实际位数为：j - next[j]**，且此值大于等于1。

 给定字符串“ABCDABD”，可求得它的next 数组如下：

![img](https://gitee.com/lbw427/blog-img/raw/master//img/20140721230250468)

对比：

![img](https://gitee.com/lbw427/blog-img/raw/master//img/20140728110939595)

 把next 数组跟之前求得的最大长度表对比后，不难发现，**next 数组相当于“最大长度值” 整体向右移动一位，然后初始值赋为-1**。

## 优化next

如果用之前的next 数组方法求模式串“abab”的next 数组，可得其next 数组为-1 0 0 1（0 0 1 2整体右移一位，初值赋为-1），当它跟下图中的文本串去匹配的时候，发现b跟c失配，于是模式串右移j - next[j] = 3 - 1 =2位。

![img](https://gitee.com/lbw427/blog-img/raw/master//img/aHR0cDovL2hpLmNzZG4ubmV0L2F0dGFjaG1lbnQvMjAxMTA2LzE0LzgzOTQzMjNfMTMwODA3NTg1OVpmdWUuanBn)

右移2位后，b又跟c失配。事实上，因为在上一步的匹配中，已经得知p[3] = b，与s[3] = c失配，而右移两位之后，让p[ next[3] ] = p[1] = b 再跟s[3]匹配时，必然失配。问题出在哪呢？

![img](https://gitee.com/lbw427/blog-img/raw/master//img/aHR0cDovL2hpLmNzZG4ubmV0L2F0dGFjaG1lbnQvMjAxMTA2LzE0LzgzOTQzMjNfMTMwODA3NTg1OTFreVYuanBn)

  问题出在不该出现p[j] = p[ next[j] ]。

为什么呢？理由是：当p[j] != s[i] 时，下次匹配必然是p[ next [j]] 跟s[i]匹配，如果p[j] = p[ next[j] ]，必然导致后一步匹配失败（因为p[j]已经跟s[i]失配，然后你还用跟p[j]等同的值p[next[j]]去跟s[i]匹配，很显然，必然失配）

```java
//优化过后的next 数组求法
void GetNextval(char* p, int next[])
{
	int pLen = strlen(p);
	next[0] = -1;
	int k = -1;
	int j = 0;
	while (j < pLen - 1)
	{
		//p[k]表示前缀，p[j]表示后缀  
		if (k == -1 || p[j] == p[k])
		{
			++j;
			++k;
			//较之前next数组求法，改动在下面4行
			if (p[j] != p[k])
				next[j] = k;   //之前只有这一行
			else
				//因为不能出现p[j] = p[ next[j ]]，所以当出现时需要继续递归，k = next[k] = next[next[k]]
				next[j] = next[k];
		}
		else
		{
			k = next[k];
		}
	}
}
```



对于优化后的next数组可以发现一点：如果模式串的后缀跟前缀相同，那么它们的next值也是相同的，例如模式串abcabc，它的前缀后缀都是abc，其优化后的next数组为：-1 0 0 -1 0 0，前缀后缀abc的next值都为-1 0 0。
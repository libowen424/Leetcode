java int 类整数的最大值是 2 的 31 次方 - 1 = **2147483648 - 1** = **2147483647**

bai -231——231-1，即-2147483648——2147483647

可以用 **Integer.MAX_VALUE** 表示它，即 int value = Integer.MAX_VALUE;

Integer.MAX_VALUE + 1 = Integer.MIN_VALUE = **-2147483648**

再大的数就要用 long （最大值 2 的 63 次方 - 1 ）或者 BigDecimal 表示





前缀和、前缀积也称前缀和数组，前缀积数组。

给一数组A，

前缀和：新建一数组B，数组中每一项B[i]保存A中[0…i]的和；

后缀和：新建一数组B，数组中每一项B[i]保存A中[i…n-1]的和；

前缀积：新建一数组B，数组中每一项B[i]保存A中[0…i]的积；

后缀积：新建一数组B，数组中每一项B[i]保存A中[i…n-1]的积；

前缀和数组比原数组长度+1，前缀和第一项为0
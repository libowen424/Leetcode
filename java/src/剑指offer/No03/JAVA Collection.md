![java Collection](https://gitee.com/lbw427/blog-img/raw/master//img/java%20Collection.png)

Collection 中的常用方法

```
boolean add(Object e) 向集合中添加元素
int size() 获取集合中元素的个数
void clear() 清空集合
boolean contains(Object o) 判断当前集合中是否包含元素,包含返回true，不包含返回false
boolean remove(object o) 删除集合中的某个元素。
boolean isEmpty() 判断该集合中元素的个数是否为0
object[] toArray()调用这个方法可以把集合转换成数组


```

Map接口中常用的方法：

```
V put(K key, V value) //向Map集合中添加键值对
V get(object key) //通过key获取value
boolean containsKey(Object key) //判断Map中是否包含某个key
boolean containsValue(Object value) //判断Map中是否包含某个value
void clear() //清空Map集合
boolean isEmpty() //判断Map集合中元素个数是否为0
int size() //获取Map集合中键值对的个数
V remove(Object key) //通过key删除键值对
Set<K> keySet() //获取Map集合所有的key(所有的键是一个set集合)
Collection<V> values() //获取Map集合中所有的value,返回一MCollection
Set<Map.Entry<K,V>> entrySet() //将Map集合转换成set集合
```



https://blog.csdn.net/qq_46539113/article/details/115170437
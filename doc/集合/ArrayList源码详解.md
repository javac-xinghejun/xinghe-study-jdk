# ArrayList源码分析

![ArrayList](https://gitee.com/javac_xinghejun/img/raw/master/image-20220426225631259.png)

本文分为以下几个部分：

1. ArrayList的底层实现
2. ArrayList的
3. 优化
4. 跟其他集合的对比



## ArrayList的底层实现

在谈实现之前，先想一下ArrayList是什么，用来干什么。

ArrayList是一个集合，集合就是要装东西的。那么它肯定有**容量**、和把东西（元素）放进去的和取出来的方法。这里就先从这几个问题开始说起。

### ArrayList的基本属性

先看代码：

```java
public class ArrayList<E> extends AbstractList<E>
        implements List<E>, RandomAccess, Cloneable, java.io.Serializable
{
    /**
     * 序列号
     */
    private static final long serialVersionUID = 8683452581122892189L;

    /**
     * Default initial capacity.
     * 初始容器容量
     */
    private static final int DEFAULT_CAPACITY = 10;

    /**
     * Shared empty array instance used for empty instances.
     * 空实例使用的数组
     */
    private static final Object[] EMPTY_ELEMENTDATA = {};

    /**
     * Shared empty array instance used for default sized empty instances. We
     * distinguish this from EMPTY_ELEMENTDATA to know how much to inflate when
     * first element is added.
     * 用于默认大小的空实例的共享空数组实例。我们将其与 EMPTY_ELEMENTDATA 区分开来，以了解添加第一个元素时要扩容多少。
     */
    private static final Object[] DEFAULTCAPACITY_EMPTY_ELEMENTDATA = {};

    /**
     * The array buffer into which the elements of the ArrayList are stored.
     * The capacity of the ArrayList is the length of this array buffer. Any
     * empty ArrayList with elementData == DEFAULTCAPACITY_EMPTY_ELEMENTDATA
     * will be expanded to DEFAULT_CAPACITY when the first element is added.
     * 存储 ArrayList 元素的数组缓冲区。 
     * ArrayList 的容量就是这个数组缓冲区的长度。
     * 当添加第一个元素时，任何具有 elementData == DEFAULTCAPACITY_EMPTY_ELEMENTDATA 的空 ArrayList 都将扩展为 DEFAULT_CAPACITY。
     */
    transient Object[] elementData; // non-private to simplify nested class access

    /**
     * The size of the ArrayList (the number of elements it contains).
     * 当前ArrayList里面的元素数量
     * @serial
     */
    private int size;
}
```



## 附录ArrayList中的方法汇总

![ArrayList方法](https://gitee.com/javac_xinghejun/img/raw/master/ArrayList%E6%96%B9%E6%B3%95%20(1).png)


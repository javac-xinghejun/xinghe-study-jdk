# ArrayList源码分析

![ArrayList](https://gitee.com/javac_xinghejun/img/raw/master/image-20220426225631259.png)

本文分为以下几个部分：

1. ArrayList的底层实现
2. ArrayList内部方法汇总



## ArrayList的底层实现

在谈实现之前，先想一下ArrayList是什么，用来干什么。我们知道，List是有序列表，可以放可重复的元素和null。那么ArrayList顾名思义就是用数组实现的list。ArrayList是一个集合，集合就是要装东西的。那么它肯定有**容量**、和**把东西（元素）放进去的和取出来的方法**。这里就先从这几个问题开始说起。

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

抛开序列号不管，这几个成员变量分别是：默认数组、默认空数组、默认数组的初始容量、盛放元素的数组和元素个数。

## 放入元素

下面以add(E)方法来看看ArrayList的底层实现和扩容。

```java
public boolean add(E e) {
    ensureCapacityInternal(size + 1);  // Increments modCount!!
    elementData[size++] = e;
    return true;
}
```



在放入元素的过程中，首先会判断容器的容量是否够，如果不够则进行一次扩容：

```java
private void ensureCapacityInternal(int minCapacity) {
    ensureExplicitCapacity(calculateCapacity(elementData, minCapacity));
}

private static int calculateCapacity(Object[] elementData, int minCapacity) {
    if (elementData == DEFAULTCAPACITY_EMPTY_ELEMENTDATA) {
        return Math.max(DEFAULT_CAPACITY, minCapacity);
    }
    return minCapacity;
}

private void ensureExplicitCapacity(int minCapacity) {
    modCount++;// 当前ArrayList结构修改次数+1

    // overflow-conscious code
    if (minCapacity - elementData.length > 0)
        grow(minCapacity);
}

private void grow(int minCapacity) {
    // overflow-conscious code
    int oldCapacity = elementData.length;
    int newCapacity = oldCapacity + (oldCapacity >> 1); // 新的容量是在原容量的大小上增加1/2。 >> 原数据/2，即：10 >> 1 == 5
    if (newCapacity - minCapacity < 0)
        newCapacity = minCapacity; // 如果扩充后的容量还是比给定的最小容量小，则直接把新容量设置为给定的最小容量。即：每次只会扩充一次
    if (newCapacity - MAX_ARRAY_SIZE > 0)
        newCapacity = hugeCapacity(minCapacity);
    // minCapacity is usually close to size, so this is a win:
    elementData = Arrays.copyOf(elementData, newCapacity);
}
```

这里可以看出，扩容逻辑主要在这里完成的。每次扩容到原来的1.5倍，设定的默认最大数组长度是 `Integer.MAX_VALUE - 8` ，一旦分配的长度超过这个数，则分配的数组长度最大为 ` Integer.MAX_VALUE `。代码的最后可以看出，其实现是通过调用Arrays.copyOf()方法来实现的。以上就是ArrayList添加元素及扩容的主要实现。

### 移除元素

接下来看看remove()方法。源码如下：

```java
    public E remove(int index) {
        // 检查是否越界
        rangeCheck(index);

        modCount++;
        // 取出原来的元素，这个位置往后的元素需要移动
        E oldValue = elementData(index);
        // index的前一位
        int numMoved = size - index - 1;
        if (numMoved > 0)
            // 从原数组的起始位置开始，把numMoved个元素复制到目标数组中去
            System.arraycopy(elementData, index+1, elementData, index,
                             numMoved);
        // 最后一位复制为null，为了更好地垃圾回收
        elementData[--size] = null; // clear to let GC do its work

        return oldValue;
    }
```



## 总结

可知ArrayList的底层是通过对数组进行扩容和复制来实现数据的加入和移除的。





## 附录ArrayList中的方法汇总

![ArrayList方法](https://gitee.com/javac_xinghejun/img/raw/master/ArrayList%E6%96%B9%E6%B3%95%20(1).png)


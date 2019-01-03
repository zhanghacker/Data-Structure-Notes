package com.zxxdream.svgdemo;

/**
 * ProjectName:  Array
 * Author:  xxzhang
 * CreateAt:  2018/12/26  17:37
 * Description:  数据结构之数组自由扩容
 * Copyright © itzxx Inc. All Rights Reserved
 */
public class Array<E> {

    private int capacity;//数组容量
    private int size;
    private E[] data;

    /**
     * 无参数的构造函数，默认数组的容量capacity=10
     */
    public Array(){
        this(10);
    }

    public Array(int capacity) {
        this.capacity = capacity;
        crateArray(capacity);
    }

    /**
     * 创建数组
     *
     * @param capacity 容量
     */
    public void crateArray(int capacity) {
        this.data = (E[]) new Object[capacity];
    }

    /**
     * 数组数据长度
     *
     * @return
     */
    public int getSize() {
        return size;
    }

    /**
     * 返回数组是否为空
     *
     * @return
     */
    public boolean isEmpty(){
        return size == 0;
    }

    /**
     * 添加到尾部
     *
     * @param e 数据
     */
    public void addLast(E e) {
        add(size, e);
    }

    /**
     * 添加到头部
     *
     * @param e 数据
     */
    public void addFirst(E e) {
        add(0, e);
    }

    /**
     * 修改数据
     *
     * @param index 索引
     * @param e     数据
     */
    public void set(int index, E e) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("index out of bound");

        this.data[index] = e;
    }

    /**
     * 获取数据
     *
     * @param index 索引
     * @return 返回数据
     */
    public E get(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("index out of bound");
        return this.data[index];
    }

    /**
     * 添加统一处理
     *
     * @param index 索引
     * @param e     数据
     */
    public void add(int index, E e) {
        //        if (size == capacity)
        //            throw new IllegalArgumentException("add is failed,array is full");

        //当数据是容量的饱和就自动扩容
        if (size == capacity) {//根据java底层默认1.5倍
            resise((int) (1.5 * capacity));
        }
        for (int i = size - 1; i >= index; i--) {
            this.data[i + 1] = this.data[i];
        }
        size++;


        this.data[index] = e;
    }

    /**
     * 是否包含数据
     *
     * @param e
     * @return
     */
    public boolean contains(E e) {
        for (int i = 0; i < data.length; i++) {
            if (data[i].equals(e))
                return true;
        }
        return false;
    }

    /**
     * 查找数据下标，未有数据返回-1
     *
     * @param e
     * @return
     */
    private int find(E e) {
        for (int i = 0; i < size; i++) {
            if (this.data[i].equals(e)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 根据索引删除数据
     *
     * @param index 下标索引
     * @return 被删除的数据
     */
    public E romove(int index) {

        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Remove failed. Index is illegal.");
        }

        E e = data[index];
        for (int i = index + 1; i < size; i++) {
            data[i - 1] = data[i];
        }
        size--;

        //当数据是容量的1/3就缩小容器
        if (size == (int)(0.3* capacity+0.5)) {
            resise((int) (0.5 * capacity));
        }
        return e;
    }


    /**
     * 根据元素删除数据
     *
     * @param e 要删除的数据
     */
    public void romoveElement(E e) {
        int index = find(e);
        if (index != -1) {
            romove(index);
        }
    }

    /**
     * 删除收个数据
     */
    public void removeFirst() {
        romove(0);
    }

    /**
     * 删除最后一个数据
     */
    public void removeLast() {
        romove(size - 1);
    }

    /**
     * 扩容
     *
     * @param capacity 容量大小
     */
    public void resise(int capacity) {
        E[] newData = (E[]) new Object[capacity];

        for (int i = 0; i < size; i++) {
            newData[i] = data[i];
        }
        data = newData;
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("数组当前数据个数：%s  ", +size));
        stringBuilder.append(String.format("数组当前数据容量：%s\n", +capacity));
        stringBuilder.append("[");
        for (int i = 0; i < size; i++) {
            stringBuilder.append(data[i]);
            if (i != size - 1)
                stringBuilder.append(",");
        }
        stringBuilder.append("]");

        return stringBuilder.toString();
    }
}

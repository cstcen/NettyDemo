package com.cx.udp.util;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by cx on 2018-3-6.
 */
public class CircularQueue<T> implements Serializable {

    private BlockingQueue<T> blockingQueue;

    public CircularQueue(int maxNum) {
        this.blockingQueue = new ArrayBlockingQueue<T>(maxNum);
    }

    /**
     * 取走BlockingQueue里排在首位的对象,若BlockingQueue为空,阻断进入等待状态直到BlockingQueue有新的数据被加入;
     * @return
     * @throws InterruptedException
     */
    public T take() throws InterruptedException {
        return this.blockingQueue.take();
    }

    /**
     * 一次性从BlockingQueue获取所有可用的数据对象，通过该方法，可以提升获取数据效率；不需要多次分批加锁或释放锁。
     * @return
     * @throws InterruptedException
     */
    public List<T> pollBulk() throws InterruptedException {
        List<T> objList = new LinkedList<T>();
        this.blockingQueue.drainTo(objList);
        return objList;
    }

    /**
     * @param maxItem 指定获取数据的个数
     * @return
     * @throws InterruptedException
     */
    public List<T> poolBulk(int maxItem) throws InterruptedException {
        List<T> objList = new LinkedList<T>();
        this.blockingQueue.drainTo(objList, maxItem);
        return objList;
    }

    public boolean offer(T obj) {
        return this.blockingQueue.offer(obj);
    }

    public int size() {
        return this.blockingQueue.size();
    }
}

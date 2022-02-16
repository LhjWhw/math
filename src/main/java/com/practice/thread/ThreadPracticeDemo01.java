package com.practice.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 多线程例题一：
 * 有A、B、C 三个现场， A输出A，B输出B，C输出C，要求同时启动三个线程，按顺序输出ABC，循环10次，如何设计？
 */
public class ThreadPracticeDemo01 {

    public static void main(String[] args) {
        final Lock lock = new ReentrantLock();
        Thread a = new Thread(new PrintfABCThread("A", lock, 0));
        Thread b = new Thread(new PrintfABCThread("B", lock, 1));
        Thread c = new Thread(new PrintfABCThread("C", lock, 2));

        a.start();
        b.start();
        c.start();

    }

}

class PrintfABCThread implements Runnable {
    private Lock lock;
    private String name;
    private Integer flag;

    public static int count = 0;

    public static final int MAX = 30;

    public PrintfABCThread(String name, Lock lock, Integer flag) {
        this.name = name;
        this.lock = lock;
        this.flag = flag;
    }

    @Override
    public void run() {
        while (true) {
            lock.lock();
            if (count >= MAX) {
                lock.unlock();
                return;
            }
            if (count % 3 == flag) {
                System.out.println(name);
                count++;
            }
            lock.unlock();
        }
    }
}

package com.practice.thread;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * 线程常用api使用
 * sleep、wait、join、yield、notify、notifyAll
 */

@Slf4j
public class ThreadApiTest {

    static final Object lock = new Object();

    @Test
    public void sleepTest() throws Exception {
        /**
         * sleep();使线程休眠，会将运行中的线程进入阻塞状态。当休眠时间结束后，重新争抢cpu的时间片继续运行
         */
        Thread.sleep(2000);
        System.out.println("abc");
    }

    @Test
    public void waitTest() {
        /**
         * wait() 释放锁 进入 waitSet 可传入时间，如果指定时间内未被唤醒 则自动唤醒
         * notify()随机唤醒一个waitSet里的线程
         * notifyAll()唤醒waitSet中所有的线程
         */

        new Thread(() -> {
            synchronized (lock) {
                log.info("开始执行");
                try {
                    // 同步代码内部才能调用
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.info("继续执行核心逻辑");
            }
        }, "t1").start();

        new Thread(() -> {
            synchronized (lock) {
                log.info("开始执行");
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.info("继续执行核心逻辑");
            }
        }, "t2").start();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("开始唤醒");

        synchronized (lock) {
            // 同步代码内部才能调用
            lock.notifyAll();
        }

    }

    @Test
    public void joinTest() throws Exception{
        /**
         * join();指调用该方法的线程进入阻塞状态，等待某线程执行完成后恢复运行
         */
        int[] r = {0};
        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(1000);
                r[0] = 10;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread.start();
        thread.join();
        log.info("r:{}", r[0]);

    }

    @Test
    public void yieldTest() {
        /**
         * yield();线程礼让。当一个线程调用来yield()方法后，会暂停当前线程，礼让给优先级不比自己小的其他线程去获取cpu，
         * 当然有可能cpu不会切换，继续执行当前线程，因为可能其他的线程优先级都比当前线程低。
         */

        Runnable r1 = () -> {
            int count = 0;
            for (int i = 0; i < 10; i++) {
                log.info("---- 1>" + count++);
            }
        };

        Runnable r2 = () -> {
            int count = 0;
            for (int i = 0; i < 10; i++) {
                Thread.yield();
                log.info("---- 2>" + count++);
            }
        };

        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r2);
        t1.start();
        t2.start();

    }


}

package com.practice.thread;

/**
 * 线程安全问题案例
 */
public class ThreadSafeDemo01 {

    private static int count = 0;

    // 保证统一对象加锁才有效
    private static Object lock = new Object();

    public static void main(String[] args) throws Exception{
        Thread thread1 = new Thread(() -> {
           for (int i = 0; i < 5000; i++) {
               synchronized (lock){
                   count++;
               }
           }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 5000; i++) {
                synchronized (lock){
                    count--;
                }
            }
        });

        thread1.start();
        thread2.start();

        //让thread1、thread2都执行完
        thread1.join();
        thread2.join();

        System.out.println(count);
    }

}

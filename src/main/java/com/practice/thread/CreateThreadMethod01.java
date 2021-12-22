package com.practice.thread;


import lombok.extern.slf4j.Slf4j;

/**
 * 创建线程方式一：继承Thread类
 */
@Slf4j
public class CreateThreadMethod01 extends Thread {
    @Override
    public void run() {
        log.info("我是继承Thread的任务");
    }
}


class ThreadTest{
    public static void main(String[] args) {
        //使用 new T().start() 方式启动
        CreateThreadMethod01 threadMethod01 = new CreateThreadMethod01();
        CreateThreadMethod01 threadMethod02 = new CreateThreadMethod01();
        threadMethod01.start();
        threadMethod02.start();

        //使用匿名内部类方式启动
        /*Thread thread = new Thread() {
            @Override
            public void run() {
                super.run();
            }
        };
        thread.start();*/
    }

}

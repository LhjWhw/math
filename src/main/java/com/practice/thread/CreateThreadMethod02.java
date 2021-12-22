package com.practice.thread;

import lombok.extern.slf4j.Slf4j;

/**
 * 创建线程方式二：实现runnable接口，重写run方法
 */
@Slf4j
public class CreateThreadMethod02 implements Runnable {
    @Override
    public void run() {
        log.info("我是实现Runnable的任务");
    }
}

@Slf4j
class ThreadTest2{
    public static void main(String[] args) {

        //方式一
        new Thread(new CreateThreadMethod02()).start();

        //方式二
        /*new Thread(new Runnable() {
            @Override
            public void run() {
                log.info("我是Runnable匿名内部类的任务");
            }
        }).start();*/

        //方式三
        //new Thread(() -> log.info("我是Runnable匿名内部类的任务")).start();
    }

}
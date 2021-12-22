package com.practice.thread;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * 创建线程方式三-实现callable接口(需要配合FutureTask)
 */
@Slf4j
public class CreateThreadMethod03 implements Callable {
    @Override
    public Object call() throws Exception {
        log.info("我是实现Callable的任务");
        return "success";
    }
}

@Slf4j
class ThreadTest3{
    public static void main(String[] args) throws Exception{
        FutureTask task = new FutureTask(new CreateThreadMethod03());
        new Thread(task).start();
        log.info("执行返回值：" + task.get());
    }

}
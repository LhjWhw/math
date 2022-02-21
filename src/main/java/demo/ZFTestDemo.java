package demo;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 卓繁测试题：
 *  线程a不停的往队列里存放数据，线程b不停的往队列中获取数据（只要队列不为空）
 */
public class ZFTestDemo{


    public static Queue<String> queue = new ArrayBlockingQueue(100000000);
    public static AtomicInteger count = new AtomicInteger(0);

    public static void main(String[] args) throws Exception{


        Thread threadA = new Thread(()->{
            while(true){
                count.addAndGet(1);
                queue.add("a" + count);
            }
        });


        threadA.start();

        Thread threadB = new Thread(() -> {
            while(true){
                if(queue != null && queue.size() > 0){
                    System.out.println("获取list队列中的数据：" + queue.remove());
                }
            }
        });

        threadB.start();

    }

}

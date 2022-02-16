package com.practice.thread;

/**
 * ExecuteService 是一个接口的概念，ThreadPoolExecutor才是具体的落地实现，是最常用的创建线程池方案，也是阿里推荐使用的创建线程方案，务必掌握！
 *
 */

import java.util.concurrent.*;

/**
 * 线程创建方式四-使用线程池
 */
public class CreateThreadMethod04 {

    public static void main(String[] args) throws Exception {

        // 获取本机CPU核心数目，8核
        System.out.println(Runtime.getRuntime().availableProcessors());

        /**
         * 1、定长线程池
         * 特点：只有核心线程，线程数量固定，执行完立即回收，任务队列为链表结构的有界队列
         * 应用场景：控制线程最大并发数
         */
        ExecutorService pool1 = Executors.newFixedThreadPool(10);

        /**
         * 2、定时线程池
         * 特点：核心线程数量固定，非核心线程数量无限，执行完闲置 10ms 后回收，任务队列为延时阻塞队列
         * 应用场景：执行定时或周期性的任务
         */
        ScheduledExecutorService pool2 = Executors.newScheduledThreadPool(2);

        /**
         * 3、可缓存线程池
         * 特点：无核心线程，非核心线程数量无限，执行完闲置 60s 后回收，任务队列为不存储元素的阻塞队列
         * 应用场景：执行大量、耗时少的任务
         */
        ExecutorService pool3 = Executors.newCachedThreadPool();

        /**
         * 4、单线程化线程池
         * 特点：只有 1 个核心线程，无非核心线程，执行完立即回收，任务队列为链表结构的有界队列
         * 应用场景：不适合并发但可能引起 IO 阻塞性及影响 UI 线程响应的操作，如数据库操作、文件操作等
         */
        ExecutorService pool4 = Executors.newSingleThreadExecutor();

        /**
         * 5、ThreadPoolExecutor原生创建线程池（推荐使用！！）
         * 上述的四种方式已经不推荐使用，其内部也是通过ThreadPoolExecutor来创建线程出来的，现在官方推荐使用ThreadPoolExecutor直接来创建线程池，
         * 因此就需要充分了解线程池参数的意义和线程池创建的规则，根据硬件和业务的需要创建出更为合适的线程池！！
         *
         * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!*****线程池的七大重要参数*****!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
         * 1、corePoolSize：核心线程数。线程池中持有的线程数，即使线程空闲，除非设置了允许核心线程过期
         * 2、maximumPoolSize：最大线程数。线程池中允许存放的最大线程数量
         * 3、keepAliveTime：非核心线程等待回收时间。当线程池中的线程数量大于核心线程数的时候，非核心线程数等待执行任务的最大时间，超过这个时间，非核心线程需要被回收
         * 4、unit：非核心线程等待回收时间单位。
         * 5、workQueue：任务队列。用来存放由execute方法提交的实现Runnable接口的任务，Executors框架为我们提供了7种任务队列分别为：（有界、无界、同步移交）
         *      （1）ArrayBlockingQueue：一个由数组结构组成的有界阻塞队列（数组结构可配合指针实现一个环形队列）
         *      （2）LinkedBlockingQueue：一个由链表结构组成的有界阻塞队列，在未指明容量时，容量默认为 Integer.MAX_VALUE
         *      （3）PriorityBlockingQueue：一个支持优先级排序的无界阻塞队列，对元素没有要求，可以实现 Comparable 接口也可以提供 Comparator 来对队列中的元素进行比较。跟时间没有任何关系，仅仅是按照优先级取任务
         *      （4）DelayQueue：类似于PriorityBlockingQueue，是二叉堆实现的无界优先级阻塞队列。要求元素都实现 Delayed 接口，通过执行时延从队列中提取任务，时间没到任务取不出来
         *      （5）SynchronousQueue：一个不存储元素的阻塞队列，消费者线程调用 take() 方法的时候就会发生阻塞，直到有一个生产者线程生产了一个元素，消费者线程就可以拿到这个元素并返回；生产者线程调用 put() 方法的时候也会发生阻塞，直到有一个消费者线程消费了一个元素，生产者才会返回
         *      （6）LinkedBlockingDeque：使用双向队列实现的有界双端阻塞队列。双端意味着可以像普通队列一样 FIFO（先进先出），也可以像栈一样 FILO（先进后出）
         *      （7）LinkedTransferQueue：它是ConcurrentLinkedQueue、LinkedBlockingQueue 和 SynchronousQueue 的结合体，但是把它用在 ThreadPoolExecutor 中，和 LinkedBlockingQueue 行为一致，但是是无界的阻塞队列
         * 6、threadFactory：线程工厂。执行者用来创建新线程，Executors框架为我们提供了默认的线程工厂：DefaultThreadFactory
         * 7、handler：拒绝策略。当线程池和任务队列满了，此时任务执行会阻塞，就需要用到拒绝策略，Executors框架为我们提供了4个拒绝策略，分别为：
         *      （1）AbortPolicy：默认。丢弃任务并抛出 RejectedExecutionException 异常
         *      （2）CallerRunsPolicy：由调用线程处理该任务
         *      （3）DiscardPolicy：丢弃任务，但是不抛出异常。可以配合这种模式进行自定义的处理方式
         *      （4）DiscardOldestPolicy：丢弃队列最早的未处理任务，然后重新尝试执行任务
         *
         *
         *
         *  面试重要知识点：线程池的工作机制
         *
         *  当向线程池中提交任务的时候，会发生以下流程：  核心线程数、任务队列、最大线程数这几个重要判断节点要记住
         *      （1）判断当前的工作线程数是否大于核心线程数，如果不大于，那么直接创建新的核心线程，否则执行（2）
         *      （2）判断当前的任务队列有没有满，如果没有满，则加入到队列中等待执行，如果满了执行（3）
         *      （3）判断当前的工作线程数是否大于线程池的最大线程数，如果不是，那么创建一个线程，核心线程数量以外的线程会在设定好的过期时间后进行回收，如果是，执行（4）
         *      （4）执行设置的拒绝策略
         *
         *
         *  高级面试题：如何把握线程池的参数设置？
         *  依据两个方面：（1）线程池处理的程序是CPU密集还是IO密集；
         *                  CPU密集的话，核心线程数设置为：核数 + 1，IO密集的话，核心线程数设置为：核数 * 2
         *              （2）业务需求量考虑。（每秒任务数多少task？每个任务需要执行多久taskTime？系统容忍的最大响应时间多少resTime？）
         *                  核心线程数：task * taskTime / resTime。
         *
         *                  举个例子：每秒的任务数，假设为500~1000，每个任务花费时间，假设为0.1s，系统允许容忍的最大响应时间，假设为1s
         *                     解： 由公式可以知道核心线程数为：50 ~ 100
         *                          根据8020原则，如果80%的每秒任务数小于800，那么corePoolSize设置为80即可
         *                          那么任务队列大小可以根据公式：核心线程数 * resTime / taskTime = 800
         *
         *                          注意：任务队列的大小切不可使用默认，因为默认为Integer.MAX_VALUE（2的31次方 - 1），过大，如果任务量突增，不能新开线程来执行的话，机会积压在任务队列当中，由此带来的响应时间也会增加。
         *
         *                          此外，我们在生产上一般将核心线程数和最大线程数设置为一样，这样可以减少在任务处理过程中创建线程的开销。
         *
         *                          拒绝策略要视情况而定，如果是不重要的任务，可以直接丢弃，如果任务比较重要可以利用一些缓冲机制来处理。
         *
         *                          超时时间和时间单位一般采用默认即可。
         *
         */

        ThreadPoolExecutor pool = new ThreadPoolExecutor(
                65,
                65,
                60,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(650),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy()
        );

        //使用Runnable接口定义一个任务
        Runnable runnable = () -> System.out.println(Thread.currentThread().getName() + "_Runnable");

        //使用Thread类定义另一个任务
        Thread thread = new Thread(() -> System.out.println(Thread.currentThread().getName() + "_Thread"));

        //使用Callable接口定义一个任务，带返回值
        Callable<String> callable = () -> {
            System.out.println(Thread.currentThread().getName() + "_Callable");
            return "success";
        };

        /**
         * 执行任务: submit() / execute()
         *      (1)submit();内部也执行了execute()方法，有返回值，用Future接受
         *      (2)execute();无返回值
         */
        for (int i = 0; i < 3; i++) {
            pool.execute(runnable);
            pool.execute(thread);
            Future<String> future = pool.submit(callable);
            if ("success".equals(future.get())) {
                System.out.println("ok");
            }
        }

        //关闭线程池
        pool.shutdown();
    }

}

package com.practice.designmode.singleton;

/**
 * 懒汉式单例模式（静态内部类）------- 推荐使用！！
 */
public class LazySingletonInnerClass {

    //构造器私有
    private LazySingletonInnerClass() {}

    public static LazySingletonInnerClass getInstance() {
        return LazyHolder.INSTANCE;
    }

    //静态内部类
    private static class LazyHolder {
        private static final LazySingletonInnerClass INSTANCE = new LazySingletonInnerClass();
    }

}

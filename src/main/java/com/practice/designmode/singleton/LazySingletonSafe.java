package com.practice.designmode.singleton;

/**
 * 懒汉式单例模式（线程安全）
 */
public class LazySingletonSafe {
    //构造器私有
    private LazySingletonSafe() {}

    private static LazySingletonSafe single = null;

    public synchronized static LazySingletonSafe getInstance() {
        if (single == null) {
            single = new LazySingletonSafe();
        }
        return single;
    }

}

package com.practice.designmode.singleton;

/**
 * 懒汉式单例模式（线程不安全）
 */
public class LazySingleton {
    //构造器私有
    private LazySingleton() {}

    private static LazySingleton single = null;

    public static LazySingleton getInstance() {
        if (single == null) {
            single = new LazySingleton();
        }
        return single;
    }
}

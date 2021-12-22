package com.practice.designmode.singleton;

/**
 * 饿汉式单例模式
 */
public class HungrySingleton {

    //构造器私有
    private HungrySingleton() {}

    private static final HungrySingleton single = new HungrySingleton();

    public static HungrySingleton getInstance() {
        return single;
    }

}

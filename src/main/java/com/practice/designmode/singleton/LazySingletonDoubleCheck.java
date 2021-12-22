package com.practice.designmode.singleton;

/**
 * 懒汉式单例模式（双重检查）
 */
public class LazySingletonDoubleCheck {

    //构造器私有
    private LazySingletonDoubleCheck() {}

    //必须加上volatile关键字
    private static volatile LazySingletonDoubleCheck single = null;

    public static LazySingletonDoubleCheck getInstance() {
        if (single == null) {
            synchronized (LazySingletonDoubleCheck.class) {
                if (single == null) {
                    single = new LazySingletonDoubleCheck();
                }
            }
        }
        return single;
    }
}

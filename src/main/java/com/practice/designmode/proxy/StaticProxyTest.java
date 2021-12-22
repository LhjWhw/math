package com.practice.designmode.proxy;

/**
 * 代理模式---静态代理
 */
public class StaticProxyTest {
    public static void main(String[] args) {

        Person zhangsan = new Student("张三");

        Person proxy = new StudentProxy(zhangsan);

        proxy.giveMoney();

    }

}

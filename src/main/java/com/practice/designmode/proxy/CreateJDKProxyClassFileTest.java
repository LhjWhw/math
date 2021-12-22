package com.practice.designmode.proxy;

import sun.misc.ProxyGenerator;

import java.io.FileOutputStream;

/**
 * 获取动态代理生成的代理类class文件
 */
public class CreateJDKProxyClassFileTest {
    public static void main(String[] args) {
        byte[] classFile = ProxyGenerator.generateProxyClass("$Proxy0", Student.class.getInterfaces());
        String path = "/Users/liuhejun/Desktop/StuProxy.class";
        try (
                FileOutputStream fos = new FileOutputStream(path)) {
            fos.write(classFile);
            fos.flush();
            System.out.println("代理类class文件写入成功");
        } catch (
                Exception e) {
            System.out.println("写文件错误");
        }
    }
}

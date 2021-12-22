package com.practice.designmode.proxy.jdk_cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CglibProxy implements MethodInterceptor {
    //需要代理的目标对象
    private Object target;

    //重写拦截方法
    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        System.out.println("Cglib动态代理，监听开始！");
        //方法执行参数：target 目标对象 arr参数数组
        Object result = method.invoke(target,args);
        System.out.println("Cglib动态代理，监听结束！");
        return result;
    }

    //定义获取代理对象的方法
    public UserManager getCglibProxy(Object targetObject) {
        //为目标对象target赋值
        this.target = targetObject;
        Enhancer enhancer = new Enhancer();
        //设置父类,因为Cglib是针对指定的类生成一个子类，所以需要指定父类
        enhancer.setSuperclass(targetObject.getClass());
        //设置回调
        enhancer.setCallback(this);
        //创建并返回代理对象
        Object result = enhancer.create();
        return (UserManager) result;
    }

}
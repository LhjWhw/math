package com.practice.designmode.proxy.jdk_cglib;

/**
 *  jdk/cglib动态代理测试
 */
public class ClientTest {
    public static void main(String[] args) {
        //实例化JDKProxy对象
        JdkProxy jdkProxy = new JdkProxy();
        //获取代理对象
        UserManager userJdk = (UserManager) jdkProxy.getJDKProxy(new UserManagerImpl());
        userJdk.addUser("admin","123456");

        //实例化CglibProxy对象
        CglibProxy cglibProxy = new CglibProxy();
        //获取代理对象
        UserManager userCglib = cglibProxy.getCglibProxy(new UserManagerImpl());
        userCglib.delUser("admin");

        /**
         * JDK和CGLIB动态代理总结
         * JDK动态代理只能对实现了接口的类生成代理，而不能针对类 ，使用的是 Java反射技术实现，生成类的过程比较高效。
         * CGLIB是针对类实现代理，主要是对指定的类生成一个子类，覆盖其中的方法 ，使用asm字节码框架实现，相关执行的过程比较高效，生成类的过程可以利用缓存弥补，因为是继承，所以该类或方法最好不要声明成final
         * JDK代理是不需要第三方库支持，只需要JDK环境就可以进行代理，使用条件:实现InvocationHandler + 使用Proxy.newProxyInstance产生代理对象 + 被代理的对象必须要实现接口
         * CGLib必须依赖于CGLib的类库，但是它需要类来实现任何接口代理的是指定的类生成一个子类，覆盖其中的方法，是一种继承但是针对接口编程的环境下推荐使用JDK的代理；
         */

    }
}
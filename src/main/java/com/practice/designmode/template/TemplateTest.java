package com.practice.designmode.template;

/**
 * 设计模式--模版模式（其实就是利用了子类继承父类如果重新方法的话，会覆盖父类）
 */
public class TemplateTest {
    public static void main(String[] args) {
        Worker it1 = new ITWorker("鸿洋");
        it1.workOneDay();
        Worker it2 = new ITWorker("老张");
        it2.workOneDay();
        Worker hr = new HRWorker("迪迪");
        hr.workOneDay();
        Worker qa = new QAWorker("老李");
        qa.workOneDay();
        Worker pm = new ManagerWorker("坑货");
        pm.workOneDay();
    }
}

package com.practice.designmode.proxy;

public class StudentProxy implements Person {

    private Student student;

    public StudentProxy(Person student) {
        if(student.getClass() == Student.class){
            this.student = (Student) student;
        }
    }

    @Override
    public void giveMoney() {
        //使用代理，进行功能的增强！
        System.out.println("张三学习成绩有进步！！");
        student.giveMoney();
    }
}

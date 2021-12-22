package com.practice.designmode.strategy;

/**
 * 策略模式构建方式：
 *  （1）先创建一个策略接口
 *  （2）不同的策略创建不同的类，去实现这个策略接口
 *  （3）根据具体业务，执行不同的策略实现类(可以通过map、枚举或者spring的beanName去匹配)完成后续动作
 */
public class StrategyTest {
    public static void main(String[] args) {
        Role roleA = new RoleA("A");
        roleA.setAttackBehavior(new AttackJY())
                .setDefendBehavior(new DefendTBS())
                .setDisplayBehavior(new DisplayYZ())
                .setRunBehavior(new RunJCTQ());
        System.out.println(roleA.name + ":");
        roleA.run();
        roleA.attack();
        roleA.defend();
        roleA.display();
    }
}

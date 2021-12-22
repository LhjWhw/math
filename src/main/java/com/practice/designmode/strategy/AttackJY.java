package com.practice.designmode.strategy;

public class AttackJY implements IAttackBehavior {
    @Override
    public void attack() {
        System.out.println("九阳神功！");
    }

}

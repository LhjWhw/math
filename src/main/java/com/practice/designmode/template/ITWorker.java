package com.practice.designmode.template;

public class ITWorker extends Worker {

    public ITWorker(String name) {
        super(name);
    }

    @Override
    public void work() {
        System.out.println(name + "写程序-测bug-fix bug");
    }

    @Override
    public boolean isNeedPrintDate() {
        return true;
    }
}

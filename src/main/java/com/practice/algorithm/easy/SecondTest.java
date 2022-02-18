package com.practice.algorithm.easy;

import org.junit.Test;


/**
 * 常见算法题汇总-2
 * <p>
 * （1）有1、2、3、4四个数，能组成多少个互不相同且无重复数字的三位数，都是多少？
 * （2）方程求解问题:一个整数加上100之后是一个完全平方数，加上168之后也是一个完全平方数，求这个数是多少？
 * （3）判断日期是一年当中的第几天？输入某年某月某日，判断这一天是这一年的第几天
 */
public class SecondTest {

    @Test
    public void test01() {
        int count = 0;
        for (int i = 1; i <= 4; i++) {
            for (int j = 1; j <= 4; j++) {
                for (int k = 1; k <= 4; k++) {
                    if (k != i && k != j && i != j) {
                        int num = i * 100 + j * 10 + k;
                        count++;
                        System.out.print(num + "\t");
                    }
                }
            }
        }
        System.out.println("\n共能组成" + count + "个数");
    }

    @Test
    public void test02(){
        for (int i = 0; i < Integer.MAX_VALUE - 168; i++){
            if(Math.sqrt(i + 100) % 1 == 0 && Math.sqrt(i + 168) % 1 == 0){
                System.out.println(i);
            }
        }
    }

    @Test
    public void test03(){
        int year = 2021;
        int month = 10;
        int day = 8;
        int sum = 0;
        for (int i = 1; i < month; i++) {
            int num = 0;
            switch (i) {
                case 1:
                case 3:
                case 5:
                case 7:
                case 8:
                case 10:
                case 12:
                    num = 31;
                    break;
                case 4:
                case 6:
                case 9:
                case 11:
                    num = 30;
                    break;
                case 2:
                    if (year % 400 == 0 || (year % 4 == 0 && year % 100 != 0)) {
                        num = 29;
                    } else {
                        num = 28;
                    }
                    break;
            }
            sum = sum + num;
        }
        sum = sum + day;
        System.out.println(year + "年" + month + "月" + day + "日 是" + year + "年的第 " + sum + " 天，祝您开心度过每一天！");
    }

}

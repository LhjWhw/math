package com.practice.algorithm.easy;

import org.junit.Test;

import java.util.*;

/**
 * 常见算法题目汇总：
 * 1、斐波那契数列
 * 2、素数
 * 3、水仙花数
 * 4、正整数分解质因数
 * 5、完数
 * 6、小球自由落体
 * 7、数字自由组合
 * 8、完全平方数
 * 9、阶乘
 * 10、递归求阶乘
 * 11、回文数
 */

public class OtherTest {

    /**
     * (1) 斐波那契数列 ： 1 1 2 3 5 8 13 21 ...
     * n = 1, result = 1
     * n = 4, result = 3
     * 输入n， 输出结果！（可能还要考虑一下整数的最大取值范围）
     */
    @Test
    public void test01() {
        int number = 12;
        int result = getFBNQNumber(number);
        System.out.println(result);
    }

    /**
     * （2）判断101-200之间有多少个素数，并输出所有素数
     */
    @Test
    public void test02() {
        List<Integer> list = getZSInfo();
        System.out.println("质数个数为：" + list.size());
        System.out.println("质数分别为：" + list);
    }

    /**
     * (3)打印出所有的 "水仙花数 "，所谓 "水仙花数 "是指一个三位数，其各位数字立方和等于该数本身
     */
    @Test
    public void test03() {
        List<Integer> list = getAllSXHSList();
        System.out.println(list);
    }

    /**
     * (4)将一个正整数分解质因数。例如：输入90,打印出90=2*3*3*5。
     */
    @Test
    public void test04() {
        int n = 90;
        int k = 2;
        while (n >= k) {
            if (n == k) {
                System.out.println(k);
                break;
            } else if (n % k == 0) {
                System.out.println(k);
                n = n / k;
            } else {
                k++;
            }
        }
    }

    /**
     * (5)一个数如果恰好等于它的因子之和，这个数就称为 "完数 "。例如6=1＋2＋3，找出1000以内的所有完数
     */
    @Test
    public void test05() {
        for (int i = 1; i <= 1000; i++) {
            int t = 0;
            for (int j = 1; j <= i / 2; j++) {
                if (i % j == 0) {
                    t += j;
                }
            }
            if (t == i) {
                System.out.println(i);
            }
        }
    }

    /**
     * （6）一球从100米高度自由落下，每次落地后反跳回原高度的一半；再落下，求它在 第10次落地时，共经过多少米？第10次反弹多高？
     */
    @Test
    public void test06() {
        double h = 100;
        double s = 100;
        for (int i = 1; i <= 10; i++) {
            h = h / 2;
            s = s + 2 * h;
        }
        System.out.println(s);
        System.out.println(h);
    }

    /**
     * （7）有1、2、3、4四个数字，能组成多少个互不相同且一个数字中无重复数字的三位数？并把他们都输入
     */
    @Test
    public void test07() {
        int count = 0;
        for (int i = 1; i < 5; i++) {
            for (int j = 1; j < 5; j++) {
                for (int k = 1; k < 5; k++) {
                    if (i != j && j != k && i != k) {
                        count++;
                        System.out.println(i * 100 + j * 10 + k);
                    }
                }
            }
        }
        System.out.println(count);
    }

    /**
     * （8）一个整数，它加上100后是一个完全平方数，再加上168又是一个完全平方数，请问该数是多少？
     */
    @Test
    public void test08() {
        for (int i = -100; i < 10000; i++) {
            if (Math.sqrt(i + 100) % 1 == 0 && Math.sqrt(i + 168) % 1 == 0) {
                System.out.println(i);
            }
        }
    }

    /**
     * (9)求1+2!+3!+…+20!的和
     */
    @Test
    public void test09() {
        long sum = 0, ver = 1;
        for (int i = 1; i <= 20; i++) {
            ver = ver * i;
            sum += ver;
        }
        System.out.println(sum);
    }

    /**
     * (10)利用递归方法求5!。
     */
    @Test
    public void test10() {
        System.out.println(fac(5));
    }

    /**
     * （11）一个5位数，判断它是不是回文数。即12321是回文数，个位与万位相同，十位与千位相同
     */
    @Test
    public void test11() {
        int numtest = 1231;
        System.out.println(ver(numtest));
    }

    private static boolean ver(int num) {
        if (num < 0 || (num != 0 && num % 10 == 0))
            return false;
        int ver = 0;
        while (num > ver) {
            ver = ver * 10 + num % 10;
            num = num / 10;
        }
        return (num == ver || num == ver / 10);
    }

    private static int fac(int i) {
        if (i == 1) return 1;
        else {
            return i * fac(i - 1);
        }
    }

    private List<Integer> getAllSXHSList() {
        List<Integer> list = new ArrayList<>();
        for (int i = 100; i < 1000; i++) {
            int first = i / 100;
            int second = i / 10 % 10;
            int third = i % 10;
            if (first * first * first + second * second * second + third * third * third == i) {
                list.add(i);
            }
        }
        return list;
    }


    private List<Integer> getZSInfo() {
        List<Integer> list = new ArrayList<>();
        int count = 0;
        for (int i = 101; i < 200; i += 2) {//位数是0、2、4、6、8的直接跳过
            boolean flag = true;
            for (int j = 2; j < Math.sqrt(i); j++) {//开方
                if (i % j == 0) {
                    flag = false;
                    break;
                }
            }
            if (flag == true) {
                list.add(i);
                count++;
            }
        }
        return list;
    }


    private int getFBNQNumber(int number) {
        if (number <= 0) {
            throw new RuntimeException("参数异常，输入的参数不能为负数！");
        }
        if (number == 1 || number == 2) {
            return 1;
        }
        int f1 = 1;
        int f2 = 1;
        for (int i = 2; i < number; i++) {
            int f = f2;
            f2 = f1 + f2;
            f1 = f;
        }
        return f2;
    }

}

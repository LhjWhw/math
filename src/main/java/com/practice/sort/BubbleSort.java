package com.practice.sort;

import java.util.Arrays;

/**
 * 冒泡排序算法
 */
public class BubbleSort {

    public static void main(String[] args) {
        int[] arr = {19, 25, 12, 34, 18, 5, 5, 45};
        System.out.println("冒泡排序之前的顺序为：" + Arrays.toString(arr));
        System.out.println(getBubbleSort(arr) == null ? "参数为空" : "冒泡排序之后的顺序为：" + Arrays.toString(getBubbleSort(arr)));
    }

    private static int[] getBubbleSort(int[] array) {
        if (array == null || array.length == 0) return null;
        for (int i = 0; i < array.length; i++) {
            //提前退出冒泡循环的标志
            boolean flag = false;
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    //表示有数据交换
                    flag = true;
                }
                if (!flag) {
                    //没有数据交换(说明已排好序无需再进行冒泡),提前退出
                    break;
                }
            }
        }
        return array;
    }

}

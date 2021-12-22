package com.practice.sort;

import java.util.Arrays;

/**
 * 插入排序算法
 * 插入排序的基本思想就是将无序序列插入到有序序列中。
 * 例如要将数组arr=[4,2,8,0,5,1]排序，可以将4看做是一个有序序列(图中用蓝色标出)，将[2,8,0,5,1]看做一个无序序列。
 * 无序序列中2比4小，于是将2插入到4的左边，此时有序序列变成了[2,4]，无序序列变成了[8,0,5,1]。
 * 无序序列中8比4大，于是将8插入到4的右边，有序序列变成了[2,4,8],无序序列变成了[0,5,1]。
 * 以此类推，最终数组按照从小到大排序。该算法的时间复杂度为O(n^2)
 */
public class InsertSort {

    public static void main(String[] args) {
        int[] arr = {19, 25, 12, 34, 18, 5, 5, 45};
        System.out.println("插入排序之前的顺序为：" + Arrays.toString(arr));
        System.out.println(getInsertSort(arr) == null ? "参数为空" : "插入排序之后的顺序为：" + Arrays.toString(getInsertSort(arr)));
    }

    private static int[] getInsertSort(int[] array) {
        if (array == null || array.length == 0) return null;
        for (int i = 1; i < array.length; i++) {
            int j;
            if (array[i] < array[i - 1]) {
                int temp = array[i];
                for (j = i - 1; j >= 0 && temp < array[j]; j--) {
                    array[j + 1] = array[j];
                }
                array[j + 1] = temp;
            }
        }
        return array;
    }
}

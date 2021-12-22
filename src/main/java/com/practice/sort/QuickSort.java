package com.practice.sort;

import java.util.Arrays;

/**
 * 快速排序算法
 * <p>
 * 快速排序的基本思想是：通过一趟排序将待排记录分割成独立的两部分，其中一部分记录的关键字均比另一部分记录的关键字小，
 * 则可分别对这两部分记录继续进行排序，已达到整个序列有序。一趟快速排序的具体过程可描述为：从待排序列中任意选取一个记录(通常选取第一个记录)作为基准值，
 * 然后将记录中关键字比它小的记录都安置在它的位置之前，将记录中关键字比它大的记录都安置在它的位置之后。这样，以该基准值为分界线，将待排序列分成的两个子序列。
 * <p>
 * 一趟快速排序的具体做法为：设置两个指针low和high分别指向待排序列的开始和结尾，记录下基准值baseval(待排序列的第一个记录)，
 * 然后先从high所指的位置向前搜索直到找到一个小于baseval的记录并互相交换，接着从low所指向的位置向后搜索直到找到一个大于baseval的记录并互相交换，
 * 重复这两个步骤直到low=high为止。
 */
public class QuickSort {

    public static void main(String[] args) {
        int[] arr = {19, 25, 12, 34, 18, 5, 5, 45};
        System.out.println("快速排序之前的顺序为：" + Arrays.toString(arr));
        System.out.println(getQuickSort(arr, 0, arr.length - 1) == null ? "参数为空" :
                "快速排序之后的顺序为：" + Arrays.toString(getQuickSort(arr, 0, arr.length - 1)));
    }

    private static int[] getQuickSort(int[] arr, int start, int end) {
        if (arr == null || arr.length == 0) return null;
        if (start >= end)
            return null;
        int i = start;
        int j = end;
        // 基准数
        int baseval = arr[start];
        while (i < j) {
            // 从右向左找比基准数小的数
            while (i < j && arr[j] >= baseval) {
                j--;
            }
            if (i < j) {
                arr[i] = arr[j];
                i++;
            }
            // 从左向右找比基准数大的数
            while (i < j && arr[i] < baseval) {
                i++;
            }
            if (i < j) {
                arr[j] = arr[i];
                j--;
            }
        }
        // 把基准数放到i的位置
        arr[i] = baseval;
        // 递归
        getQuickSort(arr, start, i - 1);
        getQuickSort(arr, i + 1, end);
        return arr;
    }

}

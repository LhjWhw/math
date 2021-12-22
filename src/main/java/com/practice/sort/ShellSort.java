package com.practice.sort;

import java.util.Arrays;

/**
 * 希尔排序算法
 * 希尔排序(Shell's Sort)在插入排序算法的基础上进行了改进，算法的时间复杂度与前面几种算法相比有较大的改进。
 * 其算法的基本思想是：先将待排记录序列分割成为若干子序列分别进行插入排序，待整个序列中的记录"基本有序"时，再对全体记录进行一次直接插入排序
 */
public class ShellSort {
    public static void main(String[] args) {
        int[] arr = {19, 25, 12, 34, 18, 5, 5, 45};
        System.out.println("希尔排序之前的顺序为：" + Arrays.toString(arr));
        System.out.println(getShellSort(arr) == null ? "参数为空" : "希尔排序之后的顺序为：" + Arrays.toString(getShellSort(arr)));
    }

    private static int[] getShellSort(int[] array) {
        if (array == null || array.length == 0) return null;
        int increasement = array.length;
        int i, j, k;
        do {
            // 确定分组的增量
            increasement = increasement / 3 + 1;
            for (i = 0; i < increasement; i++) {
                for (j = i + increasement; j < array.length; j += increasement) {
                    if (array[j] < array[j - increasement]) {
                        int temp = array[j];
                        for (k = j - increasement; k >= 0 && temp < array[k]; k -= increasement) {
                            array[k + increasement] = array[k];
                        }
                        array[k + increasement] = temp;
                    }
                }
            }
        } while (increasement > 1);
        return array;
    }
}

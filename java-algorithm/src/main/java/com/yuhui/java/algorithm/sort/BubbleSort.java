package com.yuhui.java.algorithm.sort;

import java.util.Arrays;

/**
 * @author yuhui
 */
public class BubbleSort implements IArraySort {

    /**
     * 从大到小排列
     *
     * @param sourceArray
     * @return
     */
    @Override
    public int[] sort(int[] sourceArray) throws Exception {
        int[] arr = Arrays.copyOf(sourceArray, sourceArray.length);
        //从1开始，如果数组长度为0、1直接不用排序了，返回就行
        for (int i = 1; i < arr.length; i++) {
            // 设定一个标记，若为true，则表示此次循环没有进行交换，也就是待排序列已经有序，排序已经完成。
            boolean flag = true;
            for (int j = 0; j < arr.length - i; j++) {
                //比较两个相邻的元素，把小的放前边
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;

                    flag = false;
                }
            }
//            if (flag) {
//                break;
//            }
        }
        return arr;
    }
}

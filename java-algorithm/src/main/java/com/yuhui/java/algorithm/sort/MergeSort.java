package com.yuhui.java.algorithm.sort;

import java.util.Arrays;

/**
 * @author yuhui
 */
public class MergeSort implements IArraySort {

    /**
     * 归并排序（归并排序核心是将一个数组分分成两个，然后返回为一个有序的数组）
     *
     * @param sourceArray
     * @return
     * @throws Exception
     */
    @Override
    public int[] sort(int[] sourceArray) throws Exception {
        int[] arr = Arrays.copyOf(sourceArray, sourceArray.length);
        int index = arr.length / 2;
        return arr;
    }


}

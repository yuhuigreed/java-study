package com.yuhui.java.algorithm.sort;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author yuhui
 */
public class SortTest {

    @Test
    public void testBubble() throws Exception {
        int[] arr = new int[]{2, 1, 3, 6, 4, 3, 2, 5, 9, 10};
        int[] rest = new BubbleSort().sort(arr);
        for (int i : rest) {
            System.out.println(i);
        }
    }
}

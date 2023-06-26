package com.yuhui.java.algorithm.algorithm._1集合;

public class _238_product_of_array_except_self {

    public int[] productExceptSelf(int[] nums) {
        //特殊情况的处理
        if (nums.length <= 1) {
            return nums;
        }
        //先定义两个容器，分别存放左侧、右侧相乘的结果
        int[] left = new int[nums.length];
        int[] right = new int[nums.length];
        int[] res = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            int j = nums.length - 1 - i;
            //边界处理
            if (i == 0) {
                left[i] = 1;
                right[j]=1;
            } else {
                left[i] = nums[i - 1] * left[i - 1];
                right[j] = nums[j + 1] * right[j + 1];
            }
            //当i和j相遇时可以开始计算
            if (i == j) {
                res[i] = left[i] * right[j];
            } else if (i > j) {
                res[j] = left[j] * right[j];
                res[i] = left[i] * right[i];
            }
        }
        return res;
    }
}

package com.yuhui.java.algorithm.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author yuhui
 * 给定一个整数数组和一个目标值，找出数组中和为目标值的两个数。
 * <p>
 * 你可以假设每个输入只对应一种答案，且同样的元素不能被重复利用。
 * <p>
 * 示例:
 * <p>
 * 给定 nums = [2, 7, 11, 15], target = 9
 * <p>
 * 因为 nums[0] + nums[1] = 2 + 7 = 9
 * <p>
 * 所以返回 [0, 1]
 */

public class TwoSum {


    public static void main(String[] args) throws Exception {
        int[] nums = new int[]{2, 7, 11, 15};
        int target = 9;
        int[] res = solution(nums, target);
        System.out.println(Arrays.toString(res));
    }

    /**
     * 解题思路：
     * 使用HashMap,可以将复杂度降为O（n）
     *
     * @param nums
     * @param target
     */
    public static int[] solution(int[] nums, int target) throws Exception {
        Map<Integer, Integer> numMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int num1 = nums[i];
            int num2 = target - num1;
            if (numMap.containsKey(num2)) {
                return new int[]{numMap.get(num2), i};
            }
            numMap.put(num1, i);
        }
        throw new Exception("没找到符合要求的数字");
    }
}

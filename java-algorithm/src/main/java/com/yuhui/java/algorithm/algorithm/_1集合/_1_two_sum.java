package com.yuhui.java.algorithm.algorithm._1集合;

import java.util.HashMap;
import java.util.Map;

public class _1_two_sum {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer,Integer> numIndexMap=new HashMap<>();
        int[] res = new int[2];
        for(int i=0;i<nums.length;i++){
            int num = nums[i];
            int left = target - num;
            //判断 numIndexMap 里面是否有这个数
            if (numIndexMap.containsKey(left)) {
                res[0] = i;
                res[1] = numIndexMap.get(left);
                break;
            }
            numIndexMap.put(num, i);
        }
        return res;
    }
}

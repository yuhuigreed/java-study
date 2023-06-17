package com.yuhui.java.algorithm.algorithm._1集合;

import java.util.HashSet;
import java.util.Set;

public class _217_contains_duplicate {

    public boolean containsDuplicate(int[] nums) {
        Set<Integer> numSet=new HashSet<>();
        boolean res=false;
        for(int i=0;i<nums.length;i++){
            Integer num = nums[i];
            if(numSet.contains(num)){
                res=true;
                break;
            }
            numSet.add(num);
        }
        return res;
    }
}

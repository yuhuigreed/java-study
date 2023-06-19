package com.yuhui.java.algorithm.algorithm._1集合;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class _347_top_k_frequent_elements {

    public static void main(String[] args) throws JsonProcessingException {
        int[] nums = new int[]{1};
        int k = 2;
        int[] ints = topKFrequent(nums, k);
        System.out.println(new ObjectMapper().writeValueAsString(ints));
    }


    public static int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        //遍历一遍，计算大小
        for (int num : nums) {
            Integer count = map.getOrDefault(num, 0);
            map.put(num, count + 1);
        }
        PriorityQueue<Integer> queue = new PriorityQueue<>(((o1, o2) -> {
            Integer count1 = map.get(o1);
            Integer count2 = map.get(o2);
            return Integer.compare(count1, count2);
        }));
        Integer[] integers;
        if (map.size() <= k) {
            integers = map.keySet().toArray(new Integer[map.size()]);
        } else {
            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                Integer num = entry.getKey();
                Integer count = entry.getValue();
                if (queue.size() < k) {
                    queue.add(num);
                } else {
                    Integer least = queue.peek();
                    Integer leastCount = map.get(least);
                    if (count > leastCount) {
                        queue.poll();
                        queue.add(num);
                    }
                }
            }
            integers = queue.toArray(new Integer[queue.size()]);
        }
        int[] res = new int[integers.length];
        for (int i = 0; i < integers.length; i++) {
            Integer integer = integers[i];
            res[i] = integer;
        }
        return res;
    }
}

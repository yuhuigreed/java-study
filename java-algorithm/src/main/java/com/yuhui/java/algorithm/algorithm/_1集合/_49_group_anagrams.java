package com.yuhui.java.algorithm.algorithm._1集合;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class _49_group_anagrams {


    public static void main(String[] args) throws JsonProcessingException {
        String[] strs = new String[]{"ac", "ca", "abc", "bbb", "cab"};
        List<List<String>> test = test(strs);
        ObjectMapper objectMapper = new ObjectMapper();
        System.out.printf( objectMapper.writeValueAsString(test));
    }

    public static List<List<String>> test(String[] strs){
        if (strs == null || strs.length == 0) {
            return new ArrayList<>();
        }
        Map<String, List<String>> map = new HashMap<>();
        for (String s : strs) {
            char[] ca = new char[26];
            for (char c : s.toCharArray()) {
                ca[c - 'a']++;
            }
            String keyStr = String.valueOf(ca);
            if (!map.containsKey(keyStr)) {
                map.put(keyStr, new ArrayList<>());
            }
            map.get(keyStr).add(s);
        }
        return new ArrayList<>(map.values());
    }

    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> result = new ArrayList<>();
        Map<String, Integer> strIndexMap = new HashMap<>();
        for (int i = 0; i < strs.length; i++) {
            String str = strs[i];
            String sortedStr = str;
            if (str.length() >= 2) {
                //排个序
                char[] chars = str.toCharArray();
                quickSort(chars, 0, chars.length - 1);
                sortedStr = new String(chars);
            }
            //判断是否之前是否已经有了
            if (strIndexMap.containsKey(sortedStr)) {
                Integer index = strIndexMap.get(sortedStr);
                result.get(index).add(str);
            } else {
                List<String> lists = new ArrayList<>();
                lists.add(str);
                result.add(lists);
                strIndexMap.put(sortedStr, result.size() - 1);
            }
        }
        return result;
    }


    /**
     * 快排
     *
     * @return
     */
    public void quickSort(char[] a, int l, int r) {
        if (l < r) {
            int i, j;
            char x;

            i = l;
            j = r;
            x = a[i];
            while (i < j) {
                while (i < j && a[j] > x) {
                    j--; // 从右向左找第一个小于x的数
                }
                if (i < j) {
                    a[i++] = a[j];
                }
                while (i < j && a[i] < x) {
                    i++; // 从左向右找第一个大于x的数
                }
                if (i < j) {
                    a[j--] = a[i];
                }
            }
            a[i] = x;
            quickSort(a, l, i - 1); /* 递归调用 */
            quickSort(a, i + 1, r); /* 递归调用 */
        }
    }


}

package com.yuhui.java.algorithm.algorithm._1集合;

import java.util.Arrays;

public class _242_valid_anagram {
    public boolean isAnagram(String s, String t) {
        //首先判断两个字符串的长度是否相同
        if(s == null || t == null){
            return false;
        }
        if(s.length()!=t.length()){
            return false;
        }
        //两个字符串都转成字符数组
        char[] sChars = s.toCharArray();
        char[] tChars = t.toCharArray();
        Arrays.sort(sChars);
        Arrays.sort(tChars);
        boolean res = true;
        for (int i = 0; i < sChars.length; i++) {
            char sChar = sChars[i];
            char tChar = tChars[i];
            if (sChar != tChar) {
                return false;
            }
        }
        return res;
    }
}

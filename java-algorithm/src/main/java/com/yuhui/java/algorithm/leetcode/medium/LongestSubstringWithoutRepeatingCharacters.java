package com.yuhui.java.algorithm.leetcode.medium;


import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 3. 无重复字符的最长子串
 * <p>
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 * <p>
 * 示例 1:
 * <p>
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * 示例 2:
 * <p>
 * 输入: "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * 示例 3:
 * <p>
 * 输入: "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-substring-without-repeating-characters
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LongestSubstringWithoutRepeatingCharacters {

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("au"));
    }


    /**
     * 使用map存储字符位置的映射，分别指向前后的字符
     *
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring(String s) {
        char[] chars = s.toCharArray();
        if (chars.length < 2) {
            return chars.length;
        }
        Map<Character, Integer> indexMap = new HashMap<>();
        int length = 1;
        int start = -1;
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            Integer index = indexMap.get(c);
            //说明有重复的了
            if (Objects.nonNull(index) && index >= start) {
                start = index;
            }
            int lengthTmp = i - start;
            if (lengthTmp > length) {
                length = lengthTmp;
            }
            indexMap.put(c, i);
        }
        return length;
    }


    /**
     * 更优解
     * 们可以使用一个256位的数组来替代hashmap，以进行优化。（因为ASCII码表里的字符总共有128个。ASCII码的长度是一个字节，8位，理论上可以表示256个字符，
     * 但是许多时候只谈128个。具体原因可以下去自行学习~）
     * <p>
     * 这个只适用于英文字符，有局限性
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring2(String s) {
        int n = s.length();
        int result = 0;
        int[] charIndex = new int[256];
        for (int left = 0, right = 0; right < n; right++) {
            char c = s.charAt(right);
            left = Math.max(charIndex[c], left);
            result = Math.max(result, right - left + 1);
            charIndex[c] = right + 1;
        }

        return result;
    }
}

package com.yuhui.java.algorithm.leetcode.easy;

import java.util.*;

/**
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
 * <p>
 * 有效字符串需满足：
 * <p>
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * 注意空字符串可被认为是有效字符串。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/valid-parentheses
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class ValidParentheses {
    public static void main(String[] args) {
//        System.out.println(isValid("()"));
        System.out.println(isValid("){"));
    }

    /**
     * 我的实现：使用栈结构来处理
     *
     * @param s
     * @return
     */
    public static boolean isValid(String s) {
        if (s.length() % 2 == 1) {
            return false;
        }
        if (s.length() == 0) {
            return true;
        }
        //先把配对关系存入map
        Map<Character, Character> map = new HashMap<>(3);
        //注意要右左存放，后续从栈中取是应该取左括号使用
        map.put(')', '(');
        map.put('}', '{');
        map.put(']', '[');
        Stack<Character> stack = new Stack<>();
        boolean flag = true;
        for (char c : s.toCharArray()) {
            Character left = map.get(c);
            if (left != null) {
                if (stack.isEmpty() || !stack.pop().equals(left)) {
                    flag = false;
                    break;
                }
            } else {
                stack.push(c);
            }
        }
        if (!stack.isEmpty()) {
            flag = false;
        }
        return flag;
    }

    /**
     * leetcode巧妙的解法
     */
    public boolean isValid1(String s) {
        Stack<Character> stack = new Stack<Character>();
        for (char c : s.toCharArray()) {
            if (c == '(') stack.push(')');
            else if (c == '[') stack.push(']');
            else if (c == '{') stack.push('}');
            else if (stack.isEmpty() || c != stack.pop()) return false;
        }
        return stack.isEmpty();
    }
}

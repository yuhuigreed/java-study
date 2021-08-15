package com.yuhui.java.algorithm.leetcode.easy;


/**
 * 70. 爬楼梯
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 * <p>
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 * <p>
 * 注意：给定 n 是一个正整数。
 * <p>
 * 示例 1：
 * <p>
 * 输入： 2
 * 输出： 2
 * 解释： 有两种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶
 * 2.  2 阶
 * 示例 2：
 * <p>
 * 输入： 3
 * 输出： 3
 * 解释： 有三种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶 + 1 阶
 * 2.  1 阶 + 2 阶
 * 3.  2 阶 + 1 阶
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/climbing-stairs
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class ClimbingStairs {
    public static void main(String[] args) {

    }

    /**
     * 先理解题意：
     *  假设有N个台阶，N>2，那么可以说最后一步有两种走法，走一步或者两步，分别是(N-1)个台阶和(N-2)个台阶走法之和
     *  那我们假设(N-1)个台阶有f(n-1)种走法，(N-2)个台阶有f(n-2)种走法，那么得到
     *  公式 f(n)=f(n-1)+f(n-2)，f(1)=1,f(2)=2
     * @param n
     * @return
     */

    /**
     * 递归解法
     *
     * 时间复杂度：O(2^n)
     *
     * @param n
     * @return
     */
    public static int climbStairs(int n) {
        if (n == 1) {
            return 1;
        } else if (n == 2) {
            return 2;
        } else {
            return climbStairs(n - 1) + climbStairs(n - 2);
        }
    }

    /**
     * 非递归循环解法
     *
     * 时间复杂度：O(n)
     *
     * @param n
     * @return
     */
    public static int climbStairs2(int n) {
        if (n == 1) {
            return 1;
        } else if (n == 2) {
            return 2;
        }
        int pre1 = 1;
        int pre2 = 2;
        int current = 0;
        for (int i = 3; i <= n; i++) {
            current = pre1 + pre2;
            pre1 = pre2;
            pre2 = current;
        }
        return current;
    }

    /**
     * 斐波那契数列的公式
     *
     * @param n
     * @return
     */
    public static int climbStairs3(int n) {
        double sqrt_5 = Math.sqrt(5);
        double fib_n = Math.pow((1 + sqrt_5) / 2, n + 1) - Math.pow((1 - sqrt_5) / 2,n + 1);
        return (int)(fib_n / sqrt_5);
    }

}

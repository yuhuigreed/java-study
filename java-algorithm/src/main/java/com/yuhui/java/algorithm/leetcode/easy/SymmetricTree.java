package com.yuhui.java.algorithm.leetcode.easy;


import java.util.Objects;

/**
 * 101. 对称二叉树
 * <p>
 * 给定一个二叉树，检查它是否是镜像对称的。
 * <p>
 *  
 * <p>
 * 例如，二叉树 [1,2,2,3,4,4,3] 是对称的。
 * <p>
 * 1
 * / \
 * 2   2
 * / \ / \
 * 3  4 4  3
 *  
 * <p>
 * 但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的:
 * <p>
 * 1
 * / \
 * 2   2
 * \   \
 * 3    3
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/symmetric-tree
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class SymmetricTree {

    public static void main(String[] args) {

    }


    /**
     * 递归的解法
     * 两个指针分别从
     *
     * @param root
     * @return
     */
    public  boolean isSymmetric(TreeNode root) {
        return check(root, root);
    }

    /**
     * 递归的终止条件
     * <p>
     * p==null,q==null 结束
     * <p>
     * p/q只有一个为null，false
     *
     * p.val!=p.val也是false
     *
     * @param p
     * @param q
     * @return
     */
    private static boolean check(TreeNode p, TreeNode q) {
        if (p == null && q != null) {
            return false;
        } else if (p != null && q == null) {
            return false;
        }
        if (p == null && q == null) {
            return true;
        }
        return p.val == q.val && check(p.left, q.right) && check(p.right, q.left);
    }


    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

}


package com.yuhui.java.algorithm.leetcode.medium;

import java.util.Objects;
import java.util.Stack;

/**
 * 2.两数相加
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 * <p>
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 * <p>
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 * <p>
 * 示例：
 * <p>
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/add-two-numbers
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class AddTwoNumbers {
    public static void main(String[] args) {
        ListNode l1 = new ListNode(3);
        ListNode l2 = new ListNode(4);
        ListNode l3 = new ListNode(2);
        l1.next = l2;
        l2.next = l3;

        ListNode l4 = new ListNode(4);
        ListNode l5 = new ListNode(6);
        ListNode l6 = new ListNode(5);
        l4.next = l5;
        l5.next = l6;


        ListNode listNode = addTwoNumbers(l1, l4);

    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode node1 = l1;
        ListNode node2 = l2;
        int temp = 0;
        ListNode head = null;
        ListNode preNode = null;
        while (Objects.nonNull(node1) || Objects.nonNull(node2)) {
            int val1 = 0;
            int val2 = 0;
            if (Objects.nonNull(node1)) {
                val1 = node1.val;
                node1 = node1.next;
            }
            if (Objects.nonNull(node2)) {
                val2 = node2.val;
                node2 = node2.next;
            }
            int sum = val1 + val2 + temp;
            temp = sum / 10;
            int val = sum % 10;
            ListNode listNode = new ListNode(val);
            if (Objects.isNull(head)) {
                head = listNode;
                preNode = listNode;
            } else {
                preNode.next = listNode;
                preNode = listNode;
            }
        }
        if (temp > 0) {
            ListNode node = new ListNode(temp);
            preNode.next = node;
        }
        return head;
    }

    /**
     * 网上看到的比较好的解法
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        ListNode root = new ListNode(0);
        ListNode cursor = root;
        int carry = 0;
        while (l1 != null || l2 != null || carry != 0) {
            int l1Val = l1 != null ? l1.val : 0;
            int l2Val = l2 != null ? l2.val : 0;
            int sumVal = l1Val + l2Val + carry;
            carry = sumVal / 10;

            ListNode sumNode = new ListNode(sumVal % 10);
            cursor.next = sumNode;
            cursor = sumNode;

            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
        }

        return root.next;
    }


    static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }
}

package com.yuhui.java.algorithm.leetcode.easy;

import java.util.Objects;

/**
 * 21. 合并两个有序链表
 * <p>
 * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。 
 * <p>
 *  
 * <p>
 * 示例：
 * <p>
 * 输入：1->2->4, 1->3->4
 * 输出：1->1->2->3->4->4
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/merge-two-sorted-lists
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MergeTwoSortedLists {

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode head = null;
        ListNode current = null;
        while (Objects.nonNull(l1) || Objects.nonNull(l2)) {
            ListNode temp = null;
            if (Objects.isNull(l2) || (Objects.nonNull(l1) && l1.val < l2.val)) {
                temp = l1;
                l1 = l1.next;
            } else if (Objects.isNull(l1) || (Objects.nonNull(l2) && l2.val <= l1.val)) {
                temp = l2;
                l2 = l2.next;
            }
            if (Objects.isNull(head)) {
                current = temp;
                head = temp;
            } else {
                current.next = temp;
                current = temp;
            }

        }
        return head;
    }


    class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}

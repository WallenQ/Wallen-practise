package com.wallen.practise.leetcode.linked;

import com.wallen.practise.leetcode.ListNode;

/**
 * 203. 移除链表元素
 * <p>
 * 给你一个链表的头节点 head 和一个整数 val ，请你删除链表中所有满足 Node.val == val 的节点，并返回 新的头节点 。
 * <p>
 * 示例 1： 输入：head = [1,2,6,3,4,5,6], val = 6; 输出：[1,2,3,4,5]
 * 示例 2： 输入：head = [], val = 1; 输出：[]
 * 示例 3： 输入：head = [7,7,7,7], val = 7 ;输出：[]
 * <p>
 * 提示：
 * 列表中的节点数目在范围 [0, 104] 内
 * 1 <= Node.val <= 50
 * 0 <= val <= 50
 * <p>
 * 链接：https://leetcode.cn/problems/remove-linked-list-elements/
 *
 * @Author qianwenlong
 * @Date 2025/10/10 16:14
 */
public class RemoveLinkedListElements203 {
    public static void main(String[] args) {
        ListNode l7 = new ListNode(6);
        ListNode l6 = new ListNode(5, l7);
        ListNode l5 = new ListNode(4, l6);
        ListNode l4 = new ListNode(3, l5);
        ListNode l3 = new ListNode(6, l4);
        ListNode l2 = new ListNode(2, l3);
        ListNode l1 = new ListNode(1, l2);

        ListNode result = removeElements1(l1, 6);

        while (result != null) {
            System.out.println(result.val);
            result = result.next;
        }
    }

    public static ListNode removeElements(ListNode head, int val) {
        ListNode result = null;
        ListNode pre = null;

        while (head != null) {
            if (head.val != val) {
                if (result == null) {
                    result = head;
                }
                pre = head;
            } else {
                if (pre != null) {
                    pre.next = head.next;
                }
            }
            head = head.next;
        }

        return result;
    }

    /**
     * 递归
     *
     * @param head
     * @param val
     * @return
     */
    public static ListNode removeElements1(ListNode head, int val) {
        if (head == null) {
            return head;
        }
        head.next = removeElements1(head.next, val);
        return head.val == val ? head.next : head;
    }

}

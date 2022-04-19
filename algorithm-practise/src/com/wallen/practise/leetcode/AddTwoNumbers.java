package com.wallen.practise.leetcode;

/**
 * 给出两个非空的链表用来表示两个非负的整数。其中，它们各自的位数是按照逆序的方式存储的，并且它们的每个节点只能存储一位
 * 数字。如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。您可以假设除了数字0之外，这两个数都不会以0开头。
 * <p>
 * 示例：
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 *
 * @author Wallen
 * 2020/7/17 15:11
 */
public class AddTwoNumbers {

	public static void main(String[] args) {
		ListNode l1 = new ListNode(5);
		/*l1.next = new ListNode(4);
		l1.next.next = new ListNode(3);*/

		ListNode l2 = new ListNode(5);
		/*l2.next = new ListNode(6);
		l2.next.next = new ListNode(4);*/

		ListNode result = addTwoNumbers(l1, l2);
	}

	public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		ListNode resultNode = new ListNode(0);
		ListNode current = resultNode;
		int carry = 0;
		while (l1 != null || l2 != null || carry != 0) {
			int result = (l1 != null ? l1.val : 0) + (l2 != null ? l2.val : 0) + carry;
			if (l1 != null) {
				l1 = l1.next;
			}
			if (l2 != null) {
				l2 = l2.next;
			}
			carry = result / 10;
			current.val = result % 10;
			if (l1 != null || l2 != null || carry != 0) {
				current.next = new ListNode(0);
				current = current.next;
			}
		}

		return resultNode;
	}
}

class ListNode {
	int val;
	ListNode next;

	ListNode(int x) {
		val = x;
	}
}
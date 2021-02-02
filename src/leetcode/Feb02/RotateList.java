package leetcode.Feb02;

import leetcode.common.ListNode;

/**
 * 61
 * 
 * @author Johnny
 * 
 *         2021年2月2日 下午3:19:07
 */
public class RotateList {
	public ListNode rotateRight(ListNode head, int k) {
		if (head == null) {
			return head;
		}
		// 链表长度
		int len = 0;
		ListNode cur = head;
		while (cur != null) {
			len++;
			cur = cur.next;
		}
		// 有效旋转次数
		k = k % len;
		// 链表长度1，或者 旋转0次，直接返回head
		if (len == 1 || k == 0) {
			return head;
		}
		// 找到新链表的尾节点
		cur = head;
		while (--len > k) {
			cur = cur.next;
		}
		// 旋转后新的头节点
		ListNode res = cur.next;
		// 新链表的尾节点指向null
		cur.next = null;

		// 将原链表尾节点指向原链表头节点
		cur = res;
		while (cur.next != null) {
			cur = cur.next;
		}
		cur.next = head;
		return res;
	}
}

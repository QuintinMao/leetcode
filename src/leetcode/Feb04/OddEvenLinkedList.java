package leetcode.Feb04;

import leetcode.common.ListNode;

/**
 * 328.
 * 
 * @author Johnny
 * 
 *         2021年2月4日 下午1:58:37
 */
public class OddEvenLinkedList {

	public ListNode oddEvenList(ListNode head) {
		if (head == null) {
			return head;
		}
		ListNode evenHead = head.next;
		ListNode oddTail = head;
		ListNode evenTail = evenHead;
		while (evenTail != null && evenTail.next != null) {
			oddTail.next = evenTail.next;
			oddTail = oddTail.next;
			evenTail.next = oddTail.next;
			evenTail = evenTail.next;
		}
		oddTail.next = evenHead;
		return head;
	}
}

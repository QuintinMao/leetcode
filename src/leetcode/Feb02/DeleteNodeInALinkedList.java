package leetcode.Feb02;

import leetcode.common.ListNode;

/**
 * 237
 * 
 * node.val = node.next.val;
 * 
 * node.next = node.next.next;
 * 
 * @author Johnny
 * 
 *         2021年2月2日 下午3:44:48
 */
public class DeleteNodeInALinkedList {
	public void deleteNode(ListNode node) {
		ListNode cur = node.next;
		while (cur != null) {
			node.val = cur.val;
			if (cur.next == null) {
				node.next = null;
			} else {
				node = node.next;
			}
			cur = cur.next;
		}
	}
}

package leetcodeTop100;

import java.util.HashSet;
import java.util.Set;

/**
 * Linked List Cycle II
 * 快慢指针
 * fast 与 slow。它们起始都位于链表的头部。随后，slow 指针每次向后移动一个位置，而 fast 指针向后移动两个位置。如果链表中存在环，
 * 则 fast 指针最终将再次与slow 指针在环中相遇
 * 
 *fs:快指针走过的距离；ss慢指针走过的距离；a 头到入环点的距离 ；b 入环点到相遇点的距离；c相遇点到入环点的距离
 * 相遇时候  fs = a+(b+c)n + b;   ss = a+b;
 * ∵fs = 2ss
 * ∴ a+n(b+c)+b = 2(a+b) => a = c+(n-1)(a+b)
 * @author Johnny
 * 
 * 2021年1月21日 下午6:32:02
 */
public class N142 {

	public ListNode detectCycle(ListNode head) {
		Set<ListNode> used = new HashSet<>();
		while (head != null) {
			used.add(head);
			head = head.next;
			if (head != null && used.contains(head)) {
				break;
			}
		}
		return head;
	}

	static class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
			next = null;
		}
	}

	public ListNode detectCycleFSPointer(ListNode head) {
		ListNode fast = head, slow = head;
		while (true) {
			if (fast == null || fast.next == null) {
				return null;
			}
			fast = fast.next.next;
			slow = slow.next;
			if (fast == slow) {
				break;
			}
		}
		fast = head;
		while (fast != slow) {
			fast = fast.next;
			slow = slow.next;
		}

		return fast;
	}

}

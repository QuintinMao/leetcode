package leetcode.Jan29;

/**
 * 160
 * 
 * @author Johnny
 * 
 *         2021年1月29日 下午4:07:19
 */
public class IntersectionOfTwoLinkedLists {
	/**
	 * A:123789
	 * 
	 * B:45789
	 * 
	 * AB:12378945789
	 * 
	 * BA:45789123789
	 * 
	 * 组合链表长度相同，且相同的节点是尾部对齐的。
	 * 
	 * @param headA
	 * @param headB
	 * @return
	 */
	public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
		if (headA == null || headB == null) {
			return null;
		}
		ListNode a = headA;
		ListNode b = headB;
		while (a != b) {
			a = a == null ? headB : a.next;
			b = b == null ? headA : b.next;
		}
		return a;
	}
}

class ListNode {
	int val;
	ListNode next;

	ListNode(int x) {
		val = x;
		next = null;
	}
}

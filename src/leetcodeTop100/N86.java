package leetcodeTop100;

/**
 * 分隔链表
 * @author Johnny
 * 
 * 2021年1月7日 上午11:34:56
 */
public class N86 {

	public static void main(String[] args) {
		ListNode head = new ListNode(1);
		ListNode n2 = new ListNode(4);
		ListNode n3 = new ListNode(3);
		ListNode n4 = new ListNode(2);
		ListNode n5 = new ListNode(5);
		ListNode n6 = new ListNode(2);
		head.next = n2;
		n2.next = n3;
		n3.next = n4;
		n4.next = n5;
		n5.next = n6;
		n6.next = null;

		int x = 3;
		ListNode rs = new N86().partition(head, x);
		ListNode cur = rs;
		while (cur != null) {
			System.out.println(cur.val);
			cur = cur.next;
		}
	}

	public ListNode partition(ListNode head, int x) {
		ListNode small = null;
		ListNode large = null;
		ListNode smallCur = null;
		ListNode largeCur = null;
		while (head != null) {
			if (head.val < x) {
				if (small == null) {
					small = head;
					smallCur = small;
				} else {
					smallCur.next = head;
					smallCur = smallCur.next;
				}
			} else {
				if (large == null) {
					large = head;
					largeCur = large;
				} else {
					largeCur.next = head;
					largeCur = largeCur.next;
				}
			}
			head = head.next;
		}
		if (largeCur != null) {
			largeCur.next = null;
		}
		smallCur.next = large;

		return small;
//		   ListNode small = new ListNode(0);
//	        ListNode smallHead = small;
//	        ListNode large = new ListNode(0);
//	        ListNode largeHead = large;
//			while (head != null) {
//				if (head.val < x) {
//					small.next = head;
//					small = small.next;
//				} else {
//					large.next = head;
//	                large = large.next;
//				}
//				head = head.next;
//			}
//	        //large.next = null;
//			small.next = largeHead.next;
//
//			return smallHead.next;
	}
}

class ListNode {
	int val;
	ListNode next;

	ListNode(int x) {
		val = x;
	}

	@Override
	public String toString() {
		return val + "";
	}
}

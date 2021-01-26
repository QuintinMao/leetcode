package leetcodeTop100;

/**
 * Sort List
 * 
 * @author Johnny
 * 
 *         2021年1月25日 上午11:52:59
 */
public class N48 {

	/**
	 * 归并排序，自顶向下
	 * 
	 * 找到链表的中点，将链表拆成两个部分。 分别对这两部分进行排序。 最后将这两部分合并（等同于合并两个有序链表） 快慢指针找到中点，
	 * 
	 * 递归 时间复杂度O(nlogn) 空间复杂度O(logn)
	 * 
	 * @param head
	 * @return
	 */
	public ListNode sortList(ListNode head) {
		return sortList(head, null);
	}

	public ListNode sortList(ListNode head, ListNode tail) {
		if (head == null) {
			return head;
		}
		if (head.next == tail) {
			head.next = null;
			return head;
		}
		ListNode fast = head, slow = head;
		while (fast != tail) {
			fast = fast.next;
			slow = slow.next;
			if (fast != tail) {
				fast = fast.next;
			}
		}
		ListNode mid = slow;
		ListNode ln1 = sortList(head, mid);
		ListNode ln2 = sortList(mid, tail);
		return merge(ln1, ln2);
	}

	public ListNode merge(ListNode l1, ListNode l2) {
		if (l1 == null || l2 == null) {
			return l1 != null ? l1 : l2;
		}
		ListNode head = new ListNode(0);
		ListNode tail = head, cur1 = l1, cur2 = l2;
		while (cur1 != null && cur2 != null) {
			if (cur1.val < cur2.val) {
				tail.next = cur1;
				cur1 = cur1.next;
			} else {
				tail.next = cur2;
				cur2 = cur2.next;
			}
			tail = tail.next;
		}
		tail.next = (cur1 == null) ? cur2 : cur1;
		return head.next;

	}

	/**
	 * 自底向上 归并
	 * 
	 * 1. 用subLength表示每次需要排序的子链表的长度，default 1 2.
	 * 每次将链表拆分成若干个长度为subLength的子链表，每两个一组进行合并。 3.
	 * 将subLength值加倍，重复第2步，直到有序子链表的长度>=length。
	 * 
	 * @param head
	 * @return
	 */
	public ListNode sortList2(ListNode head) {
		if (head == null) {
			return head;
		}
		// 原始链表长度。
		int length = 0;
		ListNode node = head;
		while (node != null) {
			length++;
			node = node.next;
		}

		// 哑节点
		ListNode dummyHead = new ListNode(0, head);
		// subLength从1开始每次*2
		for (int subLength = 1; subLength < length; subLength *= 2) {
			ListNode prev = dummyHead, cur = dummyHead.next;
			while (cur != null) {
				ListNode head1 = cur;
				// 指针走到本组（subLength组）最后一个节点
				for (int i = 1; i < subLength && cur.next != null; i++) {
					cur = cur.next;
				}
				// 第二组
				ListNode head2 = cur.next;
				//将第一组最后一个元素的next置为null
				cur.next = null;
				cur = head2;
				for (int i = 1; i < subLength && cur != null && cur.next != null; i++) {
					cur = cur.next;
				}
				//下一轮合并的开始值
				ListNode next = null;
				if (cur != null) {
					next = cur.next;
					//将第二组最后i一个元素的next置为null
					cur.next = null;
				}
				//执行合并
				ListNode merged = merge(head1, head2);
				//将合并结果追加到prev尾部
				prev.next = merged;
				//指针走到prev尾部
				while (prev.next != null) {
					prev = prev.next;
				}
				cur = next;

			}
		}
		return dummyHead.next;
	}

	private static class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
		}

		ListNode(int val, ListNode next) {
			this.val = val;
			this.next = next;
		}

		@Override
		public String toString() {
			return val + "";
		}
	}

	public static void main(String[] args) {
		ListNode n3 = new ListNode(3);
		ListNode n1 = new ListNode(1, n3);
		ListNode n2 = new ListNode(2, n1);
		ListNode head = new ListNode(4, n2);
		ListNode res = new N48().sortList2(head);
		while (res != null) {
			System.out.print(res.val + ",");
			res = res.next;

		}
	}
}

package leetcodeTop100;

/**
 * N2. Add Two Numbers
 * @author Johnny
 * 
 * 2021年1月14日 下午7:03:29
 */
public class N2 {

	public static void main(String[] args) {
		ListNode l17 = new ListNode(9, null);
		ListNode l16 = new ListNode(9, l17);
		ListNode l15 = new ListNode(9, l16);
		ListNode l14 = new ListNode(9, l15);  
		ListNode l13 = new ListNode(9, l14);
		ListNode l12 = new ListNode(9, l13);
		ListNode l1 = new ListNode(9, l12);

		ListNode l24 = new ListNode(9, null);
		ListNode l23 = new ListNode(9, l24);
		ListNode l22 = new ListNode(9, l23);
		ListNode l2 = new ListNode(9, l22);
		ListNode rs = new N2().addTwoNumbers(l1, l2);
		while (rs != null) {
			System.out.print(rs.val + ",");
			rs = rs.next;
		}
	}

	public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		return helper(l1, l2, false);
	}

	public ListNode helper(ListNode l1, ListNode l2, boolean addOne) {
		if (l1 == null && l2 == null) {
			if (addOne) {
				return new ListNode(1);
			} else {
				return null;
			}
		}
		int rs = (l1 == null ? 0 : l1.val) + (l2 == null ? 0 : l2.val) + (addOne ? 1 : 0);
		boolean add = rs >= 10;
		rs %= 10;
		return new ListNode(rs, helper(l1 == null ? null : l1.next, l2 == null ? null : l2.next, add));
	}

	static class ListNode {
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

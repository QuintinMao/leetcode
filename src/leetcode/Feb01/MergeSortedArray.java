package leetcode.Feb01;


/**
 * 88
 * 
 * @author Johnny
 * 
 *         2021年2月1日 下午12:01:00
 */
public class MergeSortedArray {
	public static void main(String[] args) {
		int[] nums1 = new int[] { 2, 0 };
		int[] nums2 = new int[] { 1 };
		new MergeSortedArray().merge(nums1, 1, nums2, 1);
		for (int n : nums1) {
			System.out.print(n + ",");
		}
	}

	public void merge(int[] nums1, int m, int[] nums2, int n) {
		if (n == 0) {
			return;
		}
		if (m == 0) {
			for (int i = 0; i < n; i++) {
				nums1[i] = nums2[i];
			}
			return;
		}
		int p1 = m - 1;
		int p2 = n - 1;
		for (int i = nums1.length - 1; i >= 0; i--) {
			if (p1 >= 0 && p2 >= 0) {
				if (nums2[p2] >= nums1[p1]) {
					nums1[i] = nums2[p2];
					p2--;
				} else {
					nums1[i] = nums1[p1];
					p1--;
				}
			} else {
				if (p1 >= 0) {
					nums1[i] = nums2[p1];
					p1--;
				}
				if (p2 >= 0) {
					nums1[i] = nums2[p2];
					p2--;
				}
			}
			if (p2 < 0) {
				break;
			}
		}

	}
}

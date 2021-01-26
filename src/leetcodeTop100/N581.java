package leetcodeTop100;

import java.util.Arrays;

public class N581 {
	public static void main(String[] args) {
		int[] nums = { 2, 6, 4, 8, 10, 9, 15 };
//		int[] nums = { 1, 2, 3, 4 };
		System.out.println(new N581().findUnsortedSubarray2(nums));
	}

	public int findUnsortedSubarray(int[] nums) {
		if (nums.length < 2) {
			return 0;
		}
		int n = nums.length;
		int[] ori = Arrays.copyOf(nums, n);
		Arrays.sort(nums);
		int i = 0, j = n - 1;
		for (; i < n; i++) {
			if (nums[i] != ori[i]) {
				break;
			}
		}

		if (i == j + 1) {
			return 0;
		}
		for (; j > i; j--) {
			if (nums[j] != ori[j]) {
				break;
			}
		}
		j += 1;
		return i == j ? 0 : j - i;
	}

	/**
	 * 暴力解法2
	 * 
	 * @param nums
	 * @return
	 */
	public int findUnsortedSubarray2(int[] nums) {
		int n = nums.length;
		int left = n, right = 0;
		for (int i = 0; i < n - 1; i++) {
			for (int j = i + 1; j < n; j++) {
				if (nums[j] < nums[i]) {
					right = Math.max(right, j);
					left = Math.min(left, i);
				}
			}
		}
		return left > right ? 0 : right - left + 1;
	}

	public int findUnsortedSubarray3(int[] nums) {
		int len = nums.length;
		if (len == 1) {
			return 0;
		}
		int max = nums[0];
		int r = 0;
		for (int i = 1; i < len; i++) {
			if (max > nums[i]) {
				r = i;
			} else {
				max = nums[i];
			}
		}
		int min = nums[len - 1];
		int l = len - 1;
		for (int i = len - 2; i >= 0; i--) {
			if (min < nums[i]) {
				l = i;
			} else {
				min = nums[i];
			}
		}
		return r - l < 0 ? 0 : r - l + 1;
	}
}

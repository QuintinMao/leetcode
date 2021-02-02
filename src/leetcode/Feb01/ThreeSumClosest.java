package leetcode.Feb01;

import java.util.Arrays;

/**
 * 16
 * 
 * @author Johnny
 * 
 *         2021年2月1日 下午7:05:26
 */
public class ThreeSumClosest {
	public static void main(String[] args) {
		int[] nums = { -3, -2, -5, 3, -4 };
		int target = -1;
		System.out.println(new ThreeSumClosest().threeSumClosest(nums, target));
	}

	public int threeSumClosest(int[] nums, int target) {
		int res = 20_000;
		int n = nums.length;
		Arrays.sort(nums);
		// a
		for (int a = 0; a < n; a++) {
			if (a > 0 && nums[a] == nums[a - 1]) {
				continue;
			}
			int b = a + 1, c = n - 1;
			while (b < c) {
				int sum = nums[a] + nums[b] + nums[c];
				if (sum == target) {
					return sum;
				}

				if (Math.abs(sum - target) < Math.abs(res - target)) {
					res = sum;
				}
				if (sum > target) {
					int c0 = c - 1;
					while (b < c0 && nums[c0] == nums[c]) {
						c0--;
					}
					c = c0;
				} else {
					int b0 = b + 1;
					while (b0 < c && nums[b0] == nums[b]) {
						b0++;
					}
					b = b0;
				}
			}
		}
		return res;
	}
}

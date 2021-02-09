package leetcode.Feb06;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 18.4Sum
 * 
 * @author Johnny
 * 
 *         2021年2月7日 上午11:17:21
 */
public class FourSum {

	public static void main(String[] args) {
		int[] nums = { -1, 0, -5, -2, -2, -4, 0, 1, -2 };
		int target = -9;
		List<List<Integer>> res = new FourSum().fourSum(nums, target);
		for (List<Integer> rr : res) {
			for (int r : rr) {
				System.out.print(r + ",");
			}
			System.out.println();
		}
	}

	public List<List<Integer>> fourSum(int[] nums, int target) {
		List<List<Integer>> res = new ArrayList<>();
		if (nums == null || nums.length < 4) {
			return res;
		}
		int n = nums.length;
		// ASC
		Arrays.sort(nums);

		// 固定a
		for (int a = 0; a < n; ++a) {
			// 过滤重复a值
			if (a > 0 && nums[a] == nums[a - 1]) {
				continue;
			}
			int t_a = target - nums[a];
			// 固定b
			for (int b = a + 1; b < n; ++b) {
				// 过滤重复b值
				if (b > a + 1 && nums[b] == nums[b - 1]) {
					continue;
				}

				int t_ab = t_a - nums[b];
				// 在(b,len)之间寻找 t_ab=target-nums[a]-nums[b];
				int left = b + 1, right = n - 1;
				while (left < right) {
					// 剪枝
					if (t_ab < nums[left] + nums[left + 1]) {
						break;
					}
					// 剪枝
					if (t_ab > nums[right] + nums[right - 1]) {
						break;
					}

					int cd = nums[left] + nums[right];
					if (cd == t_ab) {
						List<Integer> subRes = new ArrayList<>();
						subRes.add(nums[a]);
						subRes.add(nums[b]);
						subRes.add(nums[left]);
						subRes.add(nums[right]);
						res.add(subRes);
						left++;
						while (left < right && nums[left - 1] == nums[left]) {
							left++;
						}

						right--;
						while (left < right && nums[right + 1] == nums[right]) {
							right--;
						}

					} else if (cd > t_ab) {
						--right;
					} else {
						++left;
					}
				}
			}

		}
		return res;

	}
}

package leetcodeTop100;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Longest Increasing Subsequence
 * 
 * @author Johnny
 * 
 *         2021年1月20日 上午11:23:09
 */
public class N300 {

	public static void main(String[] args) {
		int[] nums = { 10, -5, 5, 6, 4, 1, 3, 29, 2, 5, 3, 7, 101, 18 };
		System.out.println(new N300().lengthOfLISGreedy(nums));
	}

	public int lengthOfLIS(int[] nums) {
		int res = 1;
		Queue<int[]> queue = new PriorityQueue<>((x, y) -> x[1] == y[1] ? y[0] - x[0] : y[1] - x[1]);
		int n = nums.length;
		queue.offer(new int[] { nums[0], 1 });
		for (int i = 1; i < n; i++) {
			List<int[]> foo = new ArrayList<>();
			int[] prev = null;
			while (!queue.isEmpty()) {
				prev = queue.poll();
				foo.add(prev);
				if (nums[i] > prev[0]) {
					res = Math.max(res, prev[1] + 1);
					break;
				}
				prev = null;
			}
			queue.offer(new int[] { nums[i], prev == null ? 1 : prev[1] + 1 });
			if (foo.size() > 0) {
				queue.addAll(foo);
			}
		}

		return res;
	}

	/**
	 * 动态规划
	 * 
	 * @param nums
	 * @return
	 */
	public int lengthOfLISDP(int[] nums) {
		int n = nums.length;
		// dp表示已index元素为结尾的升序子串的长度
		// dp[i] = max(dp[i],dp[j]+1); // nums[j]<nums[i]
		int[] dp = new int[n];
		// 第0个元素结尾的升序子串长度为1
		dp[0] = 1;
		int res = 0;
		for (int i = 1; i < n; i++) {
			// index==i结尾的，只有第i一个元素的 升序字串的长度为1
			dp[i] = 1;
			for (int j = 0; j < i; j++) {
				if (nums[i] > nums[j]) {
					dp[i] = Math.max(dp[i], dp[j] + 1);
				}
			}
			res = Math.max(res, dp[i]);
		}
		return res;
	}

	/**
	 * greedy + dichotomy 
	 * 
	 * 考虑一个简单的贪心，如果我们要使上升子序列尽可能的长，则我们需要让序列上升得尽可能慢
	 * ，因此我们希望每次在上升子序列最后加上的那个数尽可能的小。
	 * 
	 * @param nums
	 * @return
	 */
	public int lengthOfLISGreedy(int[] nums) {
		int n = nums.length;
		// 表示长度为 i的最长上升子序列的末尾元素的最小值
		int[] dp = new int[n + 1];
		int len = 1;
		dp[len] = nums[0];

		for (int i = 1; i < n; i++) {

			// 如果当前元素大于dp数组的index为当前最大长度len，的元素。
			// 将numsi 添加到dp【++len】
			if (nums[i] > dp[len]) {
				dp[++len] = nums[i];

			} else {
				// 否则 查找dp数组中第一个小于nums[i]的值的坐标target
				int l = 1, r = len;
				// 如果找不到说明所有的数都比 nums[i] 大，此时要更新 dp[1]，所以这里将 target 设为 0
				int target = 0;
				while (l <= r) {
					int mid = (l + r) / 2;
					if (dp[mid] < nums[i]) {
						target = mid;
						l = mid + 1;
					} else {
						r = mid - 1;
					}
				}
				// 注意target+1
				dp[target + 1] = nums[i];
			}
		}

		return len;
	}
}

package leetcodeTop100;

import java.util.Arrays;
import java.util.Collections;

/**
 * Target Sum
 * 
 * DFS+剪枝: 第一版超时 第二版1219ms 第三版270ms 第四版55ms 第五版53ms
 * 
 * @author Johnny
 * 
 *         2021年1月18日 下午3:15:11
 */
public class N494 {

	public static void main(String[] args) {
//		int[] nums = { 16, 40, 9, 17, 49, 32, 30, 10, 38, 36, 31, 22, 3, 36, 32, 2, 26, 17, 30, 47 };
		int[] nums = { 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100,
				100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100,
				100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100,
				100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100,
				100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100, 100 };
		int S = 0;
		System.out.println(new N494().findTargetSumWays2(nums, S));
	}

	int res = 0;
	int[] dp;

	/**
	 * dfs遍历
	 * 
	 * @param nums
	 * @param S
	 * @return
	 */
	public int findTargetSumWays(int[] nums, int S) {
		int n = nums.length;
		Integer numbers[] = new Integer[n];
		for (int i = 0; i < n; i++) {
			numbers[i] = new Integer(nums[i]);
		}
		// 这里倒序排列， 目的是为了更快的触发剪枝
		Arrays.sort(numbers, Collections.reverseOrder());
		for (int i = 0; i < n; i++) {
			nums[i] = numbers[i];
		}
		// dp保存第i个数字之后所有数字的最大绝对值
		dp = new int[n];
		for (int i = n - 1; i >= 0; i--) {
			int max = 0;
			for (int j = i; j < n; j++) {
				max += nums[j];
			}
			dp[i] = max;
		}
		dfs(nums, 0, true, S);
		dfs(nums, 0, false, S);
		return res;
	}

	/**
	 * 
	 * @param nums
	 * @param row
	 * @param minus  是否为减号
	 * @param target
	 */
	public void dfs(int[] nums, int row, boolean minus, int target) {
		int val = nums[row];
		if (val != 0 && minus) {
			val *= -1;
		}
		int n = nums.length;
		// 如果搜索到最后一个数字， 且target相等，表示找到了一个结果。计数器加一
		if (val == target && row == n - 1) {
			res++;
			return;
		}
		int newTarget = target - val;
		int nextRow = row + 1;
		if (nextRow > n - 1) {
			return;
		}
		// 如果当前的target绝对值已经大于dp了， 表示后面已经不可能找到target了。
		// 直接裁剪掉。
		if (Math.abs(newTarget) > dp[nextRow]) {
			return;
		}
		dfs(nums, nextRow, true, newTarget);
		dfs(nums, nextRow, false, newTarget);
	}

	public int findTargetSumWays2(int[] nums, int S) {
		int sum = 0;
		for (int n : nums) {
			sum += n;
		}
		if (S > sum || (sum + S) % 2 != 0) {
			return 0;
		}
		int capacity = (sum + S) / 2;
		int dp[] = new int[capacity + 1];
		dp[0] = 1;
		for (int n : nums) {
			for (int i = capacity; i >= n; i--) {
				dp[i] += dp[i - n];
			}
		}
		return dp[capacity];
	}

}

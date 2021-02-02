package leetcode.Jan28;

public class MaximumSubarray {

	public static void main(String[] args) {
		int[] nums = new int[] { -1, -2 };
		System.out.println(new MaximumSubarray().maxSubArray(nums));
	}

	public int maxSubArray(int[] nums) {
		int n = nums.length;
		if (n == 0) {
			return 0;
		}
		if (n == 1) {
			return nums[0];
		}
		int[] sum = new int[n];
		sum[0] = nums[0];
		int max = Integer.MIN_VALUE;
		for (int i = 1; i < n; i++) {
			sum[i] = sum[i - 1] + nums[i];
			max = Math.max(max, sum[i]);
		}

		for (int i = 0; i < n; i++) {
			for (int j = i; j < n; j++) {
				if (i == j) {
					max = Math.max(max, nums[i]);
				} else {
					// sum[i,j] = sum[0,j] - sum[0,i] + nums[i]
					max = Math.max(max, sum[j] - sum[i] + nums[i]);
				}
			}
		}
		return max;
	}

	/**
	 * DP
	 * 
	 * @param nums
	 * @return
	 */
	public int maxSubArrayDP(int[] nums) {
		int prefixSum = 0, maxSum = nums[0];

		for (int n : nums) {
			prefixSum = Math.max(prefixSum + n, n);
			maxSum = Math.max(maxSum, prefixSum);
		}
		return prefixSum;

	}
}

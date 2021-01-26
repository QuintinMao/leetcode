package leetcodeTop100;

/**
 * House Robber
 * 
 * @author Johnny
 * 
 *         2021年1月20日 下午6:30:05
 */
public class N198 {

	public static void main(String[] args) {
		int[] nums = { 1, 2, 3, 1 };
		System.out.println(new N198().rob(nums));
	}

	/**
	 * 动态规划，
	 * 
	 * @param nums
	 * @return
	 */
	public int rob(int[] nums) {
		int n = nums.length;
		if (n == 0) {
			return 0;
		}
		int not = 0;
		int rob = nums[0];
		for (int i = 1; i < n; i++) {
			int temp = not;
			not = Math.max(rob, not);
			rob = temp + nums[i];
		}
		return Math.max(rob, not);
	}

}

package leetcodeTop100;

/**
 * N1.Two Sum 两数之和
 * 
 * @author Johnny
 * 
 *         2021年1月7日 上午11:30:42
 */
public class N1 {

	public static void main(String[] args) {
		int[] nums = { 2, 7, 11, 15 };
		int target = 9;
		System.out.println(new N1().twoSum(nums, target));
	}

	public int[] twoSum(int[] nums, int target) {
		for (int i = 0; i < nums.length; i++) {
			if (i == nums.length - 1) {
				continue;
			}

			for (int j = i + 1; j < nums.length; j++) {
				if (nums[i] + nums[j] == target) {
					return new int[] { i, j };
				}
			}

		}
		return new int[0];
	}
}

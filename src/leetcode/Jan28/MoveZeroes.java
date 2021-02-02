package leetcode.Jan28;

/**
 * 283
 * 
 * @author Johnny
 * 
 *         2021年1月28日 下午6:33:04
 */
public class MoveZeroes {

	public static void main(String[] args) {
		int[] nums = { 2, 1 };
		new MoveZeroes().moveZeroes(nums);

	}

	public void moveZeroes(int[] nums) {

		int n = nums.length;
		// 边界条件1
		if (n < 2) {
			return;
		}
		int s = 0;
		while (nums[s] != 0 && s < n - 1) {
			s++;
		}
		// 边界条件2
		if (s == n - 1) {
			return;
		}

		for (int i = s; i < nums.length; i++) {
			if (nums[i] == 0) {
				continue;
			} else {
				nums[s] = nums[i];
				nums[i] = 0;
				s++;
			}
		}
	}

	/**
	 * 双指针
	 * 
	 * @param nums
	 */
	public void moveZeroesTP(int[] nums) {
		int n = nums.length;
		int left = 0, right = 0;
		while (right < n) {
			if (nums[right] != 0) {
				if (left != right) {
					nums[left] = nums[right];
					nums[right] = 0;
				}
				left++;
			}
			right++;
		}
	}
}

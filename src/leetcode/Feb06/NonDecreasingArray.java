package leetcode.Feb06;

/**
 * 665.每日一题
 * 
 * @author Johnny
 * 
 *         2021年2月7日 上午11:23:41
 */
public class NonDecreasingArray {

	public static void main(String[] args) {
		int[] nums = { -1, 4, 2, 3 };
		System.out.println(new NonDecreasingArray().checkPossibility(nums));
	}

	public boolean checkPossibility(int[] nums) {
		int n = nums.length;
		if (n < 3) {
			return true;
		}
		boolean modified = false;
		for (int i = 1; i < n; ++i) {
			if (nums[i - 1] > nums[i]) {
				if (modified) {
					return false;
				}
				if (i - 1 == 0 || (i - 1 > 0 && nums[i] >= nums[i - 2])) {
					nums[i - 1] = nums[i];
				} else {
					nums[i] = nums[i - 1];
				}
				modified = true;
			}
		}
		return true;
	}
}

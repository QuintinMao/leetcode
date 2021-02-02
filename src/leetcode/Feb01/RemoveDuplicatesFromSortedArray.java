package leetcode.Feb01;

/**
 * 26
 * 
 * 暴力，双指针
 * @author Johnny
 * 
 *         2021年2月1日 下午6:03:43
 */
public class RemoveDuplicatesFromSortedArray {
	public static void main(String[] args) {
		int[] nums = { 1, 2 };
		System.out.println(new RemoveDuplicatesFromSortedArray().removeDuplicates(nums));
	}

	public int removeDuplicatesDP(int[] nums) {
		int n = nums.length;
		if (n < 2) {
			return n;
		}
		int i = 0;
		for (int j = 1; j < n; j++) {
			if (nums[j] != nums[i]) {
				i++;
				nums[i] = nums[j];
			}
		}
		return i + 1;
	}

	public int removeDuplicates(int[] nums) {
		int n = nums.length;
		if (n < 2) {
			return n;
		}
		int res = 0;
		int scaned = 0;
		for (int i = 0; i < n - 1; i++) {
			if (nums[i] < nums[i + 1]) {
				scaned++;
				continue;
			} else {
				boolean find = false;
				for (int j = i + 2; j < n; j++) {
					if (nums[j] > nums[i]) {
						nums[i + 1] = nums[j];
						find = true;
						break;
					}
				}
				if (!find) {
					res = i + 1;
					break;
				}
			}
		}
		return scaned == n - 1 ? n : res;
	}
}

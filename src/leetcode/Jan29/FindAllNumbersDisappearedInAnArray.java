package leetcode.Jan29;

import java.util.ArrayList;
import java.util.List;

/**
 * 448
 * 
 * @author Johnny
 * 
 *         2021年1月29日 下午6:31:11
 */
public class FindAllNumbersDisappearedInAnArray {
	public static void main(String[] args) {
		int[] nums = { 4, 3, 2, 7, 8, 2, 3, 1 };
		@SuppressWarnings("unused")
		List<Integer> res = new FindAllNumbersDisappearedInAnArray().findDisappearedNumbers(nums);
	}

	public List<Integer> findDisappearedNumbers(int[] nums) {
		List<Integer> res = new ArrayList<Integer>();
		int n = nums.length;
		for (int i = 0; i < n; i++) {
			int cur = i;
			while (nums[cur] != cur + 1) {
				int temp = nums[nums[cur] - 1];
				nums[nums[cur] - 1] = nums[cur];
				if (cur == 0) {
					nums[cur] = 0;
				}
				cur = temp - 1;
			}
		}

		for (int i = 0; i < n; i++) {
			if (nums[i] == 0)
				res.add(i + 1);
		}
		return res;
	}
}

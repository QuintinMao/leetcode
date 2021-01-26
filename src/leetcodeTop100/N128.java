package leetcodeTop100;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * N128. Longest Consecutive Sequence
 * 
 * O(n)
 * @author Johnny
 * 
 *         2021年1月11日 下午2:34:29
 */
public class N128 {

	public static void main(String[] args) {
		int[] nums = { 100, 4, 200, 1, 3, 2 };
		System.out.println(new N128().longestConsecutive(nums));
	}

	public int longestConsecutive2(int[] nums) {
		if (nums.length == 0) {
			return 0;
		}
		int result = 0;
		Map<Integer, Integer> mapper = new HashMap<>();
		for (int n : nums) {
			if (!mapper.containsKey(n)) {
				int left = mapper.getOrDefault(n - 1, 0);
				int right = mapper.getOrDefault(n + 1, 0);
				int len = left + 1 + right;
				result = Math.max(len, result);
				mapper.put(n, len);
				mapper.put(n + right, len);
				mapper.put(n - left, len);
			}
		}
		return result;

	}

	public int longestConsecutive(int[] nums) {
		if (nums.length == 0) {
			return 0;
		}
		Arrays.sort(nums);
		int result = 0;
		int counter = 1;
		for (int i = 1; i < nums.length; i++) {
			if (nums[i] == nums[i - 1]) {
				continue;
			}
			// 如果是连续数字
			if (nums[i] - 1 == nums[i - 1]) {
				counter++;
			} else {
				result = Math.max(result, counter);
				counter = 1;
			}
		}
		result = Math.max(result, counter);
		return result;
	}

}

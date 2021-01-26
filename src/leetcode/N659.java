package leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 分割数组为连续子序列
 * 
 * 给你一个按升序排序的整数数组 num（可能包含重复数字），请你将它们分割成一个或多个长度至少为 3 的子序列，其中每个子序列都由连续整数组成。
 * 
 * 如果可以完成上述分割，则返回 true ；否则，返回 false
 * 
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/split-array-into-consecutive-subsequences
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class N659 {

	public static void main(String[] args) {
//		int[] nums = { 1, 2, 2, 3, 3, 4, 5, 7, 7, 8, 8, 9, 9, 9, 10, 10, 11, 11, 12 };
		int[] nums = { 1, 2, 3, 3, 4, 5 };
		System.out.println(isPossible(nums));
	}

	public static boolean isPossible(int[] nums) {
		Map<Integer, Integer> countMap = new HashMap<>();
		Map<Integer, Integer> endMap = new HashMap<>();
		for (int x : nums) {
			countMap.compute(x, (k, v) -> v == null ? 1 : v + 1);
		}

		for (int cur : nums) {
			if (countMap.getOrDefault(cur, 0) > 0) {
				if (endMap.getOrDefault(cur - 1, 0) > 0) {
					countMap.compute(cur, (k, v) -> --v);
					endMap.compute(cur - 1, (k, v) -> --v);
					endMap.compute(cur, (k, v) -> v == null ? 1 : v + 1);
				} else {
					int count1 = countMap.getOrDefault(cur + 1, 0);
					int count2 = countMap.getOrDefault(cur + 2, 0);
					if (count1 <= 0 || count2 <= 0) {
						return false;
					}
					countMap.compute(cur, (k, v) -> --v);
					countMap.compute(cur + 1, (k, v) -> --v);
					countMap.compute(cur + 2, (k, v) -> --v);
					endMap.compute(cur + 2, (k, v) -> v == null ? 1 : v + 1);
				}
			}
		}
		return true;
	}

}

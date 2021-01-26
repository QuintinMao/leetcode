package leetcodeTop100;

import java.util.ArrayList;
import java.util.List;

/**
 * 子集
 * @author Johnny
 * 
 * 2021年1月7日 上午11:34:39
 */
public class N78 {

	public static void main(String[] args) {
		int[] nums = { 1, 2, 3 };
		System.out.println(new N78().subsets1(nums));
	}

	public List<List<Integer>> subsets(int[] nums) {
		List<List<Integer>> rs = new ArrayList<>();
		rs.add(new ArrayList<>());
		for (int n : nums) {
			List<List<Integer>> newRs = new ArrayList<>();
			for (List<Integer> foo : rs) {
				List<Integer> bar = new ArrayList<>(foo);
				bar.add(n);
				newRs.add(bar);
			}
			rs.addAll(newRs);
		}
		return rs;
	}

	public List<List<Integer>> subsets1(int[] nums) {
		int n = nums.length;
		List<List<Integer>> rs = new ArrayList<>();
		for (int i = 0; i < Math.pow(2, n); i++) {
			List<Integer> foo = new ArrayList<>();
			String binary = Integer.toBinaryString(i);
			for (int j = 0; j < binary.length(); j++) {
				if (binary.charAt(j) == '1') {
					foo.add(nums[binary.length() - j - 1]);
				}
			}
			rs.add(foo);
		}
		return rs;
	}
}

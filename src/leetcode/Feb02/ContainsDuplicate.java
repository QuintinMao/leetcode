package leetcode.Feb02;

/**
 * 217. 方法一：排序。方法二：哈希表
 * 
 * @author Johnny
 * 
 *         2021年2月2日 下午3:34:51
 */
public class ContainsDuplicate {

	public static void main(String[] args) {
		int[] nums = { 1, 2, 3, 1 };
		System.out.println(new ContainsDuplicate().containsDuplicate(nums));
	}

	public boolean containsDuplicate(int[] nums) {
		int max = 0;
		for (int n : nums) {
			max = Math.max(max, n);
		}
		int[] counter = new int[max + 1];
		for (int n : nums) {
			if (counter[n] > 0) {
				return true;
			}
			counter[n]++;
		}
		return false;
	}

}

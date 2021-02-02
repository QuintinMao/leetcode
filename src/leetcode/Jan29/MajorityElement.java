package leetcode.Jan29;

import java.util.Arrays;

/**
 * 169
 * 哈希表
 * 排序
 * 随机一个数字，检查他出现的次数是否超过一半
 * 分治
 * 投票
 * @author Johnny
 * 
 *         2021年1月29日 下午1:06:04
 */
public class MajorityElement {

	public static void main(String[] args) {
		int[] nums = { 3, 3, 4 };
		System.out.println(new MajorityElement().majorityElement(nums));
	}

	public int majorityElement(int[] nums) {
		Arrays.sort(nums);
		return nums[nums.length / 2];
	}

}

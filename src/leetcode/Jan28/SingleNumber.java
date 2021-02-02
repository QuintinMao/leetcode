package leetcode.Jan28;

public class SingleNumber {

	public static void main(String[] args) {
		int[] nums = { 2, 2, 1, 3, 1 };
		System.out.println(new SingleNumber().singleNumber(nums));
	}

	// 异或操作比较骚气
	public int singleNumber(int[] nums) {
		int single = 0;
		for (int num : nums) {
			single ^= num;
		}
		return single;
	}
}

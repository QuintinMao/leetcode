package leetcodeTop100;

/**
 * Product of Array Except Self
 * 
 * @author Johnny
 * 
 *         2021年1月26日 上午11:53:58
 */
public class N238 {

	public static void main(String[] args) {
		int[] nums = { 1, 2, 3, 4, 0 };
		int[] res = new N238().productExceptSelf(nums);
		for (int n : res) {
			System.out.print(n + ",");
		}
	}

	public int[] productExceptSelf(int[] nums) {
		int len = nums.length;
		// [0,i]的乘积
		int[] prefix = new int[len];
		// [i,len-1]的乘积
		int[] suffix = new int[len];
		prefix[0] = nums[0];
		for (int i = 1; i < len; i++) {
			prefix[i] = nums[i] * prefix[i - 1];
		}
		suffix[len - 1] = nums[len - 1];
		for (int i = len - 2; i >= 0; i--) {
			suffix[i] = nums[i] * suffix[i + 1];
		}
		int[] res = new int[len];
		// 处理边界问题
		res[0] = suffix[1];
		res[len - 1] = prefix[len - 2];
		
		// res[i] = prefix[i-1] * suffix[i+1];
		for (int i = 1; i < len - 1; i++) {
			res[i] = prefix[i - 1] * suffix[i + 1];
		}
		return res;

	}

}

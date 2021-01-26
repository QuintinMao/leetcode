package leetcodeTop100;

/**
 * N31. Next Permutation
 * 
 * @author Johnny
 * 
 *         2021年1月14日 下午5:10:22
 */
public class N31 {

	public static void main(String[] args) {
		int[] nums = { 3, 2, 1 };
		new N31().nextPermutation(nums);
		for (int n : nums) {
			System.out.print(n + ",");
		}
	}

	public void nextPermutation(int[] nums) {
		int n = nums.length;
		Integer flag = null;
		for (int i = n - 1; i > 0; i--) {
			if (nums[i] > nums[i - 1]) {
				int swapVal = nums[i];
				int swapIndex = i;
				for (int j = i + 1; j <= n - 1; j++) {
					if (nums[j] > nums[i - 1]) {
						if (nums[j] < swapVal) {
							swapVal = nums[j];
							swapIndex = j;
						}
					}
				}
				nums[swapIndex] = nums[i - 1];
				nums[i - 1] = swapVal;
				flag = i;
				break;
			}
		}
		sort(nums, flag == null ? 0 : flag, n - 1);
	}

	public void sort(int[] nums, int begin, int end) {
		for (int i = 1; i <= end - begin; i++) {
			for (int j = begin; j < end; j++) {
				if (nums[j] > nums[j + 1]) {
					int t = nums[j];
					nums[j] = nums[j + 1];
					nums[j + 1] = t;
				}
			}
		}
	}

}

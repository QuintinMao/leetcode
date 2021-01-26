package leetcode;

/**
 * Rotate Array  
 * @author Johnny
 * 
 * 2021年1月13日 下午3:28:26
 */
public class N189 {

	public static void main(String[] args) {
		int nums[] = { 1, 2 };
		int k = 2;
		new N189().rotate2(nums, k);
		for (int n : nums) {
			System.out.print(n + ",");
		}
	}

	public void rotate(int[] nums, int k) {
		for (int i = 1; i <= k; i++) {
			int t = nums[nums.length - 1];
			for (int j = nums.length - 1; j > 0; j--) {
				nums[j] = nums[j - 1];
			}
			nums[0] = t;
		}
	}

	public void rotate1(int[] nums, int k) {
		int n = nums.length;
		int[] afterRotate = new int[n];

		for (int i = 0; i < n; i++) {
			afterRotate[(i + k) % n] = nums[i];
		}

		System.arraycopy(afterRotate, 0, nums, 0, n);
	}

	public void rotate2(int[] nums, int k) {
		int nLen = nums.length;
		if (k == 0 || nLen == 1 || nLen % k == 0) {
			return;
		}
		int counter = 0;

		for (int i = 0; i < nums.length; i++) {
			int temp = nums[nLen - k % nLen + i], target = i;
			do {
				int n = nums[target];
				nums[target] = temp;
				counter++;
				temp = n;
				target = (target + k) % nLen;
			} while (target != i);
			if (counter == nLen) {
				break;
			}
		}
	}
}

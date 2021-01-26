package leetcodeTop100;

/**
 * Sort Colors- Dutch flag
 * 
 * @author Johnny
 * 
 *         2021年1月22日 下午12:46:50
 */
public class N75 {

	public static void main(String[] args) {
		int[] nums = { 2, 0, 2, 1, 1, 0, 1, 2, 1, 1, 1, 2, 2, 0, 0, 0, 1, 1, 2, 0, 0, 1, 1, 0, 2 };
		new N75().sortColors4(nums);
		for (int n : nums) {
			System.out.print(n + ",");
		}
	}

	public void sortColors(int[] nums) {
		int n = nums.length;
		for (int i = 0; i < n - 1; i++) {
			for (int j = 0; j < n - 1; j++) {
				if (nums[j] >= nums[j + 1]) {
					int temp = nums[j];
					nums[j] = nums[j + 1];
					nums[j + 1] = temp;
				}
			}
		}
	}

	public void sortColors2(int[] nums) {
		int n = nums.length;
		if (n == 1) {
			return;
		}
		for (int i = 0; i < n - 1; i++) {
			// 找最后一个0交换
			if (nums[i] == 2) {
				boolean swap = false;
				for (int j = n - 1; j > i; j--) {
					if (nums[j] == 0) {
						swap(nums, i, j);
						swap = true;
						break;
					}
				}
				// 没找到0找最后一个1进行交换
				if (!swap) {
					for (int j = n - 1; j > i; j--) {
						if (nums[j] == 1) {
							swap(nums, i, j);
							--i;
							break;
						}
					}
				}

			} else if (nums[i] == 1) {
				// 找右边第一个0进行交换
				for (int j = i + 1; j < n; j++) {
					if (nums[j] == 0) {
						swap(nums, i, j);
						break;
					}
				}
			}
		}
	}

	public void sortColors3(int[] nums) {
		int n = nums.length;
		if (n == 1) {
			return;
		}
		// 0挪到前面
		int left = 0, right = n - 1;
		while (left < right) {
			if (nums[right] != 0) {
				right--;
			} else {
				if (nums[left] == 0) {
					left++;
				} else {
					swap(nums, left, right);
				}
			}
		}
		// 2挪到后面
		left = 0;
		right = n - 1;
		while (left < right) {
			if (nums[left] != 2) {
				left++;
			} else {
				if (nums[right] == 2) {
					right--;
				} else {
					swap(nums, left, right);
				}
			}
		}
	}

	public void swap(int[] nums, int i, int j) {
		int temp = nums[i];
		nums[i] = nums[j];
		nums[j] = temp;
	}

	public void sortColors4(int[] nums) {
		int len = nums.length;
		if (len < 2) {
			return;
		}

		// all in [0, p0) == 0
		// all in [p0, i) == 0
		// all in (p2, len - 1] == 0

		int p0 = 0;
		int i = 0;
		int p2 = len - 1;
		while (i <= p2) {
			if (nums[i] == 0) {
				swap(nums, i, p0);
				p0++;
				i++;
			} else if (nums[i] == 1) {
				i++;
			} else {
				// nums[i] ==2;
				swap(nums, i, p2);
				p2--;
			}
		}
	}

}

package leetcodeTop100;

/**
 * Find First and Last Position of Element in Sorted Array
 * 
 * @author Johnny
 * 
 *         2021年1月15日 下午6:03:01
 */
public class N34 {

	public static void main(String[] args) {
		int[] nums = { 2, 2 };
		int target = 2;
		int[] rs = new N34().searchRange2(nums, target);
		System.out.println("[" + rs[0] + "," + rs[1] + "]");
	}

	public int[] searchRange(int[] nums, int target) {
		int[] rs = { -1, -1 };
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] == target) {
				if (rs[0] == -1) {
					rs[0] = i;
				} else {
				}
				rs[1] = i;
			}
		}
		return rs;
	}

	public int[] searchRange2(int[] nums, int target) {
		int[] rs = { -1, -1 };
		int base = binarySearch(nums, target);
		if (base == -1) {
			return rs;
		}
		int l = base, r = l;
		while (l - 1 >= 0 && nums[l - 1] == target) {
			l--;
		}
		rs[0] = l;
		while (r + 1 < nums.length && nums[r + 1] == target) {
			r++;
		}
		rs[1] = r;
		return rs;
	}

	public int binarySearch(int[] nums, int target) {
		int l = 0;
		int r = nums.length - 1;
		while (l <= r) {
			int mid = (l + r) / 2;
			if (nums[mid] == target) {
				return mid;
			}
			if (nums[mid] < target) {
				l = mid + 1;
			}
			if (nums[mid] > target) {
				r = mid - 1;
			}
		}
		return -1;
	}

}

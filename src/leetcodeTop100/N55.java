package leetcodeTop100;

/**
 * Jump Game
 * 
 * @author Johnny
 * 
 *         2021年1月15日 下午4:00:15
 */
public class N55 {

	public static void main(String[] args) {
//		int[] nums = { 2, 0, 0 };
		int[] nums = { 3, 2, 1, 0, 4 };
		System.out.println(new N55().canJump2(nums));

	}

	public boolean canJump2(int[] nums) {
	    if (nums.length == 1) {
			return true;
		}
		if (nums[0] == 0) {
			return false;
		}
        //维护一个cursor向右能够jump的最远距离（这个距离由cursor左边的元素决定）
		int longestJump = 0;
        int len = nums.length;
		for (int i = 0; i < len - 1; i++) {
			if (nums[i] != 0) {
                //更新最远距离
                if(nums[i]>longestJump){
                    //如果能直接跳到最后一个元素，直接返回true
                    if (nums[i] >= len - i) {
					    return true;
				    }
                    longestJump = nums[i];
                }
			}
            //进入下一轮循环之前消耗一个最远距离。
            //如果最远距离<0表示不能跳跃到下一个。返回false
			if (--longestJump < 0) {
				return false;
			}

		}
		return true;

	}

	/**
	 * 暴力解法
	 * @param nums
	 * @return
	 */
	public boolean canJump(int[] nums) {
		if (nums.length == 1) {
			return true;
		}
		int target = nums.length - 1;
		while (target != 0) {
			int rs = helper(nums, target);
			if (rs == target) {
				return false;
			}
			target = rs;
		}

		return true;
	}

	public int helper(int[] nums, int target) {
		if (target == 0) {
			return 0;
		}
		int rs = target;
		for (int i = target - 1; i >= 0 && i >= target - 100_000; i--) {
			if (nums[i] >= target - i) {
				rs = Math.min(rs, i);
			}
		}
		return rs;
	}

}

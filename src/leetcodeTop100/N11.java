package leetcodeTop100;

import java.util.Arrays;

/**
 * Container With Most Water
 * 
 * @author Johnny
 * 
 *         2021年1月23日 下午2:36:47
 */
public class N11 {

	public static void main(String[] args) {
		int[] height = new int[] { 1, 8, 6, 2, 5, 4, 8, 3, 7 };
		System.out.println(new N11().maxArea2(height));
	}

	/**
	 * 暴力
	 * 
	 * @param height
	 * @return
	 */
	public int maxArea(int[] height) {
		int res = 0;
		int n = height.length;
		int[][] cache = new int[n][n];
		for (int[] foo : cache) {
			Arrays.fill(foo, -1);
		}
		for (int i = 0; i < n - 1; i++) {
			for (int j = n - 1; j > i; j--) {
				int area = 0;
				if (cache[i][j] != -1) {
					area = cache[i][j];
				} else {
					area = (j - i) * Math.min(height[i], height[j]);
					cache[i][j] = area;
				}
				res = Math.max(res, area);
			}
		}
		return res;
	}

	public int maxArea2(int[] height) {
		int left = 0, right = height.length - 1;
		int res = 0;
		while (left < right) {
			int area = (right - left) * Math.min(height[left], height[right]);
			res = Math.max(res, area);

			if (height[left] <= height[right]) {
				left++;
			} else {
				right--;
			}
		}
		return res;

	}

}

package leetcode.Jan29;

import java.util.Stack;

/**
 * 1631
 * 
 * @author Johnny
 * 
 *         2021年1月29日 上午11:20:00
 */
public class PathWithMinEffort {

	// up,down,left,right
	static final int[][] DIRECTIONS = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

	/**
	 * 要求最小高度差x， 那么如果高度差大于x时时必定可以到达右下角得。 所以我们二分法，0，100000之间搜索数字a 验证是否可以到达右下角。
	 * 最终可以找到a=x。
	 * 
	 * @param heights
	 * @return
	 */

	public int minimumEffortPath(int[][] heights) {
		int m = heights.length;
		int n = heights[0].length;
		//
		int left = 1 - 1, right = 1_000_000 - 1, res = -1;

		while (left < right) {
			int mid = (left + right) / 2;
			Stack<int[]> stack = new Stack<>();
			stack.push(new int[] { 0, 0 });
			boolean[] visited = new boolean[m * n];
			visited[0] = true;
			while (!stack.isEmpty()) {
				int[] ori = stack.pop();
				for (int i = 0; i < 4; i++) {
					int lookX = ori[0] + DIRECTIONS[i][0];
					int lookY = ori[1] + DIRECTIONS[i][1];
					if (lookX >= 0 && lookX < m && lookY >= 0 && lookY < n && !visited[lookX * n + lookY]
							&& Math.abs(heights[ori[0]][ori[1]] - heights[lookX][lookY]) <= mid) {
						visited[lookX * n + lookY] = true;
						if (lookX == m - 1 && lookY == n - 1) {
							break;
						}
						stack.push(new int[] { lookX, lookY });
					}
				}
			}
			if (visited[m * n - 1]) {
				res = mid;
				right = mid - 1;
			} else {
				left = mid + 1;
			}
		}
		return res;
	}
}

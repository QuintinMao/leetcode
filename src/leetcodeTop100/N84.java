package leetcodeTop100;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

/**
 * No.84 Largest Rectangle in Histogram
 * 
 * @author Johnny
 * 
 *         2021年1月8日 下午12:03:55
 */
public class N84 {

	public static void main(String[] args) {
		int[] heights = { 2, 1, 5, 6, 2, 3 };
		System.out.println(new N84().largestRectangleArea(heights));
	}

	public int largestRectangleAreaTimeout1(int[] heights) {
		int len = heights.length;
		int largest = heights[0];
		for (int i = 1; i < len; i++) {
			if (heights[i] == 0) {
				continue;
			}
			int j = i - 1;
			int temp = heights[i];
			int min = temp;
			while (j >= 0) {
				if (heights[j] == 0) {
					break;
				}
				min = Math.min(heights[j], min);
				temp = Math.max(min * (i + 1 - j), temp);
				j--;
			}
			largest = Math.max(largest, temp);
		}
		return largest;
	}

	public int largestRectangleAreaTimeout2(int[] heights) {
		int len = heights.length;

		if (len == 0) {
			return 0;
		}

		int result = 0;
		for (int i = 0; i < len; i++) {
			int left = i;
			int curHeight = heights[i];
			while (left > 0 && heights[left - 1] >= curHeight) {
				left--;
			}

			int right = i;
			while (right < len - 1 && heights[right + 1] >= curHeight) {
				right++;
			}

			int width = right - left + 1;
			result = Math.max(result, width * curHeight);

		}
		return result;

	}

	public int largestRectangleArea(int[] heights) {
		int len = heights.length;
		if (len == 0) {
			return 0;
		}
		if (len == 1) {
			return heights[0];
		}

		int area = 0;

		int[] newHeights = new int[len + 2];
		System.arraycopy(heights, 0, newHeights, 1, len);
		len += 2;
		heights = newHeights;

		Deque<Integer> stack = new ArrayDeque<>();
		stack.addLast(0);
		for (int i = 1; i < len; i++) {
			while (heights[stack.peekLast()] > heights[i]) {
				int height = heights[stack.pollLast()];
				int width = i - stack.peekLast() - 1;
				area = Math.max(area, width * height);
			}
			stack.addLast(i);
		}
		return area;
	}

	public int largestRectangleArea2(int[] heights) {
		int n = heights.length;
		int[] left = new int[n];
		int[] right = new int[n];

		Stack<Integer> mono_stack = new Stack<Integer>();
		for (int i = 0; i < n; ++i) {
			while (!mono_stack.isEmpty() && heights[mono_stack.peek()] >= heights[i]) {
				mono_stack.pop();
			}
			left[i] = (mono_stack.isEmpty() ? -1 : mono_stack.peek());
			mono_stack.push(i);
		}

		mono_stack.clear();
		for (int i = n - 1; i >= 0; --i) {
			while (!mono_stack.isEmpty() && heights[mono_stack.peek()] >= heights[i]) {
				mono_stack.pop();
			}
			right[i] = (mono_stack.isEmpty() ? n : mono_stack.peek());
			mono_stack.push(i);
		}

		int ans = 0;
		for (int i = 0; i < n; ++i) {
			ans = Math.max(ans, (right[i] - left[i] - 1) * heights[i]);
		}
		return ans;

	}

}

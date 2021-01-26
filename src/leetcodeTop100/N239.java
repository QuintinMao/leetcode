package leetcodeTop100;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 239. Sliding Window Maximum
 * @author Johnny
 * 
 * 2021年1月13日 下午3:30:27
 */
public class N239 {

	public static void main(String[] args) {
		int[] nums = { 1, 3, -1, -3, 5, 3, 6, 7 };
		int k = 3;
		int[] rs = new N239().maxSlidingWindows(nums, k);
		for (int i = 0; i < rs.length; i++) {
			int j = rs[i];
			System.out.println(j);
		}
	}

	public int[] maxSlidingWindows(int[] nums, int k) {
		int n = nums.length;
		int[] result = new int[n - k + 1];
		Deque<Integer> deque = new LinkedList<>();
		for (int i = 0; i < k; i++) {
			while (!deque.isEmpty() && deque.peekLast() < nums[i]) {
				deque.pollLast();
			}
			deque.offer(nums[i]);
		}

		result[0] = deque.peek();

		for (int i = k; i < n; i++) {
			if (deque.peek() == nums[i - k]) {
				deque.poll();
			}
			while (!deque.isEmpty() && deque.peekLast() < nums[i]) {
				deque.pollLast();
			}
			deque.offer(nums[i]);
			result[i - k + 1] = deque.peek();
		}
		return result;
	}

	public int[] maxSlidingWindow(int[] nums, int k) {
		int n = nums.length;
		int[] result = new int[n - k + 1];
		Deque<Integer> deque = new LinkedList<>();

		for (int i = 0; i < k; i++) {
			while (!deque.isEmpty() && deque.peekLast() < nums[i]) {
				deque.pollLast();
			}
			deque.offerLast(nums[i]);
		}

		result[0] = deque.peekFirst();

		for (int i = k; i < n; i++) {
			if (deque.peekFirst() == nums[i - k]) {
				deque.pollFirst();
			}
			while (!deque.isEmpty() && deque.peekLast() < nums[i]) {
				deque.pollLast();
			}
			deque.offerLast(nums[i]);
			result[i - k + 1] = deque.peekFirst();
		}
		return result;
	}

	public int[] maxSlidingWindowTimeout(int[] nums, int k) {
		int n = nums.length;
		int[] result = new int[n - k + 1];
		Integer lastWindowValue = null;
		for (int i = 0; i < result.length; i++) {
			int maxValue = Integer.MIN_VALUE;
			if (lastWindowValue != null) {
				maxValue = Math.max(lastWindowValue, nums[i + k - 1]);
			} else {
				for (int j = i; j < k + i; j++) {
					maxValue = Math.max(maxValue, nums[j]);
				}
			}
			result[i] = maxValue;
			if (nums[i] < maxValue) {
				lastWindowValue = maxValue;
			} else {
				lastWindowValue = null;
			}
		}
		return result;
	}

	public int[] maxSlidingWindowF(int[] nums, int k) {
		int n = nums.length;
		int[] result = new int[n - k + 1];

		int firstWindowMaxValue = Integer.MIN_VALUE;
		for (int i = 0; i < k; i++) {
			firstWindowMaxValue = Math.max(firstWindowMaxValue, nums[i]);
		}
		result[0] = firstWindowMaxValue;
		for (int i = 1; i < result.length; i++) {
			result[i] = Math.max(nums[i + k - 1], result[i - 1]);
		}
		return result;
	}

}

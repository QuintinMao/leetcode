package leetcode.Feb03;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 480.
 * 
 * base 295.
 * 
 * @author Johnny
 * 
 *         2021年2月3日 上午11:13:22
 */
public class SlidingWindowMedian {
	public static void main(String[] args) {
		int[] nums = { -2147483648, -2147483648, 2147483647, -2147483648, -2147483648, -2147483648, 2147483647,
				2147483647, 2147483647, 2147483647, -2147483648, 2147483647, -2147483648 };
		int k = 2;
		double[] res = new SlidingWindowMedian().medianSlidingWindow(nums, k);
		for (double r : res) {
			System.out.println(r);
		}
	}

	public double[] medianSlidingWindow(int[] nums, int k) {
		int n = nums.length;
		MHeap mh = new MHeap(k);
		for (int i = 0; i < k; ++i) {
			mh.insert(nums[i]);
		}
		double[] res = new double[n - k + 1];
		res[0] = mh.getMedina();
		for (int i = k; i < n; ++i) {
			mh.insert(nums[i]);
			mh.remove(nums[i - k]);
			res[i - k + 1] = mh.getMedina();
		}
		return res;
	}

	public double[] medianSlidingWindowViolence(int[] nums, int k) {
		int n = nums.length;
		double[] res = new double[n - k + 1];
		boolean even = (k & 1) == 0;
		for (int i = 0; i < res.length; i++) {
			int[] arr = new int[k];
			System.arraycopy(nums, i, arr, 0, k);
			Arrays.sort(arr);
			if (even) {
				long a = arr[k / 2];
				long b = arr[(k / 2) - 1];
				res[i] = (a + b) / 2d;
			} else {
				res[i] = arr[k / 2];
			}
		}
		return res;

	}
}

class MHeap {
	// 大根堆、最大堆、大顶堆：DESC
	Queue<Integer> maxHeap;
	// 小根堆、最小堆、小顶堆：ASC
	Queue<Integer> minHeap;
	// 最大堆和最小堆中的元素个数之和
	int k;
	// 最大堆和最小堆 当前包含的元素个数，需要扣除被「延迟删除」的元素
	int maxSize, minSize;
	// delayed delete ，记录「延迟删除」的元素，key 为元素，value 为需要删除的次数
	Map<Integer, Integer> dd;

	public MHeap(int k) {
		// DESC
		this.maxHeap = new PriorityQueue<>((x, y) -> y.compareTo(x));
		// default : asc
		this.minHeap = new PriorityQueue<>();
		this.k = k;
		this.maxSize = 0;
		this.minSize = 0;
		dd = new HashMap<>();
	}

	public double getMedina() {
		return (k & 1) == 1 ? maxHeap.peek() : ((double) maxHeap.peek() + minHeap.peek()) / 2;
	}

	public void insert(int num) {
		if (maxHeap.isEmpty() || num <= maxHeap.peek()) {
			maxHeap.offer(num);
			++maxSize;
		} else {
			minHeap.offer(num);
			++minSize;
		}
		makeBalance();
	}

	public void remove(int num) {
		dd.compute(num, (k, v) -> v == null ? 1 : ++v);
		if (num <= maxHeap.peek()) {
			--maxSize;
			if (num == maxHeap.peek()) {
				prune(maxHeap);
			}
		} else {
			--minSize;
			if (num == minHeap.peek()) {
				prune(minHeap);
			}
		}
		makeBalance();
	}

	// 调整 maxHeap 和 minHeap 中的元素个数，使得二者的元素个数满足要求
	private void makeBalance() {
		if (maxSize > minSize + 1) {
			// maxHeap 比 minHeap 元素多 2 个
			minHeap.offer(maxHeap.poll());
			--maxSize;
			++minSize;
			// maxHeap 堆顶元素被移除，需要进行 prune
			prune(maxHeap);
		} else if (maxSize < minSize) {
			// minHeap 比 maxHeap 元素多 1 个
			maxHeap.offer(minHeap.poll());
			++maxSize;
			--minSize;
			// minHeap 堆顶元素被移除，需要进行 prune
			prune(minHeap);
		}
	}

	// 不断地弹出 heap 的堆顶元素，并且更新哈希表
	public void prune(Queue<Integer> heap) {
		while (!heap.isEmpty()) {
			int num = heap.peek();
			if (dd.containsKey(num)) {
				dd.compute(num, (k, v) -> --v);
				if (dd.get(num) == 0) {
					dd.remove(num);
				}
				heap.poll();
			} else {
				break;
			}
		}
	}
}

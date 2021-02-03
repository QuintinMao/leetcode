package leetcode.Feb03;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 295.
 * 
 * advanced 480.
 * 
 * @author Johnny
 * 
 *         2021年2月3日 上午10:43:55
 */
public class FindMedianFromDataStream {

}

class MedianFinder {
	// 大根堆、最大堆、大顶堆：DESC
	Queue<Integer> maxHeap;
	// 小根堆、最小堆、小顶堆：ASC
	Queue<Integer> minHeap;
	// 最大堆和最小堆中的元素个数之和
	int count;

	/** initialize your data structure here. */
	public MedianFinder() {
		// DESC
		this.maxHeap = new PriorityQueue<>((x, y) -> y - x);
		// default : asc
		this.minHeap = new PriorityQueue<>();
		// default : 0
		this.count = 0;
	}

	public void addNum(int num) {
		++count;
		// 向最大堆中压入num
		maxHeap.offer(num);
		// 将最大堆的堆顶弹出压入最小堆（注意由于是优先队列，此时弹出的是最大堆中最大的值，并不一定是刚刚压入的num）
		minHeap.offer(maxHeap.poll());
		// count是奇数，需要把最小堆的对顶压入最大堆。
		if ((count & 1) != 0) {
			maxHeap.offer(minHeap.poll());
		}
	}

	public double findMedian() {
		// count是偶数时，中位数等于最大堆和最小堆的堆顶之和除以二
		if (count % 2 == 0) {
			return (maxHeap.peek() + minHeap.peek()) / 2d;
		} else {
			// count是奇数时，中位数等于最大堆的堆顶元素
			return maxHeap.peek();
		}
	}
}
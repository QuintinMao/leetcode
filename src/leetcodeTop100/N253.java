package leetcodeTop100;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Meeting Rooms II
 * 
 * @author Johnny
 * 
 *         2021年1月20日 下午4:21:51
 */
public class N253 {
	public static void main(String[] args) {
		int[][] intervals = { { 0, 30 }, { 5, 10 }, { 15, 20 } };
		System.out.println(new N253().minMettingRooms2(intervals));
	}

	public int minMettingRooms(int[][] intervals) {
		// 按照开始时间升序排列
		Arrays.sort(intervals, (x, y) -> x[0] - y[0]);
		Queue<int[]> rooms = new PriorityQueue<>((r1, r2) -> r1[1] - r2[1]);
		rooms.offer(intervals[0]);
		for (int i = 1; i < intervals.length; i++) {
			int[] interval = intervals[i];
			// 会议结束
			if (rooms.peek()[1] <= interval[0]) {
				rooms.poll();
			}
			rooms.offer(interval);
		}
		return rooms.size();
	}

	/**
	 * 两个数组， 分别存储开始时间和结束时间。
	 * 并且排序
	 * 两个指针分辨遍历开始和结束数组。
	 * 当开始指针的值大于等于结束指针的值，则表示要申请当前开始指针这个会议不需要申请新的会议室。维护使用中的会议室的最大值。开始指针和结束指针后移一位。
	 * 否则，表示需要申请新会议室，开始指针后移一位。
	 * 直到开始指针到达末尾，
	 * 返回最大值 
	 * @param intervals
	 * @return
	 */
	public int minMettingRooms2(int[][] intervals) {
		int res = 0;
		int n = intervals.length;
		int[] start = new int[n];
		int[] end = new int[n];
		for (int i = 0; i < n; i++) {
			start[i] = intervals[i][0];
			end[i] = intervals[i][1];
		}
		Arrays.sort(start);
		Arrays.sort(end);
		int j = 0;
		for (int i = 0; i < n; i++) {
			if (start[i] >= end[j]) {
				j++;
			} else {
				res += 1;
			}
		}
		return res;
	}
}

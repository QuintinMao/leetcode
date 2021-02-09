package leetcode.Feb08;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 384. 洗牌， 遍历,第i张牌与 第[i,len-1]张牌交换
 * 
 * @author Johnny
 * 
 *         2021年2月8日 下午4:25:00
 */
public class ShuffleAnArray {

	public static void main(String[] args) {
		int[] nums = { 1, 2, 3 };
		Solution s = new Solution(nums);
		s.shuffle();
		s.reset();
	}

	static class Solution {
		int[] ori;
		int[] array;

		Random r = new Random();

		public Solution(int[] nums) {
			array = nums;
			ori = nums.clone();
		}

		/** Resets the array to its original configuration and return it. */
		public int[] reset() {
			array = ori;
			ori = ori.clone();
			return array;
		}

		/** Returns a random shuffling of the array. */
		public int[] shuffle() {
			List<Integer> aux = getArrayCopy();

			for (int i = 0; i < array.length; i++) {
				int removeIdx = r.nextInt(aux.size());
				array[i] = aux.get(removeIdx);
				aux.remove(removeIdx);
			}

			return array;
		}

		private List<Integer> getArrayCopy() {
			List<Integer> asList = new ArrayList<Integer>();
			for (int i = 0; i < array.length; i++) {
				asList.add(array[i]);
			}
			return asList;
		}
	}
}

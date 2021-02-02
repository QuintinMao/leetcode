package leetcode.Feb01;

import java.util.HashMap;
import java.util.Map;

/**
 * 888
 * 
 * @author Johnny
 * 
 *         2021年2月1日 上午11:56:36
 */
public class FairCandySwap {

	public static void main(String[] args) {
		int[] A = new int[] { 35, 17, 4, 24, 10 };
		int[] B = new int[] { 63, 21 };
		int[] res = new FairCandySwap().fairCandySwap(A, B);
		System.out.println(res[0] + "," + res[1]);
	}

	public int[] fairCandySwap(int[] A, int[] B) {
		int sumA = 0, sumB = 0;
		Map<Integer, Integer> map = new HashMap<>();
		for (int n : A) {
			sumA += n;
		}
		for (int n : B) {
			sumB += n;
			map.put(n, n);
		}
		int dif = sumA - sumB;
		for (int n : A) {
			int key = n - (dif / 2);
			if (map.containsKey(key)) {
				return new int[] { n, key };
			}
		}
		return new int[] { -1, -1 };

	}
}

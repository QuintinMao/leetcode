package leetcodeTop100;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Array of Doubled Pairs
 * 
 * 
 * 给定一个长度为偶数的整数数组 A，只有对 A 进行重组后可以满足 “对于每个 0 <= i < len(A) / 2，都有 A[2 * i + 1] =
 * 2 * A[2 * i]” 时，返回 true；否则，返回 false。
 * 
 * 来源：力扣（LeetCode） 链接：https://leetcode-cn.com/problems/array-of-doubled-pairs
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class N954 {

	public static void main(String[] args) {
//		int[] a = { 0, 0, 0, 0, 1, -1, -1, -2, -2, 1, 1, 1, -2, -4, -3, -6, 2, 2, 2, 2, 0, 0, 1, 1, 2, 2 };
		int[] a = { -6, -3 };
		System.out.println(canReorderDoubled3rd(a));
	}

	public static boolean canReorderDoubled(int[] a) {
		Arrays.sort(a);
		Set<Integer> looked = new HashSet<>();
		for (int i = 0; i < a.length; i++) {
			if (i == a.length - 1 || looked.contains(i)) {
				continue;
			}
			boolean find = false;
			for (int j = i + 1; j < a.length; j++) {
				if (looked.contains(j)) {
					continue;
				}
				if ((a[i] < 0 && a[i] == a[j] * 2) || (a[i] >= 0 && a[i] * 2 == a[j])) {
					find = true;
					looked.add(j);
					break;
				}
			}
			if (!find) {
				return false;
			}
		}
		return true;
	}

	public static boolean canReorderDoubled2nd(int[] A) {
		Map<Integer, Integer> counter = new HashMap<>();
		for (int x : A) {
			counter.compute(x, (k, v) -> v == null ? 1 : v + 1);
		}
		Integer[] IntegerA = new Integer[A.length];
		for (int i = 0; i < IntegerA.length; i++) {
			IntegerA[i] = A[i];
		}
		Arrays.sort(IntegerA, Comparator.comparing(Math::abs));

		for (int i : IntegerA) {
			int doubleI = 2 * i;
			if (counter.getOrDefault(i, 0) == 0) {
				continue;
			}
			if (counter.getOrDefault(doubleI, 0) <= 0) {
				return false;
			}

			counter.compute(i, (k, v) -> --v);
			counter.compute(doubleI, (k, v) -> --v);
		}
		return true;
	}

	public static boolean canReorderDoubled3rd(int[] A) {
		Arrays.sort(A);
		Map<Integer, Integer> counter = new HashMap<>();
		for (int x : A) {
			counter.compute(x, (k, v) -> v == null ? 1 : v + 1);
		}

		for (int i : A) {
			if (counter.getOrDefault(i, 0) == 0) {
				continue;
			}
			boolean isNegative = i < 0;
			int doubleI = 2 * i;
			if (isNegative && i % 2 != 0) {
				return false;
			}
			int halfI = i / 2;

			if (counter.getOrDefault(isNegative ? halfI : doubleI, 0) <= 0) {
				return false;
			}

			counter.compute(i, (k, v) -> --v);
			counter.compute(isNegative ? halfI : doubleI, (k, v) -> --v);
		}
		return true;
	}

}

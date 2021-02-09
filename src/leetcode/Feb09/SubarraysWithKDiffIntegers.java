package leetcode.Feb09;

import java.util.HashMap;
import java.util.Map;

public class SubarraysWithKDiffIntegers {
	public static void main(String[] args) {
		int[] A = { 1, 2, 1, 2, 3 };
		int K = 2;
		System.out.println(new SubarraysWithKDiffIntegers().subarraysWithKDistinct(A, K));
	}

	public int subarraysWithKDistinctTimeout(int[] A, int K) {
		int res = 0;
		int len = A.length;
		for (int left = 0; left < len; ++left) {
			Map<Integer, Integer> map = new HashMap<>(K + 1);
			map.put(A[left], 1);
			int size = map.size();
			if (size == K) {
				++res;
			}
			for (int right = left + 1; right < len; ++right) {
				map.compute(A[right], (k, v) -> v == null ? 1 : ++v);
				size = map.size();
				if (size == K) {
					++res;
				} else if (size > K) {
					break;
				}
			}
		}
		return res;
	}

	public int subarraysWithKDistinct1(int[] A, int K) {
		int res = 0;
		int len = A.length;
		Map<Integer, Integer> map = new HashMap<>(K + 1);
		int right = 0;
		for (int left = 0; left < len; ++left) {
			if (left > 0) {
				map.compute(A[left - 1], (k, v) -> --v);
				if (map.get(A[left - 1]) <= 0) {
					map.remove(A[left - 1]);
				}
			}
			boolean flag = false;
			if (map.size() == K) {
				do {
					++res;
					for (int k : map.keySet()) {
						System.out.print(map.get(k) + "个" + k + "|");
					}
					System.out.println();
					map.compute(A[right - 1], (k, v) -> --v);
					if (map.get(A[right - 1]) <= 0) {
						map.remove(A[right - 1]);
					}
					if (map.size() == K) {
						--right;
					} else {
						map.compute(A[right - 1], (k, v) -> v == null ? 1 : ++v);
						flag = true;
						break;
					}
				} while (right > left);
			}
			if (flag || map.size() != K) {
				for (; right < len; ++right) {
					map.compute(A[right], (k, v) -> v == null ? 1 : ++v);
					int size = map.size();
					if (size == K) {
						++res;
						for (int k : map.keySet()) {
							System.out.print(map.get(k) + "个" + k + "|");
						}
						System.out.println();
					} else if (size > K) {
						map.remove(A[right]);
						break;
					}
				}
			}
		}
		return res;
	}

	public int subarraysWithKDistinct(int[] A, int K) {
		return atMostKDistinct(A, K) - atMostKDistinct(A, K - 1);
	}

	private int atMostKDistinct(int[] A, int K) {
		int len = A.length;
		int[] counter = new int[len + 1];

		int left = 0, right = 0;
		int count = 0, res = 0;
		while (right < len) {
			if (counter[A[right]] == 0) {
				++count;
			}
			++counter[A[right]];
			++right;

			while (count > K) {
				--counter[A[left]];
				if (counter[A[left]] == 0) {
					--count;
				}
				++left;
			}
			res += right - left;
		}
		return res;
	}

}

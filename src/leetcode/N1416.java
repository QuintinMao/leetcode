package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 恢复数组
 * @author Johnny
 * 
 * 2021年1月7日 上午11:31:43
 */
public class N1416 {
	public static void main(String[] args) {
		String s = "1234567890";
		int k = 10000;
		System.out.println(new N1416().numberOfArrays2nd(s, k));
		int kLength = 0;
		while (k > 0) {
			k = k / 10;
			kLength++;
		}
		System.out.println(kLength);
	}

	public int numberOfArrays(String s, int k) {
		long start = System.currentTimeMillis();
		List<List<Integer>> result = calc(s, k);
		System.out.println("cost :" + (System.currentTimeMillis() - start) + "ms.");
		return result.size();
	}

	public int numberOfArrays2nd(String s, int k) {
		int m = 1_000_000_007;
		int kLength = 0;
		while (k > 0) {
			k = k / 10;
			kLength++;
		}
		long[] dp = new long[s.length() + 1];
		dp[s.length()] = 1;
		for (int i = s.length() - 1; i >= 0; i--) {
			if (s.charAt(i) == '0') {
				dp[i] = 0;
				continue;
			}
			long n = 0;
			for (int j = i; j < s.length() && j - i <= kLength - 1; j++) {
				n = n * 10 + s.charAt(j) - '0';

				if (n <= k) {
					dp[i] = (dp[i] + dp[j + 1]) % m;
				} else {
					break;
				}
			}
		}
		return (int) dp[0];
	}

	public List<List<Integer>> calc(String s, int k) {
		List<List<Integer>> result = null;
		for (int i = 1; i <= s.length() && i < 10; i++) {
			Integer n = Integer.parseInt(s.substring(0, i));
			if (n > k || n == 0) {
				break;
			}
			if (result == null) {
				result = new ArrayList<>();
			}
			if (s.length() > i && s.charAt(i) == '0') {
				continue;
			}
			List<List<Integer>> subResult = calc(s.substring(i, s.length()), k);
			if (i == s.length()) {
				result.add(new ArrayList<>(i));
			} else {
				if (subResult != null) {
					for (List<Integer> list : subResult) {
						list.add(0, n);
						result.add(list);
					}
				}
			}

		}
		return result;
	}
}

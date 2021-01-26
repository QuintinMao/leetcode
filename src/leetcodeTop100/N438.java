package leetcodeTop100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Find All Anagrams in a String
 * 
 * @author Johnny
 * 
 *         2021年1月20日 下午6:51:35
 */
public class N438 {

	public static void main(String[] args) {
		String s = "abab";
		String p = "ab";
		new N438().findAnagrams(s, p).forEach(System.out::println);
	}

	public List<Integer> findAnagrams(String s, String p) {
		int n = s.length();
		int left = 0;
		int win = p.length();

		boolean[] dp = new boolean[n];
		dp[0] = false;
		List<Integer> res = new ArrayList<>();
		if (n < win || win == 0) {
			return res;
		}

		while (left + win - 1 < n) {
			boolean match = false;
			if (dp[left]) {
				match = p.charAt(left + win - 1) == p.charAt(left - 1);
			} else {
				match = match(s.substring(left, left + win), p);
			}
			if (match) {
				dp[left] = true;
				res.add(left);
			}
			left++;
		}
		return res;

	}

	public boolean match(String s, String p) {
		char[] ss = s.toCharArray();
		Arrays.sort(ss);
		char[] pp = p.toCharArray();
		Arrays.sort(pp);
		for (int i = 0; i < ss.length; i++) {
			if (ss[i] != pp[i]) {
				return false;
			}
		}
		return true;
	}
}

package leetcode.Feb04;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * 131. 回溯+DP预处理（回文中心扩散）
 * 
 * @author Johnny
 * 
 *         2021年2月4日 下午5:12:16
 */
public class PalindromePartitioning {
	public static void main(String[] args) {
		String s = "aab";
		@SuppressWarnings("unused")
		List<List<String>> res = new PalindromePartitioning().partition(s);
	}

	boolean[][] dp;

	public List<List<String>> partition(String s) {
		List<List<String>> res = new ArrayList<>();
		int len = s.length();
		if (len == 0) {
			return res;
		}

		dp = new boolean[len][len];
		for (int i = 0; i < 2 * len - 1; ++i) {
			int l = i / 2, r = i / 2 + i % 2;
			while (l >= 0 && r < len && s.charAt(l) == s.charAt(r)) {
				--l;
				++r;
				dp[l][r] = true;
			}
		}

		backtracking(s, 0, len, new ArrayDeque<>(), res);
		return res;
	}

	/**
	 * 判断s的【left，right】范围是否为回文 向中心收缩判断两端字符是否相同
	 * 
	 * @param s
	 * @param left
	 * @param right
	 * @return
	 */
	public boolean isPalindrome(String s, int left, int right) {
		while (left < right) {
			if (s.charAt(left) != s.charAt(right)) {
				return false;
			}
			++left;
			--right;
		}
		return true;
	}

	public void backtracking(String s, int start, int len, Deque<String> path, List<List<String>> res) {
		// 回溯中止条件，达到字符串末尾
		if (start == len) {
			res.add(new ArrayList<>(path));
			return;
		}

		// 从start开始 找到最短的回文
		for (int i = start; i < len; ++i) {
//			if (!isPalindrome(s, start, i)) {
			if (!dp[start][i]) {
				continue;
			}
			// 将最短回文加入path，
			path.addLast(s.substring(start, i + 1));
			// 从这个回文之后开始递归
			backtracking(s, i + 1, len, path, res);
			// 当前递归完成，恢复path
			path.removeLast();
		}
	}
}

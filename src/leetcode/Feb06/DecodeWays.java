package leetcode.Feb06;

/**
 * 91. 直接DFS 超时 用例："111111111111111111111111111111111111111111111"
 * 
 * @author Johnny
 * 
 *         2021年2月7日 下午5:50:23
 */
public class DecodeWays {

	public static void main(String[] args) {
		String s = "111111111111111111111111111111111111111111111";
		System.out.println(new DecodeWays().numDecodings(s));
	}

	int res = 0;

	public int numDecodings(String s) {
		if (!"".equals(s) && s.charAt(0) != '0') {
			dfs(s, 0);
		}
		return res;
	}

	public void dfs(String s, int start) {
		int len = s.length();
		if (start >= len - 1) {
			++res;
			return;
		}
		if (s.charAt(start) == '0') {
			return;
		}

		if (start == len - 1 || (start < len - 1 && s.charAt(start + 1) != '0')) {
			dfs(s, start + 1);
		}
//		if (start + 2 <= len && Integer.parseInt(s.substring(start, start + 2)) <= 26) {
		if (start + 2 <= len && (s.charAt(start) == '1'
				|| s.charAt(start) == '2' && '0' <= s.charAt(start + 1) && s.charAt(start + 1) <= '6')) {
			dfs(s, start + 2);
		}
	}

}

package leetcodeTop100;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * N3. Longest Substring without Repeating Characters
 * 
 * @author Johnny
 * 
 *         2021年1月14日 下午3:01:52
 */
public class N3 {

	public static void main(String[] args) {
		String s = "abba";
		System.out.println(new N3().lengthOfLongestSubstring2(s));
	}

	public int lengthOfLongestSubstring(String s) {
		int n = s.length();
		if (n < 2) {
			return n;
		}
		int rs = 0;
		Set<Character> map = new HashSet<>();
		char[] ss = s.toCharArray();
		int j = -1;
		for (int i = 0; i < n; i++) {
			if (i != 0) {
				map.remove(ss[i - 1]);
			}

			while (j + 1 < n && !map.contains(ss[j + 1])) {
				map.add(ss[j + 1]);
				j++;
			}
			rs = Math.max(rs, j - i + 1);
		}
		return rs;
	}

	/**
	 * 滑动窗口
	 * @param s
	 * @return
	 */
	public int lengthOfLongestSubstring2(String s) {
		int n = s.length();
		if (n == 0) {
			return 0;
		}
		char[] ss = s.toCharArray();
		//缓存出现过的char的下标
		Map<Character, Integer> map = new HashMap<>();
		//最大窗口长度
		int max = 0;
		//当前窗口左边界
		int left = 0;
		//初始：窗口左边界==右边界==0，i表示右边界
		for (int i = 0; i < n; i++) {
			//如果滑动窗口有边界左侧的元素之前出现过，
			if (map.containsKey(ss[i])) {
				//则窗口左边界移动到 Max(之前出现过的元素的右边,当前左边界)
				left = Math.max(left, map.get(ss[i]) + 1);
			}
			map.put(ss[i], i);
			max = Math.max(max, i - left + 1);
		}
		return max;

	}

}

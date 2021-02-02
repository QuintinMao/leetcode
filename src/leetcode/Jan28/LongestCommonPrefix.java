package leetcode.Jan28;

import java.util.Arrays;

public class LongestCommonPrefix {

	public static void main(String[] args) {

	}

	/**
	 * 排序， 逐个字符比较
	 * 
	 * @param strs
	 * @return
	 */
	public String longestCommonPrefixSort(String[] strs) {
		if (strs.length == 0 || strs[0].equals("")) {
			return "";
		}
		Arrays.sort(strs, (s1, s2) -> s1.length() - s2.length());
		for (int i = 0; i < strs[0].length(); i++) {
			for (int j = 1; j < strs.length; j++) {
				if (strs[0].charAt(i) != strs[j].charAt(i)) {
					return strs[0].substring(0, i);
				}
			}
		}
		return strs[0];
	}
}

package leetcode.Feb09;

import java.util.Arrays;

public class ValidAnagram {

	public boolean isAnagram(String s, String t) {
		int sLen = s.length();
		int tLen = t.length();
		if (sLen != tLen) {
			return false;
		}
		int[] letters = new int[26];
		for (int i = 0; i < sLen; ++i) {
			++letters[s.charAt(i) - 'a'];
			int ti = t.charAt(i) - 'a';
			--letters[ti];
		}
		for (int i = 0; i < 26; ++i) {
			if (letters[i] > 0) {
				return false;
			}
		}
		return true;
	}

	public boolean isAnagram2(String s, String t) {
		if (s.length() != t.length()) {
			return false;
		}
		char[] ss = s.toCharArray();
		char[] tt = t.toCharArray();
		Arrays.sort(ss);
		Arrays.sort(tt);
		return new String(ss).equals(new String(tt));
	}
}

package leetcode.Feb08;

import java.util.HashMap;
import java.util.Map;

public class FirstUniqueCharInString {

	public static void main(String[] args) {
		System.out.println(new FirstUniqueCharInString().firstUniqueChar2("loveleetcode"));
	}

	public int firstUniqueChar(String s) {
		char[] ss = s.toCharArray();
		Map<Character, Integer> map = new HashMap<>();
		for (int i = 0; i < ss.length; ++i) {
			map.compute(ss[i], (k, v) -> v == null ? 1 : ++v);
		}
		for (int i = 0; i < ss.length; ++i) {
			char ch = ss[i];
			if (map.get(ch) < 2) {
				return i;
			}
		}
		return -1;
	}

	public int firstUniqueChar2(String s) {
		char[] ss = s.toCharArray();
		char[] counter = new char['a' + 26];
		for (int i = 0; i < ss.length; ++i) {
			counter[ss[i]]++;
		}
		for (int i = 0; i < ss.length; ++i) {
			if (counter[ss[i]] < 2) {
				return i;
			}
		}
		return -1;
	}
}

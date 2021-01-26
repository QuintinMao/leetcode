package leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 词典中的最长单词
 * @author Johnny
 * 
 * 2021年1月7日 上午11:34:26
 */
public class N720 {

	public static void main(String[] args) {
		String[] words = { "yo", "ew", "fc", "zrc", "yodn", "fcm", "qm", "qmo", "fcmz", "z", "ewq", "yod", "ewqz",
				"y" };
		System.out.println(new N720().longestWord(words));
	}

	public String longestWord(String[] words) {
		Map<Integer, List<String>> ww = new HashMap<>();
		int maxLength = 0;
		for (String s : words) {
			int len = s.length();
			if (!ww.containsKey(len)) {
				ww.put(len, new ArrayList<>());
			}
			ww.get(len).add(s);
			if (len > maxLength) {
				maxLength = len;
			}
		}
		for (int i = maxLength; i > 0; i--) {
			if (ww.get(i) == null) {
				continue;
			}
			List<String> ll = ww.get(i);
			if (ll.size() > 1) {
				Collections.sort(ll);
			}
			if (i == 1) {
				return ll.get(0);
			}
			boolean match = true;
			for (String s : ll) {
				String foo = s;
				do {
					foo = foo.substring(0, foo.length() - 1);
					List<String> bar = ww.get(foo.length());
					if (bar == null || !bar.contains(foo)) {
						match = false;
						break;
					}
				} while (foo.length() > 1);
				if (match) {
					return s;
				}
				match = true;
			}
		}
		return "";
	}
}

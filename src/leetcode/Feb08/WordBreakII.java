package leetcode.Feb08;

import java.util.ArrayList;
import java.util.List;

public class WordBreakII {

	public static void main(String[] args) {
		List<String> wordDict = new ArrayList<>();
		wordDict.add("a");
		wordDict.add("aa");
		wordDict.add("aaa");
		wordDict.add("aaaa");
		wordDict.add("aaaaa");
		wordDict.add("aaaaaa");
		wordDict.add("aaaaaaa");
		wordDict.add("aaaaaaaa");
		wordDict.add("aaaaaaaaa");
		wordDict.add("aaaaaaaaaa");
		String s = "aaaaaaaaaa";
		new WordBreakII().wordBreak(s, wordDict).forEach(System.out::println);
	}

	/**
	 * 直接计算会超时， 需要记忆化搜索
	 * @param s
	 * @param wordDict
	 * @return
	 */
	public List<String> wordBreak(String s, List<String> wordDict) {
		List<String> res = new ArrayList<>();
		if ("".equals(s)) {
			return null;
		}
		for (String w : wordDict) {
			if (s.startsWith(w)) {
				List<String> subRes = wordBreak(s.substring(w.length(), s.length()), wordDict);
				if (subRes == null) {
					res.add(w);
				} else {
					for (String sw : subRes) {
						StringBuilder sb = new StringBuilder(w);
						sb.append(" ");
						sb.append(sw);
						res.add(sb.toString());
					}
				}
			}
		}
		return res;
	}
}

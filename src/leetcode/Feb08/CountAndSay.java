package leetcode.Feb08;

/**
 * 38
 * 
 * @author Johnny
 * 
 *         2021年2月8日 上午11:28:44
 */
public class CountAndSay {

	public static void main(String[] args) {
		System.out.println(new CountAndSay().countAndSay(0));
	}

	public String countAndSay(int n) {
		if (n == 1) {
			return "1";
		}
		String s = countAndSay(n - 1);
		char prev = s.charAt(0);
		int count = 1;
		StringBuilder builder = new StringBuilder();

		for (int i = 1; i < s.length(); ++i) {
			char cur = s.charAt(i);
			if (cur == prev) {
				++count;
			} else {
				builder.append(count);
				builder.append(prev);
				prev = cur;
				count = 1;
			}
		}
		builder.append(count);
		builder.append(prev);
		return builder.toString();
	}
}

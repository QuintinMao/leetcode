package leetcode.Feb01;

/**
 * 8. Ascii to Integer
 * 
 * @author Johnny
 * 
 *         2021年2月1日 下午5:14:12
 */
public class StringToInteger {

	public static void main(String[] args) {
		System.out.println(new StringToInteger().myAtoi("-91283472332"));
	}

	public int myAtoi(String s) {
		s = s.trim();
		StringBuilder builder = new StringBuilder();
		int n = s.length();
		boolean positive = true;
		for (int i = 0; i < n; i++) {
			char ch = s.charAt(i);
			if (i == 0) {
				if (ch == '-') {
					positive = false;
					continue;
				} else if (ch == '+') {
					continue;
				}
			}
			if (!Character.isDigit(ch)) {
				break;
			}
			builder.append(ch);
		}
		if (builder.length() == 0) {
			return 0;
		}
		try {
			return Integer.parseInt(builder.toString()) * (positive ? 1 : -1);
		} catch (Exception ex) {
			if (positive) {
				return Integer.MAX_VALUE;
			} else {
				return Integer.MIN_VALUE;
			}
		}

	}

}

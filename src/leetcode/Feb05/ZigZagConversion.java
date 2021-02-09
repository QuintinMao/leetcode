package leetcode.Feb05;

/**
 * 6.
 * 
 * @author Johnny
 * 
 *         2021年2月5日 下午4:21:16
 */
public class ZigZagConversion {

	public static void main(String[] args) {
		System.out.println(new ZigZagConversion().convert("1234567890", 5));
	}

	public String convert(String s, int numRows) {
		int len = s.length();
		if (numRows >= len || numRows == 1) {
			return s;
		}
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < numRows; ++i) {
			int k = i;
			if (i == 0 || i == numRows - 1) {
				while (k < len) {
					builder.append(s.charAt(k));
					k += numRows * 2 - 2;
				}
			} else {
				boolean flag = true;
				while (k < len) {
					builder.append(s.charAt(k));
					int a = (numRows - i - 1) * 2;
					int b = i * 2;
					if (flag) {
						k += a;
					} else {
						k += b;
					}
					flag = !flag;
				}
			}
		}

		return builder.toString();
	}
}

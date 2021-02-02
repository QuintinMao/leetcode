package leetcode.Feb01;


/**
 * 43
 * 
 * @author Johnny
 * 
 *         2021年2月1日 下午2:52:44
 */
public class MultiplyStrings {
	public static void main(String[] args) {
		String num1 = "1234567890";
		String num2 = "1";
		System.out.println(new MultiplyStrings().multiply(num1, num2));
	}

	/**
	 * 卷积
	 * 
	 * @param num1
	 * @param num2
	 * @return
	 */
	public String multiplyJJ(String num1, String num2) {
		if (num1.equals("0") || num2.equals("0")) {
			return "0";
		}
		if (num1.equals("1")) {
			return num2;
		}
		if (num2.equals("1")) {
			return num1;
		}
		int m = num1.length();
		int n = num2.length();
		// 数组保存结果， 数组最大长度为m+n
		int[] ansArr = new int[m + n];
		// 从尾至头取数字
		for (int i = m - 1; i >= 0; i--) {
			int x = num1.charAt(i) - '0';
			// 数字和另一个数字的每一位相乘
			for (int j = n - 1; j >= 0; j--) {
				int y = num2.charAt(j) - '0';
				ansArr[i + j + 1] += x * y;
			}
		}
		// 进位
		for (int i = m + n - 1; i > 0; i--) {
			ansArr[i - 1] += ansArr[i] / 10;
			ansArr[i] %= 10;
		}
		int index = ansArr[0] == 0 ? 1 : 0;
		StringBuilder builder = new StringBuilder();
		while (index < m + n) {
			builder.append(ansArr[index++]);
		}
		return builder.toString();

	}

	/**
	 * 每位相乘，补零，结果相加
	 * 
	 * @param num1
	 * @param num2
	 * @return
	 */
	public String multiply(String num1, String num2) {
		if (num1.equals("0") || num2.equals("0")) {
			return "0";
		}
		if (num1.equals("1")) {
			return num2;
		}
		if (num2.equals("1")) {
			return num1;
		}
		String res = "0";
		for (int i = 0; i < num2.length(); i++) {
			int thisNumber = num2.charAt(i) - '0';
			if (thisNumber == 0) {
				continue;
			}
			int zero = num2.length() - i - 1;
			StringBuilder another = helper(num1, thisNumber);
			while (zero-- > 0) {
				another.append(0);
			}
			res = addStrings(res, another.toString());
		}
		return res;
	}

	public StringBuilder helper(String num1, int b) {
		StringBuilder builder = new StringBuilder();
		if (b == 0) {
			builder.append(0);
			return builder;
		}
		if (b == 1) {
			builder.append(num1.toCharArray());
			return builder;
		}

		int add = 0;
		for (int i = num1.length() - 1; i >= 0; i--) {
			int a = num1.charAt(i) - '0';
			int c = a * b + add;
			builder.append(c % 10);
			add = c / 10;
		}
		if (add != 0) {
			builder.append(add);
		}
		return builder.reverse();
	}

	public String addStrings(String num1, String num2) {
		if (num1.equals("0")) {
			return num2;
		}
		if (num2.equals("0")) {
			return num1;
		}
		int index1 = num1.length() - 1;
		int index2 = num2.length() - 1;
		int addOne = 0;
		StringBuilder builder = new StringBuilder();
		while (index1 >= 0 || index2 >= 0) {
			int a = 0, b = 0;
			if (index1 >= 0) {
				a = num1.charAt(index1--) - '0';
			}
			if (index2 >= 0) {
				b = num2.charAt(index2--) - '0';
			}
			int c = a + b + addOne;
			addOne = c / 10;
			builder.append(c % 10);
		}
		if (addOne > 0) {
			builder.append(1);
		}
		return builder.reverse().toString();
	}
}

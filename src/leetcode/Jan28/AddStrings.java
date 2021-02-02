package leetcode.Jan28;


/**
 * N415
 * 
 * @author Johnny
 * 
 *         2021年1月28日 下午4:48:04
 */
public class AddStrings {

	public static void main(String[] args) {
		String num1 = "12345678901234567890";
		String num2 = "987654321987654321";
		System.out.println(new AddStrings().addStrings(num1, num2));
	}

	/**
	 * 人工模拟，
	 * 
	 * 优化：反转结果
	 * 
	 * @param num1
	 * @param num2
	 * @return
	 */
	public String addStrings(String num1, String num2) {
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

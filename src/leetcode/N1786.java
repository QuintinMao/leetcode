package leetcode;


/**
 * 反转单词顺序
 * 
 * 输入一个英文句子，翻转句子中单词的顺序，但单词内字符的顺序不变。为简单起见，标点符号和普通字母一样处理。例如输入字符串"I am a student.
 * "，则输出"student. a am I"。
 * 
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/fan-zhuan-dan-ci-shun-xu-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class N1786 {

	public static void main(String[] args) {
		String str = " the     sky     is blue. ";
//		String str = "a";

		System.out.println(reverseWords(str));
//		 StringBuilder builder = new StringBuilder(" ");
//		 builder.append(" ").append("abc");
//		 System.out.println(builder.toString());

	}

	public static String reverseWords3rd(String s) {
		if (s == null || s.length() <= 0 || s.trim().length() <= 0) {
			return "";
		}

		String[] strs = s.split(" ");
		int length = strs.length;
		for (int i = 0; i < length / 2; i++) {
			String tempStr = strs[i].trim();
			strs[i] = strs[length - 1 - i].trim();
			strs[length - 1 - i] = tempStr;
		}

		StringBuilder builder = new StringBuilder(strs[0]);
		for (int i = 1; i < strs.length; i++) {
			if (strs[i].length() > 0) {
				builder.append(" ").append(strs[i]);
			}
		}
		return builder.toString();
	}

	public static String reverseWords2nd(String s) {
		s = s.trim();
		if ("".equals(s)) {
			return "";
		}
		StringBuilder builder = new StringBuilder();
		int endIndex = -1;
		int startIndex = -1;
		for (int i = s.length() - 1; i >= 0; i--) {
			int codePoint = s.codePointAt(i);
			if (codePoint != ' ') {
				if (endIndex == -1) {
					endIndex = i + 1;
				}
			} else {
				if (endIndex != -1 && startIndex == -1) {
					startIndex = i + 1;
				}

				if (startIndex != -1 && endIndex != -1) {
					if (builder.length() != 0) {
						builder.append(" ");
					}
					builder.append(s.substring(startIndex, endIndex));
					startIndex = -1;
					endIndex = -1;
				}
			}
		}
		builder.append(" ");
		builder.append(s.substring(0, endIndex));
		startIndex = -1;
		endIndex = -1;
		return builder.toString().trim();

	}

	public static String reverseWords(String s) {
		s = s.trim();
		String[] foo = s.split("[ ]");

		StringBuilder sb = new StringBuilder(foo[foo.length - 1]);
		for (int i = foo.length - 2; i >= 0; i--) {
			String bar = foo[i].trim();
			if (bar.length() > 0) {
				sb.append(" ").append(bar);
			}
		}
		return sb.toString();
	}

}

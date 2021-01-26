package leetcode;

/**
 * 1021.Remove Outermost Parentheses 刪除最外层的括号
 * @author Johnny
 * 
 * 2021年1月7日 上午11:31:24
 */
public class N1021 {
	public static void main(String[] args) {
		String S = "(()())(())(()(()))";
		System.out.println(new N1021().removeOuterParentheses(S));
	}

	public String removeOuterParentheses(String S) {
		char[] ss = S.toCharArray();
		int counter = 0;
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < ss.length; i++) {
			char c = ss[i];
			if (c == '(' && counter++ > 0) {
				builder.append(c);
			} else if (c == ')' && --counter > 0) {
				builder.append(c);
			}
		}
		return builder.toString();
	}
}

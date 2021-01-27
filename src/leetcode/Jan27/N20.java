package leetcode.Jan27;

import java.util.Stack;

/**
 * Valid Parentheses
 * 
 * @author Johnny
 * 
 *         2021年1月27日 下午4:26:53
 */
public class N20 {

	public static void main(String[] args) {
		String s = "";
		System.out.println(new N20().isValid(s));
	}

	public boolean isValid(String s) {
		char[] ss = s.toCharArray();
		if (ss.length < 1 || ss.length % 2 == 1) {
			return false;
		}
		Stack<Character> stack = new Stack<>();
		for (char c : ss) {
			if (c == '(' || c == '[' || c == '{') {
				stack.push(c);
			} else {
				if (c == ')') {
					if (stack.isEmpty() || stack.peek() != '(') {
						return false;
					}
					stack.pop();
				} else if (c == ']') {
					if (stack.isEmpty() || stack.peek() != '[') {
						return false;
					}
					stack.pop();
				} else if (c == '}') {
					if (stack.isEmpty() || stack.peek() != '{') {
						return false;
					}
					stack.pop();
				}
			}
		}
		return stack.isEmpty();
	}

}

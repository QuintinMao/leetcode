package leetcodeTop100;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * 最长有效括号
 * @author Johnny
 * 
 * 2021年1月7日 上午11:33:00
 */
public class N32 {

	public static void main(String[] args) {
		String s = "(((((((((()";
		System.out.println(new N32().longestValidParentheses4th(s));
	}

	public int longestValidParentheses4th(String s) {
 		int max = 0;
		Deque<Integer> stack = new LinkedList<>();
		stack.push(-1);
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) == '(') {
				stack.push(i);
			} else {
				stack.pop();
				if (stack.isEmpty()) {
					stack.push(i);
				} else {
					int foo = stack.peek();
					max = Math.max(max, i - foo);
				}
			}
		}
		return max; 
	}

	public int longestValidParentheses3rd(String s) {
		char[] ss = s.toCharArray();
		int[] dp = new int[ss.length];
		int max = 0;
		for (int i = 1; i < ss.length; i++) {
			if (ss[i] == ')') {
				if (ss[i - 1] == '(') {
					dp[i] = (i >= 2 ? dp[i - 2] : 0) + 2;
				} else {
					if (i - dp[i - 1] > 0 && ss[i - dp[i - 1] - 1] == '(') {
						dp[i] = dp[i - 1] + ((i - dp[i - 1]) >= 2 ? dp[i - dp[i - 1] - 2] : 0) + 2;
					}
				}
				max = Math.max(max, dp[i]);
			}
		}
		return max;
	}

	public int longestValidParentheses(String s) {
		int max = 0;
		char[] ss = s.toCharArray();
		int current = 0;
		int balance = 0;
		int lastCurrent = 0;
		boolean searching = false;
		boolean nextTo = true;
		for (int j = 0; j < ss.length; j++) {
			for (int i = j; i < ss.length; i++) {
				if (ss[i] == '(') {
					if (!searching) {
						searching = true;
					}
					balance++;
				} else {
					if (!searching) {
						nextTo = false;
						continue;
					}
					balance--;
				}
				if (balance < 0) {
					nextTo = false;
					balance = 0;
					current = 0;
				} else if (balance == 0) {
					int foo = current += 1;
					if (nextTo) {
						foo += lastCurrent;
						lastCurrent = foo;
					}
					max = Math.max(max, foo);
					current = 0;
					searching = false;
				} else {
					current++;
				}

			}
			current = 0;
			lastCurrent = 0;
			balance = 0;
			searching = false;
			nextTo = true;
		}

		return max;
	}

	public int longestValidParentheses2nd(String s) {
		char[] ss = s.toCharArray();
		int max = 0;
		for (int i = 0; i < ss.length; i++) {
			if (ss[i] == '(') {
				for (int j = i + 1; j < ss.length; j++) {
					if (ss[j] == ')') {
						if (isValid(Arrays.copyOfRange(ss, i, j + 1))) {
							max = Math.max(max, j + 1 - i);
						}
					}
				}
			}
		}
		return max;
	}

	public boolean isValid(char[] ss) {
		int balance = 0;
		for (int i = 0; i < ss.length; i++) {
			if (ss[i] == '(') {
				balance++;
			} else {
				balance--;
			}
			if (balance < 0) {
				return false;
			}
		}
		return balance == 0;
	}

}

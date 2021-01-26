package leetcodeTop100;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * N301. Remove Invalid Parentheses
 * 
 * @author Johnny
 * 
 *         2021年1月11日 下午5:44:59
 */
public class N301 {

	public static void main(String[] args) {
		String s = "()((((((()l(";

		new N301().removeInvalidParentheses(s).forEach(System.out::println);
	}

	int validMinLen = 0;

	public List<String> removeInvalidParentheses(String s) {
		List<String> rs = handle(s);
		Set<String> result = new HashSet<>();
		for (String str : rs) {
			if (str.length() >= validMinLen) {
				result.add(str);
			}
		}
		return new ArrayList<String>(result);
	}

	public List<String> handle(String s) {
		List<String> rs = new ArrayList<String>();
		if ("".equals(s) || isValid(s)) {
			rs.add(s);
			return rs;
		}
		char[] ss = s.toCharArray();
		for (int i = 0; i < ss.length; i++) {
			char c = ss[i];
			if (c != '(' && c != ')') {
				continue;
			}
			char[] foo = new char[ss.length - 1];
			System.arraycopy(ss, 0, foo, 0, i);
			System.arraycopy(ss, i + 1, foo, i, ss.length - i - 1);
			String bar = new String(foo);
			if (isValid(bar)) {
				validMinLen = Math.max(bar.length(), validMinLen);
				rs.add(bar);
			} else {
				if (bar.length() > validMinLen) {
					List<String> subRs = handle(bar);
					if (subRs.size() > 0) {
						rs.addAll(subRs);
					}
				}
			}
		}

		return rs;

	}

	public boolean isValid(String s) {
		if ("".equals(s)) {
			return true;
		}
		char[] ss = s.toCharArray();
		int balance = 0;
		for (int i = 0; i < ss.length; i++) {
			if (ss[i] != '(' && ss[i] != ')') {
				continue;
			}
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

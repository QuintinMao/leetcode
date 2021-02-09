package leetcode.Feb08;

/**
 * 125.
 * 
 * @author Johnny
 * 
 *         2021年2月8日 下午2:44:57
 */
public class ValidPalindrome {
	public static void main(String[] args) {
		String s = "0P";
		System.out.println(new ValidPalindrome().isPalindrome(s));
	}

	public boolean isPalindrome(String s) {
		int left = 0, right = s.length() - 1;
		char[] ss = s.toCharArray();
		while (left < right) {
			if (!Character.isAlphabetic(ss[left]) && !Character.isDigit(ss[left])) {
				++left;
				continue;
			}
			if (!Character.isAlphabetic(ss[right]) && !Character.isDigit(ss[right])) {
				--right;
				continue;
			}

			if (Character.toLowerCase(ss[left]) != Character.toLowerCase(ss[right])) {
				return false;
			}
			++left;
			--right;

		}
		return true;
	}
}

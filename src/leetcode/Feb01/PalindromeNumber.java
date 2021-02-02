package leetcode.Feb01;

/**
 * 9
 * 
 * @author Johnny
 * 
 *         2021年2月1日 下午1:59:29
 */
public class PalindromeNumber {

	public static void main(String[] args) {
		System.out.println(new PalindromeNumber().isPalindrome(888776888));
	}

	public boolean isPalindrome(int x) {
		if (x < 0) {
			return false;
		}
		int t = x;
		int y = 0;
		try {
			while (t != 0) {
				y = y * 10 + t % 10;
				t /= 10;
			}
		} catch (Exception ex) {
			return false;
		}
		return x == y;

	}

}

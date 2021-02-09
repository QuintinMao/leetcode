package leetcode.Feb08;

/**
 * 172.
 * 
 * @author Johnny
 * 
 *         2021年2月8日 下午1:49:51
 */
public class FactorialTrailingZeroes {

	public static void main(String[] args) {
		FactorialTrailingZeroes ftx = new FactorialTrailingZeroes();
		int i = 625;
		System.out.println(ftx.trailingZeroes(i));
		System.out.println(ftx.trailingZeroes2(i));
	}

	public int trailingZeroes2(int n) {
		int res = 0;
		while (n > 0) {
			res += n / 5;
			n /= 5;
		}
		return res;
	}

	/**
	 * 某个地方不对
	 * 
	 * @param n
	 * @return
	 */
	public int trailingZeroes(int n) {
		int res = 0;
		int m = 1;
		for (int i = 2; i <= n; ++i) {
			m *= i;
			if (m % 10 == 0) {
				while (m >= 10 && m % 5 == 0) {
					m /= 10;
					++res;
				}
			} else {
				m %= 10;
			}
		}
		return res;
	}

}

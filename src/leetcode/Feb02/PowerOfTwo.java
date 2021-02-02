package leetcode.Feb02;

/**
 * 231
 * 
 * @author Johnny
 * 
 *         2021年2月2日 下午3:56:48
 */
public class PowerOfTwo {

	public static void main(String[] args) {
		PowerOfTwo pot = new PowerOfTwo();
		for (int i = 1; i <= 100_000; i++) {
			boolean rs = pot.isPowerOfTwo2(i);
			if (rs) {
				System.out.println(i);
			}
		}
	}

	public boolean isPowerOfTwo(int n) {
		if (n == 1) {
			return true;
		}
		if (n <= 0 || n % 2 != 0) {
			return false;
		}
		n = n >> 1;
		return isPowerOfTwo(n);
	}

	/**
	 * 位运算，  2的平方  二进制  首位1 其余0  
	 * @param n
	 * @return
	 */
	public boolean isPowerOfTwo2(int n) {
		if (n <= 0) {
			return false;
		}
		
		return (n & (n - 1)) == 0;
	}
}

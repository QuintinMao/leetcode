package leetcode.Feb08;

/**
 * 204.primes 质数
 * 
 * @author Johnny
 * 
 *         2021年2月8日 下午3:37:51
 */
public class CountPrimes {

	public static void main(String[] args) {

	}

	public int countPrimes(int n) {
		boolean[] isPrime = new boolean[n];
		for (int i = 2; i < n; ++i) {
			isPrime[i] = true;
		}

		for (int i = 2; i * i < n; ++i) {
			if (!isPrime[i]) {
				continue;
			}
			for (int j = i * i; j < n; j += i) {
				isPrime[j] = false;
			}
		}
		int res = 0;
		for (int i = 2; i < n; ++i) {
			if (isPrime[i]) {
				++res;
			}
		}
		return res;
	}
}

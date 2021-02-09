package leetcode.Feb08;

public class PowXN {

	public static void main(String[] args) {
		System.out.println(new PowXN().myPow(-2, -3));
	}

	/**
	 * 分治递归法：
	 * 每次计算 指数/2，将结果相乘。 如果为奇数 最后再乘一个x即可。
	 * @param x
	 * @param n
	 * @return
	 */
	public double myPow(double x, int n) {
		long N = n;
		return N >= 0 ? quickMul(x, N) : 1 / quickMul(x, -N);
	}

	public double quickMul(double x, long N) {
		if (N == 0) {
			return 1d;
		}
		double y = quickMul(x, N / 2);
		return N % 2 == 0 ? y * y : y * y * x;
	}

	public double myPowTimeout(double x, int n) {
		if (x == 0) {
			return 0;
		}
		if (n == 0) {
			return 1;
		}
		if (n == 1) {
			return x;
		}
		double r = x;
		for (int i = 1; i < Math.abs(n); ++i) {
			r *= x;
		}
		if (n < 0) {
			return 1 / r;
		} else {
			return r;
		}
	}

}

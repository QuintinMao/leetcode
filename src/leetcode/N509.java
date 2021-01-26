package leetcode;

/**
 * 斐波那契数列
 * @author Johnny
 * 
 * 2021年1月7日 上午11:33:40
 */
public class N509 {

	public static void main(String[] args) {
		System.out.println(new N509().fib(30));
	}

	public int fib(int n) {
		if (n < 2) {
			return n;
		}
		int p = 0, q = 0, r = 1;
		for (int i = 1; i < n; i++) {
			p = q;
			q = r;
			r = p + q;
		}
		return r;
	}

}

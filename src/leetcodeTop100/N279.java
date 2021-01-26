package leetcodeTop100;

import java.util.Arrays;

/**
 * Perfect Squares
 * 
 * @author Johnny
 * 
 *         2021年1月22日 下午3:02:10
 */
public class N279 {

	public static void main(String[] args) {
		int n = 121;
		System.out.println(new N279().numSquares(n));
	}

	public int numSquares(int n) {
		if (n < 4) {
			return n;
		}
		int dp[] = new int[n + 1];
		Arrays.fill(dp, Integer.MAX_VALUE);
		dp[0] = 0;

		int max_square_index = (int) Math.sqrt(n) + 1;
		int tabs[] = new int[max_square_index];
		for (int i = 1; i < max_square_index; ++i) {
			tabs[i] = i * i;
		}

		for (int i = 1; i <= n; i++) {
			for (int s = 1; s < max_square_index; s++) {
				if (i < tabs[s]) {
					break;
				}
				dp[i] = Math.min(dp[i], dp[i - tabs[s]] + 1);
			}
		}

		return dp[n];

	}

}

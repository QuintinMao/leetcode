package leetcodeTop100;

/**
 * Best Time to Buy and Sell Stock with Cooldown
 * 
 * @author Johnny
 * 
 *         2021年1月22日 下午12:49:18
 */
public class N309 {

	public static void main(String[] args) {
		int[] prices = new int[] { 1, 2, 3, 0, 2 };
		System.out.println(new N309().maxProfit2(prices));
	}

	/**
	 * 
	 * @param prices
	 * @return
	 */
	public int maxProfit(int[] prices) {
		int len = prices.length;
		if (len < 2) {
			return 0;
		}
		// [0] 满仓 [1] 空仓冷却 [2] 空仓可购
		int[][] dp = new int[len][3];
		dp[0][0] = -prices[0];
		dp[0][1] = Integer.MIN_VALUE;
		dp[0][2] = 0;
		for (int i = 1; i < len; i++) {
			dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][2] - prices[i]);
			dp[i][1] = dp[i - 1][0] + prices[i];
			dp[i][2] = Math.max(dp[i - 1][1], dp[i - 1][2]);
		}

		return Math.max(dp[len - 1][1], dp[len - 1][2]);

	}

	/**
	 * 空间优化
	 * 
	 * @param prices
	 * @return
	 */
	public int maxProfit2(int[] prices) {
		int len = prices.length;
		if (len < 2) {
			return 0;
		}
		// [0] 满仓 [1] 空仓冷却 [2] 空仓可购
		int dp0 = -prices[0];
		int dp1 = Integer.MIN_VALUE, dp2 = 0;
		for (int i = 1; i < len; i++) {
			int t = dp0;
			dp0 = Math.max(t, dp2 - prices[i]);
			dp2 = Math.max(dp1, dp2);
			dp1 = t + prices[i];
		}

		return Math.max(dp1, dp2);

	}

}

package leetcode;

/**
 * N.123 买卖股票的最佳时机III
 * 
 * @author Johnny
 * 
 *         2021年1月9日 下午4:56:08
 */
public class N123 {

	public static void main(String[] args) {
		int[] prices = { 3, 3, 5, 0, 0, 3, 1, 4 };
		System.out.println(new N123().maxProfit(prices));
	}

	public int maxProfit(int[] prices) {
		int n = prices.length;
		// [i][0] = buy1; [i][1] = sell1; [i][2] = buy2; [i][3] = sell2;
		int[][] dp = new int[n][4];
		dp[0][0] = -prices[0];
		dp[0][1] = 0;
		dp[0][2] = -prices[0];
		dp[0][3] = 0;
		for (int i = 1; i < n; i++) {
			dp[i][0] = Math.max(dp[i - 1][0], -prices[i]);
			dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] + prices[i]);
			dp[i][2] = Math.max(dp[i - 1][2], dp[i - 1][1] - prices[i]);
			dp[i][3] = Math.max(dp[i - 1][3], dp[i - 1][2] + prices[i]);
		}
		return dp[n - 1][1];
	}

}

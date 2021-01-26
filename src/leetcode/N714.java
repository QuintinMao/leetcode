package leetcode;

/**
 * 714. 买卖股票的最佳时机含手续费
 * 
 *
 */
public class N714 {
	public static void main(String[] args) {
		int[] prices = { 1, 3, 2, 8, 4, 9 };
		int fee = 2;
		System.out.println(maxProfit2nd(prices, fee));
	}

	/**
	 * 贪心
	 * 
	 * @param prices
	 * @param fee
	 * @return
	 */
	public static int maxProfit(int[] prices, int fee) {
		int buy = prices[0] + fee;
		int profit = 0;
		for (int i = 1; i < prices.length; i++) {
			if (prices[i] + fee < buy) {
				buy = prices[i] + fee;
			} else if (prices[i] > buy) {
				profit += prices[i] - buy;
				buy = prices[i];
			}
		}
		return profit;
	}

	/**
	 * 动态规划
	 * 
	 * @param prices
	 * @param fee
	 * @return
	 */
	public static int maxProfit2nd(int[] prices, int fee) {
//		int sell = 0, buy = -prices[0];
//		for (int i = 1; i < prices.length; i++) {
//			sell = Math.max(sell, buy + prices[i] - fee);
//			buy = Math.max(buy, sell - prices[i]);
//		}
//
//		return sell;
		int n = prices.length;
		// dp[i][0] 表示没有持有股票的最大利润
		// dp[i][1] 表示持有股票的最大利润
		int[][] dp = new int[n][2];
		dp[0][0] = 0;
		dp[0][1] = -prices[0];
		for (int i = 1; i < n; i++) {
			// 第i天停盘时，不持有股票的可能性：1.昨天没持有；2.昨天持有，今天卖出。
			dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i] - fee);
			// 第i天停盘时，持有股票的可能性：1.昨天持有；2.昨天没持有，今天买进。
			dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
		}

		return dp[n - 1][0];
	}
}

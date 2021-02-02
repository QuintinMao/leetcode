package leetcode.Jan28;

import java.util.Arrays;

/**
 * 
 * @author Johnny
 * 
 *         2021年1月28日 上午11:06:06
 */
public class BestTimeToBuyAndSellStock {

	public static void main(String[] args) {
		int[] prices = new int[] { 1, 2, 3, 4, 5 };
		System.out.println(new BestTimeToBuyAndSellStock().maxProfit(prices));
	}

	/**
	 * N121 只能进行一次交易
	 * 
	 * @param prices
	 * @return
	 */
	public int maxProfit(int[] prices) {
		int n = prices.length;
		int profit = 0;
		int price = prices[0];
		for (int i = 1; i < n; i++) {
			profit = Math.max(profit, prices[i] - price);
			price = Math.min(price, prices[i]);
		}
		return profit;

	}

	/**
	 * N122 可以进行多次交易
	 * 
	 * 由于状态转移方程只涉及前一天所以空间可以进行优化
	 * 
	 * 也可用贪心的策略， 即：不考虑交易，只把每次价格上涨的利润都累加起来。
	 * 
	 * @param prices
	 * @return
	 */
	public int maxProfitII(int[] prices) {
		int n = prices.length;
		int[][] dp = new int[n][2];
		// 空仓
		dp[0][0] = 0;
		// 持仓
		dp[0][1] = -prices[0];

		for (int i = 1; i < n; i++) {
			// 空仓：昨天空仓 和 今天卖出
			dp[i][0] = Math.max(dp[i - 1][0], prices[i] + dp[i - 1][1]);
			// 持仓：昨天持仓 和今天买入
			dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
		}
		// 返回最后一天空仓的最大利润
		return dp[n - 1][0];
	}

	/**
	 * N123. 只能进行两次交易
	 * 
	 * @param prices
	 * @return
	 */
	public int maxProfitIII(int[] prices) {
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
		return dp[n - 1][3];
	}

	/**
	 * N188. 最多进行K笔交易
	 * 
	 * @param prices
	 * @return
	 */
	public int maxProfitIV(int[] prices, int k) {
		int n = prices.length;
		k = Math.min(k, n / 2);
		if (k == 0) {
			return 0;
		}
		int[] sell = new int[k];
		int[] buy = new int[k];
		Arrays.fill(buy, -prices[0]);
		for (int i = 1; i < n; i++) {
			for (int j = 0; j < k; j++) {
				sell[j] = Math.max(sell[j], prices[i] + buy[j]);
				buy[j] = Math.max(buy[j], (j == 0 ? 0 : sell[j - 1]) - prices[i]);
			}
		}
		return sell[k - 1];
	}

	/**
	 * N309. 多次交易含冷却
	 * 
	 * @param prices
	 * @return
	 */
	public int maxProfitCD(int[] prices) {
		int len = prices.length;
		if (len < 2) {
			return 0;
		}
		// [0] 满仓 [1] 空仓冷却 [2] 空仓可购
		int dp0 = -prices[0];
		int dp1 = 0, dp2 = 0;
		for (int i = 1; i < len; i++) {
			int t = dp0;
			dp0 = Math.max(t, dp2 - prices[i]);
			dp2 = Math.max(dp1, dp2);
			dp1 = t + prices[i];
		}

		return Math.max(dp1, dp2);
	}

	/**
	 * N714. 含手续费
	 * 
	 * @param prices
	 * @param fee
	 * @return
	 */
	public int maxProfitFee(int[] prices, int fee) {
		int sell = 0, buy = -prices[0];
		for (int i = 1; i < prices.length; i++) {
			sell = Math.max(sell, buy + prices[i] - fee);
			buy = Math.max(buy, sell - prices[i]);
		}
		return sell;
	}
}

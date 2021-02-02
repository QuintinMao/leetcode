package leetcode.Jan28;

public class ClimbingStairs {
	public static void main(String[] args) {
		ClimbingStairs cs = new ClimbingStairs();
		for (int i = 1; i < 50; i++) {
			System.out.println(i + " : " + cs.climbStairsDP(i));
			cs.res = 0;
		}
	}

	/**
	 * 动态规划
	 * 
	 * 可优化， 使用常数空间
	 * 
	 * @param n
	 * @return
	 */
	public int climbStairsDP(int n) {
		if (n < 3) {
			return n;
		}
		int[] dp = new int[n + 1];
		// 边界条件
		dp[1] = 1;
		dp[2] = 2;
		// 跳到第i层台阶有两种情况， 1.从第i-1层跳1格，2.从第i-2层跳2格。
		// ∴ 状态转移方程： dp[i] = dp[i-1] + dp[2];
		for (int i = 3; i <= n; i++) {
			dp[i] = dp[i - 1] + dp[i - 2];
		}
		return dp[n];
	}

	public int climbStairsDPOP(int n) {
		if (n < 3) {
			return n;
		}
		// 边界条件
		int prev1 = 1;
		int prev2 = 2;
		int prev3 = 0;
		// 跳到第i层台阶有两种情况， 1.从第i-1层跳1格，2.从第i-2层跳2格。
		// ∴ 状态转移方程： dp[i] = dp[i-1] + dp[2];
		for (int i = 3; i <= n; i++) {
			prev3 = prev1 + prev2;
			prev1 = prev2;
			prev2 = prev3;
		}
		return prev3;
	}

	public int climbStairsDFS(int n) {
		dfs(1, n);
		dfs(2, n);
		return res;
	}

	int res = 0;

	public void dfs(int step, int target) {
		if (step > target) {
			return;
		}
		if (target - step == 0) {
			res++;
			return;
		}
		dfs(1, target - step);
		dfs(2, target - step);
	}
}

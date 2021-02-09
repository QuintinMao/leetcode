package leetcode.Feb04;

/**
 * 134.
 * 
 * @author Johnny
 * 
 *         2021年2月4日 下午12:53:28
 */
public class GasStation {

	public static void main(String[] args) {
		int[] gas = { 5, 1, 2, 3, 4 };
		int[] cost = { 4, 4, 1, 5, 1 };
		System.out.println(new GasStation().canCompleteCircuit1(gas, cost));
	}

	public int canCompleteCircuit1(int[] gas, int[] cost) {
		int n = gas.length;
		int[] dif = new int[n];
		for (int i = 0; i < n; ++i) {
			dif[i] = gas[i] - cost[i];
		}
		for (int i = 0; i < n; ++i) {
			if (dif[i] < 0) {
				continue;
			}
			int j = (i == n - 1) ? 0 : i + 1;
			int tank = dif[i];
			while (tank + dif[j] >= 0) {
				tank = tank + dif[j];
				j = (j == n - 1) ? 0 : j + 1;
				if (i == j) {
					return i;
				}
			}

		}
		return -1;
	}

	public int canCompleteCircuit(int[] gas, int[] cost) {
		int n = gas.length;
		if (n == 1) {
			return gas[0] > cost[0] ? 0 : -1;
		}
		for (int i = 0; i < n; ++i) {
			int tank = gas[i] - cost[i];
			if (tank < 0) {
				continue;
			}
			int j = (i == n - 1) ? 0 : i + 1;
			while (i != j) {
				tank = tank + gas[j] - cost[j];
				if (tank >= 0) {
					j = (j == n - 1) ? 0 : j + 1;
				} else {
					break;
				}

			}
			if (i == j) {
				return i;
			}
		}
		return -1;
	}

}

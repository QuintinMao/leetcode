package leetcode.Feb05;

/**
 * 1208.
 * 
 * @author Johnny
 * 
 *         2021年2月5日 下午4:30:59
 */
public class GetEqualSubstringsWithinBudget {

	public static void main(String[] args) {
		String s = "abcd";
		String t = "cdef";
		int maxCost = 1;
		System.out.println(new GetEqualSubstringsWithinBudget().equalSubstring(s, t, maxCost));
	}

	public int equalSubstring(String s, String t, int maxCost) {
		int len = s.length();
		int[] costs = new int[len];
		for (int i = 0; i < len; ++i) {
			costs[i] = Math.abs(s.charAt(i) - t.charAt(i));
		}

		int res = 0;
		int cost = 0;
		int left = 0;
		for (; left < len; ++left) {
			if (costs[left] <= maxCost) {
				break;
			}
		}
		if (left == len) {
			return 0;
		}
		int right = left;
		cost += costs[left];
		while (right < len - 1) {
			if (costs[right + 1] + cost <= maxCost) {
				cost += costs[++right];
			} else {
				res = Math.max(res, right - left + 1);
				if (right + 1 == len - 1) {
					break;
				}
				cost -= costs[left++];
			}
		}
		res = Math.max(res, right - left + 1);
		return res;
	}
}

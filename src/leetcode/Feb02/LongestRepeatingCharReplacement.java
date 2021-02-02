package leetcode.Feb02;

/**
 * 424 双指针
 * 
 * @author Johnny
 * 
 *         2021年2月2日 下午12:13:12
 */
public class LongestRepeatingCharReplacement {

	public static void main(String[] args) {
		String s = "ABBB";
		int k = 2;
		System.out.println(new LongestRepeatingCharReplacement().characterReplacement(s, k));
	}

	public int characterReplacement(String s, int k) {
		int len = s.length();
		//使用int数组 存储字母出现的次数
		int[] counts = new int[26];
		//窗口中字母出现次数最多的那个字母的数量
		int maxn = 0;
		int left = 0, right = 0;
		while (right < len) {
			//窗口右边界字母++
			counts[s.charAt(right) - 'A']++;
			//更新maxn
			maxn = Math.max(maxn, counts[s.charAt(right) - 'A']);
			//如果 窗口长度-最多重复字母大于K，则将窗口左侧边界右移
			if (right - left + 1 - maxn > k) {
				//更新左边界移除的字母的次数
				counts[s.charAt(left) - 'A']--;
				left++;
			}
			
			right++;
		}

		return right - left;
	}

}

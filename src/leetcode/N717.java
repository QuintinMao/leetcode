package leetcode;

/**
 * 1比特与2比特字符
 * 
 * @author Johnny
 * 
 * 2021年1月7日 上午11:34:09
 */
public class N717 {

	public static void main(String[] args) {
		int[] bits = { 1, 1, 1, 0 };
		System.out.println(isOneBitCharacter(bits));
	}

	public static boolean isOneBitCharacter(int[] bits) {
		for (int i = 0; i < bits.length;) {
			int bit = bits[i];
			if (i + 1 == bits.length) {
				return bit == 0;
			}
			if (bit == 1) {
				i += 2;
			} else if (bit == 0) {
				i += 1;
			} else {
				break;
			}
		}
		return false;
	}
}

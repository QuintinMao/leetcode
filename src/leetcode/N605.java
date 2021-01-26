package leetcode;

/**
 * 种花问题
 *
 */
public class N605 {
	public static void main(String[] args) {
		int[] flowerbed = { 1, 0, 0, 0, 0, 0, 1 };
		int n = 2;
		System.out.println(new N605().canPlaceFlowers(flowerbed, n));
	}

	public boolean canPlaceFlowers(int[] flowerbed, int n) {
		for (int i = 0; i < flowerbed.length; i++) {
			int before = i == 0 ? 0 : flowerbed[i - 1];
			int after = i == flowerbed.length - 1 ? 0 : flowerbed[i + 1];
			int cur = flowerbed[i];
			if (cur == 0 && before == 0 && after == 0) {
				flowerbed[i] = 1;
				n--;
				before = 1;
				if (n <= 0) {
					break;
				}
			}
		}
		return n <= 0;
	}
}

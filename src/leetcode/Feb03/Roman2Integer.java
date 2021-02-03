package leetcode.Feb03;

/**
 * 13.
 * 
 * 12. Integer2Roman
 * 
 * @author Johnny
 * 
 *         2021年2月3日 下午3:51:23
 */
public class Roman2Integer {

	public static void main(String[] args) {
		System.out.println(new Roman2Integer().romanToInt("MCMXCIV"));
	}

	public int romanToInt(String s) {
		char[] ss = s.toCharArray();
		int len = ss.length;
		char[] tab = new char[26];
		tab['I' - 'A'] = 1;
		tab['V' - 'A'] = 5;
		tab['X' - 'A'] = 10;
		tab['L' - 'A'] = 50;
		tab['C' - 'A'] = 100;
		tab['D' - 'A'] = 500;
		tab['M' - 'A'] = 1000;
		char prev = ss[len - 1];
		int res = tab[prev - 'A'];
		for (int i = len - 2; i >= 0; --i) {
			if (tab[ss[i] - 'A'] < tab[prev - 'A']) {
				res -= tab[ss[i] - 'A'];
			} else {
				res += tab[ss[i] - 'A'];
				prev = ss[i];
			}
		}

		return res;
	}

}

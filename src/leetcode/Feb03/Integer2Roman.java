package leetcode.Feb03;

/**
 * 12.
 * 
 * 13.
 * 
 * @author Johnny
 * 
 *         2021年2月3日 下午4:48:05
 */
public class Integer2Roman {

	public static void main(String[] args) {
		System.out.println(new Integer2Roman().intToRoman(3456));
	}

	public String intToRoman2(int num) {
		int[] values = { 1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1 };
		String[] romans = { "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I" };
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < values.length && num >= 0; ++i) {
			while (values[i] <= num) {
				num -= values[i];
				sb.append(romans[i]);
			}
		}
		return sb.toString();

	}

	public String intToRoman(int num) {
		int m = num / 1000;
		num %= 1000;
		StringBuilder res = new StringBuilder();
		for (int i = 0; i < m; ++i) {
			res.append("M");
		}
		int c = num / 100;
		num %= 100;
		switch (c) {
		case 1:
			res.append("C");
			break;
		case 2:
			res.append("CC");
			break;
		case 3:
			res.append("CCC");
			break;
		case 4:
			res.append("CD");
			break;
		case 5:
			res.append("D");
			break;
		case 6:
			res.append("DC");
			break;
		case 7:
			res.append("DCC");
			break;
		case 8:
			res.append("DCCC");
			break;
		case 9:
			res.append("CM");
			break;
		}
		int x = num / 10;
		num %= 10;
		switch (x) {
		case 1:
			res.append("X");
			break;
		case 2:
			res.append("XX");
			break;
		case 3:
			res.append("XXX");
			break;
		case 4:
			res.append("XL");
			break;
		case 5:
			res.append("L");
			break;
		case 6:
			res.append("LX");
			break;
		case 7:
			res.append("LXX");
			break;
		case 8:
			res.append("LXXX");
			break;
		case 9:
			res.append("XC");
			break;
		}
		switch (num) {
		case 1:
			res.append("I");
			break;
		case 2:
			res.append("II");
			break;
		case 3:
			res.append("III");
			break;
		case 4:
			res.append("IV");
			break;
		case 5:
			res.append("V");
			break;
		case 6:
			res.append("VI");
			break;
		case 7:
			res.append("VII");
			break;
		case 8:
			res.append("VIII");
			break;
		case 9:
			res.append("IX");
			break;
		}

		return res.toString();
	}

}

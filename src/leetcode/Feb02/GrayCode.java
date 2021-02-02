package leetcode.Feb02;

import java.util.ArrayList;
import java.util.List;

/**
 * 89. The gray code is a binary numeral system where two successive values
 * differ in only one bit.
 * 
 * @author Johnny
 * 
 *         2021年2月2日 下午4:07:20
 */
public class GrayCode {

	public static void main(String[] args) {

	}

	public List<Integer> grayCode(int n) {
		List<Integer> res = new ArrayList<Integer>();
		res.add(0);
		int head = 1;
		for (int i = 0; i < n; i++) {
			for (int j = res.size() - 1; j >= 0; j--)
				res.add(head + res.get(j));
			head <<= 1;
		}
		return res;
	}

}

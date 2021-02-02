package leetcode.Feb01;

import java.util.ArrayList;
import java.util.List;

/**
 * 54
 * 模拟， 沿对角线 逐层 顺时针遍历
 * @author Johnny
 * 
 *         2021年2月1日 下午2:13:06
 */
public class SpiralMatrix {

	public static void main(String[] args) {
		int[][] matrix = new int[][] { { 2, 3 } };
		new SpiralMatrix().spiralOrder(matrix).forEach(System.out::println);

	}

	public List<Integer> spiralOrder(int[][] matrix) {
		List<Integer> res = new ArrayList<>();
		int m = matrix.length;
		int n = matrix[0].length;
		if (m == 1 || n == 1) {
			for (int i = 0; i < (m == 1 ? n : m); i++) {
				res.add(m == 1 ? matrix[0][i] : matrix[i][0]);
			}
			return res;
		}
		int total = m * n;
		int diagonal = Math.min(m, n) - 1;
		for (int i = 0; i < diagonal; i++) {
			for (int j = i; j < n - i; j++) {
				res.add(matrix[i][j]);
			}
			for (int j = i + 1; j < m - i; j++) {
				res.add(matrix[j][n - i - 1]);
			}
			if (res.size() == total) {
				break;
			}
			for (int j = n - i - 2; j >= i; j--) {
				res.add(matrix[m - 1 - i][j]);
			}
			for (int j = m - i - 2; j > i; j--) {
				res.add(matrix[j][i]);
			}
		}

		return res;
	}
}

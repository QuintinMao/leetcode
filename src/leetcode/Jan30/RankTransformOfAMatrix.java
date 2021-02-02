package leetcode.Jan30;

import java.util.Arrays;

public class RankTransformOfAMatrix {
	public static void main(String[] args) {
//		int[][] matrix = { { 20, -21, 14 }, { -19, 4, 19 }, { 22, -47, 24 }, { -19, 4, 19 } };
		int[][] matrix = { { 7, 3, 6 }, { 1, 4, 5 }, { 9, 8, 2 } };
		int[][] res = new RankTransformOfAMatrix().matrixRankTransform(matrix);
		for (int[] rr : res) {
			for (int r : rr) {
				System.out.print(r + ",");
			}
			System.out.println();
		}
	}

	public int[][] matrixRankTransform(int[][] matrix) {
		int m = matrix.length;
		int n = matrix[0].length;
		int[][] res = new int[m][n];
		int[][] sorted = new int[m * n][3];
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				sorted[i * n + j] = new int[] { i, j, matrix[i][j] };
			}
		}
		Arrays.sort(sorted, (v1, v2) -> v1[2] - v2[2]);
		for (int[] v : sorted) {
			int x = v[0];
			int y = v[1];
			int val = v[2];
			int level = 0, vertical = 0;
			for (int i = 0; i < n; i++) {
				if (res[x][level] <= res[x][i]) {
					level = i;
				}
			}
			for (int i = 0; i < m; i++) {
				if (res[vertical][y] <= res[i][y]) {
					vertical = i;
				}
			}
			if (res[x][level] == 0 && res[vertical][y] == 0) {
				res[x][y] = 1;
			} else if (res[x][level] != 0 && res[vertical][y] != 0) {
				if (res[x][level] == res[vertical][y]) {
					if (matrix[x][level] == val) {
						res[x][y] = res[x][level];
					} else {
						if (matrix[x][level] == val) {
							res[x][level]++;
							res[x][y] = res[x][level];
						} else if (matrix[vertical][y] == val) {
							res[vertical][y]++;
							res[x][y] = res[vertical][y];
						} else {
							res[x][y] = Math.max(res[x][level], res[vertical][y]) + 1;
						}
					}
				} else {
					if (res[x][level] > res[vertical][y]) {
						if (matrix[x][level] == val) {
							res[x][y] = res[x][level];
						} else {
							res[x][y] = res[x][level] + 1;
						}
					} else {
						if (matrix[vertical][y] == val) {
							res[x][y] = res[vertical][y];
						} else {
							res[x][y] = res[vertical][y] + 1;
						}
					}
				}
			} else {
				if (res[x][level] != 0) {
					if (matrix[x][level] == val) {
						res[x][y] = res[x][level];
					} else {
						res[x][y] = res[x][y] = res[x][level] + 1;
					}
				} else {
					if (matrix[vertical][y] == val) {
						res[x][y] = res[vertical][y];
					} else {
						res[x][y] = res[vertical][y] + 1;
					}
				}
			}
		}
		return res;
	}
}

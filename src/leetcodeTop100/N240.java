package leetcodeTop100;

/**
 * Search a 2D Matrix II
 * 
 * @author Johnny
 * 
 *         2021年1月16日 下午12:23:09
 */
public class N240 {

	public static void main(String[] args) {
//		int[][] matrix = { { 1, 4, 7, 11, 15 }, { 2, 5, 8, 12, 19 }, { 3, 6, 9, 16, 22 }, { 10, 13, 14, 17, 24 },
//				{ 18, 21, 23, 26, 30 } };
		int[][] matrix = { { 5 }, { 6 } };
		int target = 6;
		System.out.println(new N240().searchMatrix3(matrix, target));
	}

	/**
	 * 暴力 O(m*n)
	 * 
	 * @param matrix
	 * @param target
	 * @return
	 */
	public boolean searchMatrix(int[][] matrix, int target) {
		int m = matrix.length;
		int n = matrix[0].length;

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (matrix[i][j] == target) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 沿对角线 以此遍历每个元素，对行和列进行二分搜索 O(min(m,n)logn)
	 * 
	 * @param matrix
	 * @param target
	 * @return
	 */
	public boolean searchMatrix2(int[][] matrix, int target) {
		// 对角线个数=min(row,cols)
		int diagonal = Math.min(matrix.length, matrix[0].length);
		// 左上角
		int leftTop = matrix[0][0];
		// 右下角
		int rightBottom = matrix[matrix.length - 1][matrix[matrix.length - 1].length - 1];
		// 寻找的元素小于左下角，或者大于右下角 直接返回false
		if (leftTop > target || rightBottom < target) {
			return false;
		}

		// target更接近于右下角，从右下角开始搜索
		if (Math.abs(rightBottom - target) < Math.abs(leftTop - target)) {
			for (int i = 0; i < diagonal; i++) {
				boolean rowFound = binarySearch(matrix, matrix.length - 1 - i, target, true);
				boolean colFound = binarySearch(matrix, matrix[matrix.length - 1].length - 1 - i, target, false);
				if (rowFound || colFound) {
					return true;
				}
			}
			// target更接近于左上角，从左上角开始搜索
		} else {
			for (int i = 0; i < diagonal; i++) {
				boolean rowFound = binarySearch(matrix, i, target, true);
				boolean colFound = binarySearch(matrix, i, target, false);
				if (rowFound || colFound) {
					return true;
				}
			}
		}
		return false;
	}

	/**
	 * 矩阵上的二分搜索
	 * 
	 * @param matrix
	 * @param start
	 * @param target
	 * @param row    true:搜索行 false:搜索列
	 * @return
	 */
	public boolean binarySearch(int[][] matrix, int start, int target, boolean row) {
		int lo = start;
		int hi = row ? matrix[start].length - 1 : matrix.length - 1;
		while (lo <= hi) {
			int mid = (lo + hi) / 2;
			if (row) {
				if (matrix[start][mid] < target) {
					lo = mid + 1;
				} else if (matrix[start][mid] > target) {
					hi = mid - 1;
				} else {
					return true;
				}
			} else {
				if (matrix[mid][start] < target) {
					lo = mid + 1;
				} else if (matrix[mid][start] > target) {
					hi = mid - 1;
				} else {
					return true;
				}
			}
		}
		return false;
	}

	public boolean searchMatrix3(int[][] matrix, int target) {
		int row = 0;
		int col = matrix[0].length - 1;

		while (row < matrix.length && col >= 0) {
			if (matrix[row][col] < target) {
				row++;
			} else if (matrix[row][col] > target) {
				col--;
			} else {
				return true;
			}
		}
		return false;
	}

}

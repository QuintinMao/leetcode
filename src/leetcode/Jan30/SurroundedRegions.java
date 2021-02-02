package leetcode.Jan30;

/**
 * 1.并查集 2.DFS 从边界的Odfs 标记所有有连接的O。 最后board中剩余的O就是要替换的
 * 
 * @author Johnny
 * 
 *         2021年1月30日 下午2:00:14
 */
public class SurroundedRegions {
	public static void main(String[] args) {
		char[][] board = { { 'X', 'X', 'X', 'X' }, { 'X', 'O', 'O', 'X' }, { 'X', 'X', 'O', 'X' },
				{ 'X', 'O', 'X', 'X' } };
		new SurroundedRegions().solve(board);
		for (char[] bb : board) {
			for (char b : bb) {
				System.out.print(b);
			}
			System.out.println();
		}
	}

	public void solve(char[][] board) {
		int m = board.length;
		if (m < 3) {
			return;
		}
		int n = board[0].length;
		// 设置哑节点0表示四周
		UnionFind uf = new UnionFind(m * n + 1);
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				if (board[i][j] == 'O') {
					int index = i * n + j + 1;
					// 在边界
					if (i == 0 || i == m - 1 || j == 0 || j == n - 1) {
						uf.union(index, 0);
					}

					// 向下
					if (i < m - 1 && board[i + 1][j] == 'O') {
						uf.union(index, index + n);
					}
					// 向右
					if (j < n - 1 && board[i][j + 1] == 'O') {
						uf.union(index, index + 1);
					}
				}
			}
		}

		for (int i = 1; i < m * n + 1; i++) {
			int x = (i - 1) / n;
			int y = (i - 1) % n;
			// 替换与哑节点不联通的'O'
			if (board[x][y] == 'O' && !uf.isConnected(i, 0)) {
				board[x][y] = 'X';
			}
		}
	}
}

class UnionFind {
	int[] parents;

	public UnionFind(int n) {
		parents = new int[n];
		for (int i = 0; i < n; i++) {
			parents[i] = i;
		}
	}

	public int find(int x) {
		if (parents[x] != x) {
			parents[x] = find(parents[x]);
		}

		return parents[x];
	}

	public void union(int x, int y) {
		int rootX = find(x);
		int rootY = find(y);
		if (rootX == rootY) {
			return;
		}

		parents[rootX] = rootY;
	}

	public boolean isConnected(int x, int y) {
		return find(x) == find(y);
	}
}
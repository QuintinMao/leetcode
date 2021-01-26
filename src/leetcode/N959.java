package leetcode;

public class N959 {
	int n;

	/**
	 * 根据题意，/ \\ 只可能将一个最小格子分成左右两个区域，因此每个最小格子占两个坐标。
	 * 最后返回并查集的联通分量个数
	 * @param grid
	 * @return
	 */
	public int regionsBySlashes(String[] grid) {
		n = grid.length;
		//初始化并查集，容量为2倍的格子数
		UnionFind uf = new UnionFind(n * n * 2);
		for (int i = 0; i < n; i++) {
			char[] ss = grid[i].toCharArray();
			for (int j = 0; j < ss.length; j++) {
				//格子的左坐标，（+1为右坐标）
				int thisIndex = getIndex(i, j);
				//如果格子是空格， 则合并左右区域
				if (ss[j] == ' ') {
					uf.union(thisIndex, thisIndex + 1);
				}
				// down 尝试向下合并
				if (i < n - 1) {
					char down = grid[i + 1].charAt(j);
					//向下合并 只能是右坐标与下方合并。 。 下方如果是/则左坐标，\\就是右坐标。
					int u1 = thisIndex, u2 = getIndex(i + 1, j) + (down == '/' ? 0 : 1);
					if (ss[j] == '/') {
						u1 += 1;
					}
					uf.union(u1, u2);
				}
				// right 尝试向右合并
				if (j < n - 1) {
					uf.union(thisIndex + 1, thisIndex + 2);
				}
			}
		}
		return uf.getCount();
	}

	public int getIndex(int i, int j) {
		return (i * n + j) * 2;
	}

	static class UnionFind {
		private int[] parents;
		private int count;

		public UnionFind(int n) {
			parents = new int[n];
			for (int i = 0; i < n; i++) {
				parents[i] = i;
			}
			count = n;
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
			count--;
		}

		public int getCount() {
			return this.count;
		}
	}
}

package leetcode;


/**
 * Remove Max Number of Edges to Keep Graph Fully Traversable
 * 
 * @author Johnny
 * 
 *         2021年1月27日 下午1:11:58
 */
public class N1579 {

	public static void main(String[] args) {
		int n = 4;
		int[][] edges = { { 3, 2, 3 }, { 1, 1, 2 }, { 2, 3, 4 } };
		System.out.println(new N1579().maxNumEdgesToRemove(n, edges));
	}

	public int maxNumEdgesToRemove(int n, int[][] edges) {
		UnionFind alice = new UnionFind(n);
		UnionFind bob = new UnionFind(n);
		int res = 0;
		// 公共边
		for (int[] edge : edges) {
			if (edge[0] == 3) {
				// 如果这条边已经联通
				if (alice.union(edge[1], edge[2]) || bob.union(edge[1], edge[2])) {
					res++;
				}
			}
		}
		for (int[] edge : edges) {
			if (edge[0] == 1) {
				if (!alice.union(edge[1], edge[2])) {
					res++;
				}
			} else if (edge[0] == 2) {
				if (!bob.union(edge[1], edge[2])) {
					res++;
				}
			}
		}
		if (alice.getCount() != 1 || bob.getCount() != 1) {
			return -1;
		}
		return res;
	}

	private class UnionFind {
		private int[] parents;
		private int count;

		public UnionFind(int n) {
			parents = new int[n + 1];
			for (int i = 0; i < n + 1; i++) {
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

		public boolean union(int x, int y) {
			int rootX = find(x);
			int rootY = find(y);
			if (rootX == rootY) {
				return false;
			}
			parents[rootX] = rootY;
			count--;
			return true;
		}

		public int getCount() {
			return this.count;
		}
	}

}

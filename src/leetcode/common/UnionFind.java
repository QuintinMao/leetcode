package leetcode.common;

public class UnionFind {
	int[] parents;
	int count;

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

	public boolean isConnected(int x, int y) {
		return find(x) == find(y);
	}
}

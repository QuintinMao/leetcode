package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Number of Operations to Make Network Connected
 * 
 * 并查集 将所有边输入并查集， 如果边已经联通，则cycle++； 输入完成后，看并查集的连通分量个数getCount。 如果 ==1 则已经全部联通；
 * 如果不等于1. 看看cycle的数量是否足够连接这些联通分量的。 ∵连接N个点 最少需要n-1条边
 * 
 * DFS DFS每一个顶点， 遍历到的左右节点记录visited状态=true 这样我能能得到联通分量的个数
 * 
 * @author Johnny
 * 
 *         2021年1月23日 下午12:09:14
 */
public class N1319 {

	public static void main(String[] args) {
		int n = 7;
		int[][] connections = { { 0, 1 }, { 1, 0 }, { 1, 2 }, { 3, 4 }, { 5, 6 } };
		System.out.println(new N1319().makeConnectedDFS(n, connections));
	}

	boolean[] visited;
	List<List<Integer>> edges;

	public int makeConnectedDFS(int n, int[][] connections) {
		// 如果边小于顶点-1， 无法组成
		if (connections.length < n - 1) {
			return -1;
		}
		visited = new boolean[n];
		edges = new ArrayList<>(n);
		for (int i = 0; i < n; i++) {
			edges.add(new ArrayList<>());
		}

		for (int[] edge : connections) {
			edges.get(edge[0]).add(edge[1]);
			edges.get(edge[1]).add(edge[0]);
		}

		int res = 0;
		for (int i = 0; i < n; i++) {
			if (!visited[i]) {
				dfs(i);
				res += 1;
			}
		}

		return res - 1;
	}

	public void dfs(int v) {
		visited[v] = true;
		for (int vr : edges.get(v)) {
			if (!visited[vr]) {
				dfs(vr);
			}
		}
	}

	public int makeConnected(int n, int[][] connections) {
		if (connections.length < n - 1) {
			return -1;
		}
		if (n < 2) {
			return 0;
		}
		int cycle = 0;
		UnionFind uf = new UnionFind(n);
		for (int[] edge : connections) {
			if (!uf.union(edge[0], edge[1])) {
				cycle += 1;
			}
		}
		int count = uf.getCount();
		return cycle >= count - 1 ? count - 1 : -1;
	}

	private class UnionFind {
		private int parents[];
		private int count;

		public UnionFind(int n) {
			this.parents = new int[n];
			this.count = n;
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

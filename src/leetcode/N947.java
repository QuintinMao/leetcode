package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Most Stones Removed with Same Row or Column
 * 
 * @author Johnny
 * 
 *         2021年1月15日 上午11:03:34
 */
public class N947 {

	public static void main(String[] args) {
//		int stones[][] = { { 0, 1 }, { 1, 2 }, { 1, 3 }, { 3, 3 }, { 2, 3 }, { 0, 2 } };
//		int stones[][] = { { 0, 0 }, { 1, 0 }, { 1, 2 }, { 2, 1 }, { 2, 2 }, { 0, 1 } };
		int stones[][] = { { 0, 1 }, { 0, 2 }, { 4, 3 }, { 2, 4 }, { 0, 3 }, { 1, 1 } };
//		int stones[][] = { { 1, 1 } };
		System.out.println(new N947().removeStones4(stones));
	}

	public int removeStones(int[][] stones) {
		int n = stones.length;
		// <x,ys>
		Map<Integer, Set<Integer>> xy = new HashMap<>();
		Map<Integer, Set<Integer>> yx = new HashMap<>();
		for (int[] stone : stones) {
			int x = stone[0];
			int y = stone[1];
			if (!xy.containsKey(x)) {
				xy.put(x, new HashSet<>());
			}
			xy.get(x).add(y);

			if (!yx.containsKey(y)) {
				yx.put(y, new HashSet<>());
			}
			yx.get(y).add(x);
		}

		for (int[] stone : stones) {
			int x = stone[0];
			int y = stone[1];
			if (xy.get(x).size() > 1 || yx.get(y).size() > 1) {
				xy.get(x).remove(y);
				if (xy.get(x).size() == 0) {
					xy.remove(x);
				}
				yx.get(y).remove(x);
				if (yx.get(y).size() == 0) {
					yx.remove(y);
				}
			}
		}
		int rs = 0;
		for (Set<Integer> s : xy.values()) {
			rs += s.size();
		}
		return n - rs;
	}

	/**
	 * union find 根据题意，可知 只要x相等 或者y相等， 就认为这块石头与另一块石头联通。 计算出所有的联通分量，每个联通分量只能保留一块石头
	 * 最大移除数量=石头数-联通分量*1 注意：x、y坐标为两个条件 无法直接复制到parents， 因此根据题意：坐标值<=10000 可让y+10001
	 * ，分断处理 小于10001的坐标为x坐标 否则为y坐标
	 * 
	 * @param stones
	 * @return
	 */
	public int removeStones2(int[][] stones) {
		UnionFind uf = new UnionFind();
		for (int[] stone : stones) {
			uf.union(stone[0], stone[1] + 10001);
		}
		return stones.length - uf.getCount();
	}

	static class UnionFind {
		// 维护xy，yx 的根节点，因为已经对xy进行了分段，因此用一个map就可以了
		private Map<Integer, Integer> parent;
		// 连通分量的个数
		private int count;

		public UnionFind() {
			this.parent = new HashMap<>();
			this.count = 0;
		}

		public int getCount() {
			return count;
		}

		public void union(int x, int y) {
			int rootX = find(x);
			int rootY = find(y);
			if (rootX == rootY) {
				return;
			}
			parent.put(rootX, rootY);
			count--;

		}

		public int find(int x) {
			if (!parent.containsKey(x)) {
				parent.put(x, x);
				count++;
			}

			if (x != parent.get(x)) {
				parent.put(x, find(parent.get(x)));
			}
			return parent.get(x);
		}
	}

	/**
	 * DFS深度优先搜索
	 * 
	 * @param stones
	 * @return
	 */
	public int removeStones3(int[][] stones) {
		int n = stones.length;
		List<List<Integer>> edges = new ArrayList<>();
		// 一个石头和其他所有石头比较
		for (int i = 0; i < n; i++) {
			edges.add(new ArrayList<>());
			for (int j = 0; j < n; j++) {
				// 如果x相同或者y相同， 则第i块石头和第j块石头联通。生成他们之间的一条边
				if (stones[i][0] == stones[j][0] || stones[i][1] == stones[j][1]) {
					edges.get(i).add(j);
				}
			}
		}

		boolean[] visited = new boolean[n];
		int num = 0;
		// dfs一个顶点会将所有与这个顶点联通的顶点的访问状态设置成true。
		// 因num计数器的个数就是不连通的图的个数。
		for (int i = 0; i < n; i++) {
			if (!visited[i]) {
				num++;
				dfs(i, edges, visited);
			}
		}
		return n - num;
	}

	public void dfs(int x, List<List<Integer>> edges, boolean[] visited) {
		// 根据题意 不需要维护访问中的状态，因此使用boolean表示未访问或已访问
		visited[x] = true;
		for (int y : edges.get(x)) {
			if (!visited[y]) {
				dfs(y, edges, visited);
			}
		}
	}

	/**
	 * DFS深度优先搜索 优化建图
	 * 
	 * @param stones
	 * @return
	 */
	public int removeStones4(int[][] stones) {
		int n = stones.length;
		List<List<Integer>> edges = new ArrayList<>();
		// 一个石头和其他所有石头比较
		for (int i = 0; i < n; i++) {
			edges.add(new ArrayList<>());
		}
		Map<Integer, List<Integer>> map = new HashMap<>();
		for (int i = 0; i < n; i++) {
			int x = stones[i][0];
			int y = stones[i][1];
			if (!map.containsKey(x)) {
				map.put(x, new ArrayList<>());
			}
			map.get(x).add(i);
			if (!map.containsKey(y + 10001)) {
				map.put(y + 10001, new ArrayList<>());
			}
			map.get(y + 10001).add(i);
		}
		for (Map.Entry<Integer, List<Integer>> entry : map.entrySet()) {
			List<Integer> list = entry.getValue();
			for (int i = 1; i < list.size(); i++) {
				edges.get(list.get(i - 1)).add(list.get(i));
				edges.get(list.get(i)).add(list.get(i - 1));
			}
		}

		boolean[] visited = new boolean[n];
		int num = 0;
		// dfs一个顶点会将所有与这个顶点联通的顶点的访问状态设置成true。
		// 因num计数器的个数就是不连通的图的个数。
		for (int i = 0; i < n; i++) {
			if (!visited[i]) {
				num++;
				dfs(i, edges, visited);
			}
		}
		return n - num;
	}

	/**
	 * 并查集 优化建图 CV
	 * @param stones
	 * @return
	 */
	public int removeStones5(int[][] stones) {
		int n = stones.length;
		DisjointSetUnion dsu = new DisjointSetUnion();
		for (int i = 0; i < n; i++) {
			dsu.unionSet(stones[i][0], stones[i][1] + 10000);
		}

		return n - dsu.numberOfConnectedComponent();
	}

	static class DisjointSetUnion {
		int[] f;
		int[] rank;

		public DisjointSetUnion() {
			f = new int[20001];
			rank = new int[20001];
			Arrays.fill(f, -1);
			Arrays.fill(rank, -1);
		}

		public int find(int x) {
			if (f[x] < 0) {
				f[x] = x;
				rank[x] = 1;
			}
			return f[x] == x ? x : (f[x] = find(f[x]));
		}

		public void unionSet(int x, int y) {
			int fx = find(x), fy = find(y);
			if (fx == fy) {
				return;
			}
			if (rank[fx] < rank[fy]) {
				int temp = fx;
				fx = fy;
				fy = temp;
			}
			rank[fx] += rank[fy];
			f[fy] = fx;
		}

		public int numberOfConnectedComponent() {
			int num = 0;
			for (int i = 0; i < 20000; i++) {
				if (f[i] == i) {
					num++;
				}
			}
			return num;
		}

	}
}

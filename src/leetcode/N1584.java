package leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Min Cost to Connect All Points
 * 
 * @author Johnny
 * 
 *         2021年1月19日 下午5:24:59
 */
public class N1584 {

	public static void main(String[] args) {
		int[][] points = { { 3, 12 }, { -2, 5 }, { -4, 1 } };
		System.out.println(new N1584().minCostConnectPointsPrime(points));
	}

	/**
	 * minimum spanning tree - Kruskal
	 * 
	 * 1.根据points中每个顶点，穷举出所有边。完全图。
	 * 
	 * 2.按照边的汉密尔顿距离从小到大排序
	 * 
	 * 3.遍历每一个边，将边的两个顶点输入并查集
	 * 
	 * 4.如果不属于同一连通分量，则将这个边的距离累加到结果上。并且计数器+1；
	 * 
	 * 5.当计数器等于points的length时，跳出循环。最小生成树完成。
	 * 
	 * TODO rank在本例中貌似没起到作用， 因为不需要得到最小生成树的形状， 只需要知道总的最小花费？
	 * 
	 * @param points
	 * @return
	 */
	public int minCostConnectPoints(int[][] points) {
		int n = points.length;
		DisjointSetUnion dsu = new DisjointSetUnion(n);
		List<Edge> edges = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			for (int j = i + 1; j < n; j++) {
				int dis = Math.abs(points[i][0] - points[j][0]) + Math.abs(points[i][1] - points[j][1]);
				edges.add(new Edge(dis, i, j));
			}
		}
		Collections.sort(edges, (e1, e2) -> e1.distance - e2.distance);
		int res = 0;
		int counter = 1;
		for (Edge edge : edges) {
			if (dsu.unionSet(edge.x, edge.y)) {
				res += edge.distance;
				counter++;
				if (counter == n) {
					break;
				}
			}
		}
		return res;
	}

	static class DisjointSetUnion {
		int[] parents;
//		int[] rank; 

		public DisjointSetUnion(int n) {
//			this.rank = new int[n];
//			Arrays.fill(this.rank, 1);
			this.parents = new int[n];
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

		public boolean unionSet(int x, int y) {
			int rootX = find(x), rootY = find(y);
			if (rootX == rootY) {
				return false;
			}

//			if (rank[rootX] < rank[rootY]) {
//				int temp = rootX;
//				rootX = rootY;
//				rootY = temp;
//			}
//			rank[rootX] += rank[rootY];
			parents[rootY] = rootX;
			return true;
		}
	}

	static class Edge {
		int distance, x, y;

		public Edge(int distance, int x, int y) {
			this.distance = distance;
			this.x = x;
			this.y = y;
		}

	}

	public int minCostConnectPoints2(int[][] points) {
		int n = points.length;
		UnionFind uf = new UnionFind(n);
		PriorityQueue<int[]> edges = new PriorityQueue<>((e1, e2) -> e1[2] - e2[2]);
		for (int i = 0; i < n; i++) {
			for (int j = i + 1; j < n; j++) {
				edges.add(new int[] { i, j,
						Math.abs(points[i][0] - points[j][0]) + Math.abs(points[i][1] - points[j][1]) });
			}
		}
		int res = 0;
		int counter = 1;
		while (!edges.isEmpty()) {
			int[] edge = edges.poll();
			if (uf.union(edge[0], edge[1])) {
				res += edge[2];
				counter++;
				if (counter == n) {
					break;
				}
			}
		}
		return res;
	}

	static class UnionFind {
		int[] parents;

		public UnionFind(int n) {
			this.parents = new int[n];
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
			int rootX = find(x), rootY = find(y);
			if (rootX == rootY) {
				return false;
			}
			parents[rootY] = rootX;
			return true;
		}
	}

	public int minCostConnectPointsPrime(int[][] points) {

		PriorityQueue<int[]> queue = new PriorityQueue<>((v1, v2) -> v1[2] - v2[2]);
		List<int[]> mst = new ArrayList<>();
		mst.add(points[0]);
		for (int i = 1; i < points.length; i++) {
			queue.offer(new int[] { points[i][0], points[i][1], calcDistance(points[i], mst.get(0)) });
		}
		int res = 0;
		while (!queue.isEmpty()) {
			int[] minDistance = queue.poll();
			mst.add(minDistance);
			res += minDistance[2];
			for (int[] p : mst) {
				Iterator<int[]> it = queue.iterator();
				while (it.hasNext()) {
					int[] cal = it.next();
					cal[2] = Math.min(cal[2], calcDistance(cal, p));
				}
			}
		}
		return res;
	}

	public int calcDistance(int[] v1, int[] v2) {
		return Math.abs(v1[0] - v2[0]) + Math.abs(v1[1] - v2[1]);
	}
}

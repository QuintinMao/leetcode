package leetcode.Jan30;

/**
 * 685
 * 
 * @author Johnny
 * 
 *         2021年1月30日 下午4:09:23
 */
public class RedundantConnectionII {

	public static void main(String[] args) {

	}

	public int[] findRedundantDirectedConnection(int[][] edges) {
		int n = edges.length;
		UnionFind uf = new UnionFind(n + 1);
		int[] parents = new int[n + 1];
		// 初始时，每个节点得父节点指向自己
		for (int i = 1; i <= n; i++) {
			parents[i] = i;
		}

		// 冲突的节点
		int conflict = -1;
		// 成环的节点
		int cycle = -1;

		for (int i = 0; i < n; i++) {
			int[] edge = edges[i];
			int fromNode = edge[0];
			int toNode = edge[1];
			// 判断toNode是否由多个父亲节点。

			// 第一次判断toNode，因为默认根节点是自己。所以不冲突。
			if (parents[toNode] != toNode) {
				// 走到这说明toNode的父亲节点>1。冲突
				conflict = i;
			} else {
				// 如果不冲突，更新toNode的父亲节点。
				parents[toNode] = fromNode;
				// 判断是否成环.在这条边输入并查集之前，两个node已经联通，说明这个边将形成环
				if (uf.isConnected(fromNode, toNode)) {
					cycle = i;
				} else {
					uf.union(fromNode, toNode);
				}
			}
		}
		// 没冲突，就成环
		if (conflict == -1) {
			return edges[cycle];
		} else {
			int[] conflictEdge = edges[conflict];
			// 冲突没成环
			if (cycle == -1) {
				return conflictEdge;
				// 冲突且成环
			} else {
				// 这句话很重要
				return new int[] { parents[conflictEdge[1]], conflictEdge[1] };
			}
		}

	}

}

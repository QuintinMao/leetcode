package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * N1203. Sort Items by Groups Respecting Dependencies 拓扑排序
 * 由于分组之间也有先导条件，所以 
 * BFS   建立两个图，一个是组图一个是items图
 * 
 * 先拓扑组图， 然后根据组图拓扑结果，拓扑组内的item。 将拓扑结果追加到总结果的末尾
 * ＴＯＤＯ   1.O(n+m)? 2.用DFS实现一遍
 *	初始化数据： 
 *
 * 
 * @author Johnny
 * 
 *         2021年1月12日 上午10:35:08
 */
public class N1203 {
	public static void main(String[] args) {
		int n = 8;
		int m = 2;
		int[] group = { -1, -1, 1, 0, 0, 1, 0, -1 };
		List<List<Integer>> beforeItems = new ArrayList<>();
		List<Integer> l0 = new ArrayList<>();
		List<Integer> l1 = new ArrayList<>();
		l1.add(6);
		List<Integer> l2 = new ArrayList<>();
		l2.add(5);
		List<Integer> l3 = new ArrayList<>();
		l3.add(6);
		List<Integer> l4 = new ArrayList<>();
		l4.add(3);
		l4.add(6);
		List<Integer> l5 = new ArrayList<>();
		List<Integer> l6 = new ArrayList<>();
		List<Integer> l7 = new ArrayList<>();
		beforeItems.add(l0);
		beforeItems.add(l1);
		beforeItems.add(l2);
		beforeItems.add(l3);
		beforeItems.add(l4);
		beforeItems.add(l5);
		beforeItems.add(l6);
		beforeItems.add(l7);

		int[] rs = new N1203().sortItemsCV(n, m, group, beforeItems);
		for (int i = 0; i < rs.length; i++) {
			int j = rs[i];
			System.out.print(j + ",");

		}
	}

	public int[] sortItemsCV(int n, int m, int[] group, List<List<Integer>> beforeItems) {
		List<List<Integer>> groupItem = new ArrayList<List<Integer>>();
		for (int i = 0; i < n + m; ++i) {
			groupItem.add(new ArrayList<Integer>());
		}

		// 组间和组内依赖图
		List<List<Integer>> groupGraph = new ArrayList<List<Integer>>();
		for (int i = 0; i < n + m; ++i) {
			groupGraph.add(new ArrayList<Integer>());
		}
		List<List<Integer>> itemGraph = new ArrayList<List<Integer>>();
		for (int i = 0; i < n; ++i) {
			itemGraph.add(new ArrayList<Integer>());
		}

		// 组间和组内入度数组
		int[] groupDegree = new int[n + m];
		int[] itemDegree = new int[n];

		List<Integer> id = new ArrayList<Integer>();
		for (int i = 0; i < n + m; ++i) {
			id.add(i);
		}

		int leftId = m;
		// 给未分配的 item 分配一个 groupId
		for (int i = 0; i < n; ++i) {
			if (group[i] == -1) {
				group[i] = leftId;
				leftId += 1;
			}
			groupItem.get(group[i]).add(i);
		}
		// 依赖关系建图
		for (int i = 0; i < n; ++i) {
			int curGroupId = group[i];
			for (int item : beforeItems.get(i)) {
				int beforeGroupId = group[item];
				if (beforeGroupId == curGroupId) {
					itemDegree[i] += 1;
					itemGraph.get(item).add(i);
				} else {
					groupDegree[curGroupId] += 1;
					groupGraph.get(beforeGroupId < curGroupId ? beforeGroupId : curGroupId)
							.add(beforeGroupId < curGroupId ? curGroupId : beforeGroupId);
				}
			}
		}

		// 组间拓扑关系排序
		List<Integer> groupTopSort = topSort(groupDegree, groupGraph, id);
		if (groupTopSort.size() == 0) {
			return new int[0];
		}
		int[] ans = new int[n];
		int index = 0;
		// 组内拓扑关系排序
		for (int curGroupId : groupTopSort) {
			int size = groupItem.get(curGroupId).size();
			if (size == 0) {
				continue;
			}
			List<Integer> res = topSort(itemDegree, itemGraph, groupItem.get(curGroupId));
			if (res.size() == 0) {
				return new int[0];
			}
			for (int item : res) {
				ans[index++] = item;
			}
		}
		return ans;
	}

	public List<Integer> topSort(int[] deg, List<List<Integer>> graph, List<Integer> items) {
		Queue<Integer> queue = new LinkedList<Integer>();
		for (int item : items) {
			if (deg[item] == 0) {
				queue.offer(item);
			}
		}
		List<Integer> res = new ArrayList<Integer>();
		while (!queue.isEmpty()) {
			int u = queue.poll();
			res.add(u);
			for (int v : graph.get(u)) {
				if (--deg[v] == 0) {
					queue.offer(v);
				}
			}
		}
		return res.size() == items.size() ? res : new ArrayList<Integer>();
	}

	class Node {
		int item;
		int group;

		public Node(int item, int group) {
			this.item = item;
			this.group = group;
		}
	}

	Map<Integer, PriorityQueue<Node>> edges;
	int[] visited;
	// 定义总结果 valid 默认 true
	boolean valid = true;
	List<Integer> result;

	public int[] sortItems(int n, int m, int[] group, List<List<Integer>> beforeItems) {
		result = new ArrayList<>(n);
		// 初始化节点访问状态 默认都是未访问
		visited = new int[n];
		// 遍历先导条件，设置边
		edges = new HashMap<>(n);
		for (int i = 0; i < n; i++) {
			List<Integer> list = beforeItems.get(i);
			if (list == null || list.size() == 0) {
				continue;
			}
			int v = i;
			for (int k : list) {
				if (!edges.containsKey(k)) {
					edges.put(k, new PriorityQueue<>((o1, o2) -> o1.group - o2.group));
				}
				edges.get(k).offer(new Node(v, group[v] < 0 ? Integer.MAX_VALUE : group[v]));
			}
		}
		// 遍历课程 ,dfs
		for (int item = 0; item < n && valid; item++) {
			if (visited[item] == 0) {
				dfs(item);
			}
		}
		if (!valid) {
			return new int[0];
		}
		int[] rs = new int[n];
		for (int i = 0; i < rs.length; i++) {
			rs[i] = result.get(rs.length - i - 1);
		}
		return rs;
	}

	// dfs 实现
	public void dfs(int node) {
		// 设置为访问中
		visited[node] = 1;
		// 遍历node所有边
		if (edges.containsKey(node)) {
			while (!edges.get(node).isEmpty()) {
				Node edge = edges.get(node).poll();
				// 如果边没有访问过 进行dfs
				if (visited[edge.item] == 0) {
					dfs(edge.item);
					// 如果总结果为假返回
					if (!valid) {
						return;
					}
					// 如果边的状态为1 说明环形，总结果设为false 返回
				} else if (visited[edge.item] == 1) {
					valid = false;
					return;
				}

			}
		}
		// 访问过所有边或者没有边，设置状态为2
		visited[node] = 2;
		result.add(node);
	}
}

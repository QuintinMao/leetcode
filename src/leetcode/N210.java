package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * 课程表 II
 * @author Johnny
 * 
 * 2021年1月13日 下午3:29:50
 */
public class N210 {

	public static void main(String[] args) {
		int numCourses = 1;
		int[][] prerequisites = {};
		for (int c : new N210().findOrder(numCourses, prerequisites)) {
			System.out.print(c + ",");
		}
	}

	public int[] findOrder(int numCourses, int[][] prerequisites) {
		return findOrderBFS(numCourses, prerequisites);
	}

	/**
	 * 有向图的广度优先搜索
	 * 
	 * 从入度作为切入点
	 * 
	 * @param numCourses
	 * @param prerequisites
	 * @return
	 */
	public int[] findOrderBFS(int numCourses, int[][] prerequisites) {
		edges = new HashMap<>(numCourses);
		inDegrees = new int[numCourses];
		// 遍历prerequisites
		for (int[] edge : prerequisites) {
			// 设置边
			// 注意 方向：edge[1]->edge[0] edge[1]为先决条件
			if (!edges.containsKey(edge[1])) {
				edges.put(edge[1], new ArrayList<Integer>());
			}
			edges.get(edge[1]).add(edge[0]);
			// 计算入度
			inDegrees[edge[0]] += 1;
		}
		// 定义队列
		Queue<Integer> queue = new LinkedList<>();
		// 将入度为0的顶点加入队列
		for (int id = 0; id < numCourses; id++) {
			if (inDegrees[id] == 0) {
				queue.offer(id);
			}
		}
		int[] result = new int[numCourses];
		int visited = 0;
		// poll from queue
		// 取出的顶点入度==0
		while (!queue.isEmpty()) {
			// 更新计数器
			int thisCourse = queue.poll();
			result[visited++] = thisCourse;
			// 遍历这个顶点的所有边
			List<Integer> thisEdges = edges.get(thisCourse);
			if (thisEdges != null && thisEdges.size() > 0) {
				for (int e : thisEdges) {
					// 更新边的入度
					inDegrees[e] -= 1;
					// 如果更新后入度为0 择把这条边对应的顶点加入队列
					if (inDegrees[e] == 0) {
						queue.offer(e);
					}
				}
			}
		}
		// 计数器 和课程数量相等表示找到了正确答案
		return visited == numCourses ? result : new int[0];
	}

	// 每个顶点的入度
	int[] inDegrees;
	// 每个顶点的有向边
	Map<Integer, List<Integer>> edges;

	// 定义visited[] 存储所有节点的访问状态
	int[] visited;
	// 0-未访问 1-访问中 2-已访问
	// 定义总结果 valid 默认 true
	boolean valid = true;
	// 保存结果
	List<Integer> result;

	/**
	 * 有向图的深度优先搜索
	 * 
	 * 
	 * 从出度作为切入点
	 * 
	 * @param numCourses
	 * @param prerequisites
	 * @return
	 */
	public int[] findOrderDFS(int numCourses, int[][] prerequisites) {
		result = new ArrayList<>(numCourses);
		// 初始化节点访问状态 默认都是未访问
		visited = new int[numCourses];
		// 遍历先导条件，设置边
		edges = new HashMap<>(numCourses);
		for (int[] edge : prerequisites) {
			int k = edge[1];
			int v = edge[0];
			if (!edges.containsKey(k)) {
				edges.put(k, new ArrayList<>());
			}
			edges.get(k).add(v);
		}
		// 遍历课程 ,dfs
		for (int course = 0; course < numCourses && valid; course++) {
			if (visited[course] == 0) {
				dfs(course);
			}
		}
		if (!valid) {
			return new int[0];
		}
		int[] rs = new int[numCourses];
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
			for (int edge : edges.get(node)) {
				// 如果边没有访问过 进行dfs
				if (visited[edge] == 0) {
					dfs(edge);
					// 如果总结果为假返回
					if (!valid) {
						return;
					}
					// 如果边的状态为1 说明环形，总结果设为false 返回
				} else if (visited[edge] == 1) {
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

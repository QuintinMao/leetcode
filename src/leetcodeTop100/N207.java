package leetcodeTop100;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class N207 {

	public static void main(String[] args) {
		int numCourses = 2;
		int[][] prerequisites = { { 1, 0 }, { 0, 1 } };
		System.out.println(new N207().canFinish(numCourses, prerequisites));
	}

	//每个顶点的入度
	int[] inDegrees;
	//每个顶点的有向边
	Map<Integer, List<Integer>> edges;

	public boolean canFinish(int numCourses, int[][] prerequisites) {
		return canFinishDFS(numCourses, prerequisites);
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
	public boolean canFinishBFS(int numCourses, int[][] prerequisites) {
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
		// 定义计数器，记录已访问元素的个数
		int visited = 0;
		// poll from queue
		// 取出的顶点入度==0
		while (!queue.isEmpty()) {
			// 更新计数器
			visited += 1;
			// 遍历这个顶点的所有边
			int thisCourse = queue.poll();
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
		// 返回 计数器 是否和课程数量相等
		return visited == numCourses;
	}

	// 定义visited[] 存储所有节点的访问状态
	int[] visited;
	// 0-未访问 1-访问中 2-已访问
	// 定义总结果 valid 默认 true
	boolean valid = true;

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
	public boolean canFinishDFS(int numCourses, int[][] prerequisites) {
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
		// 返回总结果
		return valid;
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
	}
}

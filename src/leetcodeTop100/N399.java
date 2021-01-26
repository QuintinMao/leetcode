package leetcodeTop100;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 除法求值
 * 
 * 并查集
 * @author Johnny
 * 
 * 2021年1月7日 上午11:33:11
 */
public class N399 {
	public static void main(String[] args) {
		List<List<String>> equations = new ArrayList<>();
		List<String> sub1 = new ArrayList<>();
		sub1.add("a");
		sub1.add("b");
		List<String> sub2 = new ArrayList<>();
		sub2.add("e");
		sub2.add("f");
		List<String> sub3 = new ArrayList<>();
		sub3.add("b");
		sub3.add("e");
		equations.add(sub1);
		equations.add(sub2);
		equations.add(sub3);

		double[] values = { 3.4d, 1.4d, 2.3d };

		List<List<String>> queries = new ArrayList<>();
		List<String> q1 = new ArrayList<>();
		q1.add("a");
		q1.add("f");
		queries.add(q1);

		for (double d : new N399().calcEquation1(equations, values, queries)) {
			System.out.println(d);
		}

	}

	public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
		Map<String, Set<Helper>> tabs = new HashMap<>();
		for (int i = 0; i < equations.size(); i++) {
			List<String> pair = equations.get(i);
			double val = values[i];
			Helper hr = new Helper(val, pair.get(0), pair.get(1));
			if (!tabs.containsKey(hr.getK())) {
				tabs.put(hr.getK(), new HashSet<>());
			}
			tabs.get(hr.getK()).add(hr);
			if (!tabs.containsKey(hr.getDk())) {
				tabs.put(hr.getDk(), new HashSet<>());
			}
			tabs.get(hr.getDk()).add(hr.inverse());
		}
		double[] result = new double[queries.size()];

		// 开始计算
		for (int i = 0; i < queries.size(); i++) {
			List<String> query = queries.get(i);
			double ans = -1;
			String k = query.get(0);
			String dk = query.get(1);
			if (tabs.containsKey(k) && k.equals(dk)) {
				result[i] = 1;
				continue;
			}
			Set<Helper> searched = new HashSet<>();
			List<Helper> path = null;
			if (tabs.containsKey(k)) {
				path = findPath(tabs, searched, k, dk);
			}
			if (path != null && path.size() > 0) {
				double rs = 1;
				for (Helper p : path) {
					rs *= p.coefficient;
				}
				ans = rs;

				tabs.get(k).add(new Helper(rs, k, dk));
				tabs.get(dk).add(new Helper(1 / rs, dk, k));
			}
			result[i] = ans;
		}
		return result;
	}

	public List<Helper> findPath(Map<String, Set<Helper>> tabs, Set<Helper> searched, String k, String dk) {
		List<Helper> path = null;
		Set<Helper> foo = tabs.get(k);
		for (Helper h : foo) {
			if (searched.contains(h)) {
				continue;
			}
			searched.add(h);
			if (h.dk.equals(dk)) {
				path = new ArrayList<>();
				path.add(h);
				break;
			} else {
				List<Helper> rs = findPath(tabs, searched, h.dk, dk);
				if (rs != null && rs.size() > 0) {
					rs.add(0, h);
					path = rs;
				}
			}
		}
		return path;
	}

	class Helper {
		final double coefficient;
		final String k;
		final String dk;

		@Override
		public String toString() {
			return "k:" + k + " dk:" + dk + " c:" + coefficient;
		}

		public Helper(double coefficient, String k, String dk) {
			super();
			this.coefficient = coefficient;
			this.k = k;
			this.dk = dk;
		}

		public double getCoefficient() {
			return coefficient;
		}

		public String getK() {
			return k;
		}

		public String getDk() {
			return dk;
		}

		public Helper inverse() {
			return new Helper(1 / coefficient, dk, k);
		}

		@Override
		public int hashCode() {
			return k.hashCode() + dk.hashCode();
		}

		@Override
		public boolean equals(Object obj) {
			if (obj instanceof Helper) {
				Helper h = (Helper) obj;
				return (h.k.equals(this.k) && h.dk.equals(this.dk)) || (h.k.equals(this.dk) && h.dk.equals(this.k));
			}
			return false;
		}
	}

	/**
	 * 并查集实现
	 * 
	 * @param equations
	 * @param values
	 * @param queries
	 * @return
	 */
	public double[] calcEquation1(List<List<String>> equations, double[] values, List<List<String>> queries) {
		int equationSize = equations.size();

		UnionFind unionFind = new UnionFind(2 * equationSize);

		Map<String, Integer> key2IdMapper = new HashMap<>(2 * equationSize);
		int id = 0;
		for (int i = 0; i < equationSize; i++) {
			List<String> equation = equations.get(i);
			String x = equation.get(0);
			String y = equation.get(1);

			if (!key2IdMapper.containsKey(x)) {
				key2IdMapper.put(x, id++);
			}
			if (!key2IdMapper.containsKey(y)) {
				key2IdMapper.put(y, id++);
			}

			unionFind.union(key2IdMapper.get(x), key2IdMapper.get(y), values[i]);
		}

		int querySize = queries.size();
		double[] result = new double[querySize];
		for (int i = 0; i < querySize; i++) {
			List<String> query = queries.get(i);
			String x = query.get(0);
			String y = query.get(1);

			Integer xId = key2IdMapper.get(x);
			Integer yId = key2IdMapper.get(y);

			if (xId == null || yId == null) {
				result[i] = -1.0d;
			} else {
				result[i] = unionFind.isConnectedThenCalc(xId, yId);
			}
		}
		return result;
	}

	private class UnionFind {
		private int[] parents;
		private double[] weights;

		public UnionFind(int n) {
			parents = new int[n];
			weights = new double[n];
			for (int i = 0; i < n; i++) {
				parents[i] = i;
				weights[i] = 1.0d;
			}
		}

		public void union(int x, int y, double value) {
			int rootX = find(x);
			int rootY = find(y);
			// 已经在同一个集合不需要合并
			if (rootX == rootY) {
				return;
			}
			// 或者 parents[rootY] = rootX; 都可以
			parents[rootX] = rootY;

			// TODO 重新计算权值
			weights[rootX] = weights[y] * value / weights[x];
		}

		public int find(int x) {
			if (x != parents[x]) {
				int origin = parents[x];
				parents[x] = find(parents[x]);
				weights[x] *= weights[origin];
			}
			return parents[x];
		}

		public double isConnectedThenCalc(int x, int y) {
			int rootX = find(x);
			int rootY = find(y);

			if (rootX == rootY) {
				return weights[x] / weights[y];
			} else {
				return -1.0d;
			}
		}

	}
}

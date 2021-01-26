package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * N1202. Smallest String With Swaps
 * 
 * @author Johnny
 * 
 *         2021年1月11日 上午11:16:47
 */
public class N1202 {

	public static void main(String[] args) {
		String s = "fqtvkfkt";
		List<List<Integer>> pairs = new ArrayList<List<Integer>>();
		List<Integer> p1 = new ArrayList<Integer>();
		p1.add(2);
		p1.add(4);
		pairs.add(p1);
		List<Integer> p2 = new ArrayList<Integer>();
		p2.add(5);
		p2.add(7);
		pairs.add(p2);
		List<Integer> p3 = new ArrayList<Integer>();
		p3.add(1);
		p3.add(0);
		pairs.add(p3);
		List<Integer> p4 = new ArrayList<Integer>();
		p4.add(0);
		p4.add(0);
		pairs.add(p4);
		List<Integer> p5 = new ArrayList<Integer>();
		p5.add(4);
		p5.add(7);
		pairs.add(p5);
		List<Integer> p6 = new ArrayList<Integer>();
		p6.add(0);
		p6.add(3);
		pairs.add(p6);
		List<Integer> p7 = new ArrayList<Integer>();
		p7.add(4);
		p7.add(1);
		pairs.add(p7);
		List<Integer> p8 = new ArrayList<Integer>();
		p8.add(1);
		p8.add(3);
		pairs.add(p8);
		System.out.println(new N1202().smallestStringWithSwaps(s, pairs));

	}

	public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
		char[] ss = s.toCharArray();
		List<Set<Integer>> uList = new ArrayList<>();
		for (List<Integer> p : pairs) {
			Set<Integer> pa = null;
			Set<Integer> pb = null;
			for (Set<Integer> u : uList) {
				if (u.contains(p.get(0))) {
					pa = u;
				} else if (u.contains(p.get(1))) {
					pb = u;
				}
			}
			if (pa != null && pb != null) {
				pa.addAll(pb);
				uList.remove(pb);
			} else if (pa == null && pb == null) {
				Set<Integer> newU = new HashSet<>();
				newU.add(p.get(0));
				newU.add(p.get(1));
				uList.add(newU);
			} else {
				if (pa == null) {
					pb.add(p.get(0));
				} else {
					pa.add(p.get(1));
				}
			}
		}

		for (Set<Integer> u : uList) {
			Integer[] union = u.toArray(new Integer[0]);
			Arrays.sort(union);
			Character[] cc = new Character[union.length];
			for (int i = 0; i < union.length; i++) {
				cc[i] = ss[union[i]];
			}
			Arrays.sort(cc);
			for (int i = 0; i < union.length; i++) {
				ss[union[i]] = cc[i];
			}
		}

		return new String(ss);

	}

	public void swap(char[] ss, int a, int b) {
		char t = ss[a];
		ss[a] = ss[b];
		ss[b] = t;
	}

	private class UnionFind {
		int[] parents;

		public UnionFind(int n) {
			parents = new int[n];
			for (int i = 0; i < parents.length; i++) {
				parents[i] = i;
			}
		}

		public int find(int x) {
			if (parents[x] != x) {
				// 压缩路径
				parents[x] = find(parents[x]);
				return parents[x];
			}
			return x;
		}

		public void union(int x, int y) {
			int rootX = find(x);
			int rootY = find(y);
			if (rootX != rootY) {
				parents[rootX] = rootY;
			}
		}

	}

	public String smallestStringWithSwaps2(String s, List<List<Integer>> pairs) {
		char[] ss = s.toCharArray();
		int len = ss.length;
		UnionFind uf = new UnionFind(s.length());
		// 使用并查集找到所有连同分量，作为子问题处理。
		for (List<Integer> p : pairs) {
			uf.union(p.get(0), p.get(1));
		}

		// <根节点，所有在根节点连通分量中的字符（priorityQueue保证了顺序）>
		Map<Integer, PriorityQueue<Character>> map = new HashMap<>(len);
		for (int i = 0; i < len; i++) {
			int root = uf.find(i);
			if (!map.containsKey(root)) {
				// 默认比较器升序排列,也可在此传入比较器new PriorityQueue<>((x, y) -> x - y)
				map.put(root, new PriorityQueue<>((x, y) -> x - y));
			}
			//将每个字符压入自己连通分量根节点的队列中
			map.get(root).add(ss[i]);
		}

		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < len; i++) {
			//找到字符的连通分量
			int root = uf.find(i);
			//弹出连通分量字符队列中的字符
			builder.append(map.get(root).poll());
		}
		return builder.toString();

	}
}

package leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 并查集实现
 * 
 * @author Johnny
 * 
 *         2021年1月18日 下午1:27:11
 */
public class N721 {

	public static void main(String[] args) {
		List<List<String>> accounts = new ArrayList<List<String>>();
		String[][] ss = { { "John", "johnsmith@mail.com", "john00@mail.com" }, { "John", "johnnybravo@mail.com" },
				{ "John", "johnsmith@mail.com", "john_newyork@mail.com" }, { "Mary", "mary@mail.com" } };
		for (String[] s : ss) {
			List<String> account = new ArrayList<String>();
			for (String str : s) {
				account.add(str);
			}
			accounts.add(account);
		}
		@SuppressWarnings("unused")
		List<List<String>> res = new N721().accountsMerge(accounts);

	}

	// 给每个邮箱地址生成一个int形的id。
	// 维护邮箱地址和这个id之间的双向映射关系。
	private Map<String, Integer> email2Id = new HashMap<>();
	private Map<Integer, String> id2Email = new HashMap<>();
	private int counter = 0;

	/**
	 * 并查集实现 只要有一个邮箱地址相同的两个账户就是一个人的。 在同一联通分量上的邮箱都属于同一个人。
	 * 
	 * @param accounts
	 * @return
	 */
	public List<List<String>> accountsMerge(List<List<String>> accounts) {
		Map<String, String> email2Name = new HashMap<>();
		int n = accounts.size();
		// 根据题意：并查集大小为 n个账户*每个账户最大邮箱数10 + 1；
		UnionFind uf = new UnionFind((n * 10) + 1);
		for (List<String> account : accounts) {
			// 用户名
			String name = account.get(0);
			// 第一个邮箱id
			int x = getIdByEmail(account.get(1));
			// 遍历所有其余邮箱，输入并查集
			for (String email : account.subList(1, account.size())) {
				// 建立连通分量和name的映射
				email2Name.put(email, name);
				int y = getIdByEmail(email);
				uf.union(x, y);
			}
		}
		// 组织结果
		Map<Integer, List<String>> root2Emails = new HashMap<>();
		for (String email : email2Id.keySet()) {
			int root = uf.find(getIdByEmail(email));
			if (!root2Emails.containsKey(root)) {
				root2Emails.put(root, new ArrayList<>());
			}
			root2Emails.get(root).add(email);
		}
		List<List<String>> res = new ArrayList<>();
		for (List<String> emails : root2Emails.values()) {
			List<String> account = new ArrayList<>();
			account.add(email2Name.get(emails.get(0)));
			// 要求按字典排序
			Collections.sort(emails);
			account.addAll(emails);
			res.add(account);
		}
		return res;

	}

	// 获取邮箱地址的数字id，如果不存在则生成一个。
	public int getIdByEmail(String email) {
		if (!email2Id.containsKey(email)) {
			int id = counter++;
			email2Id.put(email, id);
			id2Email.put(id, email);
		}
		return email2Id.get(email);
	}

	static class UnionFind {

		private int[] parents;

		public UnionFind(int n) {
			parents = new int[n];
			for (int i = 0; i < parents.length; i++) {
				parents[i] = i;
			}
		}

		public int find(int x) {
			if (parents[x] != x) {
				parents[x] = find(parents[x]);
			}
			return parents[x];
		}

		public void union(int x, int y) {
			int rootX = find(x);
			int rootY = find(y);
			if (rootX == rootY) {
				return;
			}
			parents[rootY] = rootX;
		}
	}
}

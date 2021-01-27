package leetcodeTop100;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Combination Sum
 * 
 * @author Johnny
 * 
 *         2021年1月26日 下午4:32:00
 */
public class N39 {

	public static void main(String[] args) {
		int[] candidates = new int[] { 2, 3, 6, 7 };
		int target = 7;
		@SuppressWarnings("unused")
		List<List<Integer>> res = new N39().combinationSum(candidates, target);
	}

	public List<List<Integer>> combinationSum(int[] candidates, int target) {
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		List<Integer> path = new ArrayList<Integer>();
		backtrack(candidates, target, res, path, 0);
		return res;
	}

	/**
	 * 回溯
	 * @param candidates 候选数字
	 * @param target 目标值
	 * @param res 已经找到的符合要求的数字组合
	 * @param path 记录回溯过程中的路径
	 * @param idx 回溯点
	 */
	public void backtrack(int[] candidates, int target, List<List<Integer>> res, List<Integer> path, int idx) {
		if (idx > candidates.length - 1) {
			return;
		}
		if (target == 0) {
			res.add(new ArrayList<Integer>(path));
			return;
		}
		// 不选，跳过
		backtrack(candidates, target, res, path, idx + 1);
		// 选择当前数
		if (target - candidates[idx] >= 0) {
			path.add(candidates[idx]);
			backtrack(candidates, target - candidates[idx], res, path, idx);
			path.remove(path.size() - 1);

		}
	}

	public List<List<Integer>> combinationSum1(int[] candidates, int target) {
		List<List<Integer>> res = helper(candidates, target);
		Map<Integer, List<List<Integer>>> checker = new HashMap<>();
		Iterator<List<Integer>> it = res.iterator();
		while (it.hasNext()) {
			List<Integer> ans = it.next();
			Collections.sort(ans);

			if (!checker.containsKey(ans.size())) {
				checker.put(ans.size(), new ArrayList<>());
				checker.get(ans.size()).add(ans);
			} else {
				boolean match = true;
				for (List<Integer> foo : checker.get(ans.size())) {
					for (int i = 0; i < foo.size(); i++) {
						if (foo.get(i) != ans.get(i)) {
							match = false;
						}
					}
				}
				if (match) {
					it.remove();
				} else {
					checker.get(ans.size()).add(ans);
				}
			}

		}
		return res;

	}

	public List<List<Integer>> helper(int[] candidates, int target) {
		if (target < 0) {
			return null;
		}
		List<List<Integer>> res = new ArrayList<>();
		for (int c : candidates) {
			if (target - c == 0) {
				List<Integer> ans = new ArrayList<Integer>();
				ans.add(c);
				res.add(ans);
			} else {
				List<List<Integer>> ans = helper(candidates, target - c);
				if (ans != null) {
					for (List<Integer> l : ans) {
						List<Integer> subRes = new ArrayList<>();
						subRes.add(c);
						subRes.addAll(l);
						res.add(subRes);
					}
				}
			}
		}
		return res;
	}
}

package leetcode.Feb02;

import java.util.ArrayList;
import java.util.List;

import leetcode.common.TreeNode;

/**
 * 230. DFS MidOrder   LDR
 * 
 * @author Johnny
 * 
 *         2021年2月2日 下午6:18:57
 */
public class KthSmallestElementInBST {

	List<Integer> res = new ArrayList<>();
	int k;

	public int kthSmallest(TreeNode root, int k) {
		this.k = k;
		dfs(root);
		return res.get(k - 1);
	}

	public void dfs(TreeNode root) {
		if (root == null) {
			return;
		}
		dfs(root.left);
		res.add(root.val);
		if (res.size() == k) {
			return;
		}
		dfs(root.right);
	}
}

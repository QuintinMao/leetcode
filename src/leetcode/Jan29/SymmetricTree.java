package leetcode.Jan29;

import java.util.ArrayList;
import java.util.List;

/**
 * 101
 * 
 * @author Johnny
 * 
 *         2021年1月29日 下午3:44:29
 */
public class SymmetricTree {

	public static void main(String[] args) {
		System.out.println((int) '#');
		TreeNode n44 = new TreeNode(4);
		TreeNode n4 = new TreeNode(4);
		TreeNode n33 = new TreeNode(3, n4, null);
		TreeNode n3 = new TreeNode(3, null, n44);
		TreeNode n22 = new TreeNode(2, n3, null);
		TreeNode n2 = new TreeNode(2, null, n33);
		TreeNode root = new TreeNode(1, n2, n22);
		System.out.println(new SymmetricTree().isSymmetric(root));
	}

	public boolean isSymmetric(TreeNode root) {
		List<Integer> dlr = new ArrayList<>();
		dlr(root, dlr);
		List<Integer> drl = new ArrayList<>();
		drl(root, drl);
		for (int i = 0; i < dlr.size(); i++) {
			if (dlr.get(i) != drl.get(i)) {
				return false;
			}
		}
		return true;

	}

	public void dlr(TreeNode root, List<Integer> res) {
		if (root == null) {
			res.add(null);
			return;
		}
		res.add(root.val);
		dlr(root.left, res);
		dlr(root.right, res);
	}

	public void drl(TreeNode root, List<Integer> res) {
		if (root == null) {
			res.add(null);
			return;
		}
		res.add(root.val);
		drl(root.right, res);
		drl(root.left, res);
	}

	/**
	 * 递归
	 * @param p
	 * @param q
	 * @return
	 */
	public boolean recurse(TreeNode p, TreeNode q) {
		if (p == null && q == null) {
			return true;
		}

		if (p == null || q == null) {
			return false;
		}

		return p.val == q.val && recurse(p.left, q.right) && recurse(p.right, q.left);
	}
}

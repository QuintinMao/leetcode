package leetcodeTop100;

import java.util.Stack;

public class N98 {

	public static void main(String[] args) {
		TreeNode n7 = new TreeNode(7);
		TreeNode n3 = new TreeNode(3);
		TreeNode n6 = new TreeNode(6, n3, n7);
		TreeNode n4 = new TreeNode(4);
		TreeNode n5 = new TreeNode(5, n4, n6);
		TreeNode root = n5;
		System.out.println(new N98().isValidBSTLDR(root));

	}

	/**
	 * 二叉搜索树：中序遍历 得到升序 结果。 ；中序遍历时，判断当前元素如果小于等于前一个元素， 则不是一颗合法的BST
	 * 
	 * @param root
	 * @return
	 */
	public boolean isValidBSTLDR(TreeNode root) {
		Stack<TreeNode> stack = new Stack<>();
		long prevVal = Long.MIN_VALUE;
		while (!stack.isEmpty() || root != null) {
			while (root != null) {
				stack.push(root);
				root = root.left;
			}
			root = stack.pop();
			if (root.val <= prevVal) {
				return false;
			}
			prevVal = root.val;
			root = root.right;
		}
		return true;
	}

	public boolean isValidBST(TreeNode root) {
		return helper(root, null, null);
	}

	public boolean helper(TreeNode root, Integer min, Integer max) {
		if (root == null) {
			return true;
		}
		if (min != null && root.val <= min || max != null && root.val >= max) {
			return false;
		}
		if (!helper(root.left, min, root.val) || !helper(root.right, root.val, max)) {
			return false;
		}
		return true;
	}

	static class TreeNode {
		@Override
		public String toString() {
			return val + "";
		}

		int val;
		TreeNode left;
		TreeNode right;

		TreeNode() {
		}

		TreeNode(int val) {
			this.val = val;
		}

		TreeNode(int val, TreeNode left, TreeNode right) {
			this.val = val;
			this.left = left;
			this.right = right;
		}

	}

}

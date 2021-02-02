package leetcode.Jan29;

/**
 * 104 二叉树的最大深度
 * 
 * @author Johnny
 * 
 *         2021年1月29日 下午12:45:43
 */
public class MaxDepthBinaryTree {
	public static void main(String[] args) {
		TreeNode n15 = new TreeNode(15);
		TreeNode n7 = new TreeNode(7);
		TreeNode n20 = new TreeNode(20, n15, n7);
		TreeNode n9 = new TreeNode(9);
		TreeNode root = new TreeNode(3, n9, n20);
		System.out.println(new MaxDepthBinaryTree().maxDepth(root));
	}

	public int maxDepth(TreeNode root) {
		if (root == null) {
			return 0;
		}
		if (root.left == null && root.right == null) {
			return 1;
		}
		int l = maxDepth(root.left);
		int r = maxDepth(root.right);
		return Math.max(l, r) + 1;
	}
}

class TreeNode {
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

	@Override
	public String toString() {
		return this.val + ":";
	}
}
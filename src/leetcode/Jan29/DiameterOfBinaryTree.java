package leetcode.Jan29;

/**
 * 543
 * 
 * @author Johnny
 * 
 *         2021年1月29日 下午4:48:39
 */
public class DiameterOfBinaryTree {

	public int diameterOfBinaryTree(TreeNode root) {
		dfs(root);
		return maxDepth;
	}

	int maxDepth = 0;

	public int dfs(TreeNode node) {
		if (node == null) {
			return 0;
		}

		int left = dfs(node.left);
		int right = dfs(node.right);
		maxDepth = Math.max(maxDepth, left + right);
		return Math.max(left, right) + 1;
	}
}

package leetcodeTop100;

/**
 * 124. Binary Tree Maximum Path Sum 二叉树中的最大路径和
 * 
 * @author Johnny
 * 
 *         2021年1月13日 下午2:52:47
 */
public class N124 {

	public static void main(String[] args) {
		TreeNode n1 = new TreeNode(-10);
		TreeNode n2 = new TreeNode(9);
		TreeNode n3 = new TreeNode(20);
		TreeNode n4 = new TreeNode(15);
		TreeNode n5 = new TreeNode(7);
		n1.left = n2;
		n1.right = n3;
		n3.left = n4;
		n3.right = n5;
		TreeNode root = n1;
		System.out.println(new N124().maxPathSum(root));

	}

	int globalSumMax = Integer.MIN_VALUE;

	public int maxPathSum(TreeNode root) {
		dfs(root);
		return globalSumMax;
	}

	public int dfs(TreeNode root) {
		// 左叶子节点的值
		int left = root.left == null ? 0 : dfs(root.left);
		// 右叶子节点的值
		int right = root.right == null ? 0 : dfs(root.right);

		// 路径经过根节点： Max(根节点,根节点+左子节点,根节点+右子节点)
		int throughRootMaxSum = Math.max(root.val, Math.max(root.val + left, root.val + right));

		// 路径不经过根节点: Max(lor,左子节点+右子节点)
		globalSumMax = Math.max(globalSumMax, Math.max(throughRootMaxSum, root.val + left + right));
		return throughRootMaxSum;
	}

	static class TreeNode {
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

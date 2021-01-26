package leetcodeTop100;

import java.util.LinkedList;
import java.util.Queue;

/**
 * House Robber III
 * 
 * @author Johnny
 * 
 *         2021年1月22日 下午6:25:00
 */
public class N337 {

	public static void main(String[] args) {
		N337 cls = new N337();
		String data = "2,1,3,null,4,null,null,null,null";
		TreeNode root = cls.deserialize(data);
		System.out.println(cls.rob(root));
	}

	public int rob(TreeNode root) {
		int[] res = dfs(root);
		return Math.max(res[0], res[1]);
	}

	public int[] dfs(TreeNode root) {
		if (root == null) {
			return new int[] { 0, 0 };
		}
		int[] left = dfs(root.left);
		int[] right = dfs(root.right);
		int rob = left[1] + right[1] + root.val;
		int noRob = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
		return new int[] { rob, noRob };
	}

	static class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}

	public static final String NULL = "null";

	public TreeNode deserialize(String data) {
		if ("".equals(data)) {
			return null;
		}
		String[] dd = data.split(",");
		TreeNode root = new TreeNode(Integer.parseInt(dd[0]));
		int cursor = 1;
		Queue<TreeNode> queue = new LinkedList<>();
		queue.offer(root);
		while (!queue.isEmpty()) {
			TreeNode node = queue.poll();
			String val = dd[cursor++];
			if (!val.equals(NULL)) {
				TreeNode leafLeft = new TreeNode(Integer.parseInt(val));
				node.left = leafLeft;
				queue.offer(leafLeft);
			}
			val = dd[cursor++];
			if (!val.equals(NULL)) {
				TreeNode leafRight = new TreeNode(Integer.parseInt(val));
				node.right = leafRight;
				queue.offer(leafRight);
			}
		}
		return root;
	}
}

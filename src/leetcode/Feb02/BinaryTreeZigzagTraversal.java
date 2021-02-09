package leetcode.Feb02;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import leetcode.common.TreeNode;

public class BinaryTreeZigzagTraversal {
	public static void main(String[] args) {
		TreeNode n15 = new TreeNode(15);
		TreeNode n7 = new TreeNode(7);
		TreeNode n20 = new TreeNode(20, n15, n7);
		TreeNode n9 = new TreeNode(9);
		TreeNode root = new TreeNode(3, n9, n20);
		@SuppressWarnings("unused")
		List<List<Integer>> res = new BinaryTreeZigzagTraversal().zigzagLevelOrder(root);
	}

	public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
		List<List<Integer>> res = new ArrayList<>();
		Queue<TreeNode[]> queue = new LinkedList<>();
		queue.offer(new TreeNode[] { root, root });
		List<Integer> subRes = new ArrayList<>();
		Object direction = null;
		while (!queue.isEmpty()) {
			TreeNode[] node = queue.poll();

			if (node[1] != direction) {
				subRes.add(node[0].val);
				res.add(subRes);
				subRes = new ArrayList<>();
				direction = direction == null ? new Object() : null;
			} else {
				subRes.add(node[0].val);
			}

			if (node[1] != null) {
				if (node[0].right != null) {
					queue.offer(new TreeNode[] { node[0].right, node[0].right });
				}
				if (node[0].left != null) {
					queue.offer(new TreeNode[] { node[0].left, node[0].left });
				}

			} else {
				if (node[0].left != null) {
					queue.offer(new TreeNode[] { node[0].left, null });
				}
				if (node[0].right != null) {
					queue.offer(new TreeNode[] { node[0].right, null });
				}
			}

		}
		return res;
	}

}

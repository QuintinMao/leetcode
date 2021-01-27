package leetcodeTop100;

/**
 * Flatten Binary Tree to Linked List
 * 
 * @author Johnny
 * 
 *         2021年1月26日 下午2:40:35
 */
public class N114 {

	public static void main(String[] args) {
		TreeNode root = new TreeNode(1);
		TreeNode n2 = new TreeNode(1);
		TreeNode n3 = new TreeNode(1);
		TreeNode n4 = new TreeNode(1);
		TreeNode n5 = new TreeNode(1);
		TreeNode n6 = new TreeNode(1);
		root.left = n2;
		root.right = n5;
		n2.left = n3;
		n2.right = n4;
		n5.right = n6;
		new N114().flatten(root);
	}

	/**
	 * 对于当前节点，如果其左子节点不为空，则在其左子树中找到最右边的节点，作为前驱节点，
	 * 将当前节点的右子节点赋给前驱节点的右子节点，然后将当前节点的左子节点赋给当前节点的右子节点，
	 * 并将当前节点的左子节点设为空。对当前节点处理结束后，继续处理链表中的下一个节点，直到所有节点都处理结束。
	 * 
	 * @param root
	 */
	public void flatten(TreeNode root) {
		TreeNode cur = root;
		while (cur != null) {
			// 左子树不为空
			if (cur.left != null) {
				TreeNode curLeft = cur.left;
				// 前驱节点(左子树前序遍历的最后一个节点)
				TreeNode precursor = curLeft;
				// 左子树最右边的一个节点
				while (precursor.right != null) {
					precursor = precursor.right;
				}
				// 将前驱节点right指向cur的right
				precursor.right = cur.right;
				cur.left = null;
				cur.right = curLeft;
			}
			cur = cur.right;
		}
	}

}

package leetcodeTop100;

import java.util.HashMap;
import java.util.Map;

/**
 * Construct Binary Tree from Preorder and Inorder Traversal
 * 
 * @author Johnny
 * 
 *         2021年1月25日 下午4:04:559a5stgvz
 */
public class N105 {

	public static void main(String[] args) {
		int[] preorder = { 3, 9, 20, 15, 7 };
		int[] inorder = { 9, 3, 15, 20, 7 };
		@SuppressWarnings("unused")
		TreeNode res = new N105().buildTree(preorder, inorder);

	}

	public TreeNode buildTree(int[] preorder, int[] inorder) {
		this.preorder = preorder;
		this.inorder = inorder;
		return buildTree(0, preorder.length - 1, 0, inorder.length - 1);
	}

	int[] preorder;
	int[] inorder;

	public TreeNode buildTree(int ps, int pe, int is, int ie) {
		if (ps > pe) {
			return null;
		}
		// preorder[0]必定是根节点
		TreeNode root = new TreeNode(preorder[ps]);
		if (ie - is == 0) {
			return root;
		}

		// 根节点在中序遍历结果中的位置
		int inorderRootIndex = 0;
		for (int i = is; i <= ie; i++) {
			if (inorder[i] == root.val) {
				inorderRootIndex = i;
				break;
			}
		}
		// inorderRootIndex 左侧的数，为root左测所有节点个数。
		// 前序遍历中 左子树和右子树的分界线

		root.left = buildTree(ps + 1, ps + inorderRootIndex - is, is, inorderRootIndex - 1);
		root.right = buildTree(ps + inorderRootIndex - is + 1, pe, inorderRootIndex + 1, ie);
		return root;
	}
	
	private Map<Integer, Integer> indexMap;

    public TreeNode myBuildTree(int[] preorder, int[] inorder, int preorder_left, int preorder_right, int inorder_left, int inorder_right) {
        if (preorder_left > preorder_right) {
            return null;
        }

        // 前序遍历中的第一个节点就是根节点
        int preorder_root = preorder_left;
        // 在中序遍历中定位根节点
        int inorder_root = indexMap.get(preorder[preorder_root]);
        
        // 先把根节点建立出来
        TreeNode root = new TreeNode(preorder[preorder_root]);
        // 得到左子树中的节点数目
        int size_left_subtree = inorder_root - inorder_left;
        // 递归地构造左子树，并连接到根节点
        // 先序遍历中「从 左边界+1 开始的 size_left_subtree」个元素就对应了中序遍历中「从 左边界 开始到 根节点定位-1」的元素
        root.left = myBuildTree(preorder, inorder, preorder_left + 1, preorder_left + size_left_subtree, inorder_left, inorder_root - 1);
        // 递归地构造右子树，并连接到根节点
        // 先序遍历中「从 左边界+1+左子树节点数目 开始到 右边界」的元素就对应了中序遍历中「从 根节点定位+1 到 右边界」的元素
        root.right = myBuildTree(preorder, inorder, preorder_left + size_left_subtree + 1, preorder_right, inorder_root + 1, inorder_right);
        return root;
    }

    public TreeNode buildTree2(int[] preorder, int[] inorder) {
        int n = preorder.length;
        // 构造哈希映射，帮助我们快速定位根节点
        indexMap = new HashMap<Integer, Integer>();
        for (int i = 0; i < n; i++) {
            indexMap.put(inorder[i], i);
        }
        return myBuildTree(preorder, inorder, 0, n - 1, 0, n - 1);
    }


}

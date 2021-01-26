package leetcodeTop100;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Serialize and Deserialize Binary Tree
 * 
 * @author Johnny
 * 
 *         2021年1月13日 下午3:32:11
 */
public class N297 {

	public static void main(String[] args) {
		TreeNode n1 = new TreeNode(3);
		TreeNode n2 = new TreeNode(2);
		TreeNode n3 = new TreeNode(3);
		TreeNode n4 = new TreeNode(3);
		TreeNode n5 = new TreeNode(1);
		n3.right = n5;
		n2.right = n4;
		n1.left = n2;
		n1.right = n3;
		TreeNode root = n1;
		String data = new N297().serialize(root);
		System.out.println(data);
		TreeNode rs = new N297().deserialize2(data);
		System.out.println(rs);
	}

	public static final String NULL = "null";

	/**
	 * 序列化二叉树 根据题意，利用层序遍历，空叶子节点用null占位
	 * 
	 * @param root
	 * @return
	 */
	public String serialize(TreeNode root) {
		if (root == null) {
			return "";
		}
		StringBuilder builder = null;
		Queue<TreeNode> queue = new LinkedList<>();
		queue.offer(root);
		while (!queue.isEmpty()) {
			root = queue.poll();
			String value = root == null ? NULL : String.valueOf(root.val);
			if (builder == null) {
				builder = new StringBuilder(value);
			} else {
				builder.append(",");
				builder.append(value);
			}
			if (root != null) {
				queue.offer(root.left == null ? null : root.left);
				queue.offer(root.right == null ? null : root.right);
			}
		}
		return builder.toString();
	}

	// Decodes your encoded data to tree.
	public TreeNode deserialize(String data) {
		if ("".equals(data)) {
			return null;
		}
		String[] dd = data.split(",");
		TreeNode root = new TreeNode(Integer.parseInt(dd[0]));
		List<List<TreeNode>> layerParentList = new ArrayList<>();
		List<TreeNode> layer1 = new ArrayList<>();
		layer1.add(root);
		layerParentList.add(layer1);
		int dataCursor = 1;
		while (dataCursor < dd.length) {
			List<TreeNode> layerN = new ArrayList<>();
			int parentLayerIndex = layerParentList.size() - 1;
			int parentLayerSize = layerParentList.get(parentLayerIndex).size();
			int doubleParentLayerSize = parentLayerSize * 2;
			for (int i = dataCursor; i < dataCursor + doubleParentLayerSize; i++) {
				String curVal = dd[i];
				boolean isNull = curVal.equals(NULL);
				if (!isNull) {
					TreeNode child = new TreeNode(Integer.parseInt(curVal));
					layerN.add(child);
					int foo = i - dataCursor;
					int parentIndex = foo / 2;
					boolean leftLeaf = foo % 2 == 0;
					if (leftLeaf) {
						layerParentList.get(parentLayerIndex).get(parentIndex).left = child;
					} else {
						layerParentList.get(parentLayerIndex).get(parentIndex).right = child;
					}
				}
			}
			layerParentList.add(layerN);
			dataCursor += doubleParentLayerSize;
		}
		return root;
	}

	public TreeNode deserialize2(String data) {
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

class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;

	TreeNode(int x) {
		val = x;
	}
}

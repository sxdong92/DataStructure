package leetLockQuestion;

/**
 * Given a binary tree where all the right nodes are either leaf nodes with a 
 * sibling (a left node that shares the same parent node) or empty, 
 * flip it upside down and turn it into a tree where the original right nodes turned into left leaf nodes. Return the new root.
 * 
 *  For example:
 *  Given a binary tree {1,2,3,4,5},
 *     1
 *    / \
 *   2   3
 *  / \
 * 4   5
 *
 *  return the root of the binary tree [4,5,2,#,#,3,1].
 *    4
 *   / \
 *  5   2
 *     / \
 *    3   1
 * 
 * @author sxd
 *
 */
public class BinaryTreeUpsideDown {
	public TreeNode upsideDownBinaryTree(TreeNode root) {
		if(root == null) return root;
		
		TreeNode node = root, parent = null, left = null, right = null;
		while(node != null) {
			left = node.left;
			node.left = right;
			right = node.right;
			node.right = parent;
			parent = node;
			node = left;
		}
		return parent;
	}
	
	public TreeNode upsideDownBinaryTreeRecursively(TreeNode root) {
		if(root == null) return null;
		
		TreeNode parent = root, left = root.left, right = root.right;
		if(left != null) {
			TreeNode ret = upsideDownBinaryTreeRecursively(left);
			left.left = right;
			left.right = parent;
			return ret;
		}
		return root;
	}
}



class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}
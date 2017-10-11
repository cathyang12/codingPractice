//101. Symmetric Tree: easy

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    
    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;        
        return isSymmetric(root, root);
    }
    
    public boolean isSymmetric(TreeNode r1, TreeNode r2) {
        if (r1 == null || r2 == null)
            return r1 == r2;
           
        return (r1.val == r2.val) 
            && isSymmetric(r1.left, r2.right)
            && isSymmetric(r1.right, r2.left);
    }  
}

//Lowest Common Ancester
int LCA(TreeNode root, int x, int y) {
    if (root == null) return -1;

    Stack<Integer> pathToX = pathToTarget(root, x);
    Stack<Integer> pathToY = pathToTarget(root, y);

    int commonAncester = -1;
    int currX = -1;
    int currY = -1;
    while (!pathToX.isEmpty() && !pathToY.isEmpty()) {
        currX = pathToX.pop();
        currY = pathToY.pop();

        if (currX == currY) {
            commonAncester = currX;
        } else {
            break;
        }
    }

    return commonAncester
}

Stack<Integer> pathToTarget(TreeNode root, int target) {
    Stack<Integer> res = new Stack<>();
    if (root == null)  return res;
    if (root.val == target) {      
        res.add(root);
        return res;
    }

    Stack<Integer> leftPath = pathToTarget(root.left, target);
    if (!leftPath.isEmpty()) {
        leftPath.add(root);
        return leftPath;
    }
    
    Stack<Integer> rightPath = pathToTarget(root.right, target);
    if (!rightPath.isEmpty()) {
        rightPath.add(root);
        return rightPath;
    }

    return res;   
}

//124. Binary Tree Maximum Path Sum: hard
//web Solution
class Solution {
    int maxValue = Integer.MIN_VALUE;
    
    public int maxPathSum(TreeNode root) {
        
        maxPath(root);
        return maxValue;    
    }

    public int maxPath(TreeNode node) {
        if (node == null) return 0;
        
        int left = Math.max(0, maxPath(node.left));
        int right = Math.max(0, maxPath(node.right));
        
        maxValue = Math.max(maxValue, left + right + node.val);
        
        return Math.max(left, right) + node.val;
    }
}

//173. Binary Search Tree Iterator
//Implement an iterator over a binary search tree (BST). Your iterator will be initialized with the root node of a BST.
//Calling next() will return the next smallest number in the BST.
//Note: next() and hasNext() should run in average O(1) time and uses O(h) memory, where h is the height of the tree.

public class BSTIterator {
    public Stack<TreeNode> stack = new Stack<>();

    public BSTIterator(TreeNode root) {
        pushLeft(root);       
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {      
        return !stack.isEmpty();
    }

    /** @return the next smallest number */
    public int next() {
        TreeNode node = stack.pop();
        pushLeft(node.right);
        return node.val;
    }
    
    public void pushLeft(TreeNode node) {
        while (node != null) {
            stack.push(node);
            node = node.left;
        }
        
        return;
    }
}

//236. Lowest Common Ancestor of a Binary Tree: medium
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) return root;
        
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        
        if (left != null && right != null) return root;
        return left != null? left: right;   
    }
}

//235. Lowest Common Ancestor of a Binary Search Tree: medium
//my solution
class Solution {
   public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == p || root == q) return root;
        
        if (p.val <= root.val && q.val <= root.val) {
            return lowestCommonAncestor(root.left, p, q);
        } else if (p.val > root.val && q.val > root.val) {
            return lowestCommonAncestor(root.right, p, q);
        } else {
            return root;
        }
       
    }
}

//web solution
public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    while ((root.val - p.val) * (root.val - q.val) > 0)
        root = p.val < root.val ? root.left : root.right;
    return root;
}

//199. Binary Tree Right Side View: medium
//web solution
class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        
        rightSide(root, res, 0);
        return res;  
    }
    
    public void rightSide(TreeNode curr, List<Integer> res, int currDepth) {
        if (curr == null) return;
        if (currDepth == res.size()) {
            res.add(curr.val);
        }
        
        rightSide(curr.right, res, currDepth+1);
        rightSide(curr.left, res, currDepth+1);
    }
}
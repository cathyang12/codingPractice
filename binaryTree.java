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
class Node {
	public String name;
	public Node[] children;
}

class Tree {
	public Node root;
}

// Depth-first Search
void search(Node root) {
	if (root == null) return;
	visit(root);
	root.visited = true;

	for (Node n : root.children) {
		if (n.visited != true) {
			search (n);
		}
	}
}

// Breath-first Search
void search(Node root) {
	Queue<Node> temp = new LinkedList<>();
	root.marked =true;
	temp.enqueue(root);

	while (!temp.isEmpty()) {
		Node curr = temp.dequeue();
		visit(curr);

		for (Node n: curr.children) {
			if (n.marked == false) {
				n.marked == true;
				temp.enqueue(n);
			}
		}

	}

}


/* Practice Questions */

// 4.1 Route between Nodes
// Hint: can use breath-first Search and or depth first search. Breath-first search is useful to find the shortest path (faster). Depth-first search is easier and can use recursion

// Combination of my answer and solution :
// Time: O(N) Space: O(N) the queue
public class Node{
    private String name;
    private boolean isVisited;
    private Node[] children;
}

public boolean search(Node s, Node e) {
    // base case
    if (s == e) return true;

    //operate as queue
    LinkedList<Node> q = new LinkedList<Node>();
    s.isVisited = true;
    q.add(s);

    // iterative approach
    Node curr;
    while (!q.isEmpty()) {
        curr = q.removeFirst();

        for (Node n : curr.children ) {
            if (n == e) return true;

            if (!n.isVisited) {
                n.isVisited = true;
                q.add(n);
            }
        }
    }
    return false;
}

//4.2 Minimal Tree: given an increasing-order array with unique integer elements, write an algo to create a binary search tree with minimal height
public class BSTNode {
    //private field
    public int val;
    private BSTNode left;
    private BSTNode right;

    //constructor
    public BSTNode(int value) {
        this.val = value;
        this.left = null;
        this.right = null;
    }

    // My Answer
    // Time: O(N^2) as copyOfRange is O(N) Space: O(N)
    public BSTNode buildBST(int array[]) {

        if (array.length == 0) return null;
        if (array.length == 1) return new BSTNode(array[0]);

        // round down
        int mid = array.length/2

        BSTNode curr = new BSTNode(array[mid]);
        // left subtree
        curr.left = buildBST(Arrays.copyOfRange(array, 0, mid));
        //right subtree
        curr.right = buildBST(Arrays.copyOfRange(array, mid, array.length));

        return curr;
    }

    // textbook solution
    public BSTNode createMinimalBST(int array[]) {
        return createMinimalBST(array, 0, array.length-1);
    }

    public BSTNode createMinimalBSTt(int arr[], int start, int end) {
        if (end < start) {
            return null;
        }
        
        int mid = (start + end) /2;
        
        BSTNode n = new BSTNode(arr[mid]);
        n.left = createMinimalBST(arr, start, mid-1);
        n.right = createMinimalBST(arr, mid + 1, end);
        return n;
    }
}

// 4.3 List Of Depth 

	public class BSTNode {
    //private field
    public int val;
    private BSTNode left;
    private BSTNode right;

    //constructor
    public BSTNode(int value) {
        this.val = value;
        this.left = null;
        this.right = null;
    }

    // Answer-Method one
    public void listOfDepths(BSTNode n, ArrayList<LinkedList<BSTNode>> lists, int level) {
        if (n == null) return;

        LinkedList<BSTNode> list = null;

        //level not contained in the list. Assume list.size() would not be greater then level. But I think it is better to be <=
        if (lists.size() == level) {
            list = new LinkedList<BSTNode>();
            lists.add(list);
        } else {
            list = lists.get(level);
        }

        list.add(n);

        listOfDepths(n.left, lists, level+1);
        listOfDepths(n.right, lists, level+1);
    }

    public ArrayList<LinkedList<BSTNode>> createLevelLinkedList(BSTNode root) {
        ArrayList<LinkedList<BSTNode>> lists = new  ArrayList<LinkedList<BSTNode>>();
        listOfDepths(root, lists, 0);

        return lists;
    }

    // Answer-Method two: breath-first search

    public ArrayList<LinkedList<BSTNode>> createLevelLinkedList2(BSTNode root) {
        ArrayList<LinkedList<BSTNode>> result = new ArrayList<LinkedList<BSTNode>>();
        LinkedList<BSTNode> curr = new LinkedList<BSTNode>();
        
        if (root != null ) {
            curr.add(root);
        }

        while (curr.size() > 0) {
            result.add(curr); //add previous level
            LinkedList<BSTNode> parents = curr;
            curr = new LinkedList<>();

            for (BSTNode parent: parents) {
                if (parent.left != null) {
                    curr.add(parent.left);
                }
                if (parent.right != null) {
                    curr.add(parent.right);
                }
            }
        }
        return result;
    }
}

// 4.4 Check balanced
 public boolean checkBalance(BSTNode curr) {
    int min = Integer.MAX_VALUE;
    int max = 0;
    int level = 0;

    if (curr == null) return false;
    LinkedList<BSTNode> list = new LinkedList<BSTNode>();
    list.add(curr);

    while (list.size()>0) {
        max = level;

        LinkedList<BSTNode> parents = list;
        list = new LinkedList<BSTNode>();

        for (BSTNode parent: parents) {
            if (parent.left != null) {
                list.add(parent.left);
            } else {
                min = Math.min(min, level);

            }

            if (parent.right != null) {
                list.add(parent.right);
            } else {
                min = Math.min(min, level);
            }
        }

        level++;
    }
    if (max - min > 1) {
        return false;
    }

    return true;
}

// Textbook Solution
/*This algo would fail if the tree looks like this
*          6
*         / \
*        3   9
*           / \
*          7   10
*               \
*                14
*/
public int checkHeight(BSTNode curr) {
    if (curr == null) return 0;
    
    int currLeft = checkHeight(curr.left);
    if (currLeft == -1) {
        return -1;
    }

    int currRight = checkHeight(curr.right);
    if (currRight == -1) {
        return -1;
    }
    
    int diff = currLeft - currRight;
    if (Math.abs(diff) > 1) {
        return -1;
    } else {
        return Math.max(currLeft, currRight) + 1;
    }
}

public boolean isBalanced(BSTNode curr) {
    int result = checkHeight(curr);
    if (result == -1) {
        return false;
    } 
    
    return true;
}

 //4.5 Validate BST
    // my solution
    public boolean validateBST(BSTNode root) {
        ArrayList<Integer> list = new ArrayList<>();

        preOrder(root, list);

        for (int i=0; i<list.size()-1; i++) {
            if (list.get(i)>list.get(i+1)) {
                return false;
            }
        }

        return true;
    }

    public void inOrder(BSTNode curr, ArrayList<Integer> list) {
        if (curr == null) return;

        // in-order traversal
        inOrder(curr.left, list);
        list.add(curr.val);
        inOrder(curr.right, list);

    }

    // textbook resolution
    class WrapInt {
       // int lastPrint = Integer.MIN_VALUE;  //my way
        Integer lastPrint = null;
        
        public boolean inOrder(BSTNode curr, ArrayList<Integer> list) {
            if (curr == null) return true;

            // in-order traversal
            if (!inOrder(curr.left, list)) return false;
            
//          if (lastPrint > curr.val) { //my way
            if (lastPrint != null && lastPrint > curr.val) {
                return false;
            }
            lastPrint = curr.val;

            if (!inOrder(curr.right, list)) return false;
        }
    }

// 4.6 Successor: Write an algo to find the "next" node of a given node in a binary search ree. You may assume that each node has a lonk to ies parent
    public BSTNode checkBST(BSTNode n) {
        if (n == null) return null;
        
        //
        if (n.right != null) {
            return leftMostChild(n.right);
        } else {
            BSTNode q = n;
            BSTNode x = q.parent;
            
            while (x != null && x.left != q) {
                q = x;
                x = x.parent;
            }
            
            return x;
        }
   }
   
   public BSTNode leftMostChild(BSTNode curr) {
        if (curr == null) return null;
       
        while (curr.left != null) {
            curr = curr.left;
        }
        return curr;
   }

   //textbook solution
    public BSTNode checkBST(BSTNode n) {
        if (n == null) return null;

        //
        if (n.right != null) {
            return leftMostChild(n.right);
        } else {
            BSTNode q = n;
            BSTNode x = q.parent;

            while (x != null && x.left != q) {
                q = x;
                x = x.parent;
            }

            return x;
        }
   }

   public BSTNode leftMostChild(BSTNode curr) {
        if (curr == null) return null;

        while (curr.left != null) {
            curr = curr.left;
        }
        return curr;
   }


   // 4.10 check subTree. t1 is much larger than t2.

   boolean containsTree(BSTNode t1, BSTNode t2) {
        if (t2 == null) {
            return true;
        }
        
        checkIdentical(t1, t2);
    }
    
    public boolean checkSubtree(BSTNode t1, BSTNode t2) {
        if (t1 == null) return false;
        if (t1 == t2 && checkIdentical(t1, t2)) {
            return true;
        } 
        
        return checkSubtree(t1.left, t2) || checkSubtree(t1.right, t2);
    }

    private boolean checkIdentical(BSTNode t1, BSTNode t2) {
        if (t1 == null && t2 == null) {
            return true;
        } else if (t1 == null || t2 == null) {
            return false;
        }


        if (t1.val != t2.val) {
            return false;
        } else {
            return checkIdentical(t1.left, t2.left) && checkIdentical(t1.right, t2.right);
        }
    }



// 98. Validate Binary Search Tree: medium
    /**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
// web solution
class Solution {
    public boolean isValidBST(TreeNode root) {       
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
       
    }
    
    public boolean isValidBST(TreeNode root, long minVal, long maxVal) {
        if (root == null) return true;       
        if (root.val <= minVal || root.val >= maxVal) return false;
        return isValidBST(root.left, minVal, root.val) && isValidBST(root.right, root.val, maxVal);
    }
}

//web solution: inorder traversal
class Solution {
    public boolean isValidBST(TreeNode root) {       
        if (root == null) return true;
        
        Stack<TreeNode> stack = new Stack<>();
        TreeNode pre = null;
        
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            
            root = stack.pop();
            if (pre != null && root.val <= pre.val) return false;
            pre = root;
            root = root.right;
        }
        
        return true;
    }  
}


//102. Binary Tree Level Order Traversal: medium
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
    public List<List<Integer>> levelOrder(TreeNode root) {
              
        List<List<Integer>> wrapList = new ArrayList<List<Integer>>();
        if (root == null) return wrapList;
       
        Queue<TreeNode> queue = new LinkedList<TreeNode>();
        queue.offer(root);
        int levelSize;
        
        while (!queue.isEmpty()) {
            levelSize = queue.size(); 
            List<Integer> subList = new ArrayList<Integer>();
            
            for (int i=0; i<levelSize; i++) {
                if (queue.peek().left != null) queue.add(queue.peek().left);
                if (queue.peek().right != null) queue.add(queue.peek().right);
                subList.add(queue.poll().val);
            }  
            
            wrapList.add(subList);
        }
        
        return wrapList;
        
    }
}


//103. Binary Tree Zigzag Level Order Traversal
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

// my solution
class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        
        if (root == null) return res;
        Deque<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int levelSize = 0;
        boolean isForward = true;
        
        while (!queue.isEmpty()) {
            levelSize = queue.size();
            
            List<Integer> subList = new ArrayList<Integer>();
            
            if (isForward) {
                for (int i=0; i<levelSize; i++) {
                    if (queue.peekFirst().left != null) queue.addLast(queue.peekFirst().left);
                    if (queue.peekFirst().right != null) queue.addLast(queue.peekFirst().right); 
                    subList.add(queue.pollFirst().val);
                }
              
                isForward = false;
            } else {
                 for (int i=levelSize-1; i>=0; i--) {
                    if (queue.peekLast().right != null) queue.addFirst(queue.peekLast().right);
                    if (queue.peekLast().left != null) queue.addFirst(queue.peekLast().left); 
                    subList.add(queue.pollLast().val);
                }
               
                isForward = true;
            }
            res.add(subList);          
        }  
        
        return res;
    }
}

//106. Construct Binary Tree from Inorder and Postorder Traversal: medium
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
//web solution + retype
class Solution {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (inorder.length == 0 || postorder.length == 0 || inorder.length != postorder.length) {
            return null;
        }
        
        HashMap<Integer, Integer> record = new HashMap<>();
        
        for (int i=0; i<inorder.length; i++) {
            record.put(inorder[i],i);    
        }
        
        return buildTree(inorder, 0, inorder.length-1, postorder, 0, postorder.length-1, record);
    }
    
    public TreeNode buildTree(int[] inorder, int is, int ie, int[] postorder, int ps, int pe, HashMap<Integer, Integer> record) {
        if (is > ie || ps > pe) return null;
        
        TreeNode root = new TreeNode(postorder[pe]);
        int ri = record.get(postorder[pe]);
        
        TreeNode left = buildTree(inorder, is, ri-1, postorder, ps, ps+ri-is-1, record);
        TreeNode right = buildTree(inorder, ri+1, ie, postorder, ps+ri-is, pe-1, record);
        
        root.left = left;
        root.right = right;
        
        return root;
    }
}

//112. Path Sum: easy
//web solution
class Solution {
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) return false;
        
        if (root.left == null && root.right == null && sum-root.val==0) return true;
        
        return hasPathSum(root.left, sum-root.val) || hasPathSum(root.right, sum-root.val);
    }
}

//114. Flatten Binary Tree to Linked List: medium
//web solution
 public void flatten(TreeNode root) {
        if (root == null) return;
        
        TreeNode left = root.left;
        TreeNode right = root.right;
        
        root.left = null;
        
        flatten(left);
        flatten(right);
        
        root.right = left;
        TreeNode cur = root;
        while (cur.right != null) cur = cur.right;
        cur.right = right;
    }


//114. Flatten Binary Tree to Linked List: medium
// Solution 2
private TreeNode prev = null;

public void flatten(TreeNode root) {
    if (root == null)
        return;
    flatten(root.right);
    flatten(root.left);
    root.right = prev;
    root.left = null;
    prev = root;
}


//116. Populating Next Right Pointers in Each Node
/**
 * Definition for binary tree with next pointer.
 * public class TreeLinkNode {
 *     int val;
 *     TreeLinkNode left, right, next;
 *     TreeLinkNode(int x) { val = x; }
 * }
 */
//my bfs solution: time: O(n), space O(n)
public class Solution {
    public void connect(TreeLinkNode root) {
        if (root == null) return;
        
        Queue<TreeLinkNode> queue = new LinkedList<TreeLinkNode>();
        queue.add(root);
        int levelSize = 0;
        while (!queue.isEmpty()) {
            levelSize = queue.size();
            TreeLinkNode curr= null;
            
            for (int i=0; i<levelSize; i++) {
                if (queue.peek().left != null) queue.add(queue.peek().left);
                if (queue.peek().right != null) queue.add(queue.peek().right);
                
                if (curr == null) {
                    curr = queue.poll();
                } else {
                    curr.next = queue.poll(); 
                    curr = curr.next;
                }                          
            }
            
            curr.next = null;
        }       
    }
}


// web solution
// Time: O(n) Space: O(1)
public class Solution {
    public void connect(TreeLinkNode root) {
        TreeLinkNode level_start=root;
        while(level_start!=null){
            TreeLinkNode cur=level_start;
            while(cur!=null){
                if(cur.left!=null) cur.left.next=cur.right;
                if(cur.right!=null && cur.next!=null) cur.right.next=cur.next.left;
                
                cur=cur.next;
            }
            level_start=level_start.left;
        }
    }
}


//208. Implement Trie (Prefix Tree): medium
//web solution
class TrieNode {
    public char val;
    public boolean isWord; 
    public TrieNode[] children = new TrieNode[26];
    public TrieNode() {}
    TrieNode(char c){
        TrieNode node = new TrieNode();
        node.val = c;
    }
}

public class Trie {
    private TrieNode root;
    public Trie() {
        root = new TrieNode();
        root.val = ' ';
    }

    public void insert(String word) {
        TrieNode ws = root;
        for(int i = 0; i < word.length(); i++){
            char c = word.charAt(i);
            if(ws.children[c - 'a'] == null){
                ws.children[c - 'a'] = new TrieNode(c);
            }
            ws = ws.children[c - 'a'];
        }
        ws.isWord = true;
    }

    public boolean search(String word) {
        TrieNode ws = root; 
        for(int i = 0; i < word.length(); i++){
            char c = word.charAt(i);
            if(ws.children[c - 'a'] == null) return false;
            ws = ws.children[c - 'a'];
        }
        return ws.isWord;
    }

    public boolean startsWith(String prefix) {
        TrieNode ws = root; 
        for(int i = 0; i < prefix.length(); i++){
            char c = prefix.charAt(i);
            if(ws.children[c - 'a'] == null) return false;
            ws = ws.children[c - 'a'];
        }
        return true;
    }
}
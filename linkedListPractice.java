// 1. Easy: Two Sum
// https://leetcode.com/problems/two-sum/description/
// Given an array of integers, return indices of the two numbers such that they add up to a specific target.
// You may assume that each input would have exactly one solution, and you may not use the same element twice.

// e.g. Given nums = [2, 7, 11, 15], target = 9,
// Because nums[0] + nums[1] = 2 + 7 = 9,
// return [0, 1].

import java.util.*;

class Solution {
    public int[] twoSum(int[] nums, int target) {
        if (nums.length == 0) return new int[]{};
        Hashtable<Integer, Integer> record = new Hashtable<>();

        for (int i = 0; i < nums.length; i++) {
            int curr = nums[i];
            if (record.containsKey(curr)) {
                return new int[]{record.get(curr), i};
            } else {
                record.put(target - nums[i], i);
            }
        }
        
        return new int[]{};
    }
}

/* 2. Medium: Add 2 linkedLists 
* https://leetcode.com/problems/add-two-numbers/solution/
* e.g. 3 -> 4 -> 2
       4 -> 6 -> 5
     = 8 -> 0 -> 7
 *    public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
     
 public ListNode addTwoNumbers(ListNode list1, ListNode list2) {   
    ListNode dummyHead = new ListNode(0);
	ListNode l1 = list1, l2 = list2, curr = dummyHead;

	//if need l1 and l2 be unchanged, we can create new ListNodes
	// ListNode p = l1, q = l2

	int carry = 0;

	while (l1 != null || l2 != null) {
		int l1Value = (l1 != null)? l1.val : 0;
		int l2Value = (l2 != null)? l2.val : 0;
		int sum = carry + l1Value + l2Value;
		carry = sum/10;
		curr.next = new ListNode(sum % 10);
		curr = curr.next;

	    if (l1 != null) l1 = l1.next;
		if (l2 != null) l2 = l2.next;
	}

	if (carry > 0) {
		curr.next = new ListNode(carry);
	}

	return dummyHead.next;
}  

// 21. Merge Two Sorted Lists
// Merge two sorted linked lists and return it as a new list. The new list should be made by splicing together the nodes of the first two lists.


public class Solution {
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if(l1 == null){
            return l2;
        }
        if(l2 == null){
            return l1;
        }
        
        ListNode mergeHead;
        if(l1.val < l2.val){
            mergeHead = l1;
            mergeHead.next = mergeTwoLists(l1.next, l2);
        }
        else{
            mergeHead = l2;
            mergeHead.next = mergeTwoLists(l1, l2.next);
        }
        return mergeHead;
    }
}


// hard: 23. Merge k Sorted Lists
// Merge two sorted linked lists and return it as a new list. The new list should be made by splicing together the nodes of the first two lists.

//my solution: work on Intellij, fail in leetcode compiler
class Solution {

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public ListNode mergeKLists(ListNode[] lists) {

        if (lists.length == 0) {
            return null;
        }

        boolean stop = true;
        for (int i = 0; i < lists.length; i++) {
            if (lists[i] != null) stop = false;
        }

        if (stop) return null;

        ListNode min = new ListNode(Integer.MAX_VALUE);
        int minVal = 0;
        for (int i = 0; i < lists.length; i++) {
            if (lists[i] == null) continue;
            if (lists[i].val < lists[minVal].val) {
                minVal = i;
            }
        }

        min = lists[minVal];
        lists[minVal] = lists[minVal].next;

        min.next = mergeKLists(lists);
        return min;
    }


// discussion solution
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        
        PriorityQueue<ListNode> queue = new PriorityQueue<ListNode>(lists.length, new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                if (o1.val < o2.val) {
                    return -1;
                } else if (o1.val == o2.val) {
                    return 0;
                } else {
                    return 1;
                }
            }
        });
        
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        
       for (ListNode node: lists) {
           if (node != null) queue.add(node);
       }
        
       while (!queue.isEmpty()) {
           tail.next = queue.poll();
           tail = tail.next;
           
           if (tail.next != null) {
                queue.add(tail.next);
           }
       }
        
       return dummy.next;
    }
}


//my solution
class Solution {
    public ListNode swapPairs(ListNode head) {
        // base case
        if (head == null || head.next == null) {
            return head;
        }
        
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;
        ListNode curr = head;
           
        ListNode next = head.next;
        
        while (curr != null && next != null) {
            //swap
            prev.next = curr.next;
            curr.next = next.next;
            next.next = curr;   
            
            //update
            prev = curr;
            curr = curr.next;
                       
            if ( curr != null) {
                next = curr.next;
            }          
        }
        
        return dummy.next;
    }
}

// web solution recursion
 public ListNode swapPairs(ListNode head) { 
       if (head == null || head.next ==  null) {
            return head;
       }
        
        ListNode n = head.next;
        head.next = swapPairs(head.next.next);
        n.next = head;
        
        return n;
        
    }


//25. Reverse Nodes in k-Group
    //my solution: recursive
public ListNode reverseKGroup(ListNode head, int k) {
    
    //base case head size is less than k
    if (k == 1) return head;
    
    int count1 = 0;
    ListNode temp = head;
    while (count1 < k) {
        if (temp == null) {
            return head;            
        }
        temp = temp.next;
        count1++;
    }
    
    ListNode dummy = new ListNode(0);
    dummy.next = head;
    ListNode prev = dummy;
    ListNode curr = head;
    ListNode next = head.next;
    
    int count2 = 1;
    while (count2 < k) {
        curr.next = next.next;
        next.next = prev.next;
        prev.next = next;          
        next = curr.next;
       
        count2++;
    }
    
    curr.next = reverseKGroup(curr.next, k);
    
    return dummy.next;
}


    // web solution recursive
public ListNode reverseKGroup(ListNode head, int k) {
    ListNode curr = head;
    int count = 0;
    while (curr != null && count != k) { // find the k+1 node
        curr = curr.next;
        count++;
    }
    if (count == k) { // if k+1 node is found
        curr = reverseKGroup(curr, k); // reverse list with k+1 node as head
        // head - head-pointer to direct part, 
        // curr - head-pointer to reversed part;
        while (count-- > 0) { // reverse current k-group: 
            ListNode tmp = head.next; // tmp - next head in direct part
            head.next = curr; // preappending "direct" head to the reversed list 
            curr = head; // move head of reversed part to a new node
            head = tmp; // move "direct" head to the next node in direct part
        }
        head = curr;
    }
    return head;
}
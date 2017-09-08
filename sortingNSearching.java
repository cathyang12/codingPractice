 
//10.1 Sorted Merge
 public void merge(int[] a, int[] b, int lastA, int lastB) {
        int indexA = lastA - 1; // Index of last element in array a
        int indexB = lastB - 1; // Index of last element in array b
        int indexMerged = lastA + lastB -1; // end of merge array

        
        while (lastB >= 0) {
            
            if(lastA >= 0 && a[lastA] > b[lastB]) {
                a[indexMerged] = a[indexA];
                indexA--;
            } else {
                a[indexMerged] =  b[indexB];
                indexB--;
            }
            indexMerged--;
        }
    }

// 10.3 Search in rotated array
    int search(int[] a, int left, int right, int target) {
        // check if the leftmost <  mid true -> check target
        if (left > right) return -1;
        int mid = (left + right)/2;
        
        if (a[mid] == target) {
            return mid;
        }
        
        // normal half on the left
        if (a[left] <= a[mid]) {
            
            //target out of range, search on right
            if (target < a[left] || target > a[mid]) {
                return search(a, mid+1, right, target);
            } else {
                return search(a, left, mid-1, target);
            }
        } else if (a[mid] < a[left] ) {
            //normal part on the right
            
            //target out of range, search on left
            if (target < a[mid] || target > a[right]) {
                return search(a, left, mid-1, target);
            } else {
                return search(a, mid+1, right, target);
            };
        } else if (a[left] == a[mid]){
            if (a[mid] != a[right]) {
                return search(a, mid+1, right, target);
            } else {
                int resOnright = search(a, mid+1, right, target);
                
                //??? not sure why need to search on the left half if we already know the left side r the same int
                if (resOnright == -1) {
                    return search(a, left, mid-1, target);
                } else {
                    return resOnright;
                }
            }
            
        }
        
        //??? Not sure why need this stmt
        return -1;
   }


    // 10.4 Sorted Search, No Size
    // The input array is actually called listy: sorty, positive integer that does not have size() but has elementAt(i) method
   int searchListy(int[] listy, int target ) {
        // base case: empty Listy
        if (listy.elementAt(0) == -1) return -1;

        //find listy length O(log N)
       int index = 1;
       while (elementAt(index) != -1 || listy[index] < target) {
           index *= 2;
       }
       binarySearch(listy, 0, index/2, target);
   }

   int binarySearch(int[] listy, int start, int end, int target) {
        int mid;

       while (start <= end) {
           mid = (start + end)/2;
           midValue = listy.elementAt(mid);

           if (midValue == target) return mid;
           if (midValue < target ||midValue == -1) {
               end = mid-1;
           } else {
               start = mid+1;
           }
       }
       return -1;
    }


//10.10
//my solution
    int getRank(BSTNode node, int x) {

        int counter = 0;
        while (node.right != null && node.left != null) {
            if (x > node.val) {
                counter += node.count + 1;
                node = node.right;
            } else if (x < node.val){
                node = node.left;
            } else {
                return counter;
            }
        }


   }

   int track(BSTNode node, int x) {

       int counter = 0;

       while (node.right != null || node.left != null) {
           if (x > node.val) {
               counter += node.count + 1;
               if (node.right != null) {
                   node = node.right;
               } else {
                   BSTNode newNode = new BSTNode(x);
                   newNode.count = counter;
                   node.right = newNode;
               }
           } else if (x <= node.val) {
               if (node.left != null) {
                   node.count ++;
                   node = node.left;
               } else {
                   BSTNode newNode = new BSTNode(x);
                   newNode.count = counter;
                   node.left = newNode;
               }
           }
       }
   }

   //textbook solution
     RankNode root = null;

    void track(int number) {
        if (root ==null) {
            root = new RankNode(number);
        } else {
            root.insert(number);
        }
    }
   public class RankNode {
       int val;
       RankNode left, right;
       int left_size;

       public RankNode(int value) {
           this.val = value;
       }

       public void insert(int x) {
           if (x > val) {
               if (right != null) {
                   this.right.insert(x);
               } else {
                   RankNode newNode = new RankNode(x);
                   newNode.left_size = left_size + 1;
                   right = newNode;
               }
           } else {
               if (left != null){
                   left_size++;
                   left.insert(x);
               } else {
                   RankNode newNode = new RankNode(x);
                   newNode.left_size = left_size;
                   left = newNode;
               }
           }
       }

       public int getRank(int target) {
           if (val == target) {
               return left_size;
           } else if (val < target) {
               int right_rank = right == null ? -1 : right.getRank(target);
               return right_rank + left_size + 1;
           } else {
               if (left == null) {
                   return -1;
               } else {
                   return left.getRank(target);
               }
           }
       }
   }



//Leetcode
//33. Search in Rotated Sorted Array
// my solution

   class Solution {
    public int search(int[] nums, int target) {
        if (nums.length == 0) return -1;
        
        int low=0, high=nums.length-1, mid;
        
        while (low <= high) {
            mid = (low + high)/2;
            
            if (nums[mid] == target) return mid;
            
            //sorted on the left
            if (nums[low] <= nums[mid]) {
                if (nums[low] <= target && target < nums[mid]) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            } else {
                //sorted on the right {
                if (nums[mid] < target && target <= nums[high]) {
                   low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
        }
        
        return -1;
    }
}
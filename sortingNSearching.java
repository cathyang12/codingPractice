//binary search time: O(nlogn)
int binarySearch(int[] arr, int target) {
  int low = 0;
  int high = arr.length-1;
  int mid;

  while (low <= high) {
      mid = (high+low)/2;
      
      if (arr[mid] < target) {
          low = mid+1;
      } else if (arr[mid] > target) {
          high = mid-1;
      } else {
        return mid;
      }
  }
  
  return -1;
} 

//bubble sort  time: O(n^2)
void bubbleSort(int[] input) {
  if (input.length == 0) return;

  for (int i=0; i<input.length-1; i++) {
    for (int j=0; j<input.length-i-1; j++) {
      if (input[j] > input[j+1]) {
        swap(input, j, j+1);
      }
    }
  }
}

//insertion sort time: O(n^2)
void insertionSort(int[] input) {
  int len = input.length;
  int key, j;

  for (int i=1; i<len; i++) {

    key = input[i];
    j = i-1; 

    while (j>=0 && input[j]>key) {
        input[j+1] = input[j];
        j--;
    }

    input[j+1] = key;
  }
}

//heapsort O(nlogn)
void heapsort(int[] arr) {
  int n = arr.length;

  // build a heap
  for (int i=n/2-1; i>=0; i--) {
    heapify(arr, n, i);
  }

  for (int i=n-1; i>=0; i--) {
     int temp = arr[0];
     arr[0] = arr[i];
     arr[i] = temp;

     heapify(arr, i, 0); 
  }
}

void heapify(int[] arr, int size, int idx) {
  int root = idx;
  int left = 2*idx + 1;
  int right = 2*idx + 2;
  int largest = i;

  // If left child is larger than root
  if (l < n && arr[l] > arr[largest]) largest = l;

  // If right child is larger than largest so far
  if (r < n && arr[r] > arr[largest]) largest = r;

  // If largest is not root
  if (largest != r) {
    int temp = arr[root];
    arr[root] = arr[largest];
    arr[largest] = temp;

    // Recursively heapify the affected sub-tree
    heapify(arr, size, largest);
  }
}

//counting sort: time O(n+k) space: O(k)
    public void sortColors(int[] nums) {
        int inputLength = nums.length;
        
        //step 1: init count array find range first
        int[] count = new int[3];
        for (int i=0; i<3; i++) {
            count[i] = 0;
        }
        
        //step 2: count occurance in count array
        for (int i=0; i<inputLength; i++) {
            count[nums[i]]++;
        }
        
        //step 3: accumulate count index
        for (int i=1; i<3; i++) {
            count[i] += count[i-1];
        }
        
        //step 4: sort
        int[] res = new int[inputLength];
        for (int i=0; i<inputLength; i++) {
            res[count[nums[i]]-1] = nums[i];
            count[nums[i]]--;
        }
        
        for ( int i=0; i<inputLength; i++) {
            System.out.println(res[i]);
        }
       
        return res
    }

//79. Word Search: medium
//my fail solution
    class Solution {
    
    public boolean exist(char[][] board, String word) {
        
        if (word.length() == 0 ) return true;
        if (board.length == 0 || word == null) return false;
        
        boolean res = false;
        
        //depth first search
        for (int i=0; i<board[0].length; i++) {
            for (int j=0; j<board.length; j++) {              
                if (board[j][i] == word.charAt(0)) {
                    
                    boolean[][] isVisited = newRecord(board.length, board[0].length);
                   // System.out.println("before result in main method: " + j + " "+  i + " ");
                    isVisited[j][i] = true;
                    res = dFS(j, i, board, word, 1, isVisited) || res;
                    System.out.println("result in main method: " + res + " " + j + " "+ i+ " ");
                    if (res) return true;
                }        
            }           
        }  
        
        return res;
    }
    
    
    public boolean dFS(int row, int col, char[][]board, String word, int currIdx, boolean[][] isVisited) {
        boolean top = false, left = false, bottom = false, right = false;
        
        if (currIdx > word.length()-1) {
            System.out.println("return true: " + currIdx);
            return true;
        }
        
        //top
        if (row > 0) {
            if (board[row-1][col] == word.charAt(currIdx) && (!isVisited[row-1][col])) {
                int temp = row-1;
                System.out.println("top result: " + temp + " " + col + " " + currIdx + " ");
                isVisited[row-1][col] = true;
                top = dFS(row-1, col, board, word, currIdx+1, isVisited); 
            } 
        }
        
        //bottom
        if ((row+1) < board.length) {
            if (board[row+1][col] == word.charAt(currIdx) && (!isVisited[row+1][col])) {
                 int temp = row+1;
                 System.out.println("bottom result: " + temp + " " + col + " " + currIdx + " ");
                 isVisited[row+1][col] = true;
                 bottom = dFS(row+1, col, board, word, currIdx+1, isVisited); 
            } 
        }
        
        //left
        if (col>0) {
            if (board[row][col-1] == word.charAt(currIdx) && (!isVisited[row][col-1])) {
                int temp = col-1;
                System.out.println("left result: " + row + " " + temp + " " + currIdx + " ");
                isVisited[row][col-1] = true;
                left = dFS(row, col-1, board, word, currIdx+1, isVisited);
            }
        }
            
        //right
        if ((col+1) < board[0].length) {
            int temp = col+1;
            //System.out.println("before right result: " + row + " " + temp + " " + currIdx + " ");
            if (board[row][col+1] == word.charAt(currIdx) && (!isVisited[row][col+1])) {
               // int temp = col+1;
                System.out.println("right result: " + row + " " + temp + " " + currIdx + " ");
                isVisited[row][col+1] = true;
                right = dFS(row, col+1, board, word, currIdx+1, isVisited);
            }   
        }
            
        return top || bottom || left || right;
                       
    }
    
    public boolean[][] newRecord(int row, int col) {
         //initilize record matrix
        boolean[][] isVisited = new boolean[row][col];
        for (int i=0; i<row; i++) {
            for (int j=0; j<col; j++) {
                isVisited[i][j] = false;    
            }
        } 
        
        return isVisited;
    }
    
}

// web solution time: O(4^n)
class Solution {   
    public boolean exist(char[][] board, String word) {
        char[] w = word.toCharArray();
        
        for (int col=0; col<board[0].length; col++) {
            for (int row=0; row<board.length; row++) {
                if (dFS(row, col, board, w, 0)) return true;
                
            }
        }
       
        return false;
    }
    
    
    public boolean dFS(int row, int col, char[][] board, char[] word, int currIdx) {
        if (currIdx == word.length) return true;
        if (row<0 || col<0 || row==board.length || col>= board[0].length || (board[row][col] != word[currIdx])) return false;
            
        board[row][col] = '*';
        boolean exist = dFS(row-1, col, board, word, currIdx+1)
            || dFS(row+1, col, board, word, currIdx+1)
            || dFS(row, col-1, board, word, currIdx+1)
            || dFS(row, col+1, board, word, currIdx+1);
        board[row][col] = word[currIdx];
        
        return exist;                  
    }   
}

//88. Merge Sorted Array: easy
// web solution + my input, time complexity: m+n
class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        if (nums1 == null || nums2 == null ) return;
        if (n == 0) return;

        int idx = m+n-1;
        int i = m-1;
        int j = n-1;

        while (i >= 0 && j >= 0) {
            
            if (nums2[j] > nums1[i]) {
                nums1[idx--] = nums2[j--];
            } else {
                nums1[idx--] = nums1[i--];
            }
            
        }
        
        while (j >= 0) 
            nums1[idx--] = nums2[j--];
        
        return;      
    }
}
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
 Helpful Method

 String Integer.toBinaryString(some_int)
 int Integer.parseInt(some_string, 2) 


//56. Merge Intervals

// my edge case not working solution
// fail case: [[2,3],[4,5],[6,7],[8,9],[1,10]]
class Solution {
    
    class SortStart implements Comparator<Interval> {
        public int compare(Interval i1, Interval i2) {
            return Integer.compare(i1.start, i2.start);
        }
    }
    
    public List<Interval> merge(List<Interval> intervals) {
        List<Interval> res = new ArrayList<Interval>();
        
        //input check
        if (intervals.size() <= 1) return intervals;          
        if (intervals == null) return res;
        
        //regular cases
        Collections.sort(intervals, new SortStart());
        
        int length = intervals.size()-1;
        int currStart = -1;
        int currEnd = -1;
        boolean isLastIncluded = false;
        for (int i=0; i<length; i++) {
            if (intervals.get(i).end >= intervals.get(i+1).start) {
                          
                if (currStart == -1) {
                    currStart = intervals.get(i).start;
                } else {
                    currStart = Math.min(currStart, intervals.get(i).start);
                }
                
                
                currEnd = Math.max(currEnd, Math.max(intervals.get(i).end, intervals.get(i+1).end));
                
                if ((i+1) == (length)) {
                    isLastIncluded = true;
                    Interval temp = new Interval(currStart, currEnd);
                    res.add(temp); 
                    break;
                }
                
                System.out.println("if true: " + i + " " + currStart + " "+ currEnd);
            } else {
                int tempStart = (currStart == -1) ? intervals.get(i).start : currStart;
                int tempEnd = (currEnd == -1) ? intervals.get(i).end : currEnd;  
                
                System.out.println("else: " + i + " " + tempStart + " "+ tempEnd);
                Interval temp = new Interval(tempStart, tempEnd);
                
                currStart = -1;
                currEnd = -1;
                res.add(temp);             
            }
        
        }
                
        
        if (!isLastIncluded) {
            res.add(intervals.get(length));
        }
        
        return res;
        
    }
}

//web solution
class Solution {
    
    class SortStart implements Comparator<Interval> {
        public int compare(Interval i1, Interval i2) {
            return Integer.compare(i1.start, i2.start);
        }
    }
    
    public List<Interval> merge(List<Interval> intervals) {
        List<Interval> res = new ArrayList<Interval>();
        
        //input check
        if (intervals.size() <= 1) return intervals;          
        if (intervals == null) return res;
        
        //regular cases
        Collections.sort(intervals, new SortStart());
          
        int start = intervals.get(0).start;
        int end =  intervals.get(0).end;
        
        boolean isLastIncluded = false;
        for (Interval i: intervals) {
            if (i.start <= end) {
                end = Math.max(i.end, end);          
            } else {
                res.add(new Interval(start,end));
                start = i.start;
                end = i.end;             
            }
                
        }
                
        res.add(new Interval(start, end));     
        return res;
        
    }
}


//71. Simplify Path: medium
//web sol + my input
class Solution {
    public String simplifyPath(String path) {
        
        Deque<String> stack = new LinkedList<>();
        
        Set<String> skip = new HashSet<>(Arrays.asList("",".",".."));
        
        for (String dir: path.split("/")){
            
            if (dir.equals("..") && !stack.isEmpty()) stack.pop();
            else if (!skip.contains(dir)) stack.push(dir);
            
        }
        
        String res = "";
        while (!stack.isEmpty()) {
            String  curr = stack.pop();
            res = "/" + curr + res;
        }
        
        return res.isEmpty() ? "/" : res;
    }
}

//73. Set Matrix Zeroes: medium
class Solution {
    public void setZeroes(int[][] matrix) {
        
        int rowNum = matrix.length;
        int colNum = matrix[0].length;
        
        if (rowNum == 0 || matrix == null); 
        
        boolean firstRow = false, firstCol = false;
          
        //check first row
        for (int i=0; i<colNum; i++) {
            if (matrix[0][i] == 0){
                firstRow = true;
                break;
            } 
        }
        
         //check first col
        for (int i=0; i<rowNum; i++) {
            if (matrix[i][0] == 0) {
                firstCol = true;
                break;
            }
        }
        
        //mark zero
        for (int i=1; i<rowNum; i++) {  //row
            for (int j=1; j<colNum; j++) {  //column
                if (matrix[i][j] == 0) {
                    matrix[0][j] = 0;
                    matrix[i][0] = 0;
                }
            }
        }
        
        //set columns zeros
        for (int i=1; i<colNum; i++) {
            if (matrix[0][i] == 0){
                setColZero(i, rowNum, matrix);
            } 
        }
        
        //set row zeros
        for (int i=1; i<rowNum; i++) {
            if (matrix[i][0] == 0){
                setRowZero(i, colNum, matrix);
                
            } 
        }
        
        if (firstRow) setRowZero(0, colNum, matrix);
        if (firstCol) setColZero(0, rowNum, matrix);
    
    }
    
    void setRowZero(int index, int length, int[][] matrix) {
        for (int i=0; i<length; i++) {
            matrix[index][i] = 0;
        }
    }
    
     void setColZero(int index, int length, int[][] matrix) {
        for (int i=0; i<length; i++) {
            matrix[i][index] = 0;
        }
    }
}


/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */
class Solution {
    
    class SortStart implements Comparator<Interval> {
        public int compare(Interval i1, Interval i2) {
            return Integer.compare(i1.start, i2.start);
        }
    }
    
    public int merge(List<Interval> intervals) {
        List<Interval> res = new ArrayList<Interval>();
        
        //input check
        if (intervals.size() <= 1) return intervals;          
        if (intervals == null) return res;
        
        //regular cases
        Collections.sort(intervals, new SortStart());
          
        int start = intervals.get(0).start;
        int end =  intervals.get(0).end;
        
        boolean isLastIncluded = false;
        for (Interval i: intervals) {
            if (i.start <= end) {
                end = Math.max(i.end, end);          
            } else {
                res.add(new Interval(start,end));
                start = i.start;
                end = i.end;             
            }
                
        }
                
        res.add(new Interval(start, end));    
        int length = 0;

        for (Interval i: res) {
            length += i.end - i.start + 1;
        } 

        return length;       
    }
}


//Maximum sum subarray
//Kabane's algorithm

public int kabane(int[] A) {
    int maxCurr = A[0], maxGlobal = A[0];
    for （int i=1; i<a.length; i++) {
        maxCurr = Math.max(A[i], maxCurr + A[i]);
        maxGlobal = Math.max(maxGlobal, maxCurr);
    }

    return maxGlobal;

 }
}

//121. Best Time to Buy and Sell Stock: easy
// Input: [7, 1, 5, 3, 6, 4]
// Output: 5
class Solution {
    public int maxProfit(int[] prices) {
        if (prices.length == 0) return 0;
        
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i=0; i< prices.length;i++) {
             if (prices[i] < min) {
                 min = prices[i];  
             } else {
                 max = Math.max(prices[i]-min, max);
             }
        }
        
        return max;
    }
}

//128. Longest Consecutive Sequence 
//web solution
class Solution {
    public int longestConsecutive(int[] nums) {
        int res = 0;       
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        
        for (int n: nums) {
            if (!map.containsKey(n)) {
                int left = map.containsKey(n-1)? map.get(n-1): 0;
                int right = map.containsKey(n+1)? map.get(n+1): 0;
                int sum = left + right + 1;
                map.put(n, sum);
                
                res = Math.max(res, sum);
                
                map.put(n, sum);               
                map.put(n-left, sum);
                map.put(n+right, sum);
            } else {
               continue;
            }
        }
        
        return res;
    }
}

//my solution
public class Solution {
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) return false;       
        ListNode slow = head;
        ListNode fast = head.next;
        
        while (fast.next != null) {
            slow = slow.next;
            fast = fast.next;
            
            if (slow == fast) return true;
            
            if (fast.next != null) {
                fast = fast.next;
            }
        }
        
        return false;
        
    }
}


node1: 4->5->9->10-11
node2: 3->9->10-11

stack 1: 11->10->9->5->4
stack 2: 11->10->9->3
//141. Linked List Cycle: easy
//web solution
public boolean hasCycle(ListNode head) {
    if(head==null) return false;
    ListNode walker = head;
    ListNode runner = head;
    while(runner.next!=null && runner.next.next!=null) {
        walker = walker.next;
        runner = runner.next.next;
        if(walker==runner) return true;
    }
    return false;
}

//153. Find Minimum in Rotated Sorted Array
// web solution
class Solution {
    public int findMin(int[] nums) {
        
        if (nums.length == 0) return -1;
        if (nums.length == 1) return nums[0];
        
        int left = 0, right = nums.length-1;
        
        while(left < right) {
            int mid = (left + right)/2;
            if(nums[mid] > nums[right])
                left = mid + 1;
            else
                right = mid;
        }
    
        return nums[left];
    }
}

//162. Find Peak Element
class Solution {
    public int findPeakElement(int[] nums) {
        if (nums.length == 0) return -1;
        
        int m1, m2, start = 0;
        int end = nums.length-1;
        
        while (start < end) {
            m1 = (start + end)/2;
            m2 = m1 + 1;
            
            if (nums[m1] > nums[m2]) {
                end = m1; 
            } else {
                start = m1 + 1;
            }
            
        }
        
        return start;
    }
}

//204. Count Primes: easy
class Solution {
    public int countPrimes(int n) {
        boolean[] isPrime = new boolean[n];
        
        for (int i=2; i<n; i++) {
            isPrime[i] = true;
        }
        
        for (int i=2; i*i<=n; i++) { 
            if (!isPrime[i]) continue;          
            for (int j=i*i; j<n; j += i) {
                isPrime[j] = false;
            }
        }
        
        int count=0;
        for (int i=0; i<n; i++) {
            if (isPrime[i]) count++;
        }
        
        return count;
    }

    //method 2
     public int countPrimes(int n) {
        boolean[] notPrime = new boolean[n];
        int count = 0;
        for (int i = 2; i < n; i++) {
            if (notPrime[i] == false) {
                count++;
                for (int j = 2; i*j < n; j++) {
                    notPrime[i*j] = true;
                }
            }
        }
        
        return count;
    }
}


//240. Search a 2D Matrix II: medium
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix.length == 0) return false;
        
        int i = matrix[0].length-1, j = 0;
        while (i >= 0 && j < matrix.length) {
            if (matrix[j][i] < target) {
                j++;
            } else if (matrix[j][i] > target) {
                i--;
            } else {
                return true;
            }
        }
            
        return false;
    }
}

//238. Product of Array Except Self
class Solution {
    public int[] productExceptSelf(int[] nums) {
        int[] res = new int[nums.length];
        
        //product of the numbers on the left side
        res[0] = 1;       
        for (int i=1; i<nums.length; i++) {
            res[i] = res[i-1] * nums[i-1];
        }
        
        int right = 1; //product of the numbers on the right side
        for (int i = nums.length-1; i>=0; i--) {
            res[i] *= right;
            right *= nums[i];
        }
        
        return res;
    }
}

//167. Two Sum II - Input array is sorted: easy
// Input: numbers={2, 7, 11, 15}, target=9
// Output: index1=1, index2=2
//web + my solution
class Solution {
    public int[] twoSum(int[] numbers, int target) {       
        int[] res = new int[2];
        if (numbers.length < 2) return res;
        if (numbers[numbers.length-1] + numbers[numbers.length-2] < target) return res;
        
        int start = 0, end = numbers.length-1;      
        while (start < end){
            int currSum = numbers[start] + numbers[end];        
            if (currSum == target) {
                res[0] = start+1;
                res[1] = end+1;
                break;
            } else if (currSum < target) {               
                start++;
            } else {             
                end--;
            }
        }       
        return res;
    }
    
}


//127. Word Ladder: medium
//build hashMap + BFS
class Solution {
    HashMap<String, List<String>> map = new HashMap<>();

    public void buildMap(String beginWord, List<String> wordList) { 
        for (String str: wordList) {
            LinkedList<String> nlist = new LinkedList<>();
            map.put(str, nlist);

            for (String word: wordList) {
                if (diff(str,word) == 1) {
                    map.get(str).add(word);
                }
            }
        }

        if (!map.containsKey(beginWord)) {
             LinkedList<String> nlist = new LinkedList<>();
            map.put(beginWord, nlist);

            for (String word: wordList) {
                if (diff(beginWord,word) == 1) {
                    map.get(beginWord).add(word);
                }
            }
        }
           
    }
    
    public int diff(String s1, String s2) {
        int count = 0;
        for (int i=0; i<s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                count++;
            }
        }

        return count;
    }

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (beginWord.equals(endWord)) return 0;   
        buildMap(beginWord, wordList);
        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<String>();

        queue.add(beginWord);
        visited.add(beginWord);
        int step = 1;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i=0; i<size; i++) {
                String curr = queue.poll();
                if (curr.equals(endWord)) return step;

                List<String> oneStepWords = map.get(curr);
                for (String str: oneStepWords) {
                    if (!visited.contains(str)) {
                        queue.add(str);
                        visited.add(str);
                    }
                }

                step++;
            }
            
        }

        return 0;
    }
}

//49. Group Anagrams: medium
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {       
        if (strs.length == 0 || strs == null) return new ArrayList<List<String>>();
        
        HashMap<String, List<String>> map = new HashMap<String, List<String>>();
        
        for (String str: strs) {
            char[] arr = str.toCharArray();
            Arrays.sort(arr);
            String temp = String.valueOf(arr);
            
            if (map.containsKey(temp)) {
                map.get(temp).add(str);
            } else {
                List<String> strGroup = new ArrayList<>(Arrays.asList(str));
                map.put(temp, strGroup);
            }
        }   
        
        return new ArrayList<List<String>>(map.values());
    }
}

//48. Rotate Image: medium
class Solution {
    public void rotate(int[][] matrix) {
        if (matrix.length == 0 || matrix.length==1) {
            return;
        } 
            
        for (int layer=0; layer<(matrix.length/2); layer++) {
            int first = layer;
            int last = matrix.length - 1 - layer;
            for (int j=first; j<last; j++) {
                int offset = j - first;            
                
                int temp = matrix[first][j];
                matrix[first][j] = matrix[last-offset][first];  // left to top
                matrix[last - offset][first] = matrix[last][last - offset];  // bottom to left
                matrix[last][last - offset] =  matrix[j][last]; //right to bottom
                matrix[j][last] =  temp; //top to right              
            }
        }
    }
}
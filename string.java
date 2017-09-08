//5. Longest Palindromic Substring
//Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.
// Example:
// Input: "babad"
// Output: "bab"
// Note: "aba" is also a valid answer.

class Solution {
    int lo, maxLen;
    
    public String longestPalindrome(String s) {
        int len = s.length();
        
        if (len < 2) {
            return s;
        }
        
        for (int i=0; i<len; i++) {
            extendPalindrome(s, i, i); //odd
            extendPalindrome(s, i, i+1); //even         
        }
        
        return s.substring(lo, lo + maxLen);
    }
    
    public void extendPalindrome(String s, int low, int high) {
        int len = s.length();
        while (low>=0 && high<len && s.charAt(low) == s.charAt(high)){
            low--;
            high++;
        }
        
        if (maxLen < high-low-1) {
            lo = low+1;
            maxLen = high-low-1;
        }
    }
}

// 15. 3Sum
// Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.
// Note: The solution set must not contain duplicate triplets.

class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        int len = nums.length;
        
        for (int i=0; i+2<len; i++) {
             if (i > 0 && nums[i] == nums[i - 1]) continue; //skip same result
            
            int j = i+1;
            int k = len-1;
            int target = -nums[i];
            
            while (j < k) {
                if ((nums[j] + nums[k]) == target){
                    res.add(Arrays.asList(nums[i], nums[j], nums[k]));
                    j++;
                    k--;
                    while (j<k && nums[j] == nums[j+1]) j++;
                    while (j<k && nums[k] == nums[k-1]) k--;
                } else if ((nums[j] + nums[k]) < target) {
                    j++;
                } else {
                    k--;
                }           
            }
        }  
        return res;
    }
}


// 20. Valid Parentheses
// Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
// The brackets must close in the correct order, "()" and "()[]{}" are all valid but "(]" and "([)]" are not.


class Solution {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        
        char[] array = s.toCharArray();
        
        for (Character c: array) {
            if (c == '{') {
                stack.push('}');
            } else if (c == '[') {
                 stack.push(']');
             } else if (c == '(') {
                 stack.push(')');
            } else if (stack.empty() || c != stack.pop()) {
                 return false;
            } 
        }
        
        return stack.empty();
    }
}


//46. Permutations
//Given a collection of distinct numbers, return all possible permutations.
class Solution {
    public List<List<Integer>> res;
    
    public List<List<Integer>> permute(int[] nums) {
        res = new ArrayList<>();
        
        if (nums.length == 0) {
            List<Integer> curr = new ArrayList<>();
            res.add(curr);
            return res;
        }
               
        permute(nums, 0);
        
        return res;
    }
    
    public void permute(int[] nums, int start) {
        if (start == nums.length-1) {
            List<Integer> curr = new ArrayList<>();
            
            for(int i=0; i<nums.length; i++) {
                curr.add(nums[i]);
            }
            res.add(curr);
        }
        
        for (int i=start; i<nums.length; i++) {
            swap(nums, start, i);
            permute(nums, start+1);
            swap(nums, start, i);
        }
        
    }
    
    public void swap(int[] nums, int start, int end) {
        int temp = nums[start];
        nums[start] = nums[end];
        nums[end] = temp;
    }
}

//47. Permutations II
//Given a collection of numbers that might contain duplicates, return all possible unique permutations.
//my solution
class Solution {
    public List<List<Integer>> res;
    
    public List<List<Integer>> permuteUnique(int[] nums) {
        res = new ArrayList<>();
        
        if (nums.length == 0) {
            List<Integer> curr = new ArrayList<>();
            res.add(curr);
            return res;
        }
               
        permute(nums, 0);
        
        return res;
    }
    
    public void permute(int[] nums, int start) {
        if (start == nums.length-1) {
            List<Integer> curr = new ArrayList<>();
            
            for(int i=0; i<nums.length; i++) {
                curr.add(nums[i]);
            }
            
            if (!res.contains(curr)) {
                res.add(curr);
            }            
        }
        
        HashSet<Integer> h = new HashSet<>();
        
        for (int i=start; i<nums.length; i++) {
            if (!h.contains(nums[i])) {
                h.add(nums[i]);
                swap(nums, start, i);
                permute(nums, start+1);
                swap(nums, start, i);
            }
            
        }
        
    }
    
    public void swap(int[] nums, int start, int end) {
        int temp = nums[start];
        nums[start] = nums[end];
        nums[end] = temp;
    }
}


//48. Rotate Image
//
class Solution {
    public void rotate(int[][] matrix) {
        if (matrix.length == 0 || matrix.length==1) {
            return;
        } 
            
        for (int i=0; i<(matrix.length/2); i++) {
            int last = matrix.length -1 - i;
            for (int j=i; j<last; j++) {
                int leftover = last - j - i;            
                
                int temp = matrix[i][j];
                matrix[i][j] = matrix[leftover][i];  // left to top
                matrix[leftover][i] = matrix[last][leftover];  // bottom to left
                matrix[last][leftover] =  matrix[j][last]; //right to bottom
                matrix[j][last] =  temp; //top to right              
            }
        }
    }
}
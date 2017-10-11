

// 300. Longest Increasing Subsequence: medium
// {10, 22, 9, 33, 21, 50, 41, 60, 80} is 6 and LIS is {10, 22, 33, 50, 60, 80}

//Input : arr[] = {50, 3, 10, 7, 40, 80}
//Output : Length of LIS = 4
//The longest increasing subsequence is {3, 7, 40, 80}

int liSequence(int[] input) {
    int[] lis = new int[input.length];

    for (int i=0; i<lis.length; i++) {
        lis[i] = 1;
    }

    for (int i=1; i<input.length; i++) {
        for (int j=0; j<i; j++) {
            if (input[i] > arr[j]) {
                lis[i] = Math.max(lis[i], lis[j]+1);               
            }
        }
    }

    int max = 0;

    for (int i=0; i<lis.length; i++) {
        if (lis[i] > max) max = lis[i];
    }

    return max;
}


//Longest Common Subsequence
int lcs(int[] s1, int[] s2, int m, int n) {
    int[][] record = new int[m+1][n+1];

    for (int i=0; i<=s1.length; i++) {
        for (int j=0; j<=s2.length; j++) {
            if (i==0 || j==0) {
                record[i][j] = 0;  
            } else if (s1[i-1] == s2[j-1]) {
                record[i][j] = record[i-1][j-1] + 1;
            } else {
                record[i][j] = Math.max(record[i-1][j],record[i][j-1]);
            }
        }
    }
    
    return record[m][n];
}

//Edit Distance
//input: "cart", "march"
//outout 3 modifications (insert, remove n replace)
//time: O(len1*len2) space: O(len1*len2)
int editDis(String s1, String s2) {
    int len1 = s1.length();
    int len2 = s2.length();

    int[][] rec = new int[len1+1][len2+1];

    if (len1 == 0) return len2;
    if (len2 == 0) return len1;

    for (int i=0; i<=len1; i++) rec[i][0] = 0;
    for (int j=0; j<=len2; j++) rec[0][j] = 0;
    
    for (int i=1; i<=len1; i++) {
        for (int j=1; j<=len2; j++) {
            if (s1.charAt(i) == s2.charAt(j)) {
                rec[i][j] = rec[i-1][j-1];
            } else {
                rec[i][j] = 1 + Math.min(rec[i-1][j-1], Math.min(rec[i-1][j], rec[i][j-1]));
            }

        }
    }

    return dp[len1][len2];
}

//Min cost path
int minCostPath(int[][] graph) {
    if (graph.length == 0) return 0;

    int rowLen = graph.length;
    int colLen = graph[0].length;
    int[][] rec = new int[rowLen][colLen];

    rec[0][0] = graph[0][0];

    //initilize first col
    for (int i=1; i<rowLen; i++) {
        rec[i][0] = rec[i-1][0] + graph[i][0];
    }

    //initilize first row
    for (int i=1; i<colLen; i++) {
        rec[0][i] = rec[0][i-1] + graph[0][i];
    }

    for (int i=1; i<rowLen; i++) {
        for (int j=1; j<colLen; j++) {
            rec[i][j] = graph[i][j] + min(rec[i][j-1], rec[i-1][j], rec[i-1][j-1]);
        }
    }

    return rec[rowLen-1][colLen-1];
}

int min(int x, int y, int z) {
    if ( x<=y && x<=z ) return x;
    if ( y<=x && y<=z ) return y;
    return z;
}


//coin change
//Given a value N, if we want to make change for N cents, and we have infinite supply of each of S = { S1, S2, .. , Sm} valued coins, how many ways can we make the change?
//e.g. N = 4 and S = {1,2,3}, there are four solutions: {1,1,1,1},{1,1,2},{2,2},{1,3}.
int coinChange(int coins[], int total) {
    if (total == 0) return 1;

    if (coins.length == 0) return -1;

    int[] rec = new int[total+1];

    Arrays.fills(rec, 0);

    // Pick all coins one by one and update the table[]
    // values after the index greater than or equal to
    // the value of the picked coin
    for (int i=0; i<coins.length; i++) {
        for (int j=coins[i]; j<coins.length; j++) {
            rec[j] += rec[j-coins[i]];
        }
    }

    return rec[total];
}


//0-1 knapsac problem
// input: capacity = 7
//        val wt 0  1  2  3  4  5  6  7
//               0  0  0  0  0  0  0  0
//        1   1  0  1  1  1  1  1  1  1
//        4   3  0  1  1  4  5  5  5  5
//        5   4  0  1  1  4  5  6  6  9
//        7   5  0  1  1  4  5  7  8  9
//output: 10 {4,3}

int knapsack(int[] val, int[] wt, int capacity) {
    if (capacity == 0) return 0;

    if (val.length ==0) return 0;

    int[][] rec = new int [wt.length+1][capacity+1];

    for (int i=0; i<=val.length; i++) {
        for (int j=0; j<=capacity; j++) {
            if ( i==0 || j==0 ) {
                rec[i][j] = 0;
            } else if (j >= wt[i-1]) {
                rec[i][j] = Math.max(rec[i-1][j], // not including the ith item
                                     rec[i-1][j-wt[i-1]] + val[i-1]);  // including the ith item
            } else {
                rec[i][j] =  rec[i-1][j];
            }
        }
    }

    return rec[wt.length][capacity];
}

//egg-drop problem
//
int eggDrop(int egg, int floor) {
    if (egg == 0) return -1;
    if (floor == 0) return 0;

    int[][] rec = new int[egg+1][floor+1];

    //initilize one-egg scenario
    for (int i=0; i<=floor; i++) {
        rec[1][i] = i
    } 

    //initilize first-floor scenario
    for (int i=0; i<=egg; j) {
        rec[i][1] = 1;
    }

    for (int i=2; i<=egg; i++) {
        for (int j=2; j<=floor; j++) {
            int min = Integer.MAX_VALUE;

            for (int k=1; k<=j; k++) {
                int temp = Math.max(rec[i-1][k-1], rec[i][j-k]);
                min = Math.max(temp, min);
            }
            rec[i][j] = 1 + min;
        }
    }

    return rec[egg][floor];
}

//matrix multiplication cost
//https://github.com/mission-peace/interview/blob/master/src/com/interview/dynamic/MatrixMultiplicationCost.java
int matricMul(int[] arr) {
    int[][] temp = new int[arr.length][arr.length];

    int len = arr.length;

    //starting from length 2 to length total array length - 2
    for (int l=2; l<len-2; l++) {
        for (int i=0; i<len-l; i++) {
            j = i+l;
            temp[i][j] = Integer.MAX_VALUE;

            for (int k=i+1; k<j; k++) {
                int curr = temp[i][k] + temp[k][j] + arr[i]*arr[k]*arr[j];
                if (curr < temp[i][j]) {
                    temp[i][j] = curr;
                }
            } 
        }
    }

    return temp[0][len-1];
}



// Find the min length of the number with the highest degree
// e.g. 
//     [2 1 2 4 4 2 4] the highest degree is 3 as there are three 4's (also there are 3 twos).
//     [2 1 2 4 4 2] length is 6
//     [4 4 2 4] length is 4
//     So return 4 as the answer.

//subset sum 
//input: arr: {2,3,7,8,10} sum: 11
int subsetSum(int[] arr, int sum) {
    int len = arr.length
    boolean[][] rec = new boolean[arr+1][sum+1];

    //initialize first col to be true
    for (int i=0; i<=len; i++) rec[i][0] = true;

    //initialize first row to be false;
    for (int i=1; i<=sum; i++) rec[0][i] = false;

    for (int i=1; i<=len; i++) {
        for (int j=1; j<=sum; j++) {
            if (i>j) {
                rec[i][j] = rec[i-1][j];
            } else {
                rec[i][j] = rec[i-1][j] || rec[i-1][j-arr[i-1]];
            }
        }
    }

    return rec[arr][sum];
}

//easy: 53. Maximum Subarray 
// Find the contiguous subarray within an array (containing at least one number) which has the largest sum.
// For example, given the array [-2,1,-3,4,-1,2,1,-5,4],
// the contiguous subarray [4,-1,2,1] has the largest sum = 6. 

//web solution: dp
class Solution {
    public int maxSubArray(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];  //dp[i] means the maximum subarray ending with A[i];
        dp[0] = nums[0];
        int max = nums[0];
        
        for (int i=1; i<n; i++) {
            dp[i] = nums[i] + (dp[i-1] > 0? dp[i-1]: 0);
            max = Math.max(max, dp[i]);
        }
        
        return max;
    }
}

// web solution no dp
public static int maxSubArray(int[] A) {
    int maxSoFar=A[0], maxEndingHere=A[0];
    for (int i=1;i<A.length;++i){
        maxEndingHere= Math.max(maxEndingHere+A[i],A[i]);
        maxSoFar=Math.max(maxSoFar, maxEndingHere); 
    }
    return maxSoFar;
}

//55. Jump Game
class Solution {
    public boolean canJump(int[] nums) {
        int length = nums.length;
           
        int lastGood = length-1;
        for (int i=length-2; i>=0; i--) {
            if((i+nums[i]) >= lastGood){
                    lastGood = i;
            }
        }       
        return lastGood==0;
    }
}

//174. Dungeon Game: hard
class Solution {
    public int calculateMinimumHP(int[][] dungeon) {
        int colLen = dungeon[0].length;
        int rowLen = dungeon.length;
        int[][] table = new int[rowLen][colLen];
        
        table[rowLen-1][colLen-1] = Math.max(1 - dungeon[rowLen-1][colLen-1], 1);
        
        //initialize last row
        for (int i=colLen-2; i>=0; i--) {
            table[rowLen-1][i] = Math.max(table[rowLen-1][i+1]-dungeon[rowLen-1][i], 1);
        }
        
        //initialize last col
        for (int i=rowLen-2; i>=0; i--) {
            table[i][colLen-1] = Math.max(table[i+1][colLen-1]-dungeon[i][colLen-1], 1);
        }
        
        // run through the table
        for (int i=rowLen-2; i>=0; i--) {
            for (int j=colLen-2; j>=0; j--) {
                int right = Math.max(table[i][j+1]-dungeon[i][j], 1);
                int down = Math.max(table[i+1][j]-dungeon[i][j], 1);
                table[i][j] = Math.min(right, down);
            }
        }
              
        return table[0][0];
    }
}

//416. Partition Equal Subset Sum: medium
//space complexity efficient

class Solution {
    public boolean canPartition(int[] nums) {
        int sum = 0;
        
        //get sum of the array
        for (int n: nums) {
            sum += n;
        }
        
        //if sum is not even, no need to continue the rest
        if (sum % 2 > 0) return false;
        sum /=2;
        
        //record array
        boolean[] dp = new boolean[sum+1];
        
        //initialize dp
        dp[0] = true;       
        for (int i=1; i<=sum; i++) {
            dp[i] = false;
        }
        
        //iterate through nums
        for (int n: nums) {
            for (int j=sum; j>=0; j--) {
                if (j >= n) {
                    dp[j] = dp[j] || dp[j-n];
                }
            }
        }
        
        return dp[sum];
    }
}

//494. Target Sum: medium
//sum(Positive) = (target + sum(nums)) / 2
class Solution {
    public int findTargetSumWays(int[] nums, int S) {
        //get sum of the array
        int sum = 0;
        for (int n: nums) {
            sum += n;
        }
        
        return ((sum+S) % 2 > 0 || sum < S) ? 0 : targetSum(nums, (sum+S)/2); 
    }
    
    public int targetSum(int[] nums, int sum) {  
        System.out.println(sum);
        //record array
        int[] dp = new int[sum+1];
        
        //initialize dp
        dp[0] = 1;       
        
        //iterate through nums
        for (int n: nums) {
            for (int j=sum; j>=n; j--) {
                dp[j] += dp[j-n];
            }
        }
        
        return dp[sum];
    }
}

//198. House Robber: easy
class Solution {
    public int rob(int[] houses) {
        int r = 0;
        int nr = 0;
        int preR = r;
        for (int n: houses) {
            preR = r;
            r = nr + n;
            nr = Math.max(preR, nr);
        }
        return Math.max(r, nr);
    }
}

//213. House Robber II: medium
class Solution {
    public int rob(int[] houses) {
        if (houses.length == 0) return 0;
        if (houses.length == 1) return houses[0];
        return Math.max(rob(houses, 1, houses.length-1), rob(houses, 0, houses.length-2));   
    }
    
    public int rob(int[] houses, int low, int hi) {
        int include = 0, exclude = 0;
        
        for (int h=low; h<=hi; h++) {
            int i = include, e = exclude;
            include = exclude + houses[h];
            exclude = Math.max(exclude, i);
        }
        
        return Math.max(include, exclude);
    }
}

//337. House Robber III: medium
class Solution {
    public int rob(TreeNode root) {
        int[] res = robHelper(root);      
        return Math.max(res[0], res[1]);
    }
    
    public int[] robHelper(TreeNode root) {
        int[] res = new int[2];
        if (root == null) {
            return res;
        }  
        
        //include current node
        int[] left = robHelper(root.left);
        int[] right = robHelper(root.right);
        res[0] = root.val + left[1] + right[1];
        //not include current node
        res[1] = Math.max(left[0],left[1]) + Math.max(right[0],right[1]);
        
        return res;
    }
}

//139. Word Break： dynamic programming
//s = "leetcode", wordDict = ["leet", "code"]
//dp = [t f f f f f f f f f]
public boolean wordBreak(String s, List<String> wordDict) {      
        boolean[] dp = new boolean[s.length()+1];    
        dp[0] = true;
        
        for (int i=1; i<=s.length(); i++ ){
            for (int j=0; j<wordDict.size(); j++) {
                int wordLen = wordDict.get(j).length();
                if (wordLen <= i) {
                    if (dp[i-wordLen] && s.substring(i-wordLen, i).equals(wordDict.get(j))) {
                        dp[i] = true;
                        break;
                    }
                }      
            }
        }
        
        return dp[s.length()];
    }
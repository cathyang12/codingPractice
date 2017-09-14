

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
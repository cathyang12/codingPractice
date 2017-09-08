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
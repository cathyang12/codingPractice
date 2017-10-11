//258. Add Digits: easy
//The problem, widely known as digit root problem, has a congruence formula:
class Solution {
    public int addDigits(int num) {
       // method 1: return num==0?0:(num%9==0?9:(num%9));
       // method 2: return 1 + (num - 1) % 9;
    }
}


//268. Missing Number: easy
//XOR
class Solution {
    public int missingNumber(int[] nums) {
        int xor = 0, i = 0;
        for (i = 0; i < nums.length; i++) {
            xor = xor ^ i ^ nums[i];
        }

        return xor ^ i;
    }
}

//Sum

public int missingNumber(int[] nums) { //sum
    int len = nums.length;
    int sum = (0+len)*(len+1)/2;
    for(int i=0; i<len; i++)
        sum-=nums[i];
    return sum;
}
//200. Number of Islands： medium
//Given a 2d grid map of '1's (land) and '0's (water), count the number of islands. An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.


class Solution {
    public int numIslands(char[][] grid) {
        int count = 0;
        
        for (int i=0; i<grid.length; i++) {
            for (int j=0; j<grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    count++;
                    searchOtherLand(grid, i, j);
                }
            }
        }
        
        return count;
    }
    
    public void searchOtherLand(char[][] grid, int row, int col) {
        if (row >= grid.length || col >= grid[0].length || row < 0 || col < 0) return;
        
        if (grid[row][col] == '1') {
            grid[row][col] = '0';
            searchOtherLand(grid, row, col+1);
            searchOtherLand(grid, row, col-1);
            searchOtherLand(grid, row+1, col);
            searchOtherLand(grid, row-1, col);
        }
       
    }
}

//subset of the combination

public void combination(char[] input) {
    Map<Character, Integer> map = new TreeMap<>();

    for (char i: input) {
        map.compute(i, (key,val) -> {
            if (val == null) {
                return 1;
            } else {
                return val + 1;
            }
        });
    }

    int idx = 0;
    char[] charArr = new char[map.size()];
    int[] count = new int[map.size()];

    for (Map.Entry<Character, Integer> entry: map.entrySet()){
        charArr[i] = entry.getKey();
        count[i] = entry.getValue();
        i++;
    }

    char[] output = new char[input.length];
    combination(charArr, count, output, 0, 0);
}

public void combination(char[] input, int[] count, char[] output, int pos, int len) {
    print(output, lenfor (int i=0; i<input.length; i++) {
        if(count[i] == 0){
            continue;
        }

        count[i]--;
        combination(input, count, output, pos+1);
    }
}


//78 Subset: medium
class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums == null || nums.length == 0) return res;
        Arrays.sort(nums);
        subsets(nums, 0, res, new ArrayList<>());       
        return res;
    }
    
    public void subsets(int[] nums, int start, List<List<Integer>> res, List<Integer> currList) {
        res.add(new ArrayList<Integer>(currList));

        for (int i=start; i<nums.length; i++) {
            // subset with duplicate: add the following line
            //if (i> start && nums[i] == nums[i-1]) continue;
            currList.add(nums[i]);
            subsets(nums, i+1, res, currList);
            currList.remove(currList.size()-1);
        }
    }

}

//46. Permutations
//Given a collection of distinct numbers, return all possible permutations.
//method 1
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

//46. Permutations: medium
//method 2
class Solution {
    public List<List<Integer>> res = new ArrayList<>();
    
    public List<List<Integer>> permute(int[] nums) {       
        backtracking(nums, new ArrayList<>());    
        return res;
    }
    
    public void backtracking(int[] nums, List<Integer> currList){
        if (currList.size() == nums.length) {
            res.add(new ArrayList<Integer>(currList));
        } else {
            for (int i=0; i<nums.length; i++) {
                if (currList.contains(nums[i])) continue;
                currList.add(nums[i]);
                backtracking(nums, currList);
                currList.remove(currList.size()-1);
            } 
        }
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

//39. Combination Sum: medium
//Given a set of candidate numbers (C) (without duplicates) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.
class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(candidates);
        combinationSum(candidates, target, res, new ArrayList<>(), 0);       
        return res;
    }
    
    public void combinationSum(int[] candidates, int target, List<List<Integer>> res, List<Integer> tempList, int start) {
        if (target < 0) {
            return;
        } else if (target == 0) {
            res.add(new ArrayList<>(tempList));
        } else {
            for (int i=start; i<candidates.length; i++) {
                tempList.add(candidates[i]);
                combinationSum(candidates, target-candidates[i], res, tempList, i);
                tempList.remove(tempList.size()-1);
            }
        }
        
    }
}

//40. Combination Sum II: medium
//Can't reuse element and no duplicated combination
class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
         List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(candidates);
        combinationSum(candidates, target, res, new ArrayList<>(), 0);       
        return res;
    }
    
    public void combinationSum(int[] candidates, int target, List<List<Integer>> res, List<Integer> tempList, int start) {
        if (target < 0) {
            return;
        } else if (target == 0) {
            res.add(new ArrayList<>(tempList));
        } else {
            for (int i=start; i<candidates.length; i++) {
                if (i>start && candidates[i] == candidates[i-1]) continue; //skip
                tempList.add(candidates[i]);
                combinationSum(candidates, target-candidates[i], res, tempList, i+1);
                tempList.remove(tempList.size()-1);
            }
        }
    }
}
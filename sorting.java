
//
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
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
import java.util.*;

public class PermutaionList {
    Hashtable<String, ArrayList<ArrayList<Integer>>> resultSet;

    public PermutaionList() {
        this.resultSet = new Hashtable<>();
    }

    // find combinations
    public ArrayList<ArrayList<Integer>> allCombinations(int sum, int length ) {

        //retrieve from result if it has been stored before
        String currCombo = "s" + sum + "l" + length;
        if (this.resultSet.containsKey(currCombo)) {
            return this.resultSet.get(currCombo);
        }

        // base case
        if (sum == 1) {
            ArrayList<ArrayList<Integer>> sumOne = new ArrayList<>();
            ArrayList<Integer> curr = new ArrayList<>();
            curr.add(1);
            fillZeros(curr, length-1);
            sumOne.add(curr);
            return sumOne;
        }

        // base case
        if (length == 1) {
            ArrayList<ArrayList<Integer>> oneList = new ArrayList<>();
            ArrayList<Integer> curr = new ArrayList<>();
            curr.add(sum);
            oneList.add(curr);
            return oneList;
        }

        // base case
        if (length == 0) {
            ArrayList<ArrayList<Integer>> zeroList = new ArrayList<>();
            ArrayList<Integer> curr = new ArrayList<>();
            zeroList.add(curr);
            return zeroList;
        }

        //recursive case
        ArrayList<ArrayList<Integer>> currSet = new ArrayList<>();
        for (int i=sum; i>0; i--) {
            ArrayList<Integer> curr = new ArrayList<>();
            ArrayList<ArrayList<Integer>> childrenResult;

            curr.add(i);
            ArrayList<Integer> currCopy1 = new ArrayList<>(curr);

             // [8 0 0 ...]
            fillZeros(currCopy1, length-1);

            int leftover = sum - i;

            // recursive case
            if (leftover != 0) {
                childrenResult= allCombinations(leftover, length-1);

                // append children result to parent
                for (ArrayList<Integer> eachChild : childrenResult) {
                    ArrayList<Integer> currCopy = new ArrayList<>(curr);
                    currCopy.addAll(eachChild);
                    currSet.add(currCopy);
                }
            }

            if (currCopy1.get(0)==sum) {
                currSet.add(currCopy1);
            }
        }

        this.resultSet.put(currCombo, currSet);
        return currSet;
    }

    public static void fillZeros(ArrayList<Integer> list, int length) {
        for (int i=0; i<length; i++) {
            list.add(0);
        }
    }

    //find permutation and return the hashset
    public ArrayList<ArrayList<Integer>> findPermutaion(ArrayList<ArrayList<Integer>> input) {
        return new ArrayList<ArrayList<Integer>>();
    }

    public static void main(String[] args) {
        PermutaionList rawList = new PermutaionList();
        ArrayList<ArrayList<Integer>> rawResult=rawList.allCombinations(8,4);
        // findPermutaion(rawResult);
    }
}


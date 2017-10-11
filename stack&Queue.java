//17. Letter Combinations of a Phone Number: medium
class Solution {
    public List<String> letterCombinations(String digits) {
        LinkedList<String> res = new LinkedList<String>();
        if (digits.length() == 0 || digits == null) return res;
        
        String[] mapping = new String[] {"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        res.add("");
        
        for (int i=0; i<digits.length(); i++) {
            int x = Character.getNumericValue(digits.charAt(i));
            
            while (res.peek().length() == i) {
                String base = res.remove();
                for (char c: mapping[x].toCharArray()) {
                    res.add(base+c);
                }
            }
        }
        
        return res;
    }
}
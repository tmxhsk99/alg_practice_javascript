package programmers.level1;

public class Replace_P_And_Y {

    public static void main(String[] args) {

        Solution solution = new Solution();
        boolean pPoooyY = solution.solution("pPoooyY");
        System.out.println("pPoooyY = " + pPoooyY);
        boolean pyy = solution.solution("pyy");
        System.out.println("pyy = " + pyy);

    }

    private static class Solution {
        public boolean solution(String input) {
            if(input == null){
                return false;
            }
            String lowerInput = input.toLowerCase();
            if (lowerInput.contains("p") && lowerInput.contains("y")) {
                int pCount = checkCharCounter(lowerInput, 'p');
                int yCount = checkCharCounter(lowerInput, 'y');
                if (pCount == yCount) {
                    return true;
                }
            }
            if(!lowerInput.contains("p") && !lowerInput.contains("y")){
                return true;
            }
            return false;
        }

        private int checkCharCounter(String origin, char findChar) {
            char[] charArray = origin.toCharArray();
            int counter = 0;
            for (char c : charArray) {
                if (c == findChar) {
                    counter++;
                }
            }
            return counter;
        }
    }
}

package programmers.level1;

public class ReverseArr {
    public static void main(String[] args) {
        int input = 12345;
        Solution solution = new Solution();
        solution.solution(input);
    }

    private static class Solution {
        public int[] solution(long n) {
            int[] result = null;
            char[] charArr = String.valueOf(n).toCharArray();
            int[] reverse = reverse(charArr);

            return reverse;
        }

        private int[] reverse(char[] charArr) {
            int[] result = new int[charArr.length];
            int resultIdx = 0;
            for (int i = charArr.length-1; i >= 0; i--) {
                result[resultIdx] = Integer.valueOf(String.valueOf(charArr[i]));
                resultIdx++;
            }
            return result;
        }
    }
}



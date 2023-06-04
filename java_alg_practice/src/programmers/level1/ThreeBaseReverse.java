package programmers.level1;

/**
 * https://school.programmers.co.kr/learn/courses/30/lessons/68935
 */
public class ThreeBaseReverse {
    public static void main(String[] args) {
        Solution solution = new Solution();
        /*int result1 = solution.solution(45);
        System.out.println(result1);*/
        int result2 = solution.solution(125);
        System.out.println(result2);

    }

    private static class Solution {
        public int solution(int n) {
            int result = 0;
            //1. 10진법을 3 진법으로 바꾼다.
            String threeBaseString = Integer.toString(n, 3);
            //2. 앞뒤 반전을 한다.
            String reverse = resverString(threeBaseString);
            //3. 3진법을 10진법으로 표현한다.
            result = ThreeBaseToTenBase(reverse);
            return result;
        }

        private String resverString(String str) {
            StringBuilder sb = new StringBuilder();
            char[] charArray = str.toCharArray();
            for (int i = charArray.length - 1; i >= 0; i--) {
                sb.append(charArray[i]);
            }
            return sb.toString();
        }

        private int ThreeBaseToTenBase(String strTreeBase) {
            int result = 0;
            char[] charArray = strTreeBase.toCharArray();
            int idx = 0;
            for (int i = charArray.length - 1; i >= 0; i--) {
                int n = Integer.parseInt(String.valueOf(charArray[i]));
                int base = idx == 0 ? 1 : (int) Math.pow(3, idx);
                int parsedInt = n * base;
                idx++;
                result += parsedInt;
            }
            return result;
        }
    }
}

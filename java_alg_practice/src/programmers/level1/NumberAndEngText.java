package programmers.level1;

import java.util.HashMap;
import java.util.Map;

public class NumberAndEngText {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int one4sevenneight = solution.solution("one4seveneight");
        System.out.println("one4seveneight = " + one4sevenneight);

        int solution1 = solution.solution("23four5six7");
        System.out.println("23four5six7 = " + solution1);

        int solution2 = solution.solution("2three45sixseven");
        System.out.println("2three45sixseven = " + solution2);

        int solution3 = solution.solution("123");
        System.out.println("123 = " + solution3);

    }

    private static class Solution {
        HashMap<String, NumberAlapabat> ApabatNumberMap = new HashMap<String, NumberAlapabat>() {{
            put("ze", new NumberAlapabat("zero", 0, "0"));
            put("on", new NumberAlapabat("one", 1, "1"));
            put("tw", new NumberAlapabat("two", 2, "2"));
            put("th", new NumberAlapabat("three", 3, "3"));
            put("fo", new NumberAlapabat("four", 4, "4"));
            put("fi", new NumberAlapabat("five", 5, "5"));
            put("si", new NumberAlapabat("six", 6, "6"));
            put("se", new NumberAlapabat("seven", 7, "7"));
            put("ei", new NumberAlapabat("eight", 8, "8"));
            put("ni", new NumberAlapabat("nine", 9, "9"));
        }};

        public int solution(String s) {
            String result = alpabatToNumber(s);
            return Integer.valueOf(result);
        }

        private String alpabatToNumber(String s) {
            char[] charArray = s.toCharArray();
            StringBuilder sb = new StringBuilder("");
            int checkIdx = 0;
            while (true) {
                Character c = charArray[checkIdx];
                if (Character.isLetter(c)) {// 알파뱃인 경우
                    String findKeyStr = s.substring(checkIdx, checkIdx + 2);
                    //System.out.println("findKeyStr = " + findKeyStr);
                    NumberAlapabat numberAlapabat = ApabatNumberMap.get(findKeyStr);
                    checkIdx += numberAlapabat.getLen();
                    sb.append(numberAlapabat.getNumberString());
                } else {// 숫자인경우
                    sb.append(c.toString());
                    checkIdx++;
                }

                if (checkIdx >= s.length()) {
                    break;
                }
            }

            return sb.toString();
        }

        private class NumberAlapabat {
            private String enName;
            private int len;
            private int number;
            private String numberString;

            public NumberAlapabat(String enName, int number, String numberString) {
                this.enName = enName;
                this.len = enName.length();
                this.number = number;
                this.numberString = numberString;
            }

            public String getEnName() {
                return enName;
            }

            public int getLen() {
                return len;
            }

            public int getNumber() {
                return number;
            }

            public String getNumberString() {
                return numberString;
            }
        }
    }

}

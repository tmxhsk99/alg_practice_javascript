package programmers.level1;

import java.util.stream.Stream;

public class MakeStrangeCharacters {
    public static void main(String[] args) {
        String testString = "try hello world";

        String result = new Solution().solution(testString);

        if (result.equals("TrY HeLlO WoRlD")) {
            System.out.println("테스트 성공");
        } else {
            System.out.println("테스트 실패");
        }
    }

    private static class Solution {
        public String solution(String input) {
            String result = null;
            char[] charArray = input.toCharArray();
            StringBuilder sb = new StringBuilder();
            boolean toUpper = true;
            for (int i = 0; i < charArray.length; i++) {
                String s = String.valueOf(charArray[i]);
                if (s.equals(" ")) {
                    sb.append(s);
                    toUpper = true;
                } else {
                    if (toUpper) {
                        sb.append(s.toUpperCase());
                    } else {
                        sb.append(s.toLowerCase());
                    }
                    toUpper = !toUpper;
                }

            }
            return sb.toString();
        }
    }
}

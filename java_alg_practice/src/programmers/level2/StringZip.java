package programmers.level2;

import java.util.ArrayList;
import java.util.List;

public class StringZip {
    /**
     * https://school.programmers.co.kr/learn/courses/30/lessons/60057
     * <p>
     * "aabbaccc"	7
     * "ababcdcdababcdcd"	9
     * "abcabcdede"	8
     * "abcabcabcabcdededededede"	14
     * "xababcdcdababcdcd"	17
     *
     * @param args
     */
    public static void main(String[] args) {
        String[] testInput = {
                "aabbaccc",
                "ababcdcdababcdcd",
                "abcabcdede",
                "abcabcabcabcdededededede",
                "xababcdcdababcdcd"
        };
        Solution solution = new Solution();
        for (int i = 0; i < testInput.length; i++) {
            int resultLength = solution.solution(testInput[i]);
            System.out.println(testInput[i] + " : " + resultLength);
        }
    }

    private static class Solution {
        public int solution(String input) {
            int min = Integer.MAX_VALUE;
            for(int len = 1; len <=input.length(); len ++){
                // 문자열 압축 후 가장 짧은 길이 선택
                int compressed = compress(input, len);
                if(compressed < min){
                    min = compressed;
                }
            }
            return min;
        }

        private int compress(String source, int length) {
            // 압축한 문자열의 길이를 반환
            StringBuilder sb = new StringBuilder();
            String last = "";
            int count = 0;
            for(String token : split(source, length)){
                //압축 문자열 구성
                if(token.equals(last)){
                    count++;
                }else{
                    // 새로운 토큰 등장처리
                    if(count > 1){ //카운트가 1번이상 중복된경우 숫자를 붙힌다.
                        sb.append(count);
                    }
                    sb.append(last); //중복된 마지막 글자를 붙힌다.
                    last = token;
                    count = 1;
                }
            }
            // 마지막 글자는 안붙혀지므로 for문을 나온후에 마지막 글자를 처리한다.
            if(count > 1){ //카운트가 1번이상 중복된경우 숫자를 붙힌다.
                sb.append(count);
            }
            sb.append(last);
            return sb.length();
        }

        private List<String> split(String source, int length) {
            List<String> tokens = new ArrayList<>();
            // source를 length만큼 잘라 tokens 리스트에 추가
            for(int startIdx =0; startIdx < source.length(); startIdx += length){
                int endIdx = startIdx + length;
                
                if(endIdx > source.length()){// 글자 최대 값 벗어나지 않도록
                    endIdx = source.length();
                }
                // 문자열을 startIdx 부터 endIdx까지 잘라 tokens 리스트에 추가
                tokens.add(source.substring(startIdx, endIdx));
            }
            return tokens;
        }
    }
}

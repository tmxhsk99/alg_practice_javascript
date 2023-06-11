package programmers.level2;

public class BinaryParse {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] result1 = solution.solution("11001010001");
        printArray(result1);
        int[] result2 = solution.solution("01110");
        printArray(result2);
        int[] result3 = solution.solution("1111111");
        printArray(result3);

    }

    public static void printArray(int[] input) {
        System.out.printf("변환횟수 : %d ,제거한 0개수 : %d\n ", input[0], input[1]);
    }

    private static class Solution {
        public int[] solution(String input) {
            int[] result = new int[2];
            String binaryString = "";
            int roofCount = 0;
            int removedZeroCount = 0;
            String removeZero = "";
            String initData = input;
            while (true) {
                roofCount++;
                //1. 해당 값의 0을 제거한다.
                removeZero = initData.replaceAll("0", "");
                removedZeroCount += initData.length() - removeZero.length();
                //2. 해당값의 길이를 구한다.
                int removeZeroLength = removeZero.length();
                //3. 바이너리로 변환한다.
                binaryString = Integer.toBinaryString(removeZeroLength);
                //4. 값이 1인 경우 현재 진행한 변화 횟수와 0의 개수를 반환한다. 아닌경우 다시 처음으로
                if (binaryString.equals("1")) {
                    result[0] = roofCount;
                    result[1] = removedZeroCount;
                    return result;
                } else {
                    initData = binaryString;
                }
            }
        }
    }
}

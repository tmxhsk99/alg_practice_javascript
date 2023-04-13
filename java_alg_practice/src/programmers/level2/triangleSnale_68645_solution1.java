package programmers.level2;

import java.util.Arrays;

public class triangleSnale_68645_solution1 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] result = solution.solution(6);
        System.out.println("메인 결과 값");
        System.out.println("result = " + Arrays.toString(result));

    }

    public static class Solution {
        /**
         * 솔루션
         *
         * @param n
         * @return
         */
        public int[] solution(int n) {
            int[][] triangle = new int[n][n];
            int v = 1;
            int x = 0;
            int y = 0;
            while (true) {
                //아래로 이동
                while (true) {
                    triangle[y][x] = v++;
                    if (y + 1 == n || triangle[y + 1][x] != 0) {
                        break;
                    }
                    y += 1;
                }
                // 오른쪽으로 이동할지 안할지 결정
                if (x + 1 == n || triangle[y][x + 1] != 0) {
                    break;
                }
                x += 1;
                // 오른쪽으로 이동
                while (true) {
                    triangle[y][x] = v++;
                    if (x + 1 == n || triangle[y][x + 1] != 0) {
                        break;
                    }
                    x += 1;
                }
                // 위로 이동할지 안할지 결정
                if (triangle[y - 1][x - 1] != 0) {
                    break;
                }
                x -= 1;
                y -= 1;

                //왼쪽 위로 이동
                while (true) {
                    triangle[y][x] = v++;
                    if (triangle[y - 1][x - 1] != 0) {
                        break;
                    }
                    x -= 1;
                    y -= 1;
                }
                if (y + 1 == n || triangle[y + 1][x] != 0) {
                    break;
                }
                y += 1;
            }

            int[] result = new int[v - 1];
            int index = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j <= i; j++) {
                    result[index++] = triangle[i][j];
                }
            }
            return result;
        }

    }
}
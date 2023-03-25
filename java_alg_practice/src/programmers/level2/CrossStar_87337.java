package programmers.level2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CrossStar_87337 {
    /**
     * 직선 A, B, C에 대한 정보가 담긴 배열 line이 매개변수로 주어집니다. 이때 모든 별을 포함하는 최소 사각형을 return 하도록 solution 함수를 완성해주세요.
     * <p>
     * 제한사항
     * line의 세로(행) 길이는 2 이상 1,000 이하인 자연수입니다.
     * line의 가로(열) 길이는 3입니다.
     * line의 각 원소는 [A, B, C] 형태입니다.
     * A, B, C는 -100,000 이상 100,000 이하인 정수입니다.
     * 무수히 많은 교점이 생기는 직선 쌍은 주어지지 않습니다.
     * A = 0이면서 B = 0인 경우는 주어지지 않습니다.
     * 정답은 1,000 * 1,000 크기 이내에서 표현됩니다.
     * 별이 한 개 이상 그려지는 입력만 주어집니다.
     * 입출력 예
     * line	result
     * [[2, -1, 4], [-2, -1, 4], [0, -1, 1], [5, -8, -12], [5, 8, 12]]	["....*....", ".........", ".........", "*.......*", ".........", ".........", ".........", ".........", "*.......*"]
     * [[0, 1, -1], [1, 0, -1], [1, 0, 1]]	["*.*"]
     * [[1, -1, 0], [2, -1, 0]]	["*"]
     * [[1, -1, 0], [2, -1, 0], [4, -1, 0]]	["*"]
     */
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] ans1 = {{2, -1, 4}, {-2, -1, 4}, {0, -1, 1}, {5, -8, -12}, {5, 8, 12}};
        int[][] ans2 = {{0, 1, -1}, {1, 0, -1}, {1, 0, 1}};
        String[] solution1 = solution.solution(ans1);
        for (String s : solution1) {
            System.out.println("solution1 " + s);
        }
        String[] solution2 = solution.solution(ans2);
        for (String s : solution2) {
            System.out.println("solution2 " + s);
        }

    }

    public static class Solution {
        public String[] solution(int[][] line) {
            /**
             * 문제 풀이 흐름
             * 1. 모든 직선 쌍에 대해 반복
             *  A. 교점 좌표 구하기
             *  B. 정수 좌표 구하기
             * 2. 저장된 정수들에 대해 x,y 좌표의 최댓값, 최솟값 구하기
             * 3. 구한 최댓값, 최솟값을 이용하여 2차원 배열 크기 결정
             * 4. 2차원 배열에 별 표시
             * 5. 문자열 배열로 변환 후 반환
             */
            List<Point> points = new ArrayList<>();
            //1. 모든 직선쌍에 대해 반복 (들어온 직선의 계수값을 전부처리한다.)
            for (int i = 0; i < line.length; i++) {
                for (int j = 0; j < line.length; j++) {
                    //line[i][j]를 이용하여 1-A,1-B 수행
                    // 두직선의 교점구하기
                    Point intersection = intersection(line[i][0], line[i][1], line[i][2],
                            line[j][0], line[j][1], line[j][2]);

                    if (intersection != null) {
                        points.add(intersection);
                    }
                }
            }

            Point minimum = getMinimumPoint(points);
            Point maximum = getMaximumPoint(points);

            //길이를 구한다.
            int width = (int) (maximum.x - minimum.x + 1);
            int height = (int) (maximum.y - minimum.y + 1);
            // 2차원 배열 초기화
            char[][] arr = new char[height][width];
            for (char[] row : arr) {
                Arrays.fill(row, '.');
            }

            // 2차원 배열에 별 찍기
            // (0,0) 은 실제 교점의 (0,0)이 아님
            // 좌표변환이 필요하다.
            // 2차원 배열의 좌표는 일반좌표비교했을 때 y축이 반대이고 min,max로 크기가 정해짐
            for (Point p : points) {
                int x = (int) (p.x - minimum.x);
                int y = (int) (maximum.y - p.y);
                arr[y][x] = '*';
            }
            // 문자열 배열로 변환후 반환
            // String 배열로 반환하여야한다.
            String[] result = new String[arr.length];
            for (int i = 0; i < result.length; i++) {
                result[i] = new String(arr[i]);
            }
            return result;
        }

        /**
         * 좌표를 표현하기 위한 좌표 클래스
         */
        private class Point {
            /**
             * 문제조건중 1000*1000 크기 이내에서 표현된다는 조건은 별들 사이의 거리가 멀지 않다는 조건일뿐
             * 실제 좌표값이 작다는 의미가 아니므로 long으로 좌표를 다뤄야 오버플로가 발생하지 않는다.
             */
            public final long x, y;

            public Point(long x, long y) {
                this.x = x;
                this.y = y;
            }
        }

        /**
         * 두 직선 방정식의 계수 및 상수값을 매개 변수로 받아 상수인 교점을 구한다.
         *
         * @param a1
         * @param b1
         * @param c1
         * @param a2
         * @param b2
         * @param c2
         * @return 두직선 교점 좌표 객체 Point 좌표가 정수가 아닌 경우 null 반환
         */
        private Point intersection(long a1, long b1, long c1, long a2, long b2, long c2) {
            // A1x + B1y + C1 = 0
            // A2x + B2y + C2 = 0
            // 크래머의 법칙에 따라 교점을 구하면 다음과 같다
            // 주어진 계수행렬의 행렬식 D를 구한다.
            // D = |A1, B1| |A2, B2|
            // D = (A1 * B2) - (A2 * B1)
            // x와 y에 대한 행렬식 Dx 와 Dy를 구한다
            // Dx = | -C1, B1 || -C2, B2 |
            // Dx = (-C1 * B2) - (-C2 * B1);
            // Dy = | A1, -C1 || A2, C2 |
            // Dy = (A1 * -C2) - (A2 * -C1)
            // x = Dx / D
            // y = Dy / D
            // x = (-C1 * B2) - (-C2 * B1) / (A1 * B2) - (A2 * B1)
            // x = (-B2 * C1) + (B1 * C2) / (A1 * B2) - (A2 * B1)
            // x = (B1 *C2) - (B2 * C1) / (A1 * B2) - (A2 * B1)
            // x = (B1 * C2 - B2 * C1) / (A1 * B2 - A2 * B1)
            // y = (A1 * -C2) - (A2 * -C1) / (A1 * B2) - (A2 * B1)
            // y = (-A1 * C2) + (A2 * C1) / (A1 * B2) - (A2 * B1)
            // y = (A2 * C1) - (A1 * C2) / (A1 * B2) - (A2 * B1)

            double x = (double) (b1 * c2 - b2 * c1) / (a1 * b2 - a2 * b1);
            double y = (double) (a2 * c1 - a1 * c2) / (a1 * b2 - a2 * b1);

            if (x % 1 != 0 || y % 1 != 0) {// 정수가 아닌경우 null 리턴
                return null;
            }

            return new Point((long) x, (long) y);
        }

        /**
         * 저장된 정수들에 대해 x,y 좌표의 최대값 최솟값구하기
         * 교점중의 x의 최대값,최소값 , y의 최대값,최소값을 구해야한다.
         */

        /**
         * 최소 좌표값
         *
         * @param points
         * @return
         */
        private Point getMinimumPoint(List<Point> points) {
            long x = Long.MAX_VALUE;
            long y = Long.MAX_VALUE;
            for (Point p : points) {
                if (p.x < x) x = p.x;
                if (p.y < y) y = p.y;
            }
            return new Point(x, y);
        }

        /**
         * 최대 좌표값
         *
         * @param points
         * @return
         */
        private Point getMaximumPoint(List<Point> points) {
            long x = Long.MIN_VALUE;
            long y = Long.MIN_VALUE;
            for (Point p : points) {
                if (p.x > x) x = p.x;
                if (p.y > y) y = p.y;
            }
            return new Point(x, y);
        }

    }
}

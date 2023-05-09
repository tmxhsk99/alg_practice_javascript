package programmers.level2;

public class procession_Multiply {
    public static void main(String[] args) {
        int[][] arr3 = {
                {1, 4},
                {3, 2},
                {4, 1}
        };

        int[][] arr4 = {
                {3, 3},
                {3, 3}
        };

        int[][] arr1 = {
                {2, 3, 2},
                {4, 2, 4},
                {3, 1, 4}
        };
        int[][] arr2 = {
                {5, 4, 3},
                {2, 4, 1},
                {3, 1, 1}
        };

        Solution solution = new Solution();
        solution.solution(arr3, arr4);
    }


    /**
     * 2차원 행렬 arr1, arr2 를 곱한 결과를 반환하는 함수, soution을완성해라
     */
    private static class Solution {
        public int[][] solution(int[][] arr1, int[][] arr2) {
            // 1. 처음 배열 크기를 계산해서 할당한다.
            // 1-1 결과 배열의 행의 길이를 구한다. 결과배열의 행은 첫번째 배열의 인자배열 내부의 길이이다.
            int rowLen = arr1.length;
            // 1-2 결과 배열의 열의 길이를 구한다. 결과배열의 열은 두번째 배열의 열, 즉 두번째 배열의 길이 이다.
            int colLen = arr2[0].length;

            int[][] result = new int[rowLen][colLen];
            // 2. 그다음 행렬을 곱하고 계산한값을 배열에 할당한다
            for (int i = 0; i < rowLen; i++) {
                for (int j = 0; j < colLen; j++) {
                    result[i][j] = calcMultiply(arr1, arr2, i, j);
                }
            }
            printArr(result);
            // 3. 반환한다.
            return result;
        }

        private int calcMultiply(int[][] arr1, int[][] arr2, int row, int col) {
            // 필요한것 결과 좌표 x, y좌표
            // 0,0 에들어가는 것은 arr1[0][0] * arr2[0][0] + arr1[0][1] * arr[1][0] ....
            // 2,1 에들어가는 것은 arr1[]
            // arr1은 행이 고정이므로 row값은 고정이고  col 열은 증가함 
            // arr2는 열이 고정이므로 col값을 고정잏고 row 행이 증가함
            int result = 0;
            int[] rows = arr1[row];
            for (int i = 0 ; i < rows.length ; i++) {
                    System.out.println(rows[i] +"*"+ arr2[i][col]);
                    result += rows[i] * arr2[i][col];
                System.out.println();
            }
            System.out.println();
            return result;
        }

        private void printArr(int[][] result) {
            for (int[] ints : result) {
                for (int anInt : ints) {
                    System.out.print(anInt + " ");
                }
                System.out.println();
            }
        }
    }
}

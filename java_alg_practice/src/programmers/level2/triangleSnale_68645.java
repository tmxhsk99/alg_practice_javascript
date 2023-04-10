package programmers.level2;


import java.util.Arrays;

public class triangleSnale_68645 {

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] result = solution.solution(6);
        System.out.println("메인 결과 값");
        System.out.println("result = " + Arrays.toString(result));

    }

    /**
     * 정수 n이  매개변수로 주워진다. 밑변의 길와 높이가 n인 삼각형에서 맨위 꼭지점부터 반시계방향
     * 달팽이 채우기를 진행후 첳행부터 마지막행까지 모두 순서대로  합친 새로운 배열릉 리턴하도록
     * 함수를 완성하라
     */

    static class Solution {
        int xPointerIdx = 1; // x 작업할 인덱스
        int yPointerIdx = 1; // y 작업할 인덱스
        int lastNumber = 0; // 마지막으로 넣은 값
        int MAXCOUNT = 0; // 최대값
        public int[] solution(int n) {
            //2로 해보자
            // 일단 빈배열을 만든다.(2차원배열)
            // 빈배열의 첫번째 항에 값을 넣는다(아래)
            // 다음 배열의 해당 인덱스의 값이 있는경우 해당배열 마지막 배열을 끝까지 채운다.
            // 해당 배열을 다채운경우 해당 인덱스의 다른 배열의 마지막 항을 다 채운다
            // 다채우면 자신의 index인 항을 배열을 돌면서 채운다.
            // 다체우면(다음 배열의 해당 인덱스의 값이 있는경우) 해당 배열의 마지막까지채운다.

            //1. 빈배열을 만든다.
            Object[] row = new Object[n];
            MAXCOUNT = 0;
            for (int i = 0; i < n; i++) {
                row[i] = new int[i + 1];
                MAXCOUNT += i + 1;
            }

            //2. 값을 넣는다.
            Object[] result = insertTriangle(row, MAXCOUNT);

            //3. 해당 배열값들을 합친다.
            int[] combineArr = combineArray(result);
            return combineArr;
        }

        public void printTri(Object[] tri) {
            for (int i = 0; i < tri.length; i++) {
            }
        }

        /**
         * 해당 배열을 합친다.
         *
         * @param tri
         * @return
         */
        public int[] combineArray(Object[] tri) {
            int[] returnArr = new int[MAXCOUNT];
            int idx = 0;
            for (int i = 0 ; i< tri.length ; i ++ ){
                int[] temp = (int[]) tri[i];
                for(int j = 0 ; j < temp.length ; j++){
                    returnArr[idx]= temp[j];
                    idx++;
                }
            }

            return returnArr;
        }

        public Object[] insertTriangle(Object[] tri, int MAXCOUNT) {
            lastNumber = 1; // 배열 내부에 삽입되는 숫자
            Object[] resultTri;
            while (true) {
                // 아래로 이동
                resultTri = downInsert(tri);
                printTri(resultTri);

                if(lastNumber >= MAXCOUNT){
                    break;
                }
                // 오른쪽으로 이동
                resultTri = rightInsert(resultTri);
                printTri(resultTri);
                if(lastNumber >= MAXCOUNT){
                    break;
                }
                // 위로 이동
                resultTri = upInsert(resultTri);
                printTri(resultTri);
                if(lastNumber >= MAXCOUNT){
                    break;
                }


            }

            return resultTri;
        }

        /**
         * 위로 값 체우기
         * @param tri
         * @return
         */
        private Object[] upInsert(Object[] tri) {
            checkStaticValue();
            int insertNumber = lastNumber;
            int start = yPointerIdx - 1;
            int x = xPointerIdx - 1;
            for (int i = start-1; i > 0 && ((int[])tri[i])[x-1] == 0 ; --i) {
                int[] col = (int[]) tri[i];
                // 최초 진행 인덱스에 row를 돌면서 값을 넣어준다.
                insertNumber = lastNumber + 1;

                col[x-1] = insertNumber;
                x--;
                //진행된 Y Idx
                yPointerIdx--;
                // 작업한 내역 세이브
                xPointerIdx--;
                // 마지막으로 넣은 값 셋팅
                lastNumber = insertNumber;
                if (lastNumber == MAXCOUNT) {
                    lastNumber = insertNumber;
                    checkStaticValue();
                    return tri;
                }
            }
            // 시작할 포인트 셋팅
            checkStaticValue();
            return tri;
        }

        /**
         * 오른쪽으로 값채우기 설명
         *
         * @param tri
         * @return
         */
        private Object[] rightInsert(Object[] tri) {
            //마지막으로 진행한 값의 행에 해당하는 배열을 가져온다.

            checkStaticValue();
            Object rowObj = tri[yPointerIdx - 1];
            int[] row = (int[]) rowObj;
            int start = xPointerIdx;
            for (int i = start; i <tri.length && row[i] == 0; i++) {
                row[i] = ++lastNumber;
                xPointerIdx++;
            }
            return tri;
        }

        /**
         * 전역 변수 값을 확인한다.
         */
        private void checkStaticValue() {
/*            System.out.println("yPointerIdx = " + yPointerIdx);
            System.out.println("xPointerIdx = " + xPointerIdx);
            System.out.println("lastNumber = " + lastNumber);
            System.out.println("MAXCOUNT = " + MAXCOUNT);*/
        }

        /**
         * 아래로 이동하면서 값을 채움
         * 아래로 이동하는데 바로 직선으로 내려값을채워야됨
         * @param tri
         */
        private Object[] downInsert(Object[] tri) {
            int insertNumber = lastNumber;
            checkStaticValue();
            int start = yPointerIdx -1;
            int idx = xPointerIdx -1;
            int[] startPoint = (int[]) tri[start];
            int nStartPoint = startPoint[idx];
            if(nStartPoint != 0 ){
               start = start + 1;
               lastNumber++;
            }
            for (int i = start; i < tri.length && ((int[])tri[i])[i] == 0 || start !=0 ; i++) {
                int[] col;
                if(start != 0){
                    try {
                        col = (int[]) tri[i];
                    }catch (RuntimeException e){
                        break;
                    }
                }else{
                    col = (int[]) tri[i];
                }
                // 최초 진행 인덱스에 row를 돌면서 값을 넣어준다.
                insertNumber = lastNumber++;
                int i1 = tri.length - i -1;
                if (insertNumber > MAXCOUNT) {
                    yPointerIdx--;
                    // 작업한 내역 세이브
                    // 마지막으로 넣은 값 셋팅
                    lastNumber = insertNumber;
                    return tri;
                }
                int isAlreadynumber = col[idx];
                if(isAlreadynumber != 0){

                    // 작업한 내역 세이브
                    // 마지막으로 넣은 값 셋팅
                    lastNumber = insertNumber-1;
                    return tri;
                }
                col[idx] = insertNumber;
                //진행된 Y Idx
                yPointerIdx++;
            }
            yPointerIdx--;
            // 작업한 내역 세이브
            // 마지막으로 넣은 값 셋팅
            lastNumber = insertNumber;
            return tri;
        }

    }

}

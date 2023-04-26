package programmers.level2;

import java.util.Arrays;

/**
 * 거리 두기 확인하기 Level2
 */
public class lesson_81302 {
    public static void main(String[] args) {
        String[][] palces = {
                {"POOOP", "OXXOX", "OPXPX", "OOXOX", "POXXP"},
                {"POOPX", "OXPXP", "PXXXO", "OXXXO", "OOOPP"},
                {"PXOPX", "OXOXP", "OXPOX", "OXXOP", "PXPOX"},
                {"OOOXX", "XOOOX", "OOOXX", "OXOOX", "OOOOO"},
                {"PXPXP", "XPXPX", "PXPXP", "XPXPX", "PXPXP"}
        };
        Solution solution = new Solution();
        int[] solution1 = solution.solution(palces);


    }

    private static class Solution {

        int[] solution(String[][] places) {
            int[] answer = new int[5];
            //1. places 에서 하나의 대기실을 꺼낸다
            Arrays.stream(places)
                    .forEach(s -> {
                        String[] place = (String[]) s;

                        // 대기실 별로 char배열로 만든다.
                        char[][] waitRooms = placeToWaitRooms(place);

                        printWaitRooms(waitRooms);

                    });

            return answer;
        }

        /**
         * 전체 대기실들을 상세 대기실 정보로 변경한다.
         *
         * @param place
         * @return
         */
        char[][] placeToWaitRooms(String[] place) {
            char[][] waitRooms = new char[5][5];
            for (int i = 0; i < place.length; i++) {
                char[] waitRoom = place[i].toCharArray();
                for (int j = 0; j < waitRoom.length; j++) {
                    waitRooms[i][j] = waitRoom[j];
                }
            }
            return waitRooms;
        }

        /**
         * 대기실 내용을 프린트한다.
         *
         * @param waitRooms
         */
        void printWaitRooms(char[][] waitRooms) {
            Arrays.stream(waitRooms)
                    .forEach(waitRoom -> {
                        char[] charArr = waitRoom;
                        System.out.printf("%c,%c,%c,%c,%c\n", charArr[0], charArr[1], charArr[2], charArr[3], charArr[4]);
                    });
            System.out.println();
        }

        /**
         * 실제 맨해튼 거리를 잘지키는지확인한다.
         * 모두 준수하는 경우는 1
         * 한명이라도 준수하지 않는 경우 0을 반환한다.
         * @param waitRooms 5*5 대기실 배치도 , 사람P , 책상O, 파티션X
         * @return
         */

        int checkWaitRoom(char[][] waitRooms) {
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    char c = waitRooms[i][j];
                    if(c == 'P'){
                        boolean compliantResult = isCompliant(waitRooms, i, j);
                        if(!compliantResult){
                            return 0;
                        }
                    }
                }
            }
            return 1;
        }

        /**
         * 준수 여부를 판단한다. 준수한경우 true를 반환, 준수하지 않은 경우 false를 반환
         * @param waitRooms
         * @param pointX
         * @param pointY
         * @return
         */
        boolean isCompliant(char[][] waitRooms ,int pointY, int pointX){
            //상
            //하
            //좌
            //우
            //우상
            //우하
            //좌상
            //좌하

            return false;
        }
    }
}

package programmers.level2;


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
        for (int i : solution1) {
            System.out.println(solution1[i]);
        }

    }

    private static class Solution {
        private static final int dx[] = {0, -1, 1, 0}; // 상, 좌 , 우, 하
        private static final int dy[] = {-1, 0, 0, 1};

        public int[] solution(String[][] places) {

            int[] answer = new int[places.length];
            for (int i = 0; i < answer.length; i++) {
                String[] place = places[i];
                char[][] room = new char[place.length][];
                for (int j = 0; j < room.length; j++) {
                    room[j] = place[j].toCharArray();
                }
                if (isDistanced(room)) {
                    answer[i] = 1;
                } else {
                    answer[i] = 0;
                }
            }
            return answer;
        }


        // 거리두기 지킨지 판단하는 함수
        private boolean isDistanced(char[][] room) {
            for (int y = 0; y < room.length; y++) {
                for (int x = 0; x < room[y].length; x++) {
                    if (room[y][x] != 'P') { // 사람이 아닌경우 다음 값으로 넘어간다...
                        continue;
                    }
                    if (!isDistanced(room, x, y)) {
                        return false;
                    }
                }
            }
            return true;
        }

        // 던져진 값이 사람인경우 거리두기 상세 체크
        private boolean isDistanced(char[][] room, int x, int y) {
            // 해당 인원의 상하좌우를 체크한다...
            // 책상이 있는지 체크, 파티션은 괜찮다..
            // 순서는 상좌 우하 순서
            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];
                // 해당 상하 좌우가 규정된 범위를 벗어나는 경우 다시 방향을 바꿔서 돈다
                if (ny < 0 || ny >= room.length || nx < 0 || nx >= room[ny].length) {
                    continue;
                }
                // 해당값에 사람이 근접해있는가 있으면 거리두기 실패
                // 책상인 경우 한번 더 근처에 사람이 있는 지 확인해야한다.
                // 사람이 있는 경우 거리두기는 실패한다.
                switch (room[ny][nx]) {
                    case 'P':
                        return false;
                    case 'O':
                        if (isNextToVolunteer(room, nx, ny, 3 - d)) {
                            return false;
                        }
                    break;
                }
            }
            return true;
        }
        // 3-d 값은
        // dx[] = {0, -1, 1, 0}; // 상, 좌 , 우, 하
        // dy[] = {-1, 0, 0, 1};
        // 현재 방향의 반대방향은 조사할 필요가 없다 이미 처음에 했기때문에 배제히기 위한 값이다.
        // 만약에 지금 방향이 우측이면 3-2 = 1, 1번 인덱스인 좌측 방향의 조사를 배제하고 (좌측은 이미 조사함)
        // 만약에 지금 방향이 좌측이면 3-1 = 2, 2번 인덱스인 우측 방향의 조사를 배제한다 (우측은 이미 조사함)
        private boolean isNextToVolunteer(char[][] room, int x, int y, int exclude) {
            for (int d = 0; d < 4; d++) {
                // 배제 방향은 조사하지 않는다.
                if (d == exclude) {
                    continue;
                }
                int nx = x + dx[d];
                int ny = y + dy[d];
                // 조사 범위를 벗어나면 조사하지 않는다.
                if (ny < 0 || ny >= room.length || nx < 0 || nx >= room[ny].length) {
                    continue;
                }
                // 사람이 있다면 거리두기 실패 이므로 true 반환
                if (room[ny][nx] == 'P'){
                    return true;
                }

            }
            return false;
        }
    }

    /**
     * 실제 정답
     */
    public static class Solution2 {
        private static final int dx[] = {0, -1, 1, 0};
        private static final int dy[] = {-1, 0, 0, 1};

        private boolean isNextToVolunteer(char[][] room, int x, int y, int exclude) {
            for (int d = 0; d < 4; d++) {
                if (d == exclude) continue;

                int nx = x + dx[d];
                int ny = y + dy[d];
                if (ny < 0 || ny >= room.length || nx < 0 || nx >= room[ny].length)
                    continue;
                if (room[ny][nx] == 'P') return true;
            }
            return false;
        }

        private boolean isDistanced(char[][] room, int x, int y) {
            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];
                if (ny < 0 || ny >= room.length || nx < 0 || nx >= room[ny].length)
                    continue;

                switch (room[ny][nx]) {
                    case 'P': return false;
                    case 'O':
                        if (isNextToVolunteer(room, nx, ny, 3- d)) return false;
                        break;
                }
            }
            return true;
        }

        private boolean isDistanced(char[][] room) {
            for (int y = 0; y < room.length; y++) {
                for (int x = 0; x < room[y].length; x++) {
                    if (room[y][x] != 'P') continue;
                    if (!isDistanced(room, x, y)) return false;
                }
            }
            return true;
        }

        public int[] solution(String[][] places) {
            int[] answer = new int[places.length];
            for (int i = 0; i < answer.length; i++) {
                String[] place = places[i];
                char[][] room = new char[place.length][];
                for (int j = 0; j < room.length; j++) {
                    room[j] = place[j].toCharArray();
                }
                if (isDistanced(room)) {
                    answer[i] = 1;
                } else {
                    answer[i] = 0;
                }
            }
            return answer;
        }
    }
}

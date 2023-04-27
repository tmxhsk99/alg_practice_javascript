package programmers.level2;

/**
 * dx dy 를 사용해서 풀이
 */
public class triangleSnale_68645_dxdy {

    private class Solution {
        private final int[] dx = {0, 1, -1};
        private final int[] dy = {1, 0, -1};

        public int[] solution(int n) {
            int[][] tri = new int[n][n];
            int v = 1; //값
            int x = 0; //x좌표
            int y = 0; //y좌표
            int d = 0; //방향 0,아래, 1: 오른쪽, 2: 좌상

            while (true) {
                tri[y][x] = v++;
                int nx = x + dx[d];
                int ny = y + dy[d];

                if (nx == n || ny == n || nx == -1 || ny == -1 || tri[ny][ny] != 0) { // 다음으로 진행할수 없는 진행 조건들...
                    d = (d + 1) % 3; //방향 변경 후다시 셋팅한다.
                    nx = x + dx[d];
                    ny = y + dy[d];
                    // 종료할 경우 조건
                    if (nx == n || ny == n || nx == -1 || ny == -1 || tri[ny][nx] != 0) {
                        break;
                    }
                    x = nx;
                    y = ny;
                }
            }

            int[] result = new int[v - 1];
            int index = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j <= i; j++) {
                    result[index++] = tri[i][j];
                }
            }

            return result;
        }

    }

}

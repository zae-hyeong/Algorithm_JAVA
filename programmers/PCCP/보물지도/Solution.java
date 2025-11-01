import java.util.*;

class Solution {
    int[][] map;
    boolean[][][] v;
    int N, M;

    boolean inBound(int y, int x) {
        return 0 <= y && y < N && 0 <= x && x < M;
    }

    public int solution(int M, int N, int[][] hole) {
        map = new int[N][M];
        v = new boolean[N][M][2];
        this.N = N;
        this.M = M;

        for(int[] h: hole) {
            map[h[1] - 1][h[0] - 1] = -1;
        }

        ArrayDeque<int[]> queue = new ArrayDeque<>(); //y, x, dist, jumped
        queue.add(new int[] {0, 0, 0, 0});
        v[0][0][0] = true;

        int[] dy = {1, 0, -1, 0};
        int[] dx = {0, 1, 0, -1};

        int[] cur;
        int ny, nx, dist, jumped;
        while(!queue.isEmpty()) {
            cur = queue.poll();
            
            for(int d = 0; d < 4; d++) {
                ny = cur[0] + dy[d];
                nx = cur[1] + dx[d];
                jumped = cur[3];

                if(inBound(ny, nx) && map[ny][nx] != -1 && !v[ny][nx][jumped]) { // 점프 안함
                    if(ny == N - 1 && nx == M - 1) {
                        return cur[2] + 1;
                    }
                    queue.add(new int[] {ny, nx, cur[2] + 1, jumped});
                    v[ny][nx][jumped] = true;
                }

                ny = ny + dy[d];
                nx = nx + dx[d];
                if(inBound(ny, nx) && map[ny][nx] != -1 && jumped == 0 && !v[ny][nx][1]) { // 점프함
                    if(ny == N - 1 && nx == M - 1) {
                        return cur[2] + 1;
                    }
                    queue.add(new int[] {ny, nx, cur[2] + 1, 1});
                    v[ny][nx][1] = true;
                }
            }
        }

        return -1;
    }
}
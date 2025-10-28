package gold.g3_1937_욕심쟁이판다;

import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[][] map, dist;

    static int[] dy = { 1, 0, -1, 0 };
    static int[] dx = { 0, 1, 0, -1 };

    static boolean inBound(int y, int x) {
        return 0 <= y && y < N && 0 <= x && x < N;
    }

    static int dfs(int y, int x) {
        if (dist[y][x] > 0)
            return dist[y][x];

        dist[y][x] = 1;

        int ny, nx;
        for (int d = 0; d < 4; d++) {
            ny = y + dy[d];
            nx = x + dx[d];

            if (!inBound(ny, nx))
                continue;

            if (map[y][x] < map[ny][nx]) {
                dist[y][x] = Math.max(dist[y][x], dfs(ny, nx) + 1);
            }
        }
        return dist[y][x];
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("./solving/gold/g3_1937_욕심쟁이판다/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        dist = new int[N][N];

        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        br.close();

        int max = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                max = Math.max(max, dfs(i, j));
            }
        }

        for(int[] d: dist) System.out.println(Arrays.toString(d));
        System.out.println(max);
    }
}

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

    static void dfs(int y, int x, int depth) {
        dist[y][x] = depth;

        for (int d = 0; d < 4; d++) {
            int ny = y + dy[d];
            int nx = x + dx[d];
            if (inBound(ny, nx) && map[y][x] > map[ny][nx] && dist[ny][nx] < depth + 1) {
                dfs(ny, nx, depth + 1);
            }
        }
    }

    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("./solving/gold/g3_1937_욕심쟁이판다/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        dist = new int[N][N];
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o2[2]-o1[2]); // [y, x, 값]

        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                pq.add(new int[]{i, j, map[i][j]});
            }
        }

        br.close();

        
        while(!pq.isEmpty()) {
            int[] cur = pq.poll();
            int y = cur[0];
            int x = cur[1];
            
            if(dist[y][x] > 0) continue;

            dfs(y, x, 1);

            
        }

        int max = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                max = Math.max(max, dist[i][j]);
            }
        }

        // for(int[] d: dist) System.out.println(Arrays.toString(d));
        System.out.println(max);
    }
}

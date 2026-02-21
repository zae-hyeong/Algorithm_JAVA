package gold.g5_7576_토마토;

import java.util.*;
import java.io.*;

public class Main {
	static int N, M;
	static int[][] map;
	
	static int[] dy = {1, 0, -1, 0};
	static int[] dx = {0, 1, 0, -1};
	
	public static void main(String[] args) throws Exception {
		// System.setIn(new FileInputStream("./solving/gold/g5_7576_토마토/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		
		ArrayDeque<int[]> queue = new ArrayDeque<>();
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 1) queue.add(new int[] {i, j, 1}); // y, x, day
			}
		}
		
		int[] cur;
		int ny, nx;
		while(!queue.isEmpty()) {
			cur = queue.poll();
			
			for(int d = 0; d < 4; d++) {
				ny = cur[0] + dy[d];
				nx = cur[1] + dx[d];
				
				if(0 <= ny && ny < N && 0 <= nx && nx < M && map[ny][nx] == 0) {
					map[ny][nx] = cur[2] + 1;
					queue.add(new int[] {ny, nx, cur[2] + 1});
				}
			}
		}
		
		// for(int[] m: map) System.out.println(Arrays.toString(m));
		
		int maxDay = -1;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(map[i][j] == 0) { // 안익은 토마토가 있음
					System.out.println(-1);
					System.exit(0);
				}
				
				maxDay = Math.max(maxDay, map[i][j]);
			}
		}

		System.out.println(maxDay - 1);
		br.close();
	}
}

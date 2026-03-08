package gold.g4_2573_빙산;

import java.util.*;
import java.io.*;

public class Main {
	static int N, M;
	static int[][] map;
	static boolean[][] v;
	
	static int[] dy = {1, 0, -1, 0};
	static int[] dx = {0, 1, 0, -1};
	public static void log(Object o) {
		System.out.println(o.toString());
	}
	
	static void resetVisited() {
		for(int i = 0; i < N; i++) 
			Arrays.fill(v[i], false);
	}
	
	static boolean inBound(int y, int x) {
		return 0 <= y && y < N && 0 <= x && x < M;
	}
	
	static void BFSWater() {
		resetVisited();
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(!v[i][j] && map[i][j] == 0) {
					meltIceFrom(i, j);
				}
			}
		}
	}
	
	static void meltIceFrom(int sy, int sx) {
		ArrayDeque<int[]> queue = new ArrayDeque<>();
		queue.offer(new int[] {sy, sx});
		v[sy][sx] = true;
		
		int[] cur;
		int ny, nx;
		while(!queue.isEmpty()) {
			cur = queue.poll();
			for(int d = 0; d < 4; d++) {
				ny = cur[0] + dy[d];
				nx = cur[1] + dx[d];
				
				if(!inBound(ny, nx) || v[ny][nx]) continue;
				
				if(map[ny][nx] > 0) { // 빙산인 경우
					map[ny][nx]--;
					if(map[ny][nx] == 0) 
						v[ny][nx] = true;
				} else { // 바다인 경우
					queue.offer(new int[] {ny, nx});
					v[ny][nx] = true;
				}
			}
		}
	}
	
	static int countGroup() {
		resetVisited();
		
		int count = 0;
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) {
				if(!v[i][j] && map[i][j] > 0) {
					bfsFrom(i, j);
					count++;
				}
			}
		}
		
		return count;
	}
	
	static void bfsFrom(int sy, int sx) {
		
		ArrayDeque<int[]> queue = new ArrayDeque<>();
		queue.offer(new int[] {sy, sx});
		v[sy][sx] = true;
		
		int[] cur;
		int ny, nx;
		while(!queue.isEmpty()) {
			cur = queue.poll();
			for(int d = 0; d < 4; d++) {
				ny = cur[0] + dy[d];
				nx = cur[1] + dx[d];
				
				if(inBound(ny, nx) && !v[ny][nx] && map[ny][nx] > 0) {
					queue.offer(new int[] {ny, nx});
					v[ny][nx] = true;
				}
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./solving/gold/g4_2573_빙산/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		v = new boolean[N][M];
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int count, year = 0;
		while(true) {
			year++;
			
			BFSWater();
//			for(int[] m : map) log(Arrays.toString(m));
			
			count = countGroup();
//			log("COUNT : " + count);
			
			if(count == 1) continue;
			else break;
		}
		
		System.out.println(count == 0 ? 0 : year);
		br.close();
	}
}

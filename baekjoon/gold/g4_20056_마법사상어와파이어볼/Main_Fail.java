package gold.g4_20056_마법사상어와파이어볼;

import java.util.*;
import java.io.*;

public class Main_Fail {
	static int N, M, K;
	
	static int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};
	static int[] dx = {0, 1, 1, 1, 0, -1, -1, -1};
	
	static ArrayList<FireBall>[][] map, newMap;
	
	static void printMap() {
		System.out.println("================MAP================");
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				System.out.println(String.format("[%d, %d]", i, j) + map[i][j].toString());
			}
			System.out.println();
		}
		
		System.out.println("===================================");
	}
	
	static class FireBall {
		int y, x, weight, speed, dir;
		boolean allOddOrEven;
		
		FireBall() {}
		
		FireBall(int y, int x, int weight, int speed, int dir) {
			this.y = y;
			this.x = x;
			this.weight = weight;
			this.speed = speed;
			this.dir = dir;
		}
		
		@Override
		public String toString() {
			return String.format("y: %d, x: %x, weight: %d, speed: %d, dir: %d", this.y, this.x, this.weight, this.speed, this.dir);
		}
	}
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("./solving/gold/g4_20056_마법사상어와파이어볼/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new ArrayList[N][N];
		newMap = new ArrayList[N][N];
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				map[i][j] = new ArrayList<>();
				newMap[i][j] = new ArrayList<>();
			}
		}
		
		FireBall fb = null;
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			
			fb = new FireBall();
			fb.y = Integer.parseInt(st.nextToken()) - 1;
			fb.x = Integer.parseInt(st.nextToken()) - 1;
			fb.weight = Integer.parseInt(st.nextToken());
			fb.speed = Integer.parseInt(st.nextToken());
			fb.dir = Integer.parseInt(st.nextToken());
			
			map[fb.y][fb.x].add(fb);
		}
		
		ArrayList<FireBall>[][] tmp;
		
		while(K-- > 0) {
			printMap();
			
			// 파이어볼 이동
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					while(!map[i][j].isEmpty()) {
						fb = map[i][j].removeLast();
						fb.y = (i + dy[fb.dir] * (fb.speed % N) + N) % N;
						fb.x = (j + dx[fb.dir] * (fb.speed % N) + N) % N;
						
						newMap[fb.y][fb.x].add(fb);
					}
				}
			}
			
			tmp = map;
			map = newMap;
			newMap = tmp;
			
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					if (map[i][j].size() > 1) {
						// 합치기
						boolean isOdd = true, isEven = true;
						
						int sumW = 0, sumS = 0;
						for(FireBall cur: map[i][j]) {
							sumW += cur.weight;
							sumS += cur.speed;
							
							if(fb.dir % 2 == 0) isOdd = false;
							else isEven = false;
						}
						
						sumW = sumW / 5;
						sumS = sumS / map[i][j].size();
						boolean allSame = isOdd || isEven; 
						
						// 4개로 나누기
						if(sumW > 0) {
							for(int d = allSame ? 0 : 1; d < 8; d += 2) {
								newMap[i][j].add(new FireBall(i, j, sumW, sumS, d));
							}
						}
						
						map[i][j].clear();
					}
				}
			}
		}
		
		printMap();
		
		int total = 0;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				if(map[i][j].size() == 0) continue;
				
				while(!map[i][j].isEmpty()) {
					total += map[i][j].removeLast().weight;
				}
			}
		}
		
		System.out.println(total);		
		br.close();
	}
}

package gold.g4_20056_마법사상어와파이어볼;

import java.util.*;
import java.io.*;

public class Main {
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
	
	static void moveFireballs() {
        ArrayList<FireBall>[][] nextMap = new ArrayList[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                nextMap[i][j] = new ArrayList<>();
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (FireBall fb : map[i][j]) {
                    // 음수 좌표를 한 번에 처리하는 모듈러 연산
                    int nr = (i + dy[fb.dir] * (fb.speed % N) + N) % N;
                    int nc = (j + dx[fb.dir] * (fb.speed % N) + N) % N;
                    nextMap[nr][nc].add(new FireBall(nr, nc, fb.weight, fb.speed, fb.dir));
                }
            }
        }
        map = nextMap;
    }

    static void processSplit() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j].size() < 2) continue;

                int sumW = 0, sumS = 0, count = map[i][j].size();
                boolean allEven = true, allOdd = true;

                for (FireBall fb : map[i][j]) {
                    sumW += fb.weight;
                    sumS += fb.speed;
                    if (fb.dir % 2 == 0) allOdd = false; // 짝수가 섞이면 "모두 홀수" 탈락
                    else allEven = false;               // 홀수가 섞이면 "모두 짝수" 탈락
                }

                map[i][j].clear();
                int nextW = sumW / 5;
                int nextS = sumS / count;
                
                if (nextW > 0) {
                    for (int d = (allEven || allOdd) ? 0 : 1; d < 8; d += 2) {
                        map[i][j].add(new FireBall(i, j, nextW, nextS, d));
                    }
                }
            }
        }
    }

    static int calculateTotalWeight() {
        int total = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                for (FireBall fb : map[i][j]) {
                    total += fb.weight;
                }
            }
        }
        return total;
    }
	
	public static void main(String[] args) throws Exception{
		System.setIn(new FileInputStream("./solving/gold/g4_20056_마법사상어와파이어볼/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		map = new ArrayList[N][N];
		
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				map[i][j] = new ArrayList<>();
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
		
		while (K-- > 0) {
            moveFireballs();
            processSplit();
        }

        System.out.println(calculateTotalWeight());
		br.close();
	}
}

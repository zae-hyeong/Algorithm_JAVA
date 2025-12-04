package gold.g2_19238_스타트택시;

import java.io.*;
import java.util.*;

class Car {
	int y, x, fuel;

	Car(int y, int x, int fuel) {
		this.y = y;
		this.x = x;
		this.fuel = fuel;
	}
}

public class Main {
	static int N, M;
	static int[][] map, dist;
	static boolean[] visitedCustomer;
	static Map<Integer, Integer> priMap;

	static int[] dy = { 1, 0, -1, 0 };
	static int[] dx = { 0, 1, 0, -1 };

	static boolean isAvailable(int y, int x) {
		return 0 <= y && y < N && 0 <= x && x < N && map[y][x] >= 0;
	}

	static boolean[][] v;
	static ArrayDeque<int[]> queue = new ArrayDeque<>();

	static void bfsFrom(int y, int x, int id) {
		queue.clear();
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				v[i][j] = false;

		queue.add(new int[] { y, x, 1 });
		v[y][x] = true;

		int[] cur;
		int ny, nx;
		while (!queue.isEmpty()) {
			cur = queue.poll();
			y = cur[0];
			x = cur[1];

			for (int d = 0; d < 4; d++) {
				ny = y + dy[d];
				nx = x + dx[d];
				if (isAvailable(ny, nx) && !v[ny][nx]) {
					if (map[ny][nx] > 0) {
						dist[id][map[ny][nx]] = cur[2];
						dist[map[ny][nx]][id] = cur[2];
					}

					v[ny][nx] = true;
					queue.add(new int[] { ny, nx, cur[2] + 1 });
				}
			}
		}
	}

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("./solving/gold/g2_19238_스타트택시/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		int fuel = Integer.parseInt(st.nextToken());

		v = new boolean[N][N];
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken()) == 1 ? -1 : 0;
			}
		}

		st = new StringTokenizer(br.readLine());
		Car car = new Car(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1, fuel);

		visitedCustomer = new boolean[2 * M + 1];

		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			int sy = Integer.parseInt(st.nextToken()) - 1;
			int sx = Integer.parseInt(st.nextToken()) - 1;
			int dy = Integer.parseInt(st.nextToken()) - 1;
			int dx = Integer.parseInt(st.nextToken()) - 1;
			map[sy][sx] = i;
			map[dy][dx] = i + M;
		}
		br.close();

//		for(int[] m : map) System.out.println(Arrays.toString(m));
		/* 메인 로직 시작 - 전처리 */
		dist = new int[2 * M + 1][2 * M + 1];
		priMap = new HashMap<>();
		int priIdx = 1;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (0 < map[i][j] && map[i][j] <= M) {
					priMap.put(map[i][j], priIdx++);
					bfsFrom(i, j, map[i][j]);
				}
			}
		}
		bfsFrom(car.y, car.x, 0);

//		for(int[] d : dist) System.out.println(Arrays.toString(d));
//		System.out.println(priMap.toString());

		/* 승객 태우기 */
		int curIdx = 0;
		for (int loop = 1; loop <= M; loop++) {
			// 택시 -> 승객 태울 승객 정하기
			int minDis = Integer.MAX_VALUE;
			int target = 0;
			for (int i = 1; i <= M; i++) {
				// 방문 못하는 경우
				if(dist[curIdx][i] == 0) continue;
				
				if (!visitedCustomer[i] && minDis > dist[curIdx][i] || (minDis == dist[curIdx][i] && priMap.get(target) > priMap.get(i))) {
					minDis = dist[curIdx][i];
					target = i;
				}
			}
			
			// 승객 태우러 가기
			curIdx = target;
			car.fuel = car.fuel - minDis;
			if(car.fuel <= 0) break;
//			System.out.format("loop%d : car.fuel=%d, curIdx=%d\n", loop, car.fuel, curIdx);
			
			// 승객 태우고 목적지 도착
			if(dist[target][target + M] == 0) continue;
			curIdx = target + M;
			car.fuel = car.fuel - dist[target][target + M];
			if(car.fuel <= 0) break;
			car.fuel = car.fuel + 2 * dist[target][target + M];
			visitedCustomer[target] = true ;
			
//			System.out.format("loop%d : car.fuel=%d, curIdx=%d\n", loop, car.fuel, curIdx);
		}

		System.out.println(car.fuel <= 0 ? -1 : car.fuel);
	}

}

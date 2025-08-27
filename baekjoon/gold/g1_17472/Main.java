package gold.g1_17472;

import java.util.*;
import java.io.*;

public class Main {
	static int N, M, numOfLand;
	static int[][] map;
	static int[][] g; // g[출발섬][도착섬] = 거리
	static int[] dy = { 1, 0, -1, 0 };
	static int[] dx = { 0, 1, 0, -1 };

	static int prim() {
		boolean[] v = new boolean[numOfLand + 1];
		int[] prim = new int[numOfLand + 1];
		Arrays.fill(prim, Integer.MAX_VALUE);
		int mst = 0;

		PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[1], o2[1]));
		pq.add(new int[] { 1, 0 }); // [node, cost]

		while (!pq.isEmpty()) {
			int cur[] = pq.poll();

			if (cur[1] > prim[cur[0]]) continue;
			if (v[cur[0]]) continue;

			v[cur[0]] = true;
			mst += cur[1];

			for (int near = 1; near <= numOfLand; near++) {
				if (near == cur[0])
					continue;

				if (!v[near] && prim[near] > g[cur[0]][near]) {
					prim[near] = g[cur[0]][near];
					pq.add(new int[] { near, prim[near] });
				}
			}
		}

		for (int i = 1; i <= numOfLand; i++) 
			if (!v[i]) return -1;
		

		return mst;
	}

	static boolean isValid(int y, int x) {
		return 0 <= y && y < N && 0 <= x && x < M;
	}

	static void makeMinBridge() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 0)
					continue;

				for (int d = 0; d < 4; d++) {
					int y = i, x = j;
					int length = 1;
					while (isValid(y, x)) {
						y = y + dy[d];
						x = x + dx[d];

						if (!isValid(y, x) || map[i][j] == map[y][x])
							break;

						if (map[y][x] == 0) {
							length++;
							continue;
						}

						if (length <= 2)
							break;

//						System.out.println(map[i][j] + " : " + map[y][x] + " >> " + length);
						int a = map[i][j], b = map[y][x];
						g[a][b] = Math.min(g[a][b], length - 1);
						g[b][a] = Math.min(g[b][a], length - 1);
						break;
					}
				}
			}
		}
	}

	static boolean[][] v;

	static void bfsFrom(int sy, int sx, int color) {
		ArrayDeque<int[]> q = new ArrayDeque<>();
		q.add(new int[] { sy, sx });
		v[sy][sx] = true;
		map[sy][sx] = color;

		int[] cur;
		int ny, nx;
		while (!q.isEmpty()) {
			cur = q.poll();

			for (int i = 0; i < 4; i++) {
				ny = cur[0] + dy[i];
				nx = cur[1] + dx[i];
				if (isValid(ny, nx) && !v[ny][nx] && map[ny][nx] != 0) {
					v[ny][nx] = true;
					map[ny][nx] = color;
					q.add(new int[] { ny, nx });
				}
			}
		}
	}

	static void color() {
		int num = 1;
		v = new boolean[N][M];

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == 0) {
					v[i][j] = true;
					continue;
				}

				if (v[i][j])
					continue;

				bfsFrom(i, j, num++);
			}
		}

		numOfLand = num - 1;
	}

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./solving/bj_17472/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		color();
//		for(int[] m : map) System.out.println(Arrays.toString(m));
//		System.out.println(numOfLand);

		g = new int[numOfLand + 1][numOfLand + 1];
		for (int i = 1; i <= numOfLand; i++) {
			Arrays.fill(g[i], Integer.MAX_VALUE);
			g[i][i] = 0;
		}

		makeMinBridge();

//		for (int[] gg : g) System.out.println(Arrays.toString(gg));

		System.out.println(prim());
		br.close();
	}
}

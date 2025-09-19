package gold.g3_17142;

import java.util.*;
import java.io.*;

public class Main {
	static int N, M, numOfVirus;
	static int[][] map;
	static int[][] dists;

	static int totalMin = Integer.MAX_VALUE;
	static int[] selected;
	static void getMin() {		
		int resultSec = 0;
		int sec;
		for (int i = 1; i <= numOfVirus; i++) {
			sec= Integer.MAX_VALUE;
			for (int s: selected) {	
				sec = Math.min(sec, dists[i][s]);
			}
			resultSec = Math.max(resultSec, sec);
		}
		
//		System.out.println(resultSec);
		totalMin = Math.min(totalMin, resultSec);
	}
	
	static void comb(int idx, int num) {
		if(idx == M) {
//			System.out.println(Arrays.toString(selected) +"==================");
			getMin();
			return;
		}
		
		for (int i = num; i <= numOfVirus; i++) {
			selected[idx] = i;
			comb(idx + 1, i + 1);
		}
	}
	
	static boolean inBound(int y, int x) {
		return 0 <= y && y < N && 0 <= x && x < N;
	}

	static int[] dy = { 1, 0, -1, 0 };
	static int[] dx = { 0, 1, 0, -1 };
	static boolean[][] v;

	static void bfsFrom(int sy, int sx) {
		ArrayDeque<int[]> queue = new ArrayDeque<>();
		v = new boolean[N][N];

		queue.add(new int[] { sy, sx, 0 }); // y, x, dist
		v[sy][sx] = true;

		int[] cur;
		int y, x, ny, nx, dist;
		while (!queue.isEmpty()) {
			cur = queue.poll();
			y = cur[0];
			x = cur[1];
			dist = cur[2];

			for (int i = 0; i < 4; i++) {
				ny = y + dy[i];
				nx = x + dx[i];

				if (inBound(ny, nx) && map[ny][nx] >= 0 && v[ny][nx] == false) {
					if (map[ny][nx] > 0) {
						dists[map[sy][sx]][map[ny][nx]] = dist;
						dists[map[ny][nx]][map[sy][sx]] = dist;
					}

					queue.add(new int[] { ny, nx, dist + 1 });
					v[ny][nx] = true;
				}
			}
		}
	}

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./solving/gold/g3_17142/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][N];
		ArrayList<int[]> posOfViruses = new ArrayList<>();
		posOfViruses.add(new int[] {});
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 1)
					map[i][j] = -1;
				else if (map[i][j] == 2) {
					map[i][j] = ++numOfVirus;
					posOfViruses.add(new int[] { i, j });
				}
			}
		}

		dists = new int[numOfVirus + 1][numOfVirus + 1];
		for (int i = 1; i <= numOfVirus; i++) {
			bfsFrom(posOfViruses.get(i)[0], posOfViruses.get(i)[1]);
		}
//		for(int[] a: dists)System.out.println(Arrays.toString(a));

		selected = new int[M];
		comb(0, 1);
//		selected = new int[] { 1, 7, 10}; 
//		getMin();
		System.out.println(totalMin);
		br.close();
	}
}

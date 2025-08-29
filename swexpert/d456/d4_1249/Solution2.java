package d456.d4_1249;

import java.util.*;
import java.io.*;

public class Solution2 {
	static int N, result;
	static int[][] map;
	static int[][] dij;
	static boolean[][] v;

	static boolean inBounds(int y, int x) {
		return 0 <= y && y < N && 0 <= x && x < N;
	}

	static int[] dy = { 1, 0, -1, 0 };
	static int[] dx = { 0, 1, 0, -1 };

	static int dijkstra() {
		dij = new int[N][N];
		for (int i = 0; i < N; i++)
			Arrays.fill(dij[i], Integer.MAX_VALUE);

		PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[2], o2[2]));
		pq.add(new int[] { 0, 0, 0 });

		while (!pq.isEmpty()) {
			int[] cur = pq.poll();
			int y = cur[0], x = cur[1], cost = cur[2];

			if (v[y][x])
				continue;
			v[y][x] = true;

			for (int d = 0; d < 4; d++) {
				int ny = y + dy[d];
				int nx = x + dx[d];

				if (inBounds(ny, nx) && !v[ny][nx] && dij[ny][nx] > map[ny][nx] + cost) {
					dij[ny][nx] = map[ny][nx] + cost;
					pq.add(new int[] { ny, nx, dij[ny][nx] });
				}
			}
		}

		return dij[N - 1][N - 1];
	}

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./solving/d456/d4_1249/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		final int TC = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= TC; tc++) {
			result = 0;
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			v = new boolean[N][N];

			char[] tmp;
			for (int i = 0; i < N; i++) {
				tmp = br.readLine().toCharArray();
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(Character.toString(tmp[j]));
				}
			}

			sb.append("#").append(tc).append(" ").append(dijkstra()).append("\n");
		}

		System.out.println(sb.toString());
		br.close();
	}
}

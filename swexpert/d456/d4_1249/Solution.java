package d456.d4_1249;

import java.util.*;
import java.io.*;

public class Solution {
	static int N;
	static int[][] map;

	static int dijkstra() {
		int[] dy = { 1, 0, -1, 0 };
		int[] dx = { 0, 1, 0, -1 };
		
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[2], o2[2]));
		boolean[][] v = new boolean[N][N];

		pq.add(new int[] { 0, 0, 0 }); // y, x, w

		int y, x, w, ny, nx;
		int[] cur;
		while (!pq.isEmpty()) {
			cur = pq.poll();
			y = cur[0];
			x = cur[1];
			w = cur[2];

			if (v[y][x])
				continue;
			if (y == N - 1 && x == N - 1)
				return w;

			v[y][x] = true;

			for (int i = 0; i < 4; i++) {
				ny = y + dy[i];
				nx = x + dx[i];

				if (isValid(ny, nx) && !v[ny][nx])
					pq.add(new int[] { ny, nx, w + map[ny][nx] });
			}
		}

		return -1;
	}

	static boolean isValid(int y, int x) {
		return 0 <= y && y < N && 0 <= x && x < N;
	}

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("solving/d456/d4_1249/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		final int TC = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= TC; tc++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];

			for (int i = 0; i < N; i++) {
				map[i] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
			}

			sb.append("#").append(tc).append(" ").append(dijkstra()).append("\n");
		}

		System.out.println(sb.toString());
		br.close();
	}
}

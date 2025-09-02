package sol;

import java.io.*;
import java.util.*;

public class d4_1249 {
	static int N, result;
	static int[][] arr;

	static boolean inBounds(int y, int x) {
		return 0 <= y && y < N && 0 <= x && x < N;
	}

	static int[] dy = { 1, 0, -1, 0 };
	static int[] dx = { 0, 1, 0, -1 };

	static int dijkstra() {
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[2], o2[2])); // [y, x, cost]
		boolean[][] v = new boolean[N][N];
		pq.add(new int[] { 0, 0, 0 });

		int ny, nx;
		while (!pq.isEmpty()) {
			int[] cur = pq.poll();
			int y = cur[0], x = cur[1], cost = cur[2];

			if (v[y][x])
				continue;
			if (y == N - 1 && x == N - 1)
				return cost;
			v[y][x] = true;

			for (int i = 0; i < 4; i++) {
				ny = y + dy[i];
				nx = x + dx[i];

				if (inBounds(ny, nx) && !v[ny][nx]) {
					pq.add(new int[] { ny, nx, cost + arr[ny][nx] });
				}
			}
		}

		return -1;
	}

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./solving/sol/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		final int TC = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= TC; tc++) {
			N = Integer.parseInt(br.readLine());
			arr = new int[N][N];

			char[] tmp;
			for (int i = 0; i < N; i++) {
				tmp = br.readLine().toCharArray();
				for (int j = 0; j < N; j++) {
					arr[i][j] = Character.getNumericValue(tmp[j]);
				}
			}

			result = dijkstra();

			sb.append("#").append(tc).append(" ").append(result).append("\n");
		}
		System.out.println(sb.toString());
		br.close();
	}
}

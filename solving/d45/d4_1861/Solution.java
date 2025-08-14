package d45.d4_1861;

import java.io.*;
import java.util.*;

public class Solution {
	static int N;
	static int[][] arr;
	static ArrayDeque<int[]> ad;

	static boolean isValid(int y, int x) {
		return 0 <= y && y < N && 0 <= x && x < N && !visited[y][x];
	}

	static boolean[][] visited;
	static int[] dy = { 1, 0, -1, 0 };
	static int[] dx = { 0, 1, 0, -1 };

	static int[] bfsFrom(int sI, int sJ) {
		int count = 1, minVal = arr[sI][sJ];
		ad = new ArrayDeque<>();
		ad.offer(new int[] { sI, sJ }); // [y, x]
		visited[sI][sJ] = true;

		int y, x, ny, nx;
		while (!ad.isEmpty()) {
			int[] cur = ad.poll();

			y = cur[0];
			x = cur[1];

			for (int i = 0; i < 4; i++) {
				ny = y + dy[i];
				nx = x + dx[i];
				if (isValid(ny, nx) && Math.abs(arr[ny][nx] - arr[y][x]) == 1) {
					visited[ny][nx] = true;
					minVal = Math.min(minVal, arr[ny][nx]);
					ad.offer(new int[] { ny, nx });
					count++;
				}
			}
		}

		return new int[] { count, minVal };
	}

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./solving/d45/d4_1861/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuffer sb = new StringBuffer();

		final int TC = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= TC; tc++) {
			N = Integer.parseInt(br.readLine());
			arr = new int[N][N];
			visited = new boolean[N][N];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			int min = Integer.MAX_VALUE, maxCount = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (visited[i][j])
						continue;

					int[] out = bfsFrom(i, j);
					if (maxCount < out[0] || (maxCount == out[0] && min > out[1])) {
						maxCount = out[0];
						min = out[1];
					}
				}
			}

			sb.append("#").append(tc).append(" ").append(min).append(" ").append(maxCount).append("\n");
		}
		System.out.println(sb.toString());
		br.close();
	}
}

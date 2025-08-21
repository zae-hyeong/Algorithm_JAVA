package d.d_2105;

import java.io.*;
import java.util.*;

public class Solution2 {

	static int N, result;
	static int[][] map;
	static Set<Integer> set;
	static int[] dx = { 1, -1, -1, 1 };
	static int[] dy = { 1, 1, -1, -1 };
	static boolean[] visited;

	public static void main(String[] args) throws Exception {
		// System.setIn(new FileInputStream("res/input3.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int testCase = Integer.parseInt(br.readLine());
		for (int test = 1; test <= testCase; test++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			result = 0;

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					visited = new boolean[101];
					dfs(i, j, i, j, 0, 1);
				}
			}
			if (result == 0)
				result = -1;
			sb.append("#").append(test).append(" ").append(result).append("\n");
		}
		System.out.println(sb.toString());
	}

	public static void dfs(int y, int x, int startY, int startX, int dir, int cnt) {
		for (int i = 0; i < 2; i++) {
			int nextDir = dir + i;
			if (nextDir >= 4)
				continue;

			int nextY = y + dy[dir];
			int nextX = x + dx[dir];
			if (nextY < 0 || nextY >= N || nextX < 0 || nextX >= N || visited[map[nextY][nextX]])
				continue;

			if (nextY == startY && nextX == startX) {
				if (cnt >= 4)
					result = Math.max(result, cnt);
				return;
			}

			visited[map[nextY][nextX]] = true;
			dfs(nextY, nextX, startY, startX, nextDir, cnt + 1);
			visited[map[nextY][nextX]] = false;

		}
	}
}
package d456.d4_2819;

import java.util.*;
import java.io.*;

public class Solution {
	static int[][] arr;
	static HashSet<Integer> set;

	static boolean inBound(int y, int x) {
		return 0 <= y && y < 4 && 0 <= x && x < 4;
	}

	static int[] dy = { 1, 0, -1, 0 };
	static int[] dx = { 0, 1, 0, -1 };

	static void dfs(int y, int x, int n, int depth) {
		if (depth == 7) {
			set.add(n);
			return;
		}

		for (int i = 0; i < 4; i++) {
			int ny = y + dy[i];
			int nx = x + dx[i];

			if (inBound(ny, nx)) {
				dfs(ny, nx, n * 10 + arr[ny][nx], depth + 1);
			}
		}
	}

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./solving/d456/d4_2819/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		final int TC = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= TC; tc++) {
			arr = new int[4][4];
			set = new HashSet<>();

			for (int i = 0; i < 4; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < 4; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 4; j++) {
					dfs(i, j, 0, 0);
				}
			}

			sb.append("#").append(tc).append(" ").append(set.size()).append("\n");
		}

		System.out.println(sb.toString());
		br.close();
	}
}

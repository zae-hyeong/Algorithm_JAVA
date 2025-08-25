package d456.d4_1868;

import java.util.*;
import java.io.*;

public class Solution {
	static int N, count;
	static int[][] map;
	static boolean[][] visited;

	static boolean isValid(int y, int x) {
		return 0 <= y && y < N && 0 <= x && x < N;
	}

	static void countElse() {
		for (int y = 0; y < N; y++)
			for (int x = 0; x < N; x++)
				if (!visited[y][x] && map[y][x] > 0)
					count++;
	}

	static int[] ddy = { -1, -1, -1, 0, 0, 1, 1, 1 };
	static int[] ddx = { -1, 0, 1, -1, 1, -1, 0, 1 };

	static void dfsFrom(int y, int x) {
		visited[y][x] = true;
		int ny, nx;

		for (int i = 0; i < 8; i++) {
			ny = y + ddy[i];
			nx = x + ddx[i];
			if (isValid(ny, nx) && !visited[ny][nx]) {
				visited[ny][nx] = true;
				if (map[ny][nx] == 0)
					dfsFrom(ny, nx);
			}
		}
	}

	static void calcZero() {
		for (int y = 0; y < N; y++) {
			for (int x = 0; x < N; x++) {
				if (map[y][x] == 0 && !visited[y][x]) {
					dfsFrom(y, x);
					count++;
				}
			}
		}
	}

	static void setMap() {
		int ny, nx, count;
		for (int y = 0; y < N; y++) {
			for (int x = 0; x < N; x++) {
				if (map[y][x] == -1)
					continue;
				count = 0;
				for (int k = 0; k < 8; k++) {
					ny = y + ddy[k];
					nx = x + ddx[k];
					if (isValid(ny, nx) && map[ny][nx] == -1)
						count++;
				}
				map[y][x] = count;
			}
		}
	}

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./solving/d456/d4_1868/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		final int TC = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= TC; tc++) {
			count = 0;
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			visited = new boolean[N][N];

			char[] tmp;
			for (int i = 0; i < N; i++) {
				tmp = br.readLine().toCharArray();
				for (int j = 0; j < N; j++) {
					map[i][j] = tmp[j] == '*' ? -1 : 0;
				}
			}

			setMap();

//			for (int[] m : map) System.out.println(Arrays.toString(m));
			calcZero();
//			for (boolean[] v : visited) System.out.println(Arrays.toString(v));
			countElse();

			sb.append("#").append(tc).append(" ").append(count).append("\n");
		}

		System.out.println(sb.toString());
		br.close();
	}
}

package gold.g2_1103_게임;

import java.util.*;
import java.io.*;

public class Main {
	static int W, H;
	static int[][] map, dp;
	static boolean[][] v;

	static int max;

	static int[] dy = { 1, 0, -1, 0 };
	static int[] dx = { 0, 1, 0, -1 };

	static void dfs(int y, int x, int depth) {
//		System.out.format("y : %d, x : %d, map[y][x] : %d, depth : %d\n", y, x, map[y][x], depth);

		max = Math.max(max, depth);
		dp[y][x] = depth;

		int ny, nx;
		for (int i = 0; i < 4; i++) {
			ny = y + dy[i] * map[y][x];
			nx = x + dx[i] * map[y][x];

			if (0 <= ny && ny < H && 0 <= nx && nx < W && map[ny][nx] >= 0) {
				if (v[ny][nx]) {
					System.out.println(-1);
					System.exit(0);
				}

				if (dp[ny][nx] >= depth + 1) 
					continue;

				v[ny][nx] = true;
				dfs(ny, nx, depth + 1);
				v[ny][nx] = false;
			}
		}
	}

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("./solving/gold/g2_1103_게임/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		map = new int[H][W];
		dp = new int[H][W];
		v = new boolean[H][W];

		String tmp;
		char c;
		for (int i = 0; i < H; i++) {
			tmp = br.readLine();
			for (int j = 0; j < W; j++) {
				c = tmp.charAt(j);
				map[i][j] = c == 'H' ? -1 : (int) c - '0';
			}
		}

		br.close();

//		for(int[] m: map) System.out.println(Arrays.toString(m));
		max = 0;
		dfs(0, 0, 1);

		System.out.println(max);
	}
}

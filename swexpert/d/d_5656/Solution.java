package d.d_5656;

import java.util.*;
import java.io.*;

public class Solution {
	static int N, W, H, result;
	static int[][] map, copyMap;

	static void clear() {
		ArrayDeque<Integer> ad = new ArrayDeque<>();
		for (int i = 0; i < W; i++) {
			ad.clear();

			for (int j = H - 1; j >= 0; j--) {
				if (copyMap[j][i] == 0)
					continue;

				ad.addLast(copyMap[j][i]);
				copyMap[j][i] = 0;
			}

			int y = H - 1;
			while (!ad.isEmpty()) {
				copyMap[y--][i] = ad.pollFirst();
			}
		}
	}

	static boolean isValid(int y, int x) {
		return 0 <= y && y < H && 0 <= x && x < W;
	}

	static int[] dy = { 1, 0, -1, 0 };
	static int[] dx = { 0, 1, 0, -1 };

	static void bfs(int sy, int sx) {
		ArrayDeque<int[]> q = new ArrayDeque<>();

		q.add(new int[] { sy, sx });

		int x, y, ny, nx;
		while (!q.isEmpty()) {
			int[] cur = q.poll();
			ny = y = cur[0];
			nx = x = cur[1];

			if (copyMap[y][x] == 0)
				continue;

			int init = copyMap[y][x];
			int num;
			copyMap[y][x] = 0;

			for (int d = 0; d < 4; d++) {
				num = init;
				ny = y;
				nx = x;
				while (--num > 0 && isValid(ny, nx)) {
					ny = ny + dy[d];
					nx = nx + dx[d];
					if (isValid(ny, nx) && copyMap[ny][nx] != 0)
						q.add(new int[] { ny, nx });
				}
			}
		}
	}

	static void checkCount() {
		int count = 0;
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				if (copyMap[i][j] != 0)
					count++;
			}
		}
		result = Math.min(result, count);
	}

	static int[] balls;

	static void throwBall() {
		for (int k = 0; k < H; k++) {
			copyMap[k] = Arrays.copyOf(map[k], W);
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < H; j++) {
				if (copyMap[j][balls[i]] == 0)
					continue;

//				for (int[] a : copyMap) System.out.println(Arrays.toString(a));
//				System.out.println(j + " : " + balls[i] + "=====================================");
				bfs(j, balls[i]);
				clear();
				break;
			}
//			for (int[] a : copyMap) System.out.println(Arrays.toString(a));
//			System.out.println("=====================================");
		}
	}

	static void perm(int n) {
		if (n == N) {
//			System.out.println("-----------------------------------" + Arrays.toString(balls));
			throwBall();
			checkCount();
			return;
		}

		for (int i = 0; i < W; i++) {
			balls[n] = i;
			perm(n + 1);
		}
	}

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./solving/d/d_5656/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		final int TC = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= TC; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());

			map = new int[H][W];
			copyMap = new int[H][W];
			balls = new int[N];

			for (int i = 0; i < H; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < W; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			result = Integer.MAX_VALUE;

			perm(0);

			sb.append("#").append(tc).append(" ").append(result).append("\n");
		}

		System.out.println(sb.toString());
		br.close();
	}
}

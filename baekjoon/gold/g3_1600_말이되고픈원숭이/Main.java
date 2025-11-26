package gold.g3_1600_말이되고픈원숭이;

import java.util.*;
import java.io.*;

public class Main {
	static int K, W, H;
	static boolean[][] isRoad;
	static boolean[][][] visited;

	static boolean isValid(int y, int x) {
		return 0 <= x && x < W && 0 <= y && y < H && isRoad[y][x];
	}

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./solving/gold/g3_1600_말이되고픈원숭이/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		K = Integer.parseInt(br.readLine());

		StringTokenizer st = new StringTokenizer(br.readLine());
		W = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());

		isRoad = new boolean[H][W];
		visited = new boolean[H][W][K + 1];

		for (int i = 0; i < H; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < W; j++) {
				isRoad[i][j] = Integer.parseInt(st.nextToken()) == 0 ? true : false;
			}
		}
		
		br.close();

		ArrayDeque<int[]> queue = new ArrayDeque<>();
		queue.add(new int[] { 0, 0, 0, 0 }); // y, x, sec, jumpCount
		visited[0][0][0] = true;
		
		int[] dy = { 0, 1, 0, -1 };
		int[] dx = { 1, 0, -1, 0 };
		int[] jumpY = { -2, -2, -1, -1, 1, 1, 2, 2 };
		int[] jumpX = { -1, 1, -2, 2, -2, 2, -1, 1 };

		int[] cur;
		int ny, nx, jumpCount, curSec;
		
		while (!queue.isEmpty()) {
			cur = queue.poll();
			curSec = cur[2];
			jumpCount = cur[3];

			if(cur[0] == H - 1 && cur[1] == W - 1) {
				System.out.println(curSec);
				return;
			}

			for (int d = 0; d < 4; d++) {
				ny = cur[0] + dy[d];
				nx = cur[1] + dx[d];

				if (isValid(ny, nx) && !visited[ny][nx][jumpCount]) {
					visited[ny][nx][jumpCount] = true;
					queue.add(new int[] { ny, nx, curSec + 1, jumpCount });
				}
			}

			if(jumpCount < K) {
				for (int d = 0; d < 8; d++) {
					ny = cur[0] + jumpY[d];
					nx = cur[1] + jumpX[d];
	
					if (isValid(ny, nx) && !visited[ny][nx][jumpCount + 1]) {
						visited[ny][nx][jumpCount + 1] = true;
						queue.add(new int[] { ny, nx, curSec + 1, jumpCount + 1 });
					}
				}
			}
		}

		System.out.println(-1);
	}
}

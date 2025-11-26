package gold.g3_1600_말이되고픈원숭이;

import java.util.*;
import java.io.*;

public class Main {
	static int K, W, H;
	static boolean[][] isRoad;
	static int[][][] sec;

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
		sec = new int[H][W][K + 1];

		for (int i = 0; i < H; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < W; j++) {
				isRoad[i][j] = Integer.parseInt(st.nextToken()) == 0 ? true : false;
			}
		}

		ArrayDeque<int[]> queue = new ArrayDeque<>();
		queue.add(new int[] { 0, 0, 0, 0 }); // y, x, sec, jumpCount
		sec[0][0][0] = 1;
		
		int[] dy = { 0, 1, 0, -1 };
		int[] dx = { 1, 0, -1, 0 };
		int[] jumpY = { -2, -2, -1, -1, 1, 1, 2, 2 };
		int[] jumpX = { -1, 1, -2, 2, -2, 2, -1, 1 };

		int[] cur;
		int ny, nx, jumpCount, curSec;
		
//		int debugCount = 0;
		while (!queue.isEmpty()) {
			cur = queue.poll();
//			System.out.println(++debugCount +" / poll : " + Arrays.toString(cur));
			curSec = cur[2];
			jumpCount = cur[3];

//			if(sec[cur[0]][cur[1]][jumpCount] > 0 && sec[cur[0]][cur[1]][jumpCount] < curSec) continue;
			if (cur[0] == H - 1 && cur[1] == W - 1) continue;

			for (int d = 0; d < 4; d++) {
				ny = cur[0] + dy[d];
				nx = cur[1] + dx[d];

				if (isValid(ny, nx) && sec[ny][nx][jumpCount] == 0) {
					sec[ny][nx][jumpCount] = curSec + 1;
					queue.add(new int[] { ny, nx, curSec + 1, jumpCount });
				}
			}

			if(jumpCount < K) {
				for (int d = 0; d < 8; d++) {
					ny = cur[0] + jumpY[d];
					nx = cur[1] + jumpX[d];
	
					if (isValid(ny, nx) && sec[ny][nx][jumpCount + 1] == 0) {
						sec[ny][nx][jumpCount] = curSec + 1;
						queue.add(new int[] { ny, nx, curSec + 1, jumpCount + 1 });
					}
				}
			}
		}

		int result = -1;
		for (int i = 0; i < K + 1; i++) {
			if (sec[H - 1][W - 1][i] > 0) result = Math.max(result, sec[H - 1][W - 1][i]);
		}

//		for (int k = 0; k < K + 1; k++) {
//			for (int i = 0; i < H; i++) {
//				for (int j = 0; j < W; j++) {
//					System.out.print(sec[i][j][k]);
//				}
//				System.out.println();
//			}
//			System.out.println("----------------------------");
//		}

		System.out.println(result);
		br.close();
	}
}

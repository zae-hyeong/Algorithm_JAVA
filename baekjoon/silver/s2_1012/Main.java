package silver.s2_1012;

import java.util.*;
import java.io.*;

public class Main {
	static int T, M, N, K; // TestCase 수, 배추밭 가로길이, 세로길이, 배추 수
	static int[][] arr = null;
	static boolean[][] visited = null;

	static int[] dy = { 1, 0, -1, 0 };
	static int[] dx = { 0, 1, 0, -1 };
	static ArrayDeque<int[]> queue = null;

	public static void bfsFrom(int fy, int fx) {
		queue = new ArrayDeque<>();
		queue.add(new int[] { fy, fx });
		int y, x, ny, nx;
		int[] tmp;

		while (queue.size() > 0) {
			tmp = queue.poll();
			y = tmp[0];
			x = tmp[1];

			for (int i = 0; i < 4; i++) {
				ny = y + dy[i];
				nx = x + dx[i];

				if (0 <= ny && ny < N && 0 <= nx && nx < M && !visited[ny][nx] && arr[ny][nx] == 1) {
					visited[ny][nx] = true;
					queue.add(new int[] { ny, nx });
				}
			}
		}
	}

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		T = Integer.parseInt(br.readLine());

		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());

			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			arr = new int[N][M];
			visited = new boolean[N][M];

			int y, x;
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				x = Integer.parseInt(st.nextToken());
				y = Integer.parseInt(st.nextToken());

				arr[y][x] = 1;
			}

			int count = 0;

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (visited[i][j])
						continue;

					visited[i][j] = true;
					if (arr[i][j] == 1) {
						bfsFrom(i, j);
						count++;
					}
				}
			}
			System.out.println(count);
		}
		br.close();
	}

}

package gold.g4_2665_미로만들기;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class Main {
	static int N, result = Integer.MAX_VALUE;
	static int[][] map;
	static boolean[][][] v;
	static int[] dy = { 1, 0, -1, 0 };
	static int[] dx = { 0, 1, 0, -1 };

	static boolean inBound(int y, int x) {
		return 0 <= y && y < N && 0 <= x && x < N;
	}

	static void bfs() {
		ArrayDeque<int[]> queue = new ArrayDeque<int[]>();

		queue.add(new int[] { 0, 0, 0 });
		v[0][0][0] = true;

		int ny, nx, breaks;
		int[] cur;
		while (!queue.isEmpty()) {
			cur = queue.poll();
			breaks = cur[2];

//			System.out.println(cur[0] + " : " + cur[1] + " : " + breaks);

			if (breaks > result)
				continue;

			if (cur[0] == N - 1 && cur[1] == N - 1) {

//				System.out.println(" >>>>>>>>>>>>>> " + cur[0] + " : " + cur[1] + " : " + breaks);

				result = Math.min(result, breaks);
				continue;
			}

			for (int d = 0; d < 4; d++) {
				ny = cur[0] + dy[d];
				nx = cur[1] + dx[d];
				breaks = cur[2];

//				System.out.println(ny +" : " + nx + " : " + breaks);

				if (inBound(ny, nx) && !v[ny][nx][breaks]) {
					if (map[ny][nx] == 0) { // 벽인 경우
						if(v[ny][nx][breaks + 1]) continue;
						
						breaks += 1;
					}

					v[ny][nx][breaks] = true;
					queue.add(new int[] { ny, nx, breaks });
				}
			}
		}
	}

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("solving/gold/g4_2665_미로만들기/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		v = new boolean[N][N][N * N];

		String tmp;
		for (int i = 0; i < N; i++) {
			tmp = br.readLine();
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(Character.toString(tmp.charAt(j)));
			}
		}

		bfs();

		System.out.println(result);
		br.close();
	}

}

package d789.d9_1949;

import java.io.*;
import java.util.*;

public class Solution {
	static int N, K, top, result;
	static int[][] arr;

	static boolean isValid(int y, int x) {
		return 0 <= y && y < N && 0 <= x && x < N;
	}

	static int[][][] visited;
	static ArrayDeque<int[]> ad;
	static int[] dy = { 1, 0, -1, 0 };
	static int[] dx = { 0, 1, 0, -1 };

	static void bfsFrom(int sY, int sX) {
		ad = new ArrayDeque<>();
		ad.offer(new int[] { sY, sX, 1, K }); // [y, x, dist, K, height]
		visited[sY][sX][K] = 1;

		int y, x, ny, nx, dist, height;
		int[] cur;
		while (!ad.isEmpty()) {
			cur = ad.poll();
			y = cur[0];
			x = cur[1];
			dist = cur[2];
			height = cur[4];

			if (arr[y][x] == top) {
				result = Math.max(result, dist);
				continue;
			}

			for (int i = 0; i < 4; i++) {
				ny = y + dy[i];
				nx = x + dy[i];

				if (isValid(ny, nx) && arr[ny][nx] > height && dist > visited[ny][nx][cur[3]]) {
					if (cur[3] == K) {
						ad.offer(new int[] { ny, nx, dist + 1, 0, Math.max(arr[ny][nx] - K, arr[y][x] + 1)});
						visited[ny][nx][0] = dist + 1;
						continue;
					}
					ad.offer(new int[] { ny, nx, cur[3], dist + 1 });
					visited[ny][nx][cur[3]] = dist + 1;
				}
			}
		}
	}
	
	static ArrayList<int[]> tops;
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./solving/d789/d9_1949/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuffer sb = new StringBuffer();

		final int TC = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= TC; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			arr = new int[N][N];
			visited = new int[N][N][K];
			result = 0;

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
					top = Math.max(top, arr[i][j]);
				}
			}
			

			sb.append("#").append(tc).append(" ").append(result).append("\n");
		}
		System.out.println(sb.toString());
		br.close();
	}
}

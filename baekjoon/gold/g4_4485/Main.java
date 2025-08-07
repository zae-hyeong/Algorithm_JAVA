package gold.g4_4485;

import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int[][] arr, sumArr;
	static boolean[][] visited;

	static int[] dy = { 1, 0, -1, 0 };
	static int[] dx = { 0, 1, 0, -1 };

	static boolean isValid(int y, int x) {
		return 0 <= y && y < N && 0 <= x && x < N && sumArr[y][x] == Integer.MAX_VALUE;
	}

	static void dijkstraFrom(int startI, int startJ) {
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1[2], o2[2]));

		pq.offer(new int[] { startI, startJ, arr[startI][startJ] });

		int y, x, ny, nx, sum;
		while (!pq.isEmpty()) {
			int[] cur = pq.poll();
			y = cur[0];
			x = cur[1];
			sum = cur[2];

			if (visited[y][x])
				continue;

			visited[y][x] = true;
			sumArr[y][x] = sum;

			for (int i = 0; i < 4; i++) {
				ny = y + dy[i];
				nx = x + dx[i];

				if (isValid(ny, nx) && sumArr[ny][nx] > sum + arr[ny][nx]) {
					pq.offer(new int[] { ny, nx, sum + arr[ny][nx] });
				}
			}
			
//			for (int[] a : sumArr) System.out.println(Arrays.toString(a));
		}
	}

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./solving/g4_4485/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int tc = 1;
		while (true) {
			N = Integer.parseInt(br.readLine());
			if (N == 0)
				break;

			arr = new int[N][N];
			sumArr = new int[N][N];
			visited = new boolean[N][N];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
					sumArr[i][j] = Integer.MAX_VALUE;
				}
			}

			dijkstraFrom(0, 0);

			sb.append("Problem ").append(tc++).append(": ").append(sumArr[N - 1][N - 1]).append("\n");
		}
		System.out.println(sb.toString());
		br.close();
	}
}
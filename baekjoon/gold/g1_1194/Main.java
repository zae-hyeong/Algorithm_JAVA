package gold.g1_1194;

import java.util.*;
import java.io.*;

public class Main {
	static int N, M;
	static char[][] map;
	static int[] start;
	static HashSet<String> set;

	static String stringMapper(int y, int x) {
		return "[" + y + "," + x + "]";
	}

	static boolean inBound(int y, int x) {
		return 0 <= y && y < N && 0 <= x && x < M;
	}

	static boolean isKey(char c) {
		return c == 'a' || c == 'b' || c == 'c' || c == 'd' || c == 'e' || c == 'f';
	}

	static boolean isDoor(char c) {
		return c == 'A' || c == 'B' || c == 'C' || c == 'D' || c == 'E' || c == 'F';
	}

	static int getKey(int keys, char key) {
		int c = 1 << (key - 'a');
		return keys | c;
	}

	static boolean canIOpen(int keys, char door) {
		int c = 1 << (int) (door - 'A');
		return c == (keys & c);
	}

	static boolean[][][] visited;
	static int[] dy = { 1, 0, -1, 0 };
	static int[] dx = { 0, 1, 0, -1 };

	static int bfs() {
		visited = new boolean[N][M][64];
		ArrayDeque<int[]> ad = new ArrayDeque<>();
		visited[start[0]][start[1]][0] = true;

		ad.add(new int[] { start[0], start[1], 0, 0 }); // y, x, keys, dist

		int y, x, keys, dist, ny, nx;
		while (!ad.isEmpty()) {
			int[] cur = ad.poll();
			y = cur[0];
			x = cur[1];
			keys = cur[2];
			dist = cur[3];

			if (set.contains(stringMapper(y, x))) {
//				System.out.println("END : " + stringMapper(y, x) + " / dist : " + dist);
				return dist;
			}

			for (int d = 0; d < 4; d++) {
				ny = y + dy[d];
				nx = x + dx[d];
				keys = cur[2];
				dist = cur[3];

				if (inBound(ny, nx) && !visited[ny][nx][keys] && map[ny][nx] != '#') {
					if (isDoor(map[ny][nx]) && !canIOpen(keys, map[ny][nx])) {
						continue;
					} else if (isKey(map[ny][nx])) {
						keys = getKey(keys, map[ny][nx]);
					}
					visited[ny][nx][keys] = true;
					ad.add(new int[] { ny, nx, keys, dist + 1 });
				}
			}
		}
		return -1;
	}

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("./solving/bj/bj_1194/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new char[N][];
		for (int i = 0; i < N; i++)
			map[i] = br.readLine().toCharArray();
		
		start = new int[2];
		set = new HashSet<>();
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == '0') {
					start[0] = i;
					start[1] = j;
				} else if (map[i][j] == '1') {
					set.add(stringMapper(i, j));
				}
			}
		}

		int result  = bfs();

		System.out.println(result);
		br.close();
	}
}

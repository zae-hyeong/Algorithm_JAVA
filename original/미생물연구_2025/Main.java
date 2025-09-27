package 미생물연구_2025;

import java.io.*;
import java.util.*;

public class Main {

	static int N, Q;
	static int[][] group;
	static boolean[][][] shape;
	static int[] size, heights, widths;
	
	static void putGroup() {
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o2[1], o1[1]));
		for (int i = 1; i <= Q; i++) {
			if(size[i] > 0) {
				pq.add(new int[] {i, size[i]});
			}
		}
		
		int[][] newGroup = new int[N][N];
		int[] cur;
		int curGroupId;
		while(!pq.isEmpty()) {
			cur = pq.poll();
			curGroupId = cur[0];
			
			for (int j = 0; j < N; j++) {
				for (int i = 0; i < N; i++) {
					
				}
			}
		}
	}

	static boolean inBound(int y, int x) {
		return 0 <= y && y < N && 0 <= x && x < N;
	}

	static int[] dy = { 1, 0, -1, 0 };
	static int[] dx = { 0, 1, 0, -1 };

	static boolean[][] v;

	static int bfsFrom(int y, int x, int groupId) {
		ArrayDeque<int[]> queue = new ArrayDeque<int[]>();

		int count = 1;
		v[y][x] = true;
		queue.add(new int[] { y, x, N - 1, N - 1 }); // y, x, shapeY, shapeX

		int[] cur;
		int ny, nx, shapeY, shapeX;
		while (!queue.isEmpty()) {
			cur = queue.poll();

			for (int d = 0; d < 4; d++) {
				ny = cur[0] + dy[d];
				nx = cur[1] + dx[d];

				shapeY = cur[2] + dy[d];
				shapeX = cur[3] + dx[d];

				if (inBound(ny, nx) && !v[ny][nx] && group[ny][nx] == groupId) {
					v[ny][nx] = true;
					count++;
					shape[groupId][shapeY][shapeX] = true;
					queue.add(new int[] { ny, nx, shapeY, shapeX });
				}
			}
		}

		return count;
	}

	static void checkGroups(int y1, int x1, int y2, int x2, int groupId) {
		v = new boolean[N][N];
		size = new int[Q + 1];

		int tmpGId;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				tmpGId = group[i][j];
				if (v[i][j])
					continue;
				if ((y1 <= i && i < y2) && (x1 <= j && j < x2))
					continue;
				if (tmpGId == 0)
					continue;

				if (size[tmpGId] > 0) {
					size[tmpGId] = 0;
					bfsFrom(i, j, 0);
					continue;
				}

				size[tmpGId] = bfsFrom(i, j, groupId);
			}
		}

		for (int i = y1; i < y2; i++) {
			for (int j = x1; j < x2; j++) {
				group[i][j] = groupId;
			}
		}
		
		size[groupId] = (y2 - y1) * (x2 - x1);
	}

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./original/미생물연구_2025/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		Q = Integer.parseInt(st.nextToken());

		shape = new boolean[Q + 1][2 * N][2 * N];
		size = new int[Q + 1];
		heights = new int[Q + 1];
		widths = new int[Q + 1];
		group = new int[N][N];
		for (int groupId = 1; groupId <= Q; groupId++) {
			st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());

			checkGroups(y1, x1, y2, x2, groupId);
			putGroups();
		}

		br.close();
	}
}

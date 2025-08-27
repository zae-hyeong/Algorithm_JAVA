package d.d_2382;

import java.util.*;
import java.io.*;

class Group {
	int num, y, x, dy, dx, idx;

	public Group(int num, int y, int x, int dy, int dx, int idx) {
		super();
		this.num = num;
		this.y = y;
		this.x = x;
		this.dy = dy;
		this.dx = dx;
		this.idx = idx;
	}

	int[] move() {
		this.y = this.y + this.dy;
		this.x = this.x + this.dx;

		return new int[] { this.y - this.dy, this.x - this.dx };
	}

	void mergeWith(Group g) {
		this.num += g.num;
		g.num = 0;
	}

	void stepRed() {
		this.num /= 2;
		this.dy = -this.dy;
		this.dx = -this.dx;
	}

	@Override
	public String toString() {

		return String.format("y : %d, x : %d, dy: %d, dx: %d, num : %d, idx : %d", this.y, this.x, this.dy, this.dx,
				this.num, this.idx);

	}
}

public class Solution {
	static int N, M, K;
	static List<Group>[][] map;
	static Group[] groups;

	static boolean isOutside(int y, int x) {
		return y == 0 || y == N - 1 || x == 0 || x == N - 1;
	}

	static void mergeGroups(List<Group> gs) {
		int maxIdx = -1;
		int maxVal = 0;

		for (Group g : gs) {
			if (maxVal < g.num) {
				maxVal = g.num;
				maxIdx = g.idx;
			}
		}

		for (Group g : gs) {
			if (g.idx == maxIdx)
				continue;

			groups[maxIdx].mergeWith(g);
		}

		gs.clear();
		gs.add(groups[maxIdx]);
	}

	static void check() {
		Group curGroup;
		int[] cor;
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < K; j++) {
				curGroup = groups[j];
				if (curGroup.num == 0)
					continue;

				cor = curGroup.move(); // 이전 위치(y, x), 현재 위치(y, x)
				map[cor[0]][cor[1]].remove(curGroup);
				map[curGroup.y][curGroup.x].add(curGroup);

				if (isOutside(curGroup.y, curGroup.x))
					curGroup.stepRed();

			}

			for (int ii = 0; ii < N; ii++) {
				for (int jj = 0; jj < N; jj++) {
					if (map[ii][jj].size() > 1)
						mergeGroups(map[ii][jj]);
				}
			}
		}
	}

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./solving/d/d_2382/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		int[] dy = { 0, -1, 1, 0, 0 };
		int[] dx = { 0, 0, 0, -1, 1 };
		final int TC = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= TC; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());

			map = new ArrayList[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					map[i][j] = new ArrayList();
				}
			}
			groups = new Group[K];

			int y, x, num, dir;
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				y = Integer.parseInt(st.nextToken());
				x = Integer.parseInt(st.nextToken());
				num = Integer.parseInt(st.nextToken());
				dir = Integer.parseInt(st.nextToken());

				groups[i] = new Group(num, y, x, dy[dir], dx[dir], i);
				map[y][x].add(groups[i]);
			}

			check();

			int result = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (map[i][j].size() > 0)
						result += map[i][j].get(0).num;
				}
			}
			sb.append("#").append(tc).append(" ").append(result).append("\n");
		}

		System.out.println(sb.toString());
		br.close();
	}
}

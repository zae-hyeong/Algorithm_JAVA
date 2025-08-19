package d123.d3_1873;

import java.io.*;
import java.util.*;

class Tank {
	char dir; // 화살표
	int curPosY;
	int curPosX;

	Tank(char dir, int i, int j) {
		this.dir = dir;
		this.curPosY = i;
		this.curPosX = j;
	}
}

public class Solution {
	static int H, W, N;
	static char[][] map;
	static char[] inputs;

	static boolean isTank(char something) {
		return something == 'v' || something == '^' || something == '<' || something == '>';
	}
	
	static boolean isValid(int y, int x) {
		return 0 <= y && y < H && 0 <= x && x < W;
	}
	
	static boolean isMove(char c) {
		return c == 'U' || c == 'D' || c == 'L' || c == 'R';
	}

	static char dirMapper(char c) {
		switch (c) {
		case 'v':
			return 'D';
		case '<':
			return 'L';
		case '>':
			return 'R';
		case '^':
			return 'U';
		case 'D':
			return 'v';
		case 'L':
			return '<';
		case 'R':
			return '>';
		case 'U':
			return '^';
		}
		return 'X';
	}

	static Tank findTank() {
		for (int i = 0; i < H; i++)
			for (int j = 0; j < W; j++)
				if (isTank(map[i][j]))
					return new Tank(map[i][j], i, j);

		return null;
	}

	static int[] getDyDx(char input) {
		int dy = 0, dx = 0;

		switch (input) {
		case 'U':
			dy = -1;
			break;
		case 'R':
			dx = 1;
			break;
		case 'L':
			dx = -1;
			break;
		case 'D':
			dy = 1;
			break;
		}

		return new int[] { dy, dx };
	}

	static void move(Tank tank, char input) {
		tank.dir = dirMapper(input);

		int[] d = getDyDx(input);
		int dy = d[0], dx = d[1];

		int ny = tank.curPosY + dy;
		int nx = tank.curPosX + dx;

		if (isValid(ny, nx) && map[ny][nx] == '.') {
			map[tank.curPosY][tank.curPosX] = '.';
			tank.curPosY = ny;
			tank.curPosX = nx;
		}

		map[tank.curPosY][tank.curPosX] = tank.dir;
	}

	static void shoot(Tank tank) {
		int[] d = getDyDx(dirMapper(tank.dir));
		int dy = d[0], dx = d[1];

		int ny = tank.curPosY + dy, nx = tank.curPosX + dx;
		while (isValid(ny, nx)) {
			if (map[ny][nx] == '*') {
				map[ny][nx] = '.';
				return;
			} else if (map[ny][nx] == '#')
				return;

			ny = ny + dy;
			nx = nx + dx;
		}
	}

	static void play() {
		Tank tank = findTank();

		for (char input : inputs) {
			if (isMove(input))
				move(tank, input);
			else {
				shoot(tank);
			}

//			for(char[] cs: map) System.out.println(cs);
//			System.out.println("-------------------------");
		}
	}

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./solving/d3_1873/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuffer sb = new StringBuffer();

		final int TC = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= TC; tc++) {
			st = new StringTokenizer(br.readLine());

			H = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			map = new char[H][];

			for (int i = 0; i < H; i++) {
				map[i] = br.readLine().toCharArray();
			}

			N = Integer.parseInt(br.readLine());
			inputs = br.readLine().toCharArray();

			play();

			sb.append("#").append(tc).append(" ");
			for (int i = 0; i < H; i++) {
				for (int j = 0; j < W; j++) {
					sb.append(map[i][j]);
				}
				sb.append("\n");
			}
		}
		System.out.println(sb.toString());
		br.close();
	}
}

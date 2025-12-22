package gold.g3_23288_주사위굴리기2;

import java.io.*;
import java.util.*;

public class Main {
	static int H, W, K;
	static int[][] map, pointMap;
	static boolean[][] v;

	static int[] dy = { 0, 1, 0, -1 }; // 동, 남, 서, 북
	static int[] dx = { 1, 0, -1, 0 };

	static class Dice {
		static int dir;
		static int posY, posX;
		static int[][] floorMapper;

		static {
			dir = 0; // 시작 방향 = 동쪽
			posY = posX = 0; // 시작점 = [0, 0]
			floorMapper = new int[3][3];
			floorMapper[0][1] = 2; // 위
			floorMapper[1][1] = 6; // 바닥
			floorMapper[2][1] = 5; // 아래
			floorMapper[1][0] = 4; // 왼
			floorMapper[1][2] = 3; // 오
		}

		static void rotateLeft() {
			dir = dir >= 1 ? dir - 1 : 3;
		}

		static void rotateRight() {
			dir = (dir + 1) % 4;
		}
		
		static int getFloorNum() {
			return floorMapper[1][1];
		}

		static int[] move() {
			if (!inBound(posY + dy[dir], posX + dx[dir])) { // Bound를 넘어가면 방향 반대로
				dir = (dir + 2) % 4;
			}

			if (dir == 0) { // 동
				floorMapper[1][0] = floorMapper[1][1];
				floorMapper[1][1] = floorMapper[1][2];
				floorMapper[1][2] = 7 - floorMapper[1][0];
			} else if (dir == 2) { // 서
				floorMapper[1][2] = floorMapper[1][1];
				floorMapper[1][1] = floorMapper[1][0];
				floorMapper[1][0] = 7 - floorMapper[1][2];
			} else if (dir == 1) { // 남
				floorMapper[0][1] = floorMapper[1][1];
				floorMapper[1][1] = floorMapper[2][1];
				floorMapper[2][1] = 7 - floorMapper[0][1];
			} else if (dir == 3) { // 북
				floorMapper[2][1] = floorMapper[1][1];
				floorMapper[1][1] = floorMapper[0][1];
				floorMapper[0][1] = 7 - floorMapper[2][1];
			}
//			System.out.println("--------------");
//			System.out.format("  %d   \n", floorMapper[0][1]);
//			System.out.format("%d %d %d\n", floorMapper[1][0], floorMapper[1][1], floorMapper[1][2]);
//			System.out.format("  %d   \n", floorMapper[2][1]);
//			System.out.println("--------------");

			posY = posY + dy[dir];
			posX = posX + dx[dir];
			return new int[] { posY, posX };
		}

	}

	static boolean inBound(int y, int x) {
		return 0 <= x && x < W && 0 <= y && y < H;
	}

	static HashMap<Integer, Integer> idCountMapper;

	static void dfsFrom(int y, int x, int id, int num) {
		v[y][x] = true;
		pointMap[y][x] = id;
		idCountMapper.put(id, idCountMapper.get(id) + 1);

		int ny, nx;
		for (int d = 0; d < 4; d++) {
			ny = y + dy[d];
			nx = x + dx[d];
			if (inBound(ny, nx) && !v[ny][nx] && num == map[ny][nx]) {
				dfsFrom(ny, nx, id, num);
			}
		}
	}

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./solving/gold/g3_23288_주사위굴리기2/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		map = new int[H][W];
		pointMap = new int[H][W];

		for (int i = 0; i < H; i++) {
			st = new StringTokenizer(br.readLine());

			for (int j = 0; j < W; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// pointMap 만들기
		v = new boolean[H][W];
		int id = 1;
		idCountMapper = new HashMap<>();
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				if (v[i][j])
					continue;
				
				idCountMapper.put(id, 0);
				dfsFrom(i, j, id, map[i][j]);
				id++;
			}
		}
		
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				pointMap[i][j] = idCountMapper.get(pointMap[i][j]) * map[i][j];
			}
		}
		
		// for(int[] p: pointMap) System.out.println(Arrays.toString(p));
		
		// 본격적으로 주사위 움직이기
		int[] curPos;
		int totalPoint = 0;
		for(int k = 0; k < K; k++) {
			curPos = Dice.move();
			
			if(Dice.getFloorNum() > map[curPos[0]][curPos[1]]) {
				Dice.rotateRight();
			} else if(Dice.getFloorNum() < map[curPos[0]][curPos[1]]) {
				Dice.rotateLeft();
			}
			totalPoint += pointMap[curPos[0]][curPos[1]];
			
//			System.out.format("curPos: [%d, %d], dir: %d / floorNum : %d / totalPoint: %d\n", curPos[0], curPos[1], dice.dir, dice.getFloorNum(), totalPoint);
//			System.out.println("###############################################################");
		}

		System.out.println(totalPoint);
		br.close();
	}
}

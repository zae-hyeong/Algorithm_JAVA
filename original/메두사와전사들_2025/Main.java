package 메두사와전사들_2025;

import java.io.*;
import java.util.*;

class RouteNode {
	int y;
	int x;
	LinkedList<int[]> route;

	public RouteNode(int y, int x, LinkedList<int[]> route) {
		this.y = y;
		this.x = x;
		this.route = route;
	}
}

class HunterNode {
	int y;
	int x;
	ArrayList<int[]> dir;

	public HunterNode(int y, int x, ArrayList<int[]> dir) {
		super();
		this.y = y;
		this.x = x;
		this.dir = dir;
	}

	@Override
	public String toString() {
		String str = "";
		for (int[] a : dir)
			str += (Arrays.toString(a) + " ");
		return String.format("[%d, %d, dir : [" + str + "]]", y, x);
	}
}

public class Main {
	static int N, M;
	static Set<Integer>[][] hunterPos;
	static int result1, result2, result3;

	static ArrayList<int[]> hunterDirMapper(int my, int mx, int hy, int hx) {
		int x = hx - mx;
		int y = hy - my;
		ArrayList<int[]> dir = new ArrayList<>();
//		System.out.println(String.format("%d %d %d %d / y : x >> %d %d", my, mx, hy, hx, y, x));

		// 일단 상하좌우 방향 추가
		if (Math.abs(x) > Math.abs(y)) { // 좌우
			if (x > 0) // 좌
				dir.add(new int[] { 0, -1 });
			else if (x < 0) // 우
				dir.add(new int[] { 0, 1 });
		} else if (Math.abs(x) < Math.abs(y)) { // 상하
			if (y < 0) // 상
				dir.add(new int[] { -1, 0 });
			else if (y > 0) // 하
				dir.add(new int[] { 1, 0 });
		}

		// 대각 방향 추가
		if (x > 0 && y < 0) { // 1사분면
			dir.add(new int[] { -1, 1 });
		} else if (x > 0 && y > 0) { // 2사분면
			dir.add(new int[] { 1, 1 });
		} else if (x < 0 && y > 0) { // 3사분면
			dir.add(new int[] { 1, -1 });
		} else if (x < 0 && y < 0) { // 4사분면
			dir.add(new int[] { -1, -1 });
		}

		return dir;
	}

	static boolean[][] watchFrom(int posY, int posX) {
		int[][][] DIR = { { { -1, -1 }, { -1, 0 }, { -1, 1 } }, // 상
				{ { 1, 1 }, { 1, 0 }, { 1, -1 } }, // 하
				{ { -1, -1 }, { 0, -1 }, { 1, -1 } }, // 좌
				{ { -1, 1 }, { 0, 1 }, { 1, 1 } }, // 우
		};

		/*
		 * int[][][] hunterDIR = { {{-1, -1}, {-1, 0}, {-1, 1}}, {{0, -1}, {0, 0}, {0,
		 * 1}}, {{1, -1}, {1, 0}, {1, 1}}, };
		 */

		int maxHunterNum = -1;
		int maxDir = -1;
		boolean[][][] isPoison = new boolean[4][N][N];

		for (int D = 0; D < 4; D++) { // 4개 방향에 대해
			ArrayDeque<HunterNode> hunterQueue = new ArrayDeque<>();
			ArrayDeque<int[]> bfsQueue = new ArrayDeque<int[]>();
			bfsQueue.add(new int[] { posY, posX });

			int[] cur;
			int y, x, ny, nx;
			ArrayList<int[]> dir;
			while (!bfsQueue.isEmpty()) {
				cur = bfsQueue.poll();
				y = cur[0];
				x = cur[1];

				for (int d = 0; d < 3; d++) {
					ny = y + DIR[D][d][0];
					nx = x + DIR[D][d][1];

					if (inBound(ny, nx) && !isPoison[D][ny][nx]) {
						isPoison[D][ny][nx] = true;
						bfsQueue.add(new int[] { ny, nx });
						if (hunterPos[ny][nx].size() > 0) {
							dir = hunterDirMapper(posY, posX, ny, nx);
							hunterQueue.add(new HunterNode(ny, nx, dir));
						}
					}
				}
			}

			// 사냥꾼 큐로 BFS(감염 안된 영역 확인)
			HunterNode curHunter;
			int hunterNum = 0; // 오염당한 위치에 사냥꾼 수
			/*
			 * for (int i = 0; i < N; i++) { for (int j = 0; j < N; j++) {
			 * System.out.print(hunterPos[i][j].size() + " "); } System.out.println(); }
			 */

//			System.out.println(hunterQueue.toString());
			while (!hunterQueue.isEmpty()) {
				curHunter = hunterQueue.poll(); // 시작점
				if (!isPoison[D][curHunter.y][curHunter.x])
					continue;

				hunterNum += hunterPos[curHunter.y][curHunter.x].size();

				for (int[] d : curHunter.dir) {
					ny = curHunter.y + d[0];
					nx = curHunter.x + d[1];
					if (inBound(ny, nx) && isPoison[D][ny][nx]) {
						isPoison[D][ny][nx] = false;
						hunterQueue.add(new HunterNode(ny, nx, curHunter.dir));
					}
				}
			}

//			for(boolean[] v: isPoison[D])System.out.println(Arrays.toString(v));
//			System.out.println("------------------");

			/*
			 * for (int i = 0; i < N; i++) { for (int j = 0; j < N; j++) {
			 * if(isPoison[D][i][j] && hunterPos[i][j].size() > 0) { hunterNum +=
			 * hunterPos[i][j].size(); } } }
			 */

			// 가장 많이 사냥당한 전사가 있는 방향 선정
			if (maxHunterNum < hunterNum) {
				maxHunterNum = hunterNum;
				maxDir = D;
			}

		}

		result2 = maxHunterNum;

//		System.out.println("maxHunter : " + maxHunterNum+ " ----------------- maxDir : " + maxDir);
//		for(boolean[] v : isPoison[maxDir]) System.out.println(Arrays.toString(v));
		return isPoison[maxDir];
		// 이제 전사들 움직일 차례임
	}

	static int[] dy1 = { -1, 1, 0, 0 }; // 상하좌우
	static int[] dx1 = { 0, 0, -1, 1 };
	static int[] dy2 = { 0, 0, -1, 1 }; // 좌우상하
	static int[] dx2 = { -1, 1, 0, 0 };

	static boolean inBound(int y, int x) {
		return 0 <= y && y < N && 0 <= x && x < N;
	}

	static LinkedList<int[]> getRoute(int startY, int startX, int endY, int endX, boolean[][] isRoad) {
		ArrayDeque<RouteNode> queue = new ArrayDeque<RouteNode>();
		boolean[][] v = new boolean[N][N];

		queue.add(new RouteNode(startY, startX, new LinkedList<>()));
		v[startY][startX] = true;

		RouteNode curNode;
		int ny, nx;
		LinkedList<int[]> newRoute;
		while (!queue.isEmpty()) {
			curNode = queue.poll();
//			System.out.println(curNode.y + " : " + curNode.x);
//			for(boolean[] vv : v) System.out.println(Arrays.toString(vv));

			for (int d = 0; d < 4; d++) {
				ny = curNode.y + dy1[d];
				nx = curNode.x + dx1[d];

				if (inBound(ny, nx) && !v[ny][nx] && isRoad[ny][nx]) {
					v[ny][nx] = true;
					newRoute = new LinkedList<int[]>(curNode.route);
					newRoute.addLast(new int[] { ny, nx });

					if (ny == endY && nx == endX)
						return newRoute;

					queue.add(new RouteNode(ny, nx, newRoute));
				}
			}
		}

		return null; // 도달하지 못하는 경우
	}

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./original/메두사와전사들_2025/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		/* input 시작 */
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		int[] startPoint = new int[2];
		startPoint[0] = Integer.parseInt(st.nextToken());
		startPoint[1] = Integer.parseInt(st.nextToken());

		int[] endPoint = new int[2];
		endPoint[0] = Integer.parseInt(st.nextToken());
		endPoint[1] = Integer.parseInt(st.nextToken());

		hunterPos = new HashSet[N][N];
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				hunterPos[i][j] = new HashSet<>();

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			hunterPos[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())].add(i);
		}

		boolean[][] isRoad = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				isRoad[i][j] = Integer.parseInt(st.nextToken()) == 0 ? true : false;
			}
		}

		br.close();
		/* input 끝 */

//		System.out.println(startPoint[0]+ " : " + startPoint[1] + " / "+endPoint[0] + " : "+ endPoint[1]);

		LinkedList<int[]> route = getRoute(startPoint[0], startPoint[1], endPoint[0], endPoint[1], isRoad);
		if (route == null) {
			System.out.println(-1);
			return;
		}

		while (!route.isEmpty()) {
			int[] posM = route.pollFirst();
			// TODO: 그 칸에 전사 있으면 말살
			boolean[][] poisonArea = watchFrom(posM[0], posM[1]);
//			for(boolean[] v: poisonArea)System.out.println(Arrays.toString(v));
//			System.out.println("--------------");
		}

		System.out.println(result1 + " " + result2 + " " + result3);
	}
}

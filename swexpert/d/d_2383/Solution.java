package d.d_2383;

import java.util.*;
import java.io.*;

public class Solution {
	static int N, numOfPerson, result;
	static int[][] map;
	static int[][] stairs; // n번 계단 -> y좌표, x 좌표, cost

	static int[][] distances;

	static int[] targetStair;

	static int simulation(int stairNum) {
		// 계단에 배정된 사람들의 도착시각(=거리) 수집
		ArrayList<Integer> arrive = new ArrayList<>();
		for (int i = 1; i <= numOfPerson; i++) 
			if (targetStair[i] == stairNum) 
				arrive.add(distances[stairNum][i]);
			
		
		if (arrive.isEmpty())
			return 0;

		// 도착시각 오름차순 정렬
		Collections.sort(arrive);

		// 계단 길이
		int L = stairs[stairNum][2];

		// 현재 계단에서 내려가는 사람들의 "끝나는 시각"을 담는 최소 힙
		PriorityQueue<Integer> heap = new PriorityQueue<>();

		int lastEnd = 0;
		for (int a : arrive) {
			int ready = a + 1; // 계단 입구 도착 후 1분 뒤에 탑승 가능

			// 이미 3명이 내려가는 중이면, 가장 먼저 끝나는 사람 한 명이 끝날 때까지 기다림
			if (heap.size() == 3) {
				int earliestEnd = heap.poll();
				// 이 사람이 끝나야 내가 계단에 올라탈 수 있음
				ready = Math.max(ready, earliestEnd);
			}

			int end = ready + L; // 탑승 시작~종료까지 L분
			heap.offer(end);
			lastEnd = Math.max(lastEnd, end);
		}

		return lastEnd;
	}

	static void subset(int idx) {
		if (idx > numOfPerson) {
//			System.out.println(Arrays.toString(targetStair));
			int time1 = simulation(0);
			int time2 = simulation(1);
//			System.out.println(Arrays.toString(targetStair) + " >> time1 : " + time1 + " / time2 : " + time2);
			if (result > Math.max(time1, time2))
				result = Math.max(time1, time2);
			return;
		}

		targetStair[idx] = 0;
		subset(idx + 1);
		targetStair[idx] = 1;
		subset(idx + 1);
	}

	static int[] dy = { 1, 0, -1, 0 };
	static int[] dx = { 0, 1, 0, -1 };

	static boolean inBound(int y, int x) {
		return 0 <= y && y < N && 0 <= x && x < N;
	}

	static void bfsFrom(int sy, int sx, int stairNum) {
		boolean[][] v = new boolean[N][N];
		ArrayDeque<int[]> ad = new ArrayDeque<>();

		ad.add(new int[] { sy, sx, 0 });
		v[sy][sx] = true;

		int y, x, cost, ny, nx;
		int[] cur;
		while (!ad.isEmpty()) {
			cur = ad.poll();
			y = cur[0];
			x = cur[1];
			cost = cur[2];

			for (int d = 0; d < 4; d++) {
				ny = y + dy[d];
				nx = x + dx[d];

				if (inBound(ny, nx) && !v[ny][nx]) {
					ad.add(new int[] { ny, nx, cost + 1 });
					v[ny][nx] = true;
					if (map[ny][nx] > 0) {
						distances[stairNum][map[ny][nx]] = cost + 1;
					}
				}
			}
		}
	}

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("./solving/d/d_2383/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		final int TC = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= TC; tc++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			stairs = new int[][] { { -1, -1, -1 }, { -1, -1, -1 } };

			numOfPerson = 0;

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());

					if (map[i][j] > 1) { // 계단
						if (stairs[0][0] == -1) {
							stairs[0][0] = i;
							stairs[0][1] = j;
							stairs[0][2] = map[i][j];
							map[i][j] = -1;
						} else {
							stairs[1][0] = i;
							stairs[1][1] = j;
							stairs[1][2] = map[i][j];
							map[i][j] = -1;
						}
					} else if (map[i][j] == 1) {
						map[i][j] = ++numOfPerson;
					}
				}
			}

//			for (int[] a : map) System.out.println(Arrays.toString(a));

			distances = new int[2][numOfPerson + 1];
			bfsFrom(stairs[0][0], stairs[0][1], 0);
			bfsFrom(stairs[1][0], stairs[1][1], 1);

//			for(int[] d: distances) System.out.println(Arrays.toString(d));

			targetStair = new int[numOfPerson + 1];
			result = Integer.MAX_VALUE;
			subset(1);

			sb.append("#").append(tc).append(" ").append(result).append("\n");
		}

		System.out.println(sb.toString());
		br.close();
	}
}

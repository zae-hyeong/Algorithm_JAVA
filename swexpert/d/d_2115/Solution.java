package d.d_2115;

import java.io.*;
import java.util.*;

public class Solution {
	static int N, M, C;
	static int[][] map;
	static int[][] maxArr;

	static ArrayList<Integer> tmp;
	static int tmpMax;

	static void makeSubset(int startY, int startX, int m, int sum, int realSum) {
		if (m == M) {
//			System.out.println("realSum : " + realSum);
			maxArr[startY][startX] = Math.max(realSum, maxArr[startY][startX]);
			return;
		}

		int curVal = map[startY][startX + m];

		makeSubset(startY, startX, m + 1, sum, realSum);
		if (sum + curVal <= C)
			makeSubset(startY, startX, m + 1, sum + curVal, realSum + (curVal * curVal));
	}

	static void calc(int startY, int startX) {
		tmp = new ArrayList<>();
		tmpMax = -1;

		makeSubset(startY, startX, 0, 0, 0);
	}

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./solving/d/d_2115/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		final int TC = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= TC; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			C = Integer.parseInt(st.nextToken());
			map = new int[N][N];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			int loop = N - M + 1;
			maxArr = new int[N][loop];

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < loop; j++) {
					calc(i, j);
				}
			}
//			for (int[] a : maxArr) System.out.println(Arrays.toString(a));

			int max = -1;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < loop; j++) {
			        for (int jj = j + M; jj < loop; jj++) {
			            max = Math.max(max, maxArr[i][j] + maxArr[i][jj]);
			        }

			        // 아래 행들 전부
			        for (int ii = i + 1; ii < N; ii++) {
			            for (int jj = 0; jj < loop; jj++) {
			                max = Math.max(max, maxArr[i][j] + maxArr[ii][jj]);
			            }
			        }
				}
			}

			sb.append("#").append(tc).append(" ").append(max).append("\n");
		}

		System.out.println(sb.toString());
		br.close();
	}
}

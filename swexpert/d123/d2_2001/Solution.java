package d123.d2_2001;

import java.io.*;
import java.util.*;

public class Solution {
	static int N, M, maxVal;
	static int[][] arr, sumArr;

	static void makeSumArr() {
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				sumArr[i][j] = arr[i - 1][j - 1] + sumArr[i - 1][j] + sumArr[i][j - 1] - sumArr[i - 1][j - 1];
			}
		}
	}

	static void catchFlies() {
		int sum;
		for (int i = M; i <= N; i++) {
			for (int j = M; j <= N; j++) {
				sum = sumArr[i][j] - sumArr[i][j - M] - sumArr[i - M][j] + sumArr[i - M][j - M];
				maxVal = Math.max(maxVal, sum);
			}
		}
	}

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./solving/d2_2001/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();

		final int TC = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= TC; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			arr = new int[N][N];
			sumArr = new int[N + 1][N + 1];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
					sumArr[i + 1][j + 1] = arr[i][j];
				}
			}

			maxVal = 0;
			makeSumArr();
			catchFlies();

			sb.append("#").append(tc).append(" ").append(maxVal).append("\n");
		}

		System.out.print(sb);
		br.close();
	}
}

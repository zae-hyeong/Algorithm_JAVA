package d123.d3_5215;

import java.util.*;
import java.io.*;

public class Solution_dp {
	static int N, L, result;
	static int[] scores, cals;

	static int[][] dp;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("swexpert/d123/d3_5215/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		final int TC = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= TC; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			L = Integer.parseInt(st.nextToken());
			scores = new int[N + 1]; // 점수
			cals = new int[N + 1]; // 칼로리

			dp = new int[N + 1][L + 1];

			for (int i = 1; i <= N; i++) {
				st = new StringTokenizer(br.readLine());
				scores[i] = Integer.parseInt(st.nextToken());
				cals[i] = Integer.parseInt(st.nextToken());
			}

			for (int idx = 1; idx <= N; idx++) {
				for (int w = 1; w <= L; w++) {
					if (cals[idx] > w) {
						dp[idx][w] = dp[idx - 1][w];
					} else {
						dp[idx][w] = Math.max(dp[idx - 1][w], dp[idx - 1][w - cals[idx]] + scores[idx]);
					}
				}
			}

			sb.append("#").append(tc).append(" ").append(dp[N][L]).append("\n");
		}

		System.out.println(sb.toString());
		br.close();
	}
}
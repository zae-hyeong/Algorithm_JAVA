package gold.g3_11066_파일합치기;

import java.util.*;
import java.io.*;

public class Main {
	static int N;
	static int[] arr, sum;
	static int[][] dp;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./solving/gold/g3_11066_파일합치기/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		final int TC = Integer.parseInt(br.readLine());
		for (int tc = 0; tc < TC; tc++) {
			N = Integer.parseInt(br.readLine());

			arr = new int[N + 1];
			sum = new int[N + 1];
			dp = new int[N + 1][N + 1];

			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
				sum[i] = sum[i - 1] + arr[i];
			}
			
			for(int range = 1; range <= N; range++) {
				for(int i = 1; i <= N - range; i++) {
					dp[i][i + range] = Integer.MAX_VALUE;
					
					for(int k = i; k < i + range; k++) {
						dp[i][i + range] = Math.min(
								dp[i][i + range], 
								dp[i][k] + dp[k + 1][i + range] + sum[i+range] - sum[i - 1]);
					}
				}
			}
			
			System.out.println(dp[1][N]);
		}

		br.close();
	}
}

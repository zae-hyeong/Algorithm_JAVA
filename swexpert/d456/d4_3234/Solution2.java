package d456.d4_3234;

import java.io.*;
import java.util.*;

public class Solution2 {
	static int T, N;
	static int[][] dp;
	static int[] W;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./swexpert/d45/d4_3234/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		T = Integer.parseInt(br.readLine());

		StringTokenizer st;
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());

			W = new int[N];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++)
				W[i] = Integer.parseInt(st.nextToken());

			int totalW = 0;
			for (int i = 0; i < N; i++)
				totalW += W[i];

			dp = new int[1 << N][totalW + 1];
			for (int i = 0; i < (1 << N); i++)
				Arrays.fill(dp[i], -1);

			System.out.printf("#%d %d\n", tc, dfs(0, 0));
		}
	}

	private static int dfs(int mask, int leftSum) {
		if (mask == (1 << N) - 1)
			return 1;

		if (dp[mask][leftSum] != -1)
			return dp[mask][leftSum];

		int sum = 0;
		for (int i = 0; i < N; i++) {
			if ((mask & (1 << i)) != 0) {
				sum += W[i];
			}
		}

		int rightSum = sum - leftSum;
		int cnt = 0;

		for (int i = 0; i < N; i++) {
			if ((mask & (1 << i)) == 0) {
				if (leftSum >= rightSum + W[i]) {
					cnt += dfs(mask | (1 << i), leftSum);
				}
				cnt += dfs(mask | (1 << i), leftSum + W[i]);
			}
		}

		return dp[mask][leftSum] = cnt;
	}

}

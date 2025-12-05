package gold.g2_3687_성냥깨비;

import java.io.*;

public class Main {
	static long[] dp;
	
	static void makeMinDp() {
		int[] count = {1, 7, 4, 2, 0, 8};
		for (int i = 9; i <= 100; ++i) {
			dp[i] = Long.MAX_VALUE;
			for (int j = 2; j <= 7; ++j) {
				dp[i] = Math.min((dp[i-j] * 10) + count[j-2], dp[i]);
			}
		}
	}
	
	static String getMax(int n) {
		StringBuilder sb = new StringBuilder();

		if (n % 2 == 1) { // 홀수
			sb.append(7);
			n -= 3;
		}

		while (n > 0) {
			sb.append(1);
			n -= 2;
		}

		return sb.toString();
	}

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("./solving/gold/g2_3687_성냥깨비/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		final int TC = Integer.parseInt(br.readLine());
		dp = new long[101];
		dp[2] = 1;
		dp[3] = 7;
		dp[4] = 4;
		dp[5] = 2;
		dp[6] = 6;
		dp[7] = 8;
		dp[8] = 10;
			
		makeMinDp();
		
		int N;
		StringBuilder sb = new StringBuilder();
		for (int tc = 0; tc < TC; tc++) {
			N = Integer.parseInt(br.readLine());
			
			sb.append(dp[N]).append(" ").append(getMax(N)).append("\n");
		}
		
		System.out.print(sb.toString());
		br.close();
	}
}

package silver.s3_2579_계단오르기;

import java.io.*;

public class Main {
	static int N;
	static int[] arr, dp;
	
	static int getMaxOf(int step) {
		if(dp[step] > 0) return dp[step];
		if(step <= 0) return 0;
		
//		System.out.println(step);
		return dp[step] = arr[step] + Math.max(getMaxOf(step - 3) + arr[step - 1], getMaxOf(step - 2));
	}

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("solving/silver/s3_2579_계단오르기/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		arr = new int[N + 1];
		dp = new int[N + 1];

		for (int i = 1; i <= N; i++) 
			arr[i] = Integer.parseInt(br.readLine());
		
		dp[0] = 0;
		dp[1] = arr[1];
		if(N >= 2)
			dp[2] = arr[1] + arr[2];

		// bottom-up
		/*
		for(int i = 3; i <= N; i++) {
			dp[i] = Math.max(dp[i - 3] + arr[i - 1], dp[i - 2]) + arr[i];
		}
		*/
		
		// top-down
		System.out.println(getMaxOf(N));
		br.close();
	}
}

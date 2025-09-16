package d123.d3_5607;

import java.util.*;
import java.io.*;

public class Solution {
	static int N, R;
	static final int PRIM = 1234567891;
	static long[] dp;

	static long pow(long a, int b) {
		if (b == 0) return 1;
		else if (b == 1) return a;
		
		if (b % 2 == 0) { 
			long tmp = pow(a, b / 2);
			return (tmp * tmp) % PRIM;
		}
		
		long tmp = pow(a, b - 1);
		return (tmp * a) % PRIM;
	}

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./solving/d123/d3_5607/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		dp = new long[1000001]; // dp[i] = i! % P
		dp[1] = 1;

		for (int i = 2; i <= 1000000; i++)
			dp[i] = (dp[i - 1] * i) % PRIM;

		final int TC = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= TC; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			R = Integer.parseInt(st.nextToken());

			
			// 분모 : (n-r)! * (r!)
			long bottom = (dp[N - R] * dp[R]) % PRIM;
			// 분모 : ((n-r)! * (r!)) ^ p-2
			long B = pow(bottom, PRIM - 2);
			
			// 결과 : n! * ((n-r)!*(r!))^p-2
			long ans = (dp[N] * B) % PRIM;
			
			sb.append("#").append(tc).append(" ").append(ans).append("\n");
		}

		System.out.println(sb.toString());
		br.close();
	}
}

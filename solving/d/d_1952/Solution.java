package d.d_1952;

import java.util.*;
import java.io.*;

public class Solution {
	static int[] costs;
	static int[] dp;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./solving/d/d_1952/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		final int TC = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= TC; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			costs = new int[4];
			for (int i = 0; i < 4; i++)
				costs[i] = Integer.parseInt(st.nextToken());

			dp = new int[13];
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= 12; i++) {
				int num = Integer.parseInt(st.nextToken());

				dp[i] = dp[i - 1] +Math.min(num * costs[0], costs[1]);

				if (i >= 3)
					dp[i] = Math.min(dp[i - 3] + costs[2], dp[i]);
			}
			System.out.println(Arrays.toString(dp));
			
			sb.append("#").append(tc).append(" ").append(Math.min(costs[3], dp[12])).append("\n");
		}

		System.out.println(sb.toString());
		br.close();
	}
}

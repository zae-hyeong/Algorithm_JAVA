package d123.d3_3282;
import java.util.*;
import java.io.*;

public class Solution {
	static int N, K;
	static int[] costs, vals, dp;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./solving/d123/d3_3282/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		final int TC = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= TC; tc++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			costs = new int[N];
			vals = new int[N];
			dp = new int[K + 1];
			
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				costs[i] = Integer.parseInt(st.nextToken());
				vals[i] = Integer.parseInt(st.nextToken());
			}
			
			for (int i = 0; i < N; i++) {
				for (int j = K; j >= costs[i]; j--) {
					dp[j] = Math.max(dp[j], dp[j - costs[i]] + vals[i]);
				}
			}
			
			sb.append("#").append(tc).append(" ").append(dp[K]).append("\n");
		}
		
		System.out.println(sb.toString());
		br.close();
	}
}

package gold.g4_1106_호텔;

import java.util.*;
import java.io.*;

public class Main {
	static int C, N;
	static int[] cost, value;
	static int[] dp;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		C = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());

		cost = new int[N];
		value = new int[N];
		dp = new int[C + 1];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			cost[i] = Integer.parseInt(st.nextToken());
			value[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.fill(dp, Integer.MAX_VALUE);
		dp[0] = 0;
		
		int target;
		for(int k = 0; k < C; k++) {
			for(int n = 0; n < N; n++) {
				target = k + value[n];
				target = target >= C ? C : target;
				
				if(dp[k] == Integer.MAX_VALUE) continue;
				
				dp[target] = Math.min(dp[target], dp[k] + cost[n]);
			}
		}
		
//		for (int k = 1; k <= C; k++) { // k명을 구하기 위한 최솟값 구하기
//			dp[k] = Integer.MAX_VALUE;
//			for (int n = 0; n < N; n++) { // n번째 도시에 홍보를 하는 경우
//				for (int a = k - value[n]; a < k; a++) {
//					a = a < 0 ? 0 : a;
//					dp[k] = Math.min(dp[k], dp[a] + cost[n]);
//				}
//			}
//		}

		System.out.println(dp[C]);
		br.close();
	}
}

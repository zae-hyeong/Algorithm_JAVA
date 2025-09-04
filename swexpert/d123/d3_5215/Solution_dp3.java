package d123.d3_5215;

import java.util.*;
import java.io.*;

public class Solution_dp3 {
	static int N, L, result;
	static int[] scores, cals;

	static int[][] dp;
	
	static int solve(int itemIdx, int currentCal) {
	    // 기저 사례 (Base Case)
	    if (itemIdx < 0) {
	        return 0;
	    }

	    // 이미 계산한 문제라면 저장된 값 반환 (Memoization)
	    if (dp[itemIdx][currentCal] != 0) {
	        return dp[itemIdx][currentCal];
	    }
	    
	    // 1. 현재 아이템을 담지 않는 경우
	    int result = solve(itemIdx - 1, currentCal);

	    // 2. 현재 아이템을 담는 경우 (담을 수 있을 때)
	    if (currentCal >= cals[itemIdx]) {
	        result = Math.max(result, solve(itemIdx - 1, currentCal - cals[itemIdx]) + scores[itemIdx]);
	    }

	    // 계산 결과를 저장하고 반환
	    return dp[itemIdx][currentCal] = result;
	}

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
			scores = new int[N]; // 점수
			cals = new int[N]; // 칼로리

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				scores[i] = Integer.parseInt(st.nextToken());
				cals[i] = Integer.parseInt(st.nextToken());
			}
			
			dp = new int[N][L + 1];


			int result = solve(N -1 , L); 
					
			for(int[] d : dp) System.out.println(Arrays.toString(d));
			sb.append("#").append(tc).append(" ").append(result).append("\n");
		}

		System.out.println(sb.toString());
		br.close();
	}
}
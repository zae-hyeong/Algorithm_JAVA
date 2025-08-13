package d45.d4_1486;

import java.io.*;
import java.util.*;

public class Solution {
	static int N, B, sum, result;
	static int[] arr;
	
	static void checkMin(int i, int sum) {
		if(sum >= B) {
			result = Math.min(result, sum);
			return;
		}
		if(i >= N) return;
		
		checkMin(i + 1, sum + arr[i]);
		checkMin(i + 1, sum);
	}

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./solving/d45/d4_1486/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		final int TC = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= TC; tc++) {
			st= new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			B = Integer.parseInt(st.nextToken());
			arr = new int[N];
			
			st= new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
				sum += arr[i];
			}
			
			result = Integer.MAX_VALUE;
			checkMin(0, 0);
			
			sb.append("#").append(tc).append(" ").append(result - B).append("\n");
		}
		
		System.out.println(sb.toString());
		br.close();
	}
}

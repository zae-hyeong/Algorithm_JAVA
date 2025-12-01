package gold.g3_11066_파일합치기;

import java.util.*;
import java.io.*;

public class Main {
	static int N;
	static int[] arr, sum;

	static int split(int start, int end) {
		if(start >= end) return arr[start];
		
		int sum1, sum2;
		int minDiff = Integer.MAX_VALUE;
		int splitPoint = -1;
		
		for(int i = start; i < end; i++) {
			sum1 = sum[i] - sum[start - 1];
			sum2 = sum[end] - sum[i];
			
			if(Math.abs(sum1 - sum2) < minDiff) {
				minDiff = Math.abs(sum1 - sum2);
				splitPoint = i;
			}
		}
		
		int cost1 = split(start, splitPoint);
		int cost2 = split(splitPoint + 1, end);
		
		System.out.println("cost1 : " + cost1 + " / cost2 : " + cost2 + " / total : " + (cost1 + cost2));
		
		return cost1 + cost2 + sum[splitPoint] - sum[start - 1] + sum[end] - sum[splitPoint];
	}
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("solving/gold/g3_11066_파일합치기/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		final int TC = Integer.parseInt(br.readLine());
		for(int tc = 0; tc < TC; tc++) {
			N = Integer.parseInt(br.readLine());
			arr = new int[N + 1];
			sum = new int[N + 1];
			
			arr[0] = sum[0] = 0;
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i = 1; i <= N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
				
				sum[i] = sum[i - 1] + arr[i];
			}
			
			System.out.println(split(1, N));
		}
		br.close();
	}

}

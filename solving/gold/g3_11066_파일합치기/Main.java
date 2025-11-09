package gold.g3_11066_파일합치기;

import java.util.*;
import java.io.*;

public class Main {
	static int N;
	static int[] arr, sum;

	static int split(int start, int end) {
		int leftSum, rightSum;
		int minDiff = Integer.MAX_VALUE, splitPoint = -1;
		
		if(start >= end) return 0;
		
		int cost = 0;
		for(int i = start; i < end; i++) {
			leftSum = sum[i] - sum[start - 1];
			rightSum = sum[end] - sum[i];
			
			if(Math.abs(leftSum - rightSum) < minDiff) {
				splitPoint = i;
				minDiff = Math.abs(leftSum - rightSum);
				cost = leftSum + rightSum;
			}
		}
		
		leftSum = split(start, splitPoint);
		rightSum = split(splitPoint + 1, end);
		
		cost = cost + leftSum + rightSum;
		
		System.out.println("------------------------------");
		System.out.println(String.format("start: %d, splitPoint : %d, end : %d", start, splitPoint, end));
		System.out.println(String.format("leftSum : %d, rightSum : %d, cost: %d", leftSum, rightSum, cost));
		
		return cost;
	}

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./solving/gold/g3_11066_파일합치기/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

//		
		final int TC = Integer.parseInt(br.readLine());
		for(int tc = 0; tc < TC; tc++) {
			N = Integer.parseInt(br.readLine());
			
			arr = new int[N+1];
			sum = new int[N+1];
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i = 1; i <= N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
				sum[i] = sum[i-1] + arr[i];
			}
			
			System.out.println(split(1, N));
		}
		
		br.close();
	}
}

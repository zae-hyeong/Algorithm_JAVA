package s3_2559;

import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/test.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		final int N = Integer.parseInt(st.nextToken());
		final int K = Integer.parseInt(st.nextToken());

		int[] arr = new int[N];

		st = new StringTokenizer(br.readLine());

		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		int sum = 0;
		for (int i = 0; i < K; i++) {
			sum += arr[i];
		}
		
		int maxSum = sum;
		
		for(int front =0, rear = K; rear < N; front++, rear++) {
			sum -= arr[front];
			sum += arr[rear];
			
			maxSum = Math.max(maxSum, sum);
		}

		System.out.println(maxSum);
		br.close();
	}

}

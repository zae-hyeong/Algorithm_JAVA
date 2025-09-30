package gold.g5_2470_두용액;

import java.util.*;
import java.io.*;

public class Main {
	static int N;
	static int[] arr;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./solving/gold/g5_2470/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());

		StringTokenizer st = new StringTokenizer(br.readLine());
		arr = new int[N];
		for (int i = 0; i < N; i++)
			arr[i] = Integer.parseInt(st.nextToken());

		br.close();
		int num1 = 0, num2 = 0, min = Integer.MAX_VALUE;

		Arrays.sort(arr);
		int front = 0, rear = N - 1, sum;
		while (front < rear) {
			sum = arr[front] + arr[rear];
			
			if (min > Math.abs(sum)) {
				min = Math.abs(sum);
				
				num1 = arr[front];
				num2 = arr[rear];
				
				if(min == 0) break;
			}
			
			if (sum < 0) front++;
			else rear--;
		}

		System.out.println(num1 + " " + num2);
	}
}

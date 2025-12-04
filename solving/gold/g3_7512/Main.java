package gold.g3_7512;

import java.io.*;
import java.util.*;

public class Main {
	static int[] prim = new int[10000];

	static void getPrims() {
		boolean[] isNotPrime = new boolean[10000000];
		
		int loop = (int) Math.sqrt(10000000);
		
		for (int i = 2; i < loop; i++) {
			if(!isNotPrime[i]) {
				for (int j = i * i; j < 10000000; j += i) {
					isNotPrime[j] = true;
				}
			}
		}
		
		int[] arr = new int[664580];
		int idx = 0;
		for (int i = 2; i < 10000000; i++) 
			if(!isNotPrime[i]) arr[idx++] = i;
		
		
		System.out.println(Arrays.toString(arr));
	}

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("solving/gold/g3_7512/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuffer sb = new StringBuffer();
		StringTokenizer st;

		getPrims();
		final int TC = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= TC; tc++) {
			int N = Integer.parseInt(br.readLine());
			int[] nums = new int[N];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) 
				nums[i] = Integer.parseInt(st.nextToken());
			
			Arrays.sort(nums);
			int min = nums[0];
			int max = nums[N - 1];
			
			int front = 0;
			int rear = 0;
			int sum = 0;
			while(true) {
				
			}
			
			int result = 0;
			
			sb.append("Scenario ").append(tc).append(":\n").append(result).append("\n\n");
		}

		System.out.println(sb.toString());
		br.close();
	}
}

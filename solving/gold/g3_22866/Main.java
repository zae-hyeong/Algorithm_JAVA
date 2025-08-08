package gold.g3_22866;

import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int[] arr;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./solving/gold/g3_22866/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(br.readLine());
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) 
			arr[i] = Integer.parseInt(st.nextToken());
		
		ArrayDeque<Integer> leftStack = new ArrayDeque<>();
		ArrayDeque<Integer> rightStack = new ArrayDeque<>();
		
		for (int i = 0; i < N; i++) {
			for (int j = i - 1; j >=0 ; j--) {
				if(arr[j] > arr[i] && arr[j] < arr[leftStack.peek()])
					leftStack.offer(j);
			}
			
			for (int j = i + 1; j < N; j++) {
				if(arr[j] > arr[i] && arr[j] < arr[rightStack.peek()])
					leftStack.offer(j);
			}
			
			sb.append(leftStack.size() + rightStack.size());
//			if(leftStack.size() + rightStack.size() > 0)
//				sb.append();
		}
		
		System.out.println(sb.toString());
		br.close();
	}

}

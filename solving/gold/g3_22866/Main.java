package gold.g3_22866;

import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int[] arr, heights, nears;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./solving/gold/g3_22866/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		heights = new int[N];
		nears = new int[N];
		Arrays.fill(nears, -1000000);

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++)
			arr[i] = Integer.parseInt(st.nextToken());
		
		// 왼쪽 빌딩들 확인
		Stack<Integer> stack = new Stack<>();
		for (int i = 0; i < N; i++) {
			while (!stack.isEmpty() && arr[stack.peek()] <= arr[i])
				stack.pop();

			heights[i] += stack.size();
			if (!stack.isEmpty()) nears[i] = stack.peek();

			stack.push(i);
		}

		// 오른쪽 빌딩들 확인
		stack = new Stack<>();
		for (int i = N - 1; i >= 0; i--) {
			while (!stack.isEmpty() && arr[stack.peek()] <= arr[i])
				stack.pop();

			heights[i] += stack.size();
			if (!stack.isEmpty() && stack.peek() - i < i - nears[i]) nears[i] = stack.peek();

			stack.push(i);
		}

		for (int i = 0; i < N; i++) {
			sb.append(heights[i]);
			if (heights[i] != 0) sb.append(" ").append(nears[i] + 1);
			sb.append("\n");
		}

		System.out.println(sb.toString());
		br.close();
	}

}
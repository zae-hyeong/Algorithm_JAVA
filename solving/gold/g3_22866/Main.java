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
		arr = new int[N];

		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++)
			arr[i] = Integer.parseInt(st.nextToken());

		ArrayDeque<Integer> leftStack, rightStack;

		for (int i = 0; i < N; i++) {
			leftStack = new ArrayDeque<>();
			rightStack = new ArrayDeque<>();
			for (int j = i - 1; j >= 0; j--) {
				if (arr[j] > arr[i]) {
					if (leftStack.size() == 0 || arr[j] < arr[leftStack.getLast()])
						leftStack.offer(j);
				}
			}

			for (int j = i + 1; j < N; j++) {
				if (arr[j] > arr[i]) {
					if (rightStack.size() == 0 || arr[j] < arr[rightStack.peek()])
						leftStack.offer(j);
				}
			}

			sb.append(leftStack.size() + rightStack.size());
			
			System.out.println(leftStack.toString());
			System.out.println(rightStack.toString());
			
			if (leftStack.size() + rightStack.size() > 0) {
				int closest = -1;
				if (rightStack.size() > 0)
					closest = rightStack.getFirst();
				if (leftStack.size() > 0 && closest != -1 && Math.abs(rightStack.getFirst() - i) >= Math.abs(leftStack.getFirst() - i))
					closest = leftStack.getFirst();
				sb.append(" ").append(closest);
			}
			sb.append("\n");
		}

		System.out.println(sb.toString());
		br.close();
	}

}

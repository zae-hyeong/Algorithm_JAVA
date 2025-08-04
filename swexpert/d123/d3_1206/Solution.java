package d123.d3_1206;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution {
	static int N;
	static int[] arr;
	static PriorityQueue<Integer> leftpq;
	static PriorityQueue<Integer> rightpq;

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("./swexpert/d3_1206/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;

		for (int tc = 1; tc <= 10; tc++) {
			int result = 0;
			N = Integer.parseInt(br.readLine());
			arr = new int[N];

			leftpq = new PriorityQueue<>((o1, o2) -> Integer.compare(o2, o1));
			rightpq = new PriorityQueue<>((o1, o2) -> Integer.compare(o2, o1));

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}

			for (int i = 0; i < 2; i++) {
				leftpq.add(0);
				rightpq.add(0);
			}

			for (int i = 3; i < Math.min(N - 2, 5); i++) {
				rightpq.remove(0);
				rightpq.add(arr[i]);
			}

			int leftDiff, rightDiff;

			for (int i = 2; i < N - 2; i++) {
				leftDiff = 0;
				rightDiff = 0;

				if (leftpq.peek() < arr[i])
					leftDiff = arr[i] - leftpq.peek();
				if (rightpq.peek() < arr[i])
					rightDiff = arr[i] - rightpq.peek();

				if (leftDiff > 0 && rightDiff > 0)
					result += Math.min(leftDiff, rightDiff);

				leftpq.remove(arr[i - 2]);
				leftpq.add(arr[i]);
				rightpq.remove(arr[i + 1]);
				if (i < N - 3)
					rightpq.add(arr[i + 3]);
			}

			sb.append("#").append(tc).append(" ").append(result).append("\n");
		}
		System.out.println(sb.toString());
		br.close();
	}
}

package d3_9229;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	static int N, M;
	static int[] arr;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("./solving/d3_9229/input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuffer sb = new StringBuffer();

		final int TC = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= TC; tc++) {
			int result = -1;
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			st = new StringTokenizer(br.readLine());

			arr = new int[N];
			for (int i = 0; i < N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}

			Arrays.sort(arr);

			int front = 0;
			int end = N - 1;

			while (front < end) {
				if (arr[front] + arr[end] > M)
					end--;
				else {
					result = Math.max(result, arr[front] + arr[end]);

					front++;
				}
			}

			sb.append("#").append(tc).append(" ").append(result).append("\n");
		}
		System.out.println(sb.toString());
		br.close();
	}
}
